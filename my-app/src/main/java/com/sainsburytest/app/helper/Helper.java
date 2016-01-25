package com.sainsburytest.app.helper;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.List;

import com.sainsburytest.app.pojo.ItemPojo;

/**
 * Collection of static auxiliary methods 
 * 
 * @author Alessandro Candolini
 */
public class Helper {

	/**
	 * Returns the value of the overall price of a list of items.
	 * Never returns null: if the list is empty, the method returns 0.0.
	 * @param list The list of items
	 * @return The total cost of all items in the list
	 */
	public static BigDecimal computeTotal(List<ItemPojo> list) {
		final BigDecimal total;
		if ( list != null ) {
			final int size = list.size();
			BigDecimal accumulator = BigDecimal.ZERO;
			for (int loop = 0; loop < size; loop++) {
				accumulator = accumulator.add( list.get(loop).getUnit_price() );
			}
			total = accumulator;
		} else {
			total = BigDecimal.ZERO;;
		}
		return total;
	}


	/**
	 * Given a properly formatted price string, extract the amount.
	 * 
	 * <p>
	 * The method strips from the input string the currency and convert the result to a BigDecimal
	 * 
	 * @param priceWithCurrency String of the unit price containing the currency 
	 * @return Amount of the price as BigDecimal 
	 */
	public static BigDecimal extractAmount(String priceWithCurrency) {
		final BigDecimal amount;

		if ( priceWithCurrency != null && !priceWithCurrency.isEmpty()) {
			String priceWithoutCurrency = priceWithCurrency.replace("&pound", "").replace("/unit", "");  

			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setDecimalSeparator('.');
			String pattern = "##0.00#";
			DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
			decimalFormat.setParseBigDecimal(true);

			// parse the string
			BigDecimal bigDecimal = null;
			try {
				bigDecimal = (BigDecimal) decimalFormat.parse(priceWithoutCurrency);
			} catch (ParseException e) {
			}
			amount = bigDecimal;
		} else {
			amount = null;
		}

		return amount;
	}


	/**
	 * Convert size in bytes of an html page to a string with the value in kilobytes and the kb string appended to it.
	 * Integer division is used, so decimal digits are not retained
	 * @param sizeInBytes The size of the html page in bytes 
	 * @return If {@code sizeInBytes} is different from null, it returns a string with the size of the html page in kilobytes and a "kb" suffix , null otherwise
	 */
	public static String formatPageSize(Integer sizeInBytes) {
		final String sizeInKyloBytes;
		if ( sizeInBytes != null) {
			Integer kbytes = sizeInBytes / 1024;	 // approximated value
			sizeInKyloBytes = String.format("%dkb" , kbytes) ;
		} else {
			sizeInKyloBytes = null;
		}
		return sizeInKyloBytes;
	}
	
}