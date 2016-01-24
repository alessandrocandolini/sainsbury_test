package com.sainsburytest.app.scraper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sainsburytest.app.exception.ScrapingException;
import com.sainsburytest.app.helper.Helper;
import com.sainsburytest.app.pojo.HtmlWebPage;
import com.sainsburytest.app.pojo.ItemPojo;


/**
 * Main class to fetch the list of items from a provided HtmlWebPage object
 * 
 * @author acando
 */
public class ListItemsScraper {

	/**
	 * HtmlWebPage object to grab the information.
	 */
	private HtmlWebPage webPage;
	
	/**
	 * List of items
	 */
	private List<ItemPojo> listOfItems;

	/**
	 * Constructor.
	 * 
	 * The list of items is initialize to null by default. 
	 * @param webPage Set the HtmlWebPage object containing the information which the list of items will be populated
	 */
	public ListItemsScraper(HtmlWebPage webPage) {
		this.webPage = webPage;
		this.listOfItems = null;
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
		if (webPage != null && webPage.getBody() != null && !webPage.getBody().isEmpty() ) {
			Document document = null;

			document = Jsoup.parse(webPage.getBody());
			Elements elements = document.select("div.product"); // TODO make global
			final int numberOfItems = elements.size();

			if ( numberOfItems > 0 ) {

				listOfItems = new ArrayList<ItemPojo>();

				for( Element element : elements) {
					// extract item url and title
					Element link = element.select("a").first();
					String url = link.attr("href");
					String title = link.text();

					// extract string price and strip currency
					String unitPriceWithCurrency = element.select("p.pricePerUnit").text();
					BigDecimal unitPrice = Helper.extractAmount(unitPriceWithCurrency);

					// Create pojo with extracted information
					ItemPojo product = new ItemPojo();
					product.setTitle(title);
					product.setUrl(url);
					product.setUnit_price(unitPrice);
					listOfItems.add(product);
				}
			}

		} else {
			throw new ScrapingException();
		}
	}

	public List<ItemPojo> getList() {
		return listOfItems;
	}

	public void setList(List<ItemPojo> list) {
		this.listOfItems = list;
	}

}
