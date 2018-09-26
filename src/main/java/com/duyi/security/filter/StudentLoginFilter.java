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
public class StudentLoginFilter extends GenericFilterBean {

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

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
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
				return ;
			}
			log.info("username:"+username);
			UserDetails user=userService.loadUserByUsername(username);
			if(user==null) {
				servletResponse.sendRedirect(usernameErrorPath);
				return ;
			}else {
				if(!encoder.matches(password, user.getPassword())) {
//					servletResponse.sendRedirect(unauthorizedPath);
					request.getRequestDispatcher(usernameErrorPath).forward(request, response);

					return ;
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
				return ;
			}
			String authToken=null;
			for(Cookie cookie : cookies) {
				if("token".equals(cookie.getName())) {
					authToken=cookie.getValue();
				}
			}
			if(Strings.isNullOrEmpty(authToken)) {
				request.getRequestDispatcher(unauthorizedPath).forward(request, response);
				return ;
			}
			UserDetails jwtUser=null;

			jwtUser=jwtGenerator.parseToken(authToken);
		
		
			if (jwtUser == null) {
				request.getRequestDispatcher(unauthorizedPath).forward(request, response);
				return;
			}
			
			String token = jwtGenerator.generateToken(jwtUser);
			servletResponse.setHeader("token", token);
			SecurityContextImpl context=new SecurityContextImpl();
			context.setPrincipal(jwtUser);
			SecurityContextHolder.setContext(context);
		}
		log.info("path:"+servletRequest.getRequestURI());

//		chain.doFilter(request, response);
	}


}
