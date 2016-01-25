package com.sainsburytest.app.pojo;

/**
 * 
 * Class encapsulating information to perform a network request.
 * 
 * <p>
 * Here, it is only a wrapper for the URL of the request.
 * Additional information can be integrated in this class, for example
 * methods (get, post, etc), timeout,  parameters, etc
 * 
 * @author acando
 *
 */
public class WebPageRequestPojo {
	
	/**
	 * Url of the request
	 */
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
