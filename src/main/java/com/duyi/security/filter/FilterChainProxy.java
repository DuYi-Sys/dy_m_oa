/**
 * 
 */
package com.duyi.security.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.duyi.commons.log.Trace;

/**
 * @author wangyan
 *
 */
@Component("filterChainProxy")
public class FilterChainProxy extends GenericFilterBean {

	private static Trace log=Trace.register(FilterChainProxy.class);
	@Resource(name="LoginFilter")
	private Filter loginFilter;
	@Resource(name="StudentLoginFilter")
	private StudentLoginFilter studentLoginFilter;
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		loginFilter.doFilter(request, response, chain);
		studentLoginFilter.doFilter(request, response, chain);
		HttpServletResponse httpServletResponse=(HttpServletResponse)response;
		httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.addHeader("Access-Control-Request-Method", "*");
		httpServletResponse.addHeader("Access-Control-Allow-Headers", "*");
		httpServletResponse.addHeader("Access-Control-Allow-Credentials","true");

//		corsFilter.doFilter(request, response, chain);
		chain.doFilter(request, response);
	}

}
