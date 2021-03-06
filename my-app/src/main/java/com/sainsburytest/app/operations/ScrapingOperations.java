package com.sainsburytest.app.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.sainsburytest.app.exception.NetworkException;
import com.sainsburytest.app.exception.ScrapingException;
import com.sainsburytest.app.helper.Helper;
import com.sainsburytest.app.network.NetworkRequestEngine;
import com.sainsburytest.app.pojo.ApplicationInputDataPojo;
import com.sainsburytest.app.pojo.WebPagePojo;
import com.sainsburytest.app.pojo.ItemPojo;
import com.sainsburytest.app.pojo.WebPageRequestPojo;
import com.sainsburytest.app.scraper.PLPScraper;
import com.sainsburytest.app.scraper.PDPScraper;

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
	public static List<ItemPojo> extractListOfItemsFromPLP(ApplicationInputDataPojo input) throws ScrapingException {
		List<ItemPojo> list = null;
		if ( input != null && input.getUrl() != null && !input.getUrl().isEmpty()) {
			WebPageRequestPojo networkRequest = new WebPageRequestPojo();
			networkRequest.setUrl(input.getUrl());
			try {
				WebPagePojo webPage = NetworkRequestEngine.retrieveWebPage(networkRequest);
				PLPScraper scraper = new PLPScraper(webPage);
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
			WebPageRequestPojo networkRequest = new WebPageRequestPojo();
			networkRequest.setUrl(item.getUrl());
			try {
				WebPagePojo webPage = NetworkRequestEngine.retrieveWebPage(networkRequest);
				PDPScraper scraper = new PDPScraper(webPage);
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
	public static List<ItemPojo> updateListOfItemUsingPDP(final List<ItemPojo> listOfItems) {
		return updateListOfItemUsingPDPMultithread(listOfItems);
	}
	
	
	
	private static List<ItemPojo> updateListOfItemUsingPDPSingleThread(final List<ItemPojo> listOfItems) {

		List<ItemPojo> output = null;
		if ( listOfItems != null) {
			final int size = listOfItems.size();
			if ( size > 0 ) {
				output = new ArrayList<ItemPojo>();
				for ( int loop = 0; loop < size; loop++ ) {
					ItemPojo item = updateItemWithSinglePageDetails(listOfItems.get(loop));
					output.add(item);
				}
			}
		}
		return output;

	}


	private static List<ItemPojo> updateListOfItemUsingPDPMultithread(final List<ItemPojo> listOfItems) {

		List<ItemPojo> outputList = null;

		if ( listOfItems != null) {
			final int size = listOfItems.size();
			if ( size > 0 ) {
				ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
				List<Future<ItemPojo>> futureList = new ArrayList<Future<ItemPojo>>(); // Diamond operator can be used here in java 1.7
				outputList = new ArrayList<ItemPojo>();
				for ( int loop = 0; loop < size; loop++ ) {

					PDPScraperCallable callable  = new PDPScraperCallable(listOfItems.get(loop));
					Future<ItemPojo> result = executor.submit(callable);
					futureList.add(result);

				}

				for(Future<ItemPojo> future : futureList) {
					try {
						final ItemPojo item = future.get() ;
						outputList.add(item);
					}  catch (InterruptedException e) {
					} catch (ExecutionException e) {
					}
				}
				//shut down the executor service now
				executor.shutdown();
			}
		}
		return outputList;

	}

	/**
	 * Callable wrapper for retrieving and parsing a PDP of a product.
	 * 
	 * @author Alessandro Candolini
	 */
	private static class PDPScraperCallable implements Callable<ItemPojo>{
		
		
		private final ItemPojo item;
		 
	    public PDPScraperCallable(final ItemPojo item) {
	        this.item = item;
	    }
	    
	    @Override
	    public ItemPojo call() throws Exception {
	    	return ScrapingOperations.updateItemWithSinglePageDetails(item);
	    }
	}

}
