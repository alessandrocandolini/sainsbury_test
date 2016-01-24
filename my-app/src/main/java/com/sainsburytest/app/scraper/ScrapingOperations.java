package com.sainsburytest.app.scraper;

import java.util.ArrayList;
import java.util.List;

import com.sainsburytest.app.exception.NetworkException;
import com.sainsburytest.app.exception.ScrapingException;
import com.sainsburytest.app.helper.Helper;
import com.sainsburytest.app.network.NetworkRequestEngine;
import com.sainsburytest.app.pojo.HtmlWebPage;
import com.sainsburytest.app.pojo.InputPojo;
import com.sainsburytest.app.pojo.ItemPojo;
import com.sainsburytest.app.pojo.NetworkRequestPojo;

/**
 * Collection of methods to glue together the network request, the web scraping and the filling of the attributes inside ItemPojo items
 * 
 * @author Alessandro Candolini
 */
public class ScrapingOperations {

	/**
	 * Method to take API input, process it and return a list of items. 
	 * @param input The input of the API
	 * @return list of products fetched from the webpage
	 * @throws ScrapingException thrown whenever the provided input breaks the web scraping 
	 */
	public static List<ItemPojo> consumeListOfItems(InputPojo input) throws ScrapingException {
		List<ItemPojo> list = null;
		if ( input != null && input.getUrl() != null && !input.getUrl().isEmpty()) {
			NetworkRequestPojo networkRequest = new NetworkRequestPojo();
			networkRequest.setUrl(input.getUrl());
			try {
				HtmlWebPage webPage = NetworkRequestEngine.consumeRequest(networkRequest);
				ListItemsScraper scraper = new ListItemsScraper(webPage);
				scraper.scrape();
				list = scraper.getList();
			} catch (NetworkException e) {
				throw new ScrapingException();
			}

		} else {
			throw new ScrapingException();
		}
		return list;
	}

	/**
	 * Given an itempojo instance, process the webpage corresponding to that item, retrieve additional information and return
	 * the item with those additional information
	 * @param item Input item
	 * @return The item object with additional fields populated according to the information available on the corresponding item page
	 */
	public static ItemPojo updateItemWithSinglePageDetails(ItemPojo item) {
		ItemPojo updatedItem = item; 
		if ( item != null && item.getUrl() != null && !item.getUrl().isEmpty()) {
			NetworkRequestPojo networkRequest = new NetworkRequestPojo();
			networkRequest.setUrl(item.getUrl());
			try {
				HtmlWebPage webPage = NetworkRequestEngine.consumeRequest(networkRequest);
				SingleItemScraper scraper = new SingleItemScraper(webPage);
				scraper.scrape();
				updatedItem.setDescription(scraper.getItemDescription());
				updatedItem.setSize(Helper.formatPageSize(scraper.getPageSizeBytes()));
			} catch (ScrapingException e) {
			} catch (NetworkException e) {
			}
		} 
		return updatedItem;
	}


	/**
	 * Given a list of items, for each item the method updateItemWithSinglePageDetails is called.  
	 * @param list list of items 
	 * @return list of items having all properties filled with information taken from the corresponding single item page
	 */
	public static List<ItemPojo> consumeSingleItemPage(final List<ItemPojo> list) {

		List<ItemPojo> output = null;
		if ( list != null) {
			final int size = list.size();
			if ( size > 0 ) {
				output = new ArrayList<ItemPojo>();
				for ( int loop = 0; loop < size; loop++ ) {
					ItemPojo item = updateItemWithSinglePageDetails(list.get(loop));
					output.add(item);
				}
			}
		}
		return output;

	}


}