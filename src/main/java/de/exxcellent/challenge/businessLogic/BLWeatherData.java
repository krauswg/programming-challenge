package de.exxcellent.challenge.businessLogic;

import java.util.Collections;
import java.util.List;

import de.exxcellent.challenge.CSVUtil;
import de.exxcellent.challenge.businessobject.WeatherRecord;

public class BLWeatherData {

	/**
	 * Reads the records from the file
	 * 
	 * @param filename to read
	 * @return the entries or an {@link Collections#emptyList()} if the file could not be read
	 */
	public static List<WeatherRecord> readRecordsFromFile(String filename) {
		List<WeatherRecord> weather = CSVUtil.readRecordsFromFile(filename, WeatherRecord::new);
		return weather;
	}

	/**
	 * Determines the day with the lowest spread between {@link WeatherRecord#getMxT()} and
	 * {@link WeatherRecord#getMnT()}
	 * 
	 * @param records to evaluate
	 * @return the record with the lowest spread
	 */
	public static WeatherRecord getLowestSpread(List<WeatherRecord> records) {
		WeatherRecord min = records.stream().min((w1, w2) -> (w1.getMxT() - w1.getMnT()) - (w2.getMxT() - w2.getMnT()))
				.get();
		return min;
	}
}
