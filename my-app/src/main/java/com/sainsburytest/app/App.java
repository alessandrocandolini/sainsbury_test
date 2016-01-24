package com.sainsburytest.app;

import java.math.BigDecimal;
import java.util.List;

import com.sainsburytest.app.exception.ScrapingException;
import com.sainsburytest.app.helper.Helper;
import com.sainsburytest.app.scraper.OutputOperations;
import com.sainsburytest.app.scraper.ScrapingOperations;
import com.sainsburytest.app.pojo.InputPojo;
import com.sainsburytest.app.pojo.ItemPojo;
import com.sainsburytest.app.pojo.MethodErrorPojo;
import com.sainsburytest.app.pojo.OutputPojo;
import com.sainsburytest.app.main.Constants;

public class App 
{
    public static void main( String[] args )
    {
    	String url = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
    	
    	InputPojo input = new InputPojo();
    	input.setUrl(url);
    	
    	OutputPojo output = new OutputPojo();
    	
    	List<ItemPojo> list = null;
		try {
			list = ScrapingOperations.consumeListOfItems(input);
		} catch (ScrapingException e) {
			MethodErrorPojo methodError = new MethodErrorPojo();
			methodError.setLocalizedDescription(Constants.DEFAULT_ERROR_MESSAGE);
			output.setError(methodError);
		}
    	
    	List<ItemPojo> list2 = ScrapingOperations.consumeSingleItemPage(list);
        
    	BigDecimal total = Helper.computeTotal(list2);
    	
    	output.setResults(list2);
    	output.setTotal(total);
    	
		System.out.println( OutputOperations.serialize(output) );
		
    
    }
}
