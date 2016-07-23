import org.braincycles.spacebits.components.propulsion.Engine;
import org.braincycles.spacebits.components.propulsion.EngineVector;
import org.braincycles.spacebits.components.propulsion.ThrustDriveInterface;
import org.braincycles.spacebits.software.PropulsionManagementSoftware;
import org.braincycles.spacebits.spacecraft.Spacecraft;
import org.braincycles.spacebits.status.SystemStatusMessage;

import asg.cliche.Command;
import asg.cliche.InputConverter;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;


public class EnginesShell implements ShellDependent{
	public String name;
	Spacecraft spacecraft;

	public EnginesShell(String name, Spacecraft spacecraft) {
		this.name = name;
		this.spacecraft = spacecraft;
	}

	@Command(name="count")
	public void enginecount() {

		System.out.println(spacecraft.getEngines().size());

	}

	@Command(name="list")
	public void listengines() {
		for(Engine engine : spacecraft.getEngines())
			System.out.println(engine.getName() + " [" + engine.getId() + "] " + engine.getTypeId().typeIdString);
	}

	private Shell theShell;

	public void cliSetShell(Shell theShell) {
		this.theShell = theShell;
	}

	
	@Command
	public SystemStatusMessage callDrive(double powerLevel, int engineIdent) {
		if(spacecraft.getSystemComputer().hasSoftware(PropulsionManagementSoftware.typeID) == false)
			System.out.println("Spacecraft engine does not have engine management software loaded");
			
		ThrustDriveInterface engineInterface = (ThrustDriveInterface)spacecraft.getSystemComputer().getSoftware(PropulsionManagementSoftware.typeID);
		SystemStatusMessage message = engineInterface.callDrive(powerLevel, engineIdent);
	    return message;
	}
	
	
	@Command
	public SystemStatusMessage callDrive(double powerLevel) {
		ThrustDriveInterface engineInterface = (ThrustDriveInterface)spacecraft.getSystemComputer().getSoftware(PropulsionManagementSoftware.typeID);
		return engineInterface.callDrive(powerLevel);
	}
	

	@Command
	public SystemStatusMessage callStop(int engineIdent) {
		return callDrive(0.0, engineIdent);
	}
	

	@Command
	public SystemStatusMessage callVector(double rollAxisComponent, double pitchAxisCompoent, double yawAxisComponent, int engineIdent) {
		if(spacecraft.getSystemComputer().hasSoftware(PropulsionManagementSoftware.typeID) == false)
			System.out.println("Spacecraft engine does not have engine management software loaded");
			
		ThrustDriveInterface engineInterface = (ThrustDriveInterface)spacecraft.getSystemComputer().getSoftware(PropulsionManagementSoftware.typeID);
		SystemStatusMessage message = engineInterface.callVector(new EngineVector(rollAxisComponent, pitchAxisCompoent, yawAxisComponent), engineIdent);
	    return message;
	}
	
	
	public static final InputConverter[] CLI_INPUT_CONVERTERS = {
	    new InputConverter() {
	        public Object convertInput(String original, Class toClass)
	                throws Exception {

	            if (toClass.equals(EngineVector.class)) {
	                return new StringBuilder(original);
	            } else {
	                return null;
	            }
	        }
	    },
	};


}
