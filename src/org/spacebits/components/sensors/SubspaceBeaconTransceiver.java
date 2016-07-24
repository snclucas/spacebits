package org.spacebits.components.sensors;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.spacebits.Configuration;
import org.spacebits.components.TypeInfo;
import org.spacebits.navigation.BeaconSignal;
import org.spacebits.physics.Physics;
import org.spacebits.physics.Unit;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.status.SystemStatus;
import org.spacebits.universe.Coordinates;
import org.spacebits.utils.Utils;
import org.spacebits.utils.math.DistanceSolver;

public class SubspaceBeaconTransceiver extends AbstractSensor implements PositioningSensor {

	public static TypeInfo typeID = new TypeInfo("SubspaceBeaconTransceiver");
	
	private static MathContext context = new MathContext(120, RoundingMode.HALF_UP);

	protected List<BeaconSignal> beaconSignals;
	SensorResponseMediator sensorResponseMediator;

	public SubspaceBeaconTransceiver(String name,
			BusComponentSpecification busResourceSpecification,
			SensorProfile sensorProfile) {
		super(name, busResourceSpecification, sensorProfile);

		beaconSignals = new ArrayList<BeaconSignal>();
		sensorResponseMediator = Configuration.getSensorResponseMediator();
	}

	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}


	@Override
	public List<SensorResult> passiveScan(int spacecraftIdent, double duration, SensorProfile sensorProfile) {
		return super.passiveScan(spacecraftIdent, duration, sensorProfile);
	}




	@Override
	public double getSensorGain() {
		return 1;
	}


	@Override
	public double getOperatingPower() {
		return getNominalPower();
	}


	@Override
	public double getOperatingCPUThroughput() {
		return getNominalCPUThroughput();
	}



	@Override
	public SystemStatus online() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override 
	public Coordinates calculatePosition() {
		
	//	sensorResponseMediator.passiveScan(66, 2, 1, Sensor.SUBSPACE_RESONANCE);

		if(beaconSignals.size() < 3 )
			return new Coordinates(Coordinates.NOT_KNOWN);

		BigDecimal[] distances = new BigDecimal[beaconSignals.size()];

		int cntr = 0;
		for(BeaconSignal bSignal : beaconSignals) {
			distances[cntr] = Physics.subspaceSignalDispersionToDistance(new BigDecimal(bSignal.getSignalDispersion()));
			cntr++;
		}

		final BigDecimal x1 = getBeaconCoordinate(0, 0, Unit.Pc);
		final BigDecimal y1 = getBeaconCoordinate(0, 1, Unit.Pc);
		final BigDecimal z1 = getBeaconCoordinate(0, 2, Unit.Pc);

		final BigDecimal x2 = getBeaconCoordinate(1, 0, Unit.Pc);
		final BigDecimal y2 = getBeaconCoordinate(1, 1, Unit.Pc);
		final BigDecimal z2 = getBeaconCoordinate(1, 2, Unit.Pc);

		final BigDecimal x3 = getBeaconCoordinate(2, 0, Unit.Pc);
		final BigDecimal y3 = getBeaconCoordinate(2, 1, Unit.Pc);
		final BigDecimal z3 = getBeaconCoordinate(2, 2, Unit.Pc);


		final BigDecimal R1 = distances[0].abs().divide(new BigDecimal(Unit.Pc), context);
		final BigDecimal R2 = distances[1].abs().divide(new BigDecimal(Unit.Pc), context);	
		final BigDecimal R3 = distances[2].abs().divide(new BigDecimal(Unit.Pc), context);

		double[] pos = DistanceSolver.solve(1e-10, x1.doubleValue(), y1.doubleValue(), z1.doubleValue(), R1.doubleValue(), 
				x2.doubleValue(), y2.doubleValue(), z2.doubleValue(), R2.doubleValue()
				, x3.doubleValue(), y3.doubleValue(), z3.doubleValue(), R3.doubleValue());

		//	BigDecimal[] posBig = DistanceSolver.solve(new BigDecimal(1e-12), x1, y1, z1, R1, 
		//			x2, y2, z2, R2, x3, y3, z3, R3);

		return new Coordinates(Utils.doubleArrayToBigDecimalArray(pos));
	}


	private BigDecimal getBeaconCoordinate(int beacon, int index, double unit) {
		return beaconSignals.get(beacon).getBeacon().getCoordinates().get(index).divide(new BigDecimal(unit), context);
	}


	@Override
	public SystemStatus runDiagnostics(int level) {
		// TODO Auto-generated method stub
		return null;
	}



	public void activate(double duration) {





	}

	@Override
	public String describe() {
		return "Subspace beacon transciever: A device capable of detecting subspace harmonic distortions usually created by subspace beacons.";
	}



}


