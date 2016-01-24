package com.sainsburytest.app.helper;

import java.math.BigDecimal;
import java.util.ArrayList;

import junit.framework.TestCase;

import com.sainsburytest.app.pojo.ItemPojo;

public class HelperTest extends TestCase {
	
	private String priceWithCurrency;
	private BigDecimal priceWithoutCurrency;
	private Integer sizeInBytes;
	private String sizeInKiloBytes;
	private BigDecimal total;
	private BigDecimal price1;
	private BigDecimal price2;
	
	@Override
	protected void setUp() {
		sizeInBytes = 1048576;
		sizeInKiloBytes = "1024kb";
		priceWithCurrency = "&pound3.50/unit";
		priceWithoutCurrency = new BigDecimal(3.50);
		price1 = new BigDecimal(1.0);
		price2 = new BigDecimal(2.5);
		total = price1.add(price2);
	}
	
	
	public void testConvertBytesToSizeString() {
		assertEquals(sizeInKiloBytes, Helper.formatPageSize(sizeInBytes));
	}
	
	public void testStripCurrencyFromPrice() {
		assertEquals(0, priceWithoutCurrency.compareTo(Helper.extractAmount(priceWithCurrency)));
	}
	
	public void testComputeTotal() {
		
		ArrayList<ItemPojo> list = new ArrayList<ItemPojo>();
		ItemPojo item1 = new ItemPojo();
		item1.setUnit_price(price1);
		list.add(item1);
		ItemPojo item2 = new ItemPojo();
		item2.setUnit_price(price2);
		list.add(item2);
		assertEquals(total, Helper.computeTotal(list));
		
	}

}
