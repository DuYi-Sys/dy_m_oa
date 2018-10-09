/**
 * 
 */
package com.duyi.security.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import com.duyi.commons.log.Trace;
import com.duyi.security.PasswordEncoder;
import com.duyi.security.SecurityContextHolder;
import com.duyi.security.SecurityContextImpl;
import com.duyi.security.jwt.IJwtGenerator;
import com.duyi.security.model.UserDetails;
import com.duyi.security.service.IUserDetailsService;
import com.google.common.base.Strings;
import com.mysql.jdbc.StringUtils;


/**
 * @author wangyan
 *
 */
@Component("StudentLoginFilter")
public class StudentLoginFilter  {

	private String secPathPrefix="/c/s/";
	private String loginPath="/c/login";
	private String unauthorizedPath="/c/login/unauthorized";
	private String usernameErrorPath="/c/login/usererr";

	private String forbiddenPath="/c/login/forbidden";

	private static Trace log=Trace.register(StudentLoginFilter.class);
	@Resource(name="studentUserDetailsService")
	private IUserDetailsService userService;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private IJwtGenerator jwtGenerator;


	public boolean doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest=(HttpServletRequest)request;
		HttpServletResponse servletResponse=(HttpServletResponse)response;
		
		String path=servletRequest.getRequestURI().replace(servletRequest.getContextPath(), "");
		System.out.println("path:"+path+"=============");
		//登陆验证
		if(path.equals(loginPath)) {
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			if(Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
				servletResponse.sendRedirect(usernameErrorPath);
				return false;
			}
			log.info("username:"+username);
			UserDetails user=userService.loadUserByUsername(username);
			if(user==null) {
				servletResponse.sendRedirect(usernameErrorPath);
				return false;
			}else {
				if(!encoder.matches(password, user.getPassword())) {
//					servletResponse.sendRedirect(unauthorizedPath);
					request.getRequestDispatcher(usernameErrorPath).forward(request, response);

					return false ;
				}else {
					String token=jwtGenerator.generateToken(user);
					Cookie cookie=new Cookie("token", token);
					cookie.setMaxAge(60*60*3);
					servletResponse.addCookie(cookie);
//					servletResponse.setHeader("token", token);
					SecurityContextImpl context=new SecurityContextImpl();
					context.setPrincipal(user);
					SecurityContextHolder.setContext(context);
				}
			}
		}else if (path.startsWith(secPathPrefix)) {
			//访问安全路径
			String header=servletRequest.getHeader("Authorization");
			Cookie[] cookies=servletRequest.getCookies();
			if(cookies==null) {
				request.getRequestDispatcher(unauthorizedPath).forward(request, response);
				return false;
			}
			String authToken=null;
			for(Cookie cookie : cookies) {
				if("token".equals(cookie.getName())) {
					authToken=cookie.getValue();
				}
			}
			if(Strings.isNullOrEmpty(authToken)) {
				request.getRequestDispatcher(unauthorizedPath).forward(request, response);
				return false;
			}
			UserDetails jwtUser=null;

			jwtUser=jwtGenerator.parseToken(authToken);
		
		
			if (jwtUser == null) {
				request.getRequestDispatcher(unauthorizedPath).forward(request, response);
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
	}


}
