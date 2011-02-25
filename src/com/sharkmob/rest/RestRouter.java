package com.sharkmob.rest;

import java.util.HashMap;
import java.util.Map;

public class RestRouter
{

	protected static HashMap<String, Class< ? extends IResource>> routingTable;
	protected Map<String, String> params;

	private static RestRouter instance = null;

	protected RestRouter()
	{
		initRoutingTable();
	}

	public static RestRouter getInstance()
	{
		if (instance == null)
		{
			instance = new RestRouter();
		}
		return instance;
	}


	public void addRoute(String url, Class< ? extends IResource> clazz)
	{
		if (routingTable == null)
		{
			initRoutingTable();
		}

		if (routingTable != null)
		{
			routingTable.put(url, clazz);
		}
	}

	private void initRoutingTable()
	{
		routingTable = new HashMap<String, Class< ? extends IResource>>();
	}

	public IResource getResource(String path)
	{
		Class<? extends IResource> resource = null;
		try {
		    Class<?> clazz = routingTable.get(path);
		    if (clazz != null)
		    {
		    	resource = clazz.asSubclass(IResource.class);
			    return resource.newInstance();	
		    }		    
		} catch(Exception e) {
		    e.printStackTrace();
		}
		return null;
	}
}
