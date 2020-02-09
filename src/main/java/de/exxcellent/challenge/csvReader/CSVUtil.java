/**
 * 
 */
package de.exxcellent.challenge.csvReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.exxcellent.challenge.interfaces.ICSVReader;
import de.exxcellent.challenge.interfaces.IReadableFromCSV;

/**
 * Provides several utility methods for working with CSV Files and records
 * 
 * @author krauswg
 */
public class CSVUtil {
	private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getSimpleName());

	/**
	 * Parses the string value to a number with the given format
	 * 
	 * @param value        to parse
	 * @param lenient      whether all characters in an non-empty string must represent the number
	 * @param numberFormat for e.g. decimal numbers
	 * @return the Number, as {@link NumberFormat#parse(String)}
	 * @throws CSVException if an non-empty string does not begin with a number, or if extraneous
	 *                      characters exists and lenient == false
	 */
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

	/**
	 * Reads the records from the file
	 * 
	 * @param filename to read
	 * @return the entries or an {@link Collections#emptyList()} if the file could not be read
	 */
	public static <BOType extends IReadableFromCSV> List<BOType> readRecordsFromFile(String filename,
			Supplier<BOType> constructor) {
		ICSVReader<BOType> reader = new SimpleCSVReader<>();
		List<BOType> records;
		File file = new File(filename);
		if (!(file.isFile() && file.canRead())) {
			LOG.log(Level.WARNING, "Not a file or does not exist: " + file.getAbsolutePath());
			return Collections.<BOType>emptyList();
		}
		try {
			records = reader.getCSVEntries(new FileInputStream(file), constructor, ",", Locale.ENGLISH, false);
		} catch (FileNotFoundException e) {
			LOG.log(Level.WARNING, "Unable to read file: " + file.getAbsolutePath(), e);
			return Collections.<BOType>emptyList();
		}

		return records;
	}

}
