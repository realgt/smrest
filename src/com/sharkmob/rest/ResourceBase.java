package com.sharkmob.rest;

import java.util.Map;

public abstract class ResourceBase implements IResource
{
	protected Map<String, String> params;
	
	public void setParams(Map<String, String> params)
	{
		this.params = params;
	}
	
	public Map<String, String> getParams()
	{
		return params;
	}
	

}
