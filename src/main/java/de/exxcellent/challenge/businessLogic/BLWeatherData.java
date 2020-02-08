package de.exxcellent.challenge.businessLogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.exxcellent.challenge.SimpleCSVReader;
import de.exxcellent.challenge.businessobject.WeatherRecord;

public class BLWeatherData {
	private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getSimpleName());

	public static List<WeatherRecord> readRecordsFromFile(String filename){
		SimpleCSVReader<WeatherRecord> reader = new SimpleCSVReader<>();
		List<WeatherRecord> weather;
		File file = new File(filename);
		try {
			weather = reader.getCSVEntries(new FileInputStream(file),
					WeatherRecord::new, ",", Locale.ENGLISH, false);
		} catch (FileNotFoundException e) {
			LOG.log(Level.WARNING, "Unable to read file: " + file.getAbsolutePath(), e);
			return Collections.<WeatherRecord> emptyList();
		}

		return weather;
	}

	public static WeatherRecord getLowestSpread(List<WeatherRecord> records) {
		WeatherRecord min = records.stream().min((w1, w2) -> 
			(w1.getMxT() - w1.getMnT())
			- (w2.getMxT() - w2.getMnT())).get();
		return min;
	}
}
