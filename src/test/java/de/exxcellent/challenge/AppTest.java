package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Example JUnit 5 test case.
 * 
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {

	private static final PrintStream stdOut = System.out;
	private static final PrintStream stdErr = System.err;
	private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@BeforeAll
	public static void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@AfterAll
	public static void restoreStreams() {
		System.setOut(stdOut);
		System.setErr(stdErr);
	}

	@Test
	void runFootball() {
		App.main("--football", "src/main/resources/de/exxcellent/challenge/football.csv");
		assertTrue(outContent.toString().contains("Team with smallest goal spread       : Aston_Villa"),
				"Expected team not present in the output.");
	}

	@Test
	void runFootballFail() {
		assertThrows(RuntimeException.class, () -> App.main("--football", "."));
	}

	@Test
	void runWeather() {
		App.main("--weather", "src/main/resources/de/exxcellent/challenge/weather.csv");
		assertTrue(outContent.toString().contains("Day with smallest temperature spread : 14"),
				"Expected team not present in the output.");
	}

	@Test
	void runWeatherFail() {
		assertThrows(RuntimeException.class, () -> App.main("--weather", "."));
	}

}