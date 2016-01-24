package com.sainsburytest.app.network;

import junit.framework.TestCase;

import com.sainsburytest.app.exception.NetworkException;
import com.sainsburytest.app.pojo.HtmlWebPage;
import com.sainsburytest.app.pojo.NetworkRequestPojo;

public class NetworkRequestEngineTest extends TestCase {
	
	private String url;
	
	@Override
	protected void setUp() {
		url = "https://www.wikipedia.org/";
	}
	
	public void testConsumeRequest() {
		
		NetworkRequestPojo networkRequest = new NetworkRequestPojo();
		networkRequest.setUrl(url);
		HtmlWebPage webPage = null;
		try {
			webPage = NetworkRequestEngine.consumeRequest(networkRequest);
			
		} catch (NetworkException e) {
		}
		assertNotNull(webPage);
		assertNotNull(webPage.getBody());
		assertEquals(false, webPage.getBody().isEmpty());
	}

}
