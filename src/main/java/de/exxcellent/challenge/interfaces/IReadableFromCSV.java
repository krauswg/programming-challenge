package de.exxcellent.challenge.interfaces;

import java.util.Locale;

public interface IReadableFromCSV {

	/**
	 * Parses the values, intended to fill the data fields of a BusinessObject
	 * 
	 * @param values  to insert into this object
	 * @param locale  for parsing, e.g. decimal numbers
	 * @param lenient whether numbers may contain trailing characters
	 */
	public void parseLine(String[] values, Locale locale, boolean lenient);
}
