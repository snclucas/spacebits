package org.spacebits.physics;


public class Unit {
	
	public static double Unity = 1.0;
	
	//Units for mass
	public static double g = 1.0e-3;
	public static double kg = 1.0;
	public static double Tonne = 1.0e3;
	
	//Units for volume
	public static double m3 = 1.0;
	public static double l = 1.0e-3;
	
	//Units for thrust/force
	public static double N = 1.0;
	public static double kN = 1.0e3;
	public static double MN = 1.0e6;
	
	//Units for CPU throughput
	public static double kFLOP = 1.0e-3;
	public static double MFLOP = 1.0;
	public static double GFLOP = 1.0e3;
	public static double TFLOP = 1.0e6;
	
	//Units for power
	public static double uW = 1.0e-6;
	public static double mW = 1.0e-3;
	public static double W = 1.0;
	public static double kW = 1.0e3;
	public static double MW = 1.0e6;
	public static double GW = 1.0e9;
	
	//Units for speed
	public static double mps = Unit.m3 / Unit.s;
	public static double lmps = 1e3 * mps;
	public static double c = Constants.c;
	
	
	//Units for energy
	public static double GJ = 1.0e-9;
	
	//Units for length
	public static double cm = 1.0e-2;
	public static double m = 1.0;
	public static double Km = 1.0e3;
	public static double Ly = 9460730472580800.0 * Unit.m;
	public static double Pc = 3.2615679661840633266036314297735 * Ly;
	public static double kPc = 1.0e3 * Pc;
	
	public static double AU =  1.49597870700e11 * Unit.m;
	
	//Units for time
	public static double s = 1.0;
	public static double day = 86400.0;
	public static double year = 365 * 86400.0;
	
	public static double percent = 1.0e-2;
	
	
	public static double G_STAR_LUMINOSITY = 3.846E26 * W;
	
	

}
