package com.sainsburytest.app;

import com.sainsburytest.app.main.ApplicationOperations;
import com.sainsburytest.app.pojo.ApplicationInputDataPojo;
import com.sainsburytest.app.pojo.ApplicationOutputDataPojo;
import com.sainsburytest.app.scraper.OutputOperations;

public class App 
{
    public static void main( String[] args )
    {
    	// hardcoded url. can be easily replaced by input arg
    	String url = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
    	
    	// build input object
    	ApplicationInputDataPojo input = new ApplicationInputDataPojo();
    	input.setUrl(url);
    	
    	// construct output object
    	ApplicationOutputDataPojo output = ApplicationOperations.processInputData(input);
    	
    	// serialize output to json
    	String outputJsonFeed = OutputOperations.serialize(output);
    	
    	// print to standard output
		System.out.println( outputJsonFeed );
		
    
    }
}
