package com.sharkmob.rest;


public abstract interface IResource
{
	/***
	 * Handles the basic Get (aka Select) operation
	 * 
	 * @return the String (json/xml?) representation of the object requested
	 */
	public String doGet();

	/***
	 * Handles the Put (aka INSERT) operation
	 */
	public void doPut();

	/***
	 * Handles the Post (aka UPDATE) operation
	 */
	public void doPost();
	

	/***
	 * Handles the Delete (aka DELETE?) operation
	 */
	public void doDelete();
}
