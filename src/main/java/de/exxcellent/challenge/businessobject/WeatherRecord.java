package de.exxcellent.challenge.businessobject;

import java.util.Locale;

import de.exxcellent.challenge.interfaces.IReadableFromCSV;

/**
 * BusinessObject for an Entry of the weather CSV file
 * 
 * @author krauswg
 */
public class WeatherRecord implements IReadableFromCSV {

	// TODO: Fields, getters, setters
	/*
	 * Header and data:
	 * Day,MxT,MnT,AvT,AvDP,1HrP TPcpn,PDir,AvSp,Dir,MxS,SkyC,MxR,Mn,R AvSLP
	 * 1,88,59,74,53.8,0,280,9.6,270,17,1.6,93,23,1004.5
	 */
	@Override
	public void parseLine(String[] values, Locale locale, boolean lenient) {
		throw new RuntimeException("Not implemented yet");
	}

}
