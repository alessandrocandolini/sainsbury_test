package com.sainsburytest.app.network;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.sainsburytest.app.exception.NetworkException;
import com.sainsburytest.app.pojo.NetworkResponsePojo;
import com.sainsburytest.app.pojo.NetworkRequestPojo;

public class NetworkRequestEngine {
	
	/**
	 * Default user agent string. 
	 */
	private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36 [scraping]";
	
	/**
	 * Default connection timeout in milliseconds
	 */
	private static final int DEFAULT_TIMEOUT_IN_MILLISECONDS = 30000 ;

	/**
	 * Stand-alone static method to perform single network requests.
	 * 
	 * <p>
	 * The information about the network request (for example, the URL) is wrapped inside the 
	 * NetworkRequestPojo input object. The response is wrapped in a HtmlWebPage object.
	 * The actual implementation relies upon Jsoup, but it is not difficult to 
	 * change the method to use other network libraries, apache http or event to create a hierarchy of engines using other ways to perform the network connection.
	 * Timeout, user agent and behavior under redirect are hardcoded to default values, but the implementation
	 * can be easily relaxed to account for a more configurable behavior. 
	 * 
	 * <p>
	 * Notice: this method is completely decoupled from the parsing, allowing one to use different
	 * sources with the same scraping tools
	 * 
	 * @param networkRequest Input object storing the details of the network request
	 * @return HtmlWebPage object filled with the response of the network request. 
	 * @throws NetworkException Thrown whenever a network connection error or a https status code different from 200 is detected
	 */
	public static NetworkResponsePojo consumeRequest(NetworkRequestPojo networkRequest) throws NetworkException {

		NetworkResponsePojo htmlWebPage = null;

		if ( networkRequest != null && networkRequest.getUrl() != null && !networkRequest.getUrl().isEmpty()) {
			try {
				Response response = Jsoup
						.connect(networkRequest.getUrl())
						.userAgent(DEFAULT_USER_AGENT)
						.followRedirects(true) 
						.timeout(DEFAULT_TIMEOUT_IN_MILLISECONDS) // in milliseconds
						.execute();

				final int statusCode = response.statusCode();

				if (statusCode == 200 ) {
					htmlWebPage = new NetworkResponsePojo();
					htmlWebPage.setUrl(networkRequest.getUrl());
					htmlWebPage.setBody(response.body());
					htmlWebPage.setPageSizeInBytes(response.bodyAsBytes().length); //  set the size of the body
				} else {
					throw new NetworkException();
				}

			} catch (IOException e) {
				throw new NetworkException();
			}

		} else {
			throw new NetworkException();
		}
		return htmlWebPage;
	}

}
