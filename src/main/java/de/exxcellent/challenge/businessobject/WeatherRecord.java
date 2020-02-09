package de.exxcellent.challenge.businessobject;

import java.text.NumberFormat;
import java.util.Locale;

import de.exxcellent.challenge.csvReader.CSVException;
import de.exxcellent.challenge.csvReader.CSVUtil;
import de.exxcellent.challenge.interfaces.IReadableFromCSV;

/**
 * BusinessObject for an Entry of the weather CSV file.
 * Assumptions for the task:
 * MxT and MnT are treated as integers.
 * All other fields are treated as Number, subject to change.
 * 
 * @author krauswg
 */
public class WeatherRecord implements IReadableFromCSV {

	private final int amountFields = 14;
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

	@Override
	public WeatherRecord parseLine(String[] values, Locale locale, boolean lenient) throws CSVException {
		if (values.length != amountFields) {
			throw new CSVException("Amount of Values and Fields do not match.");
		}
		NumberFormat numberFormat = NumberFormat.getInstance(locale);
		int i = 0;
		// * Day,MxT,MnT,AvT,AvDP,1HrP TPcpn,PDir,AvSp,Dir,MxS,SkyC,MxR,Mn,R AvSLP
		day = CSVUtil.getNumber(values[i++], lenient, numberFormat);
		mxT = CSVUtil.getNumber(values[i++], lenient, numberFormat).intValue();
		mnT = CSVUtil.getNumber(values[i++], lenient, numberFormat).intValue();
		avT = CSVUtil.getNumber(values[i++], lenient, numberFormat);
		avDP = CSVUtil.getNumber(values[i++], lenient, numberFormat);
		x1hrP_TPcp = CSVUtil.getNumber(values[i++], lenient, numberFormat);
		pDir = CSVUtil.getNumber(values[i++], lenient, numberFormat);
		avSp = CSVUtil.getNumber(values[i++], lenient, numberFormat);
		dir = CSVUtil.getNumber(values[i++], lenient, numberFormat);
		mxS = CSVUtil.getNumber(values[i++], lenient, numberFormat);
		skyC = CSVUtil.getNumber(values[i++], lenient, numberFormat);
		mxr = CSVUtil.getNumber(values[i++], lenient, numberFormat);
		mn = CSVUtil.getNumber(values[i++], lenient, numberFormat);
		r_AvSLP = CSVUtil.getNumber(values[i++], lenient, numberFormat);
		return this;
	}

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

}
