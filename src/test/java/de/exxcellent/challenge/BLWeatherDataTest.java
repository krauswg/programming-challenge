/**
 * 
 */
package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.businessLogic.BLWeatherData;
import de.exxcellent.challenge.businessobject.WeatherRecord;

/**
 * @author krauswg
 */
public class BLWeatherDataTest {

	@Test
	public void testReadFile() {
		String filename = "target/classes/de/exxcellent/challenge/weather-full.csv";
		List<WeatherRecord> records = BLWeatherData.readRecordsFromFile(filename);
		assertEquals(30, records.size(), "Unexpected amount of records.");
	}

	@Test
	public void testGetLowestSpread() {
		String filename = "target/classes/de/exxcellent/challenge/weather-full.csv";
		List<WeatherRecord> records = BLWeatherData.readRecordsFromFile(filename);

		WeatherRecord lowestDiff = BLWeatherData.getLowestSpread(records);
		int diff = lowestDiff.getMxT() - lowestDiff.getMnT();
		boolean isLowest = records.stream().allMatch(w -> (w.getMxT() - w.getMnT()) >= diff);
		assertTrue(isLowest, "The returned record is not the day with the lowest spread");
	}
}
