package de.exxcellent.challenge.csvReader;

/**
 * Used to indicate that a cell from the CSV file has invalid data, or in the strict case extraneous data.
 *
 */
public class CSVException extends Exception {

	private static final long serialVersionUID = -4610561908073084963L;

	private String reason;
	
	public CSVException(String reason) {
		super();
		this.reason = reason;
	}
	
	/**
	 * @return
	 * The offending field
	 */
	public String getReason() {
		return reason;
	}
}
