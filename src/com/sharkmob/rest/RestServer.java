package com.sharkmob.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RestServer implements Filter
{
	public static enum RequestMethod
	{
		GET, POST, PUT, DELETE;
	}

	private RequestMethod requestMethod;
	private HttpServletRequest request;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException
	{
		request = (HttpServletRequest) req;
		
		requestMethod = RequestMethod.valueOf(request.getMethod());
		
		String path = request.getRequestURI();

		if (path != null && !path.equals(""))
		{
			try
			{
				IResource resource = RestRouter.getResource(path);
				switch (requestMethod)
				{
					case GET:
						res.getWriter().append(resource.doGet()); 
						break;
					case POST:
						resource.doPost();
						break;
					case PUT:
						resource.doPut();
						break;
					case DELETE:
						resource.doDelete();
						break;
				}
			}
			catch (Exception ex)
			{
				chain.doFilter(req, res);
			}
		}

		else
		{
			// move on to the next request
			chain.doFilter(req, res);
		}

	}


	@Override
	public void destroy()	{}

	@Override
	public void init(FilterConfig config) throws ServletException {}

}
