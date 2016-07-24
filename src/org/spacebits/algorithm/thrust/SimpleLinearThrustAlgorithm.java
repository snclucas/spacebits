package org.spacebits.algorithm.thrust;

import org.spacebits.components.TypeInfo;
import org.spacebits.components.comms.Status;
import org.spacebits.status.SystemStatus;

public class SimpleLinearThrustAlgorithm extends AbstractThrustAlgorithm {
	
	public static TypeInfo typeID = new TypeInfo("SimpleLinearThrustAlgorithm");

	public SimpleLinearThrustAlgorithm(String name) {
		super(name);
	}
	
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}
	

	@Override
	public double getNormalizedThrust(double powerLevel) {
		return powerLevel;
	}
	
	@Override
	public double getNormalizedPower(double powerLevel) {
		return powerLevel;
	}

	@Override
	public double getNormalizedCPU(double powerLevel) {
		return powerLevel;
	}
	
	@Override
	public String toString() {
		return "Simple Linear Thrust Model";
	}


	@Override
	public double[][] getNormalizedThrustProfile() {
		double[] profile = new double[10];
		for(int i = 0; i<10;i++)
			profile[i] =  1.0 / (10-i);
		return new double[][]{profile};
	}
	
	@Override
	public double[][] getNormalizedPowerProfile() {
		double[] profile = new double[10];
		for(int i = 0; i<10;i++)
			profile[i] =  1.0 / (10-i);
		return new double[][]{profile};
	}
	
	

	
	

	@Override
	public SystemStatus runDiagnostics(int level) {
		//XXX Add something better here
		SystemStatus systemStatus = new SystemStatus(this);
		if(validateModel())
			systemStatus.addSystemMessage("Diagnostic [" + getName() +"] OK", -1, Status.OK);
		else
			systemStatus.addSystemMessage("Diagnostic [" + getName() +"] PROBLEM", -1, Status.PROBLEM);
		return systemStatus;
	}
	
	
	private boolean validateModel() {
		return true;
	}
	
}
