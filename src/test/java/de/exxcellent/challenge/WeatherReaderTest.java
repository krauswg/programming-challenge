package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.businessobject.WeatherRecord;
import de.exxcellent.challenge.interfaces.ICSVReader;

public class WeatherReaderTest {

	private ICSVReader<WeatherRecord> getTestReader() {
		return new SimpleCSVReader<WeatherRecord>();
	}

	@Test
	public void testReaderLenient_fail() {
		String file = "weather-fail.csv";
		boolean lenient = true;
		int expectedRecords = 0;
		int expectedErrors = 2;
		testReader(file, lenient, expectedRecords, expectedErrors);
	}

	@Test
	public void testReaderStrict_fail() {
		String file = "weather-fail.csv";
		boolean lenient = false;
		int expectedRecords = 0;
		int expectedErrors = 2;
		testReader(file, lenient, expectedRecords, expectedErrors);
	}

	@Test
	public void testReaderLenient_lenient() {
		String file = "weather-lenient.csv";
		boolean lenient = true;
		int expectedRecords = 2;
		int expectedErrors = 0;
		testReader(file, lenient, expectedRecords, expectedErrors);
	}

	@Test
	public void testReaderStrict_lenient() {
		String file = "weather-lenient.csv";
		boolean lenient = false;
		int expectedRecords = 1;
		int expectedErrors = 1;
		testReader(file, lenient, expectedRecords, expectedErrors);
	}

	@Test
	public void testReaderLenient_strict() {
		String file = "weather-strict.csv";
		boolean lenient = true;
		int expectedRecords = 1;
		int expectedErrors = 0;
		testReader(file, lenient, expectedRecords, expectedErrors);
	}

	@Test
	public void testReaderStrict_strict() {
		String file = "weather-strict.csv";
		boolean lenient = false;
		int expectedRecords = 1;
		int expectedErrors = 0;
		testReader(file, lenient, expectedRecords, expectedErrors);
	}

	private void testReader(String file, boolean lenient, int expectedRecords, int expectedErrors) {
		ICSVReader<WeatherRecord> reader = getTestReader();

		InputStream input = null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			fail("File for testing was not found!", e);
		}
		List<WeatherRecord> csvEntries = reader.getCSVEntries(input, WeatherRecord::new, ",", Locale.ENGLISH, lenient);
		assertEquals(expectedRecords, csvEntries.size(), "Unexpected record count of the parser.");
		assertEquals(expectedErrors, reader.getLinesWithError().length, "Unexpected error count of the parser.");
	}
}
