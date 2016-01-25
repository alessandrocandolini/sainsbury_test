package com.sainsburytest.app.network;

import junit.framework.TestCase;

import com.sainsburytest.app.exception.NetworkException;
import com.sainsburytest.app.pojo.WebPagePojo;
import com.sainsburytest.app.pojo.WebPageRequestPojo;

public class NetworkRequestEngineTest extends TestCase {
	
	private String url;
	
	@Override
	protected void setUp() {
		url = "https://www.wikipedia.org/";
	}
	
	public void testConsumeRequest() {
		
		WebPageRequestPojo networkRequest = new WebPageRequestPojo();
		networkRequest.setUrl(url);
		WebPagePojo webPage = null;
		try {
			webPage = NetworkRequestEngine.retrieveWebPage(networkRequest);
			
		} catch (NetworkException e) {
		}
		assertNotNull(webPage);
		assertNotNull(webPage.getBody());
		assertEquals(false, webPage.getBody().isEmpty());
	}

}
