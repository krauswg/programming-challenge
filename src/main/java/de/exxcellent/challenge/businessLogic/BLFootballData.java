/**
 * 
 */
package de.exxcellent.challenge.businessLogic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.exxcellent.challenge.businessobject.FootballTeam;
import de.exxcellent.challenge.csvReader.CSVUtil;

/**
 * Methods to work on {@link FootballTeam} data
 * 
 * @author krauswg
 */
public class BLFootballData {

	/**
	 * Reads the records from the football team file
	 * 
	 * @param filename to read
	 * @return the entries or an {@link Collections#emptyList()} if the file could not be read
	 */
	public static List<FootballTeam> readRecordsFromFile(String filename) {
		List<FootballTeam> teams = CSVUtil.readRecordsFromFile(filename, FootballTeam::new);
		return teams;
	}

	/**
	 * Determines the team with the lowest absolute difference in goals and goals allowed
	 * 
	 * @param teams to use
	 * @return the {@link FootballTeam}, or <code>null</code> if the list was empty.
	 */
	public static FootballTeam getLowestDiffInGoals(List<FootballTeam> teams) {
		return teams.stream().min(getComparatorAbsGoalDiff()).orElse(null);
	}

	/**
	 * @return a lambda comparator which compare the absolute difference between goals and goals allowed
	 */
	private static Comparator<FootballTeam> getComparatorAbsGoalDiff() {
		return (t1, t2) -> {
			int diff1 = Math.abs(t1.getGoals() - t1.getGoalsAllowed());
			int diff2 = Math.abs(t2.getGoals() - t2.getGoalsAllowed());
			return diff1 - diff2;
		};
	}
}
