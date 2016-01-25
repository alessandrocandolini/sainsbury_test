package com.sainsburytest.app.main;

import java.math.BigDecimal;
import java.util.List;

import com.sainsburytest.app.exception.ScrapingException;
import com.sainsburytest.app.helper.Helper;
import com.sainsburytest.app.pojo.ApplicationInputDataPojo;
import com.sainsburytest.app.pojo.ApplicationOutputDataPojo;
import com.sainsburytest.app.pojo.ItemPojo;
import com.sainsburytest.app.pojo.MethodErrorPojo;
import com.sainsburytest.app.scraper.ScrapingOperations;

/**
 * Class to process application input data and build the output as per instructions.
 * 
 * @author Alessandro Candolini
 */
public class ApplicationOperations {
	
	/**
	 * This method reads the API input and proceed through all the steps in order to
	 * distills the desired API output.
	 * 
	 * <p>
	 * There are basically two steps: the first one consists in retrieving the list of
	 * all items from the PLP. Each item in that list however has only limited information.
	 * To fill the missing details, for each product we need to scrape the corresponding PDP. 
	 * FInally, the function computes the total cost of all items and build the output object.
	 * 
	 * @param input The input of the API
	 * @return The output object storing the results of the web scraping
	 */
	public static ApplicationOutputDataPojo processInputData(ApplicationInputDataPojo input) {
		
		final ApplicationOutputDataPojo output = new ApplicationOutputDataPojo();
		
		// fill preliminary list of items from PLP
    	List<ItemPojo> listOfItems = null;
    	MethodErrorPojo methodError = null;
		try {
			listOfItems = ScrapingOperations.extractListOfItemsFromPLP(input);
		} catch (ScrapingException e) {
			methodError = new MethodErrorPojo();
			methodError.setLocalizedDescription(Constants.DEFAULT_ERROR_MESSAGE);
		}
    	
		if ( methodError != null) {
    		output.setError(methodError);
    	} else {
    		
    		// fill missing details from PDP
        	List<ItemPojo> listOfItemsWithDescription= ScrapingOperations.updateListOfItemUsingPDP(listOfItems);
            
        	// calculate total price of all items
        	BigDecimal total = Helper.computeTotal(listOfItemsWithDescription);
        	
    		output.setResults(listOfItemsWithDescription);
        	output.setTotal(total);
    	}
    	
    	return output;
    	
	}

}
