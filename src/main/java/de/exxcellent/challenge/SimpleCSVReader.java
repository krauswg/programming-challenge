package de.exxcellent.challenge;

import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.exxcellent.challenge.interfaces.ICSVReader;
import de.exxcellent.challenge.interfaces.IReadableFromCSV;

public class SimpleCSVReader<BO extends IReadableFromCSV> implements ICSVReader<BO> {
	private static final Logger LOG = Logger.getLogger(MethodHandles.lookup().getClass().getSimpleName());
	
	private String headerLine = null;
	private List<String> errorLines = new ArrayList<>();
	private List<BO> entries = new ArrayList<>();
	private AtomicBoolean parsingStarted = new AtomicBoolean(false);

	@Override
	public List<BO> getCSVEntries(InputStream input, Supplier<BO> constructor, String separator, Locale locale, boolean lenient) {
		if (!parsingStarted.compareAndSet(false, true)) {
			throw new IllegalStateException("The simple parser cannot be reused!");
		}
		try (Scanner scanner = new Scanner(input)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (headerLine == null) {
					headerLine = line;
					continue;
				}
				try {
					BO csvBusinessObject = constructor.get();
					csvBusinessObject.parseLine(line.split(separator), locale, lenient);
					entries.add(csvBusinessObject);
				} catch (CSVException e) {
					String msg = "Unparseable line in input. Lenient: " + lenient 
							 + ", Reason: " + e.getReason() + ", line: '" + line + "'";
					errorLines.add(line);
					LOG.log(Level.WARNING, msg, e);
				}
			}
		}
		return entries;
	}

	@Override
	public String[] getLinesWithError() {
		return errorLines.toArray(new String[errorLines.size()]);
	}

	@Override
	public boolean hasError() {
		return !errorLines.isEmpty();
	}

}
