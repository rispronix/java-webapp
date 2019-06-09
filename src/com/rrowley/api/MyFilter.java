package com.rrowley.api;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 */
@WebFilter("/*")
public class MyFilter implements Filter {
	private Logger logger = Logger.getLogger(getClass().getName());

	public MyFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest r = (HttpServletRequest) request;
		logger.info("Request from " + r.getRemoteAddr() + 
				" to " + r.getMethod() + " " + 
				r.getRequestURI() + " " + 
				r.getQueryString());
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
