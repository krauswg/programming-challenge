/**
 * 
 */
package de.exxcellent.challenge.businessobject;

import java.text.NumberFormat;
import java.util.Locale;

import de.exxcellent.challenge.csvReader.CSVException;
import de.exxcellent.challenge.csvReader.CSVUtil;
import de.exxcellent.challenge.interfaces.IReadableFromCSV;

/**
 * BusinessObject for an Entry of the football CSV file.
 * Assumptions for the task:
 * Team is a string, everything else is an int.
 */
public class FootballTeam implements IReadableFromCSV {

	private static final int amountFields = 8;
	/*
	 * Header and data:
	 * Team,Games,Wins,Losses,Draws,Goals,Goals Allowed,Points
	 * Arsenal,38,26,9,3,79,36,87
	 */
	private String team;
	private int games;
	private int wins;
	private int losses;
	private int draws;
	private int goals;
	private int goalsAllowed;
	private int points;

	@Override
	public IReadableFromCSV parseLine(String[] values, Locale locale, boolean lenient) throws CSVException {
		if (values.length != amountFields) {
			throw new CSVException("Amount of Values and Fields do not match.");
		}
		NumberFormat numberFormat = NumberFormat.getInstance(locale);
		int i = 0;
		// * Day,MxT,MnT,AvT,AvDP,1HrP TPcpn,PDir,AvSp,Dir,MxS,SkyC,MxR,Mn,R AvSLP
		team = values[i++];
		games = CSVUtil.getNumber(values[i++], lenient, numberFormat).intValue();
		wins = CSVUtil.getNumber(values[i++], lenient, numberFormat).intValue();
		losses = CSVUtil.getNumber(values[i++], lenient, numberFormat).intValue();
		draws = CSVUtil.getNumber(values[i++], lenient, numberFormat).intValue();
		goals = CSVUtil.getNumber(values[i++], lenient, numberFormat).intValue();
		goalsAllowed = CSVUtil.getNumber(values[i++], lenient, numberFormat).intValue();
		points = CSVUtil.getNumber(values[i++], lenient, numberFormat).intValue();

		return this;
	}

	public String getTeam() {
		return team;
	}

	public int getGames() {
		return games;
	}

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}

	public int getDraws() {
		return draws;
	}

	public int getGoals() {
		return goals;
	}

	public int getGoalsAllowed() {
		return goalsAllowed;
	}

	public int getPoints() {
		return points;
	}

}
