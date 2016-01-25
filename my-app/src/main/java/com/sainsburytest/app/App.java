package com.sainsburytest.app;

import com.sainsburytest.app.main.ApplicationOperations;
import com.sainsburytest.app.pojo.ApplicationInputDataPojo;
import com.sainsburytest.app.pojo.ApplicationOutputDataPojo;
import com.sainsburytest.app.scraper.OutputOperations;

public class App 
{
    public static void main( String[] args )
    {
    	String url = "http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html";
    	
    	ApplicationInputDataPojo input = new ApplicationInputDataPojo();
    	input.setUrl(url);
    	
    	ApplicationOutputDataPojo output = ApplicationOperations.processInputData(input);
    	
    	String outputJsonFeed = OutputOperations.serialize(output);
    	
		System.out.println( outputJsonFeed );
		
    
    }
}
