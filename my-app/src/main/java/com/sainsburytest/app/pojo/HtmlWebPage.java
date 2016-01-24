package com.sainsburytest.app.pojo;

/**
 * Pojo class providing a level of abstraction for a webpage
 * 
 * @author acando
 *
 */
public class HtmlWebPage {
	
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
