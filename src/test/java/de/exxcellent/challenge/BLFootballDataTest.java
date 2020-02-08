/**
 * 
 */
package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.businessLogic.BLFootballData;
import de.exxcellent.challenge.businessobject.FootballTeam;

/**
 * @author krauswg
 */
public class BLFootballDataTest {

	@Test
	public void testReadFile() {
		String filename = "target/classes/de/exxcellent/challenge/football-full.csv";
		List<FootballTeam> records = BLFootballData.readRecordsFromFile(filename);
		assertEquals(20, records.size(), "Unexpected amount of records.");
	}

	@Test
	public void testGetMinSpread() {
		String filename = "target/classes/de/exxcellent/challenge/football-full.csv";
		List<FootballTeam> records = BLFootballData.readRecordsFromFile(filename);
		FootballTeam lowestDiffInGoals = BLFootballData.getLowestDiffInGoals(records);

		int diff = Math.abs(lowestDiffInGoals.getGoals() - lowestDiffInGoals.getGoalsAllowed());
		boolean isLowest = records.stream().allMatch(t -> (Math.abs(t.getGoals() - t.getGoalsAllowed())) >= diff);
		assertTrue(isLowest, "The returned record is not the team with the lowest spread");
	}

	@Test
	public void testGetMinSpreadEmpty() {
		List<FootballTeam> records = new ArrayList<>();
		FootballTeam lowestDiffInGoals = BLFootballData.getLowestDiffInGoals(records);
		assertNull(lowestDiffInGoals, "Empty list should return null");
	}

}
