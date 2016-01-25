package com.sainsburytest.app.pojo;

/**
 * Pojo class providing a level of abstraction for the response of a network request
 * 
 * <p>
 * Here we assume that the response is a webpage and we just store the body, url and size of that page
 * @author acando
 */
public class WebPagePojo {
	
	/**
	 * String with the body of the webpage
	 */
	private String body;
	
	/**
	 * Url of the webpage
	 */
	private String url;
	
	/**
	 * Size in bytes of the webpage
	 */
	private Integer pageSizeInBytes;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPageSizeInBytes() {
		return pageSizeInBytes;
	}

	public void setPageSizeInBytes(Integer pageSizeInBytes) {
		this.pageSizeInBytes = pageSizeInBytes;
	}

}
