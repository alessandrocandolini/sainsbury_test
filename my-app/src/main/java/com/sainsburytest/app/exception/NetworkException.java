package com.sainsburytest.app.exception;

/**
 * Custom exception to track network connectivity issues. 
 * 
 * @author Alessandro Candolini
 */
public class NetworkException extends Exception {
	
	private static final long serialVersionUID = 7366692161612476161L;
   
	public NetworkException() {
	}
	
	public NetworkException(String message) {
        super(message);
    }
	
}
