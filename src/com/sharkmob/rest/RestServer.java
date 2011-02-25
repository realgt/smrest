package com.sharkmob.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestServer implements Filter
{
	public static enum RequestMethod
	{
		GET, POST, PUT, DELETE;
	}

	private RequestMethod requestMethod;
	private HttpServletRequest request;
	private IRoutes routes;

	@SuppressWarnings("unchecked")
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
				RestRouter router = RestRouter.getInstance();
				router.params = request.getParameterMap();
				IResource resource = router.getResource(path);
				if (resource != null)
				{
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
				else
				{
					//this is not a valid resource or we had trouble instantiating it!
					((HttpServletResponse) res).sendError(HttpServletResponse.SC_NOT_FOUND);
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
	public void destroy()
	{
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(FilterConfig config) throws ServletException
	{
		String routesParameter = config.getInitParameter("Routes");
		Class routesClazz = null;
		try
		{
			routesClazz = Class.forName(routesParameter);
			routes = (IRoutes) routesClazz.newInstance();
			routes.init();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
