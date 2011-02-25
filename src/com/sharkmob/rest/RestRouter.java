package com.sharkmob.rest;

import java.util.HashMap;

public final class RestRouter
{

	protected static HashMap<String, IResource> routingTable;
	protected HashMap<String, String> params;
	
	public RestRouter()
	{
		initRoutingTable();
	}
	
	public void addRoute(String url, IResource clazz)
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
	
	private void  initRoutingTable()
	{
		routingTable = new HashMap<String, IResource>();	
	}
	
	public static IResource getResource(String path)
	{
		return (IResource) routingTable.get(path);
	}
}
