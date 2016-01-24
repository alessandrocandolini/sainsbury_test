package com.sainsburytest.app.serializer;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.sainsburytest.app.pojo.ItemPojo;

/**
 * Custom gson ExclusionStrategy for ProductPojo class that disregard url field.
 *
 * @author acando
 *
 */
public class ProductPojoExclusionStrategy implements ExclusionStrategy {

    public boolean shouldSkipClass(Class<?> arg0) {
        return false;
    }

    /**
     * This method will skip the url field of the ItemPojo class when serializing ItemPojo's objects
     * 
     * @see com.google.gson.ExclusionStrategy#shouldSkipField(com.google.gson.FieldAttributes)
     */
    public boolean shouldSkipField(FieldAttributes f) {

    	final Boolean shouldSkipField;
    	
    	if (f.getDeclaringClass() == ItemPojo.class && f.getName().equals("url")) {
    		shouldSkipField = true;
    	} else {
    		shouldSkipField = false;
    	}
    	return shouldSkipField;
    }

}