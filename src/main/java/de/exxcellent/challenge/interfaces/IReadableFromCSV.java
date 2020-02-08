package de.exxcellent.challenge.interfaces;

import java.util.Locale;

import de.exxcellent.challenge.CSVException;

public interface IReadableFromCSV {

	/**
	 * Parses the values, intended to fill the data fields of a BusinessObject
	 * 
	 * @param values  to insert into this object
	 * @param locale  for parsing, e.g. decimal numbers
	 * @param lenient whether numbers may contain trailing characters
	 * @throws CSVException if a field was not parseable
	 */
	public IReadableFromCSV parseLine(String[] values, Locale locale, boolean lenient) throws CSVException;
}
