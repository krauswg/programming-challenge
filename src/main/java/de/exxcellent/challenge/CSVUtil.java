/**
 * 
 */
package de.exxcellent.challenge;

import java.text.NumberFormat;
import java.text.ParsePosition;

/**
 * @author krauswg
 */
public class CSVUtil {

	public static Number getNumber(String value, boolean lenient, NumberFormat numberFormat) throws CSVException {
		ParsePosition pos = new ParsePosition(0);
		Number field = numberFormat.parse(value, pos);
		if (pos.getIndex() == 0 && !value.isBlank()) {
			throw new CSVException(value);
		}
		if (!lenient && (pos.getIndex() < value.length() - 1)) {
			throw new CSVException(value);
		}
		return field;
	}

}
