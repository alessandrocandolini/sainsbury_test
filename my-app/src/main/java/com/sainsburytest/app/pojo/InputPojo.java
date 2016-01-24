package com.sainsburytest.app.pojo;

/**
 * Class providing an abstraction for the API input.
 * 
 * In this simple example, it only wraps the URL
 * 
 * @author acando
 */
public class InputPojo {
	
	/**
	 * Url of the webpage provided by the user
	 */
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
