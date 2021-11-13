package com.example.demo.exception;

public class BadRequestException extends RuntimeException{

	/**
	 * Method to Route the process to corresponding handler to get the complete
	 * response
	 *
	 * @param context APIContext with the request details.
	 * @param view    contains the request body.
	 * @return ResponseEntity with respective status and information.
	 * @throws APIException handles Exception from Handler class.

	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
