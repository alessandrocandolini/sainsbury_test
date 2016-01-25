package com.sainsburytest.app.scraper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.sainsburytest.app.exception.ScrapingException;
import com.sainsburytest.app.pojo.WebPagePojo;

import junit.framework.TestCase;

public class PDPScraperTest extends TestCase {

	private String body;
	private String expectedDescription;

	@Override
	protected void setUp() {
		expectedDescription = "Apricots";
		body = null;
		try {
			body = new String(Files.readAllBytes(Paths.get(getClass().getResource("/apricots.html").toURI())));
		} catch (IOException e) {
		} catch (URISyntaxException e) {
		}
	}

	public void testScrape()
	{
		WebPagePojo webPage = new WebPagePojo();
		webPage.setBody(body);

		PDPScraper scraper = new PDPScraper(webPage);
		try {
			scraper.scrape();
		} catch (ScrapingException e) {
		}

		final String actualDescription = scraper.getItemDescription();
		System.out.println(actualDescription);
		System.out.println(expectedDescription);
		assertEquals(expectedDescription, actualDescription);

	}
}
