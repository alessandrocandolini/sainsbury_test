package com.sainsburytest.app.scraper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sainsburytest.app.pojo.ItemPojo;
import com.sainsburytest.app.pojo.OutputPojo;

import junit.framework.TestCase;

public class OutputOperationsTest extends TestCase  {
	
	private String json;
	private OutputPojo output;
	
	@Override
	protected void setUp() {
		json = "{\"total\":10,\"results\":[{\"title\":\"Prodotto 1\",\"description\":\"Descrizione 1\"},{\"title\":\"Prodotto 1\",\"description\":\"Descrizione 1\"}]}";
	
		List<ItemPojo> fakeList = new ArrayList<ItemPojo>();
		ItemPojo item1 = new ItemPojo();
		item1.setTitle("Prodotto 1");
		item1.setDescription("Descrizione 1");
		item1.setUrl("http://item1");
		fakeList.add(item1);
		ItemPojo item2 = new ItemPojo();
		item2.setTitle("Prodotto 1");
		item2.setDescription("Descrizione 1");
		item2.setUrl("http://item2");
		fakeList.add(item2);
		output = new OutputPojo();
		output.setResults(fakeList);
		output.setTotal(new BigDecimal(10.0));
	}
	
	public void testSerialize() {
		assertEquals(json, OutputOperations.serialize(output));
	}

}
