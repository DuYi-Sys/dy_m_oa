/**
 * 
 */
package com.duyi.security.filter;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.duyi.admin.domain.AdminOperationInfo;
import com.duyi.admin.service.IAdminOperationService;
import com.duyi.admin.service.IPermissionService;
import com.duyi.commons.log.Trace;
import com.duyi.security.PasswordEncoder;
import com.duyi.security.SecurityContextHolder;
import com.duyi.security.SecurityContextImpl;
import com.duyi.security.jwt.IJwtGenerator;
import com.duyi.security.model.UserDetails;
import com.duyi.security.service.IUserDetailsService;
import com.google.common.base.Strings;


/**
 * @author wangyan
 *
 */
@Component("LoginFilter")
public class LoginFilter {

	private String secPathPrefix="/api/s/";
	private String loginPath="/api/adminlogin";
	private String unauthorizedPath="/api/adminlogin/unauthorized";
	private String forbiddenPath="/api/adminlogin/forbidden";

	private static Trace log=Trace.register(LoginFilter.class);
	@Resource(name="adminUserDetailsService")
	private IUserDetailsService userService;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private IJwtGenerator jwtGenerator;
	@Autowired
	private IAdminOperationService operationService;
	@Autowired
	private IPermissionService permissionService;

	public boolean doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest=(HttpServletRequest)request;
		HttpServletResponse servletResponse=(HttpServletResponse)response;
		
		String path=servletRequest.getRequestURI();

		//登陆验证
		if(path.equals(loginPath)) {
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			log.info("username:"+username);
			if(Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
				servletResponse.sendRedirect(unauthorizedPath);
				return false;
			}
			UserDetails user=userService.loadUserByUsername(username);
			if(user==null) {
				servletResponse.sendRedirect(unauthorizedPath);
				return false;
			}else {
				if(!encoder.matches(password, user.getPassword())) {
//					servletResponse.sendRedirect(unauthorizedPath);
					request.getRequestDispatcher(unauthorizedPath).forward(request, response);

					return false;
				}else {
					String token=jwtGenerator.generateToken(user);
					servletResponse.setHeader("token", token);
					SecurityContextImpl context=new SecurityContextImpl();
					context.setPrincipal(user);
					SecurityContextHolder.setContext(context);
				}
			}
		}else if (path.startsWith(secPathPrefix)) {
			//访问安全路径
			String header=servletRequest.getHeader("Authorization");
			UserDetails jwtUser=null;
			if(header!=null && header.startsWith("Bearer ")) {
				String authToken=header.substring(7);
				jwtUser=jwtGenerator.parseToken(authToken);
			}
		
		
			if (jwtUser == null) {
				request.getRequestDispatcher(unauthorizedPath).forward(request, response);
				return false;
			}
			
			//是否有权限
			if (!permissionService.hasPermission(path, servletRequest.getMethod(), jwtUser.getRoles().toArray(new String[jwtUser.getRoles().size()]))) {
//				servletResponse.sendRedirect(forbiddenPath);
				request.getRequestDispatcher(forbiddenPath).forward(request, response);

				return false;

			} 
			String token = jwtGenerator.generateToken(jwtUser);
			servletResponse.setHeader("token", token);
			SecurityContextImpl context=new SecurityContextImpl();
			context.setPrincipal(jwtUser);
			SecurityContextHolder.setContext(context);
		}
		log.info("path:"+servletRequest.getRequestURI());
		return true;
//		chain.doFilter(request, response);
	}
	/**
	 * @param path
	 * @param permission
	 * @return
	 */
	private boolean hasPermission(String path,String method) {
		List<AdminOperationInfo> operationInfos = operationService.findByPath(path);
		for (AdminOperationInfo operationInfo : operationInfos) {
			log.info("operation:" + operationInfo);
			if (operationInfo.getPath().equals(path)) {
				if ("*".equals(operationInfo.getOperation())) {
					return true;
				}else if("GET".equalsIgnoreCase(method) && "READ".equalsIgnoreCase(operationInfo.getOperation())) {
					return true;
				}else if(
						("POST".equalsIgnoreCase(method)||"PUT".equalsIgnoreCase(method)|| "PATCH".equalsIgnoreCase(method)) 
						&& "WRITE".equalsIgnoreCase(operationInfo.getOperation())) {
					return true;
				}
			}
		}
		return false;
	}


}
