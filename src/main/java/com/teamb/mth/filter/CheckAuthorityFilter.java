package com.teamb.mth.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.teamb.mth.data.SessionLoginedData;

public class CheckAuthorityFilter implements Filter 
{
    public void destroy() { }

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		SessionLoginedData sessionLoginedData = (SessionLoginedData) req.getSession().getAttribute("sessionLoginedData");
		String member_code = sessionLoginedData.getMember_code();
		String uri = req.getRequestURI();
		String firsUrl = req.getRequestURL().toString().replaceAll(req.getServletPath(), "");
		
		//uri에서 seller, admin 구분하여 권한 없으면 메인페이지로 리다이렉트
		if(uri.matches(".*/seller/.*")) 
		{
			if(!member_code.equals("SELLER"))  {  res.sendRedirect(firsUrl+"/mainPage"); }
			else { chain.doFilter(request, response); }
		}
		else if(uri.matches(".*/admin/.*"))
		{
			if(!member_code.equals("ADMIN"))  { res.sendRedirect(firsUrl+"/mainPage"); }
			else { chain.doFilter(request, response); }
		}
		else { chain.doFilter(request, response); }
	}

	public void init(FilterConfig fConfig) throws ServletException { }
}
