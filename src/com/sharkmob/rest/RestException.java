package com.sharkmob.rest;

import javax.servlet.http.HttpServletResponse;

public class RestException extends Throwable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int statusCode;

	/***
	 * RestException uses {@link HttpServletResponse} status codes
	 * 
	 * @param statusCode
	 */
	public RestException(int statusCode)
	{
		this.statusCode = statusCode;
	}
}
