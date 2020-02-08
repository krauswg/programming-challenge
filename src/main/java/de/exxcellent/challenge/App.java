package de.exxcellent.challenge;

import java.io.FileNotFoundException;
import java.util.List;

import de.exxcellent.challenge.businessLogic.BLWeatherData;
import de.exxcellent.challenge.businessobject.WeatherRecord;

/**
 * The entry class for your solution. This class is only aimed as starting point
 * and not intended as baseline for your software design. Read: create your own
 * classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {

	/**
	 * This is the main entry method of your program.
	 * 
	 * @param args The CLI arguments passed
	 * @throws FileNotFoundException 
	 */
	public static void main(String... args) {

		// Your preparation code …
		List<WeatherRecord> weather = BLWeatherData.readRecordsFromFile("src/main/resources/de/exxcellent/challenge/weather.csv");
		String dayWithSmallestTempSpread = BLWeatherData.getLowestSpread(weather).getDay() + ""; // Your day analysis function call …
		System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);

		String teamWithSmallestGoalSpread = "A good team"; // Your goal analysis function call …
		System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
	}
}
