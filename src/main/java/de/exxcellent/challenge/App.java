package de.exxcellent.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import de.exxcellent.challenge.businessLogic.BLFootballData;
import de.exxcellent.challenge.businessLogic.BLWeatherData;
import de.exxcellent.challenge.businessobject.FootballTeam;
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
	 * Going from the AppTest it is assumed that parameters are expected, i.e. --weather filename
	 * 
	 * @param args The CLI arguments passed
	 * @throws FileNotFoundException
	 */
	public static void main(String... args) {

		// Your preparation code …
		String weatherFileName = "";
		String footballFileName = "";
		for (int i = 0; i < args.length; i++) {
			if ("--weather".equals(args[i])) {
				weatherFileName = getAbsoluteFileNameForParameter(args, i);
				continue;
			}
			if ("--football".equals(args[i])) {
				footballFileName = getAbsoluteFileNameForParameter(args, i);
			}
		}
		if (!weatherFileName.isBlank()) {
			List<WeatherRecord> weather = BLWeatherData.readRecordsFromFile(weatherFileName);
			String dayWithSmallestTempSpread = BLWeatherData.getLowestSpread(weather).getDay() + "";
			System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread);
		}
		if (!footballFileName.isBlank()) {
			List<FootballTeam> teams = BLFootballData.readRecordsFromFile(footballFileName);
			String teamWithSmallestGoalSpread = BLFootballData.getLowestDiffInGoals(teams).getTeam();
			System.out.printf("Team with smallest goal spread       : %s%n", teamWithSmallestGoalSpread);
		}
	}

	private static String getAbsoluteFileNameForParameter(String[] args, int idx) {
		if (idx + 1 >= args.length) {
			throw new RuntimeException(args[idx] + " needs to be followed by the filename!");
		}
		File csvFile = new File(args[idx + 1]);
		if (!(csvFile.canRead() && csvFile.isFile())) {
			throw new RuntimeException("Unable to read file at: " + csvFile.getAbsolutePath());
		}
		return csvFile.getAbsolutePath();
	}
}
