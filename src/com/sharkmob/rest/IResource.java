package com.sharkmob.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public abstract interface IResource
{
	/***
	 * Handles the basic Get (aka Select) operation
	 * 
	 * @return the String (json/xml?) representation of the object requested
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response);

	/***
	 * Handles the Put (aka INSERT) operation
	 */
	public void doPut(HttpServletRequest request, HttpServletResponse response);

	/***
	 * Handles the Post (aka UPDATE) operation
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response);
	

	/***
	 * Handles the Delete (aka DELETE?) operation
	 */
	public void doDelete(HttpServletRequest request, HttpServletResponse response);
	
	public void setParams(Map<String, String> params);
	
	public Map<String, String> getParams();
	
}
