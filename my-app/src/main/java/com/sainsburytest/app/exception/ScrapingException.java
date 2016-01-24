package com.sainsburytest.app.exception;

/**
 * Custom exception to track scraping issues. 
 * 
 * @author Alessandro Candolini
 */
public class ScrapingException extends Exception {
	
	private static final long serialVersionUID = 7366602161612476161L;
   
	public ScrapingException() {
	}
	
	public ScrapingException(String message) {
        super(message);
    }
	
}