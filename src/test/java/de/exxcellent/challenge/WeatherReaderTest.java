package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.businessobject.WeatherRecord;
import de.exxcellent.challenge.csvReader.SimpleCSVReader;
import de.exxcellent.challenge.interfaces.ICSVReader;

public class WeatherReaderTest {

	private ICSVReader<WeatherRecord> getTestReader() {
		return new SimpleCSVReader<WeatherRecord>();
	}

	@Test
	public void testReaderLenient_fail() {
		String file = "target/classes/de/exxcellent/challenge/weather-fail.csv";
		boolean lenient = true;
		int expectedRecords = 0;
		int expectedErrors = 2;
		testReader(file, lenient, expectedRecords, expectedErrors);
	}

	@Test
	public void testReaderStrict_fail() {
		String file = "target/classes/de/exxcellent/challenge/weather-fail.csv";
		boolean lenient = false;
		int expectedRecords = 0;
		int expectedErrors = 2;
		testReader(file, lenient, expectedRecords, expectedErrors);
	}

	@Test
	public void testReaderLenient_lenient() {
		String file = "target/classes/de/exxcellent/challenge/weather-lenient.csv";
		boolean lenient = true;
		int expectedRecords = 2;
		int expectedErrors = 0;
		testReader(file, lenient, expectedRecords, expectedErrors);
	}

	@Test
	public void testReaderStrict_lenient() {
		String file = "target/classes/de/exxcellent/challenge/weather-lenient.csv";
		boolean lenient = false;
		int expectedRecords = 1;
		int expectedErrors = 1;
		testReader(file, lenient, expectedRecords, expectedErrors);
	}

	@Test
	public void testReaderLenient_strict() {
		String file = "target/classes/de/exxcellent/challenge/weather-strict.csv";
		boolean lenient = true;
		int expectedRecords = 1;
		int expectedErrors = 0;
		testReader(file, lenient, expectedRecords, expectedErrors);
	}

	@Test
	public void testReaderStrict_strict() {
		String file = "target/classes/de/exxcellent/challenge/weather-strict.csv";
		boolean lenient = false;
		int expectedRecords = 1;
		int expectedErrors = 0;
		testReader(file, lenient, expectedRecords, expectedErrors);
	}

	@Test
	public void testReaderValues() {
		String file = "target/classes/de/exxcellent/challenge/weather-strict.csv";
		boolean lenient = false;
		int expectedRecords = 1;
		int expectedErrors = 0;
		List<WeatherRecord> csvEntry = testReader(file, lenient, expectedRecords, expectedErrors);
		WeatherRecord record = csvEntry.get(0);
		// 1,88,59,74,53.8,0,280,9.6,270,17,1.6,93,23,1004.5
		assertEquals(88, record.getMxT(), "Max temperature was not as expected!");
		assertEquals(59, record.getMnT(), "Max temperature was not as expected!");
		assertEquals(1004.5d, record.getR_AvSLP(), "R AvSLP was not as expected!");
	}

	@Test
	public void testReuse() {
		ICSVReader<WeatherRecord> reader = getTestReader();
		String filename = "target/classes/de/exxcellent/challenge/weather-strict.csv";
		InputStream input = null;
		File file = new File(filename);
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			fail("File for testing was not found: " + file.getAbsolutePath(), e);
		}
		reader.getCSVEntries(input, WeatherRecord::new, ",", Locale.ENGLISH, true);
		try {
			reader.getCSVEntries(input, WeatherRecord::new, ",", Locale.ENGLISH, true);
		} catch (IllegalStateException e) {
			// expected exception
			return;
		}
		fail("Expected exception was not thrown!");
	}

	/**
	 * Reads the entries from the file and asserts the expected errors/entry count
	 * 
	 * @param filename
	 * @param lenient
	 * @param expectedRecords
	 * @param expectedErrors
	 * @return the successfully read records if the counts match
	 */
	private List<WeatherRecord> testReader(String filename, boolean lenient, int expectedRecords, int expectedErrors) {
		ICSVReader<WeatherRecord> reader = getTestReader();

		InputStream input = null;
		File file = new File(filename);
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			fail("File for testing was not found: " + file.getAbsolutePath(), e);
		}
		List<WeatherRecord> csvEntries = reader.getCSVEntries(input, WeatherRecord::new, ",", Locale.ENGLISH, lenient);
		assertEquals(expectedRecords, csvEntries.size(), "Unexpected record count of the parser.");
		assertEquals(expectedErrors, reader.getLinesWithError().length, "Unexpected error count of the parser.");
		assertEquals(expectedErrors > 0, reader.hasError(), "Unexpected state of hasError.");
		return csvEntries;
	}
}
