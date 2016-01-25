package com.sainsburytest.app.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sainsburytest.app.exception.ScrapingException;
import com.sainsburytest.app.pojo.WebPagePojo;
/**
 * Main class to fetch the webpage of a single product.
 * 
 * @author acando
 */
public class PDPScraper {

	/**
	 * HtmlWebPage object to grab the information.
	 */
	private WebPagePojo webPage;
	
	/**
	 * Description of the item.
	 */
	private String itemDescription;
	
	/**
	 * Size in bytes of the item webpage
	 */
	private Integer pageSizeBytes;

	public PDPScraper(WebPagePojo webPage) {
		this.webPage = webPage;
		this.pageSizeBytes = null;
		this.itemDescription = null;
	}

	/**
	 * Core method for scraping the data from the provided webpage.
	 * 
	 * <p>
	 * This method relies on jsoup
	 * 
	 * @throws ScrapingException Thrown whenever the webpage has no content. 
	 */
	public void scrape() throws ScrapingException {
		// hide the details: the user is not aware we are using jsoup here
		Document document = null;
		if (webPage != null && webPage.getBody() != null && !webPage.getBody() .isEmpty() ) {
			document = Jsoup.parse(webPage.getBody());
			this.pageSizeBytes = webPage.getPageSizeInBytes();
			Elements elements = document.select("div.productText");
			Element descriptionElement = elements.first();
			itemDescription = descriptionElement.text();
		} else {
			throw new ScrapingException();
		}
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Integer getPageSizeBytes() {
		return pageSizeBytes;
	}

	public void setPageSizeBytes(Integer pageSizeBytes) {
		this.pageSizeBytes = pageSizeBytes;
	}


}
