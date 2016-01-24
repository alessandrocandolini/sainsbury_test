package com.sainsburytest.app.scraper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sainsburytest.app.pojo.OutputPojo;
import com.sainsburytest.app.serializer.ProductPojoExclusionStrategy;

/**
 * 
 * Collection of serialization methods for the output
 * 
 * @author acando
 *
 */
public class OutputOperations {
	
	/**
	 * Provides custom serialization of the {@code OutputPojo} object.
	 * 
	 * The actual implementation relies upon gson library.
	 * 
	 * @param output The OutputPojo method
	 * @return Json string representing the output object
	 */
	public static String serialize(OutputPojo output) {
		Gson gson = new GsonBuilder()
    	.setExclusionStrategies(new ProductPojoExclusionStrategy())
    	            .create();
    	return gson.toJson(output);
	}

}
