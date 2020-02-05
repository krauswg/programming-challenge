package de.exxcellent.challenge.interfaces;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;

/**
 * An interface for a simple CSV reader
 * 
 * @author krauswg
 * @param <CSVObjectType> An instantiable Class implementing {@link IReadableFromCSV} to hold the
 *                        data of a line
 */
public interface ICSVReader<CSVObjectType extends IReadableFromCSV> {

	/**
	 * Returns the contents of the stream splitted per line including an possibly existing header
	 * 
	 * @param input     to read from, needs to have the right encoding
	 * @param separator for splitting lines
	 * @param locale    for parsing, e.g. decimal numbers
	 * @param lenient   whether numbers may contain trailing characters
	 * @return An unmodifiable List of the read Objects
	 */
	public List<CSVObjectType> getCSVEntries(InputStream input, String separator, Locale locale, boolean lenient);

	/**
	 * Returns a List of lines which failed to parse. See
	 * {@link #getCSVEntries(InputStream, String, Locale, boolean)} for lenient.
	 * 
	 * @return a list with unparsable lines
	 */
	public String[] getLinesWithError();

	/**
	 * Returns whether at least one line could not be parsed. See
	 * {@link #getCSVEntries(InputStream, String, Locale, boolean)} for lenient.
	 * 
	 * @return true, if at least one line could not be parsed
	 */
	public boolean hasError();
}
