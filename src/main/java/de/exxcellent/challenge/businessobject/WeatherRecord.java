package de.exxcellent.challenge.businessobject;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import de.exxcellent.challenge.CSVException;
import de.exxcellent.challenge.interfaces.IReadableFromCSV;

/**
 * BusinessObject for an Entry of the weather CSV file.
 * Assumtions for the task:
 * MxT and MnT are integers.
 * All other fields are treated as Number, subject to change.
 * 
 * @author krauswg
 */
public class WeatherRecord implements IReadableFromCSV {

	public Number getDay() {
		return day;
	}

	public int getMxT() {
		return mxT;
	}

	public int getMnT() {
		return mnT;
	}

	public Number getAvT() {
		return avT;
	}

	public Number getAvDP() {
		return avDP;
	}

	public Number getX1hrP_TPcp() {
		return x1hrP_TPcp;
	}

	public Number getpDir() {
		return pDir;
	}

	public Number getAvSp() {
		return avSp;
	}

	public Number getDir() {
		return dir;
	}

	public Number getMxS() {
		return mxS;
	}

	public Number getSkyC() {
		return skyC;
	}

	public Number getMxr() {
		return mxr;
	}

	public Number getMn() {
		return mn;
	}

	public Number getR_AvSLP() {
		return r_AvSLP;
	}

	/*
	 * Header and data:
	 * Day,MxT,MnT,AvT,AvDP,1HrP TPcpn,PDir,AvSp,Dir,MxS,SkyC,MxR,Mn,R AvSLP
	 * 1,88,59,74,53.8,0,280,9.6,270,17,1.6,93,23,1004.5
	 */
	private Number day;
	private int mxT;
	private int mnT;
	private Number avT;
	private Number avDP;
	private Number x1hrP_TPcp;
	private Number pDir;
	private Number avSp;
	private Number dir;
	private Number mxS;
	private Number skyC;
	private Number mxr;
	private Number mn;
	private Number r_AvSLP;
	private final int amountFields = 14;

	@Override
	public WeatherRecord parseLine(String[] values, Locale locale, boolean lenient) throws CSVException {
		if (values.length != amountFields) {
			throw new CSVException("Amount of Values and Fields do not match.");
		}
		NumberFormat numberFormat = NumberFormat.getInstance(locale);
		int i = 0;
		// * Day,MxT,MnT,AvT,AvDP,1HrP TPcpn,PDir,AvSp,Dir,MxS,SkyC,MxR,Mn,R AvSLP
		day = getNumber(values[i++], lenient, numberFormat);
		mxT = getNumber(values[i++], lenient, numberFormat).intValue();
		mnT = getNumber(values[i++], lenient, numberFormat).intValue();
		avT = getNumber(values[i++], lenient, numberFormat);
		avDP = getNumber(values[i++], lenient, numberFormat);
		x1hrP_TPcp = getNumber(values[i++], lenient, numberFormat);
		pDir = getNumber(values[i++], lenient, numberFormat);
		avSp = getNumber(values[i++], lenient, numberFormat);
		dir = getNumber(values[i++], lenient, numberFormat);
		mxS = getNumber(values[i++], lenient, numberFormat);
		skyC = getNumber(values[i++], lenient, numberFormat);
		mxr = getNumber(values[i++], lenient, numberFormat);
		mn = getNumber(values[i++], lenient, numberFormat);
		r_AvSLP = getNumber(values[i++], lenient, numberFormat);
		return this;
	}

	
	private Number getNumber(String value, boolean lenient, NumberFormat numberFormat) throws CSVException {
		ParsePosition pos = new ParsePosition(0);
		Number field = numberFormat.parse(value, pos);
		if (pos.getIndex() == 0 && !value.isBlank()) {
			throw new CSVException(value);
		}
		if (!lenient && (pos.getIndex() < value.length() - 1)) {
			throw new CSVException(value);
		}
		return field;
	}
}
