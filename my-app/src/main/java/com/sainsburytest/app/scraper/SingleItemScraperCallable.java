package com.sainsburytest.app.scraper;

import java.util.concurrent.Callable;

import com.sainsburytest.app.pojo.ItemPojo;

public class SingleItemScraperCallable implements Callable<ItemPojo>{
	private final ItemPojo item;
	 
    public SingleItemScraperCallable(final ItemPojo item) {
        this.item = item;
    }
    
    @Override
    public ItemPojo call() throws Exception {
    	return ScrapingOperations.updateItemWithSinglePageDetails(item);
    }
}
