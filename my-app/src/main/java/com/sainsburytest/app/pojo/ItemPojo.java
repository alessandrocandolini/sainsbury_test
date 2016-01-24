package com.sainsburytest.app.pojo;

import java.math.BigDecimal;

/**
 * Store information about one single product
 * 
 * @author acando
 *
 */
public class ItemPojo {
	
	/**
	 * Name of the item
	 */
	private String title;
	
	/**
	 * Size in kybytes of the webpage of the item
	 */
	private String size;
	
	/**
	 * Price of the item per unit
	 */
	private BigDecimal unit_price;
	
	/**
	 * Description of the item
	 */
	private String description;
	
	/**
	 * Url of the webpage of the item
	 */
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public BigDecimal getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(BigDecimal unit_price) {
		this.unit_price = unit_price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
