package com.teamb.mth.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CheckLoginedSessionFilter implements Filter
{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException  { }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		/*String uri = req.getRequestURI();*/
		HttpSession session = req.getSession(false);
		
		if(session == null || session.getAttribute("sessionLoginedData")==null)
		{
			req.setAttribute("loginedFilter", false);
			RequestDispatcher view = req.getRequestDispatcher("/loginForm");
			view.forward(req, res);
		}
		else
		{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() { }
	
}
