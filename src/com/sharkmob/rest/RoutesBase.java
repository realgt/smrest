package com.sharkmob.rest;

public abstract class RoutesBase implements IRoutes
{
	protected 	RestRouter router = RestRouter.getInstance();


	public RoutesBase()
	{
	}
}
