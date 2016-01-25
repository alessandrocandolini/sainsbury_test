package com.sainsburytest.app.scraper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.sainsburytest.app.exception.ScrapingException;
import com.sainsburytest.app.pojo.WebPagePojo;

import junit.framework.TestCase;

public class ListItemsScraperTest extends TestCase {
	
	private String body;
	private int expectedSize;
	
	@Override
	protected void setUp() {
		expectedSize = 7;
		body = null;
		try {
			body = new String(Files.readAllBytes(Paths.get(getClass().getResource("/products.html").toURI())));
		} catch (IOException e) {
		} catch (URISyntaxException e) {
		}
	}
	
	 public void testScrape()
	    {
	        WebPagePojo webPage = new WebPagePojo();
	        webPage.setBody(body);
	        
	        PLPScraper scraper = new PLPScraper(webPage);
	        try {
				scraper.scrape();
			} catch (ScrapingException e) {
			}
	        
	        final int actualSize = scraper.getList().size();
	        assertEquals(expectedSize, actualSize);
	        
	    }
	 

}
