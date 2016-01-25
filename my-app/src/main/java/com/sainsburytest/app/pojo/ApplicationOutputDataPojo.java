package com.sainsburytest.app.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Encapsulate information for the output of the API. 
 * 
 * @author acando
 *
 */
public class ApplicationOutputDataPojo {
	
	/**
	 * Total cost of all products found
	 */
	private BigDecimal total;
	
	/**
	 * List of all products found
	 */
	private List<ItemPojo> results;
	
	/**
	 * Error
	 */
	private MethodErrorPojo error; 

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<ItemPojo> getResults() {
		return results;
	}

	public void setResults(List<ItemPojo> results) {
		this.results = results;
	}

	public MethodErrorPojo getError() {
		return error;
	}

	public void setError(MethodErrorPojo error) {
		this.error = error;
	}
	
}
