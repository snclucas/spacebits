package org.braincycles.spacebits.universe.celestialobjects;

import org.braincycles.spacebits.physics.Unit;

public class SensorSignalResponseLibrary {
	
	public final static String SUBSPACE_BEACON = "Subspace beacon";
	
	public final static String O_CLASS_STAR = "O-class star"; // e.g. 10 Lacertra
	public final static String B_CLASS_STAR = "B-class star"; // e.g. Rigel, SPica
	public final static String A_CLASS_STAR = "A-class star"; // e.g. Sirius, Vega
	public final static String F_CLASS_STAR = "F-class star"; // e.g. Canopus, Procyon
	public final static String G_CLASS_STAR = "G-class star"; // e.g. Sol
	public final static String K_CLASS_STAR = "K-class star"; // e.g. Arcturus, Alderbaran
	public final static String M_CLASS_STAR = "M-class star"; // e.g. Betegeuse
	
	public final static String BROWN_DWARF = "Brown dwarf"; 
	public final static String WHITE_DWARF = "White dwarf"; 
	
	public final static String RADIO_PULSAR = "Radio pulsar"; 
	public final static String X_RAY_PULSAR = "X-ray pulsar"; 
	public final static String GAMMA_RAY_PULSAR = "Gamma-ray pulsar"; 
	
	public final static String SUPERNOVA = "Supernova"; 
	public final static String HYPERNOVA = "Hypernova";
	public final static String BLACK_HOLE = "Black hole";
	public final static String SUPERMASSIVE_BLACK_HOLE = "Supermassive black hole";
	
	public static SensorSignalResponseProfile getStandardSignalResponseProfile(String objectClassification) {
		switch(objectClassification) {
		case SUBSPACE_BEACON:
			return new SensorSignalResponseProfile(50, responseForSphere(100 * Unit.m, 100.0), 0.0, 0.0, 10);
		case O_CLASS_STAR:
			return new SensorSignalResponseProfile(-5.5, responseForSphere(10 * Unit.G_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
		case B_CLASS_STAR:
			return new SensorSignalResponseProfile(-1.74, responseForSphere(5 * Unit.G_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
		case A_CLASS_STAR:
			return new SensorSignalResponseProfile(1.25, responseForSphere(1.7 * Unit.G_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
		case F_CLASS_STAR:
			return new SensorSignalResponseProfile(3.0, responseForSphere(1.3 * Unit.G_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
		case G_CLASS_STAR: //384.6e24 J/s
			return new SensorSignalResponseProfile(4.83, responseForSphere(1 * Unit.G_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
		case K_CLASS_STAR:
			return new SensorSignalResponseProfile(6.5, responseForSphere(0.7 * Unit.G_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
		case M_CLASS_STAR:
			return new SensorSignalResponseProfile(14, responseForSphere(0.3 * Unit.G_STAR_RADIUS, 1.0), 1000.0, 100.0, 0.0);
		
			
		case SUPERNOVA: // Brighter than 10 billion suns
			return new SensorSignalResponseProfile(-17.65, 0.0, 0.0, 100.0, 0.0);
		
			
		}
		
		return null;
	}
	
	private static double responseForSphere(double radius, double reflectanceFactor) {
		return 4 * Math.PI * Math.pow(radius, 2.0) * reflectanceFactor;
	}

}
