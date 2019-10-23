package com.webservices.soap.soapWebService.soap;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CLIENT) // this annotation is used to tell that this exception is a fault to be send to the user when a bad request happen
public class CourseNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseNotFoundException(String message) {
		super(message);		
	}
	
}
