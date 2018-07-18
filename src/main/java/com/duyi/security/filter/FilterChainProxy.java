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
	private Filter f1;
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("filterChainProxy===");
		f1.doFilter(request, response, chain);
//		chain.doFilter(request, response);
	}

}