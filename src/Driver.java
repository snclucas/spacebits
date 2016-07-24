

import org.spacebits.algorithm.SimpleRadioFrequencyPropagationModel;
import org.spacebits.components.comms.RadioCommunicator;
import org.spacebits.components.propulsion.Engine;
import org.spacebits.components.propulsion.ThrustDriveInterface;
import org.spacebits.software.PropulsionManagementSoftware;
import org.spacebits.spacecraft.BusComponentSpecification;
import org.spacebits.spacecraft.Spacecraft;
import org.spacebits.spacecraft.SpacecraftFactory;
import org.spacebits.status.SystemStatus;
import org.spacebits.universe.Universe;
import org.spacebits.universe.celestialobjects.Star;


public class Driver {
	
	
	public Driver() {
		
		
		BusComponentSpecification busSpec = new BusComponentSpecification();
		SimpleRadioFrequencyPropagationModel model = new SimpleRadioFrequencyPropagationModel("ddd");
		RadioCommunicator com = new RadioCommunicator("hfghfgh", busSpec, model);
		
		
		
		Universe universe = Universe.getUniverse();
		
		universe.getLocationsByType(Star.typeID);
		
		Spacecraft simpleSpacecraft = SpacecraftFactory.getSpacecraft(SpacecraftFactory.SHUTTLE);
		SystemStatus status = simpleSpacecraft.online();
		
		//for(SystemStatusMessage message : status.getMessages())
			//System.out.println(message.getStatusProvider() + " : " +  message.getMessage());
		
		if(status.isOK() == false) {
			System.out.println("There was a problem onlining your spacecraft");
			System.exit(-1);
		}
		
		
		//List<SystemStatusMessage> statusMessages = simpleSpacecraft.getSystemComputer().getSystemMessages();
		
		//Message message = new SystemMessage(null, null,"Hello", 34.3);
		//simpleSpacecraft.getSystemComputer().getMessagingSystem().broadcastMessage(message);
		
		//Read system status messages
		
		//for(SystemStatusMessage statuses : statusMessages) {
		//	System.out.println(statuses.toString());
		//}
		
		//int ident = simpleSpacecraft.getIdentifier();
		
		System.out.println(simpleSpacecraft.getTotalMassOfSpacecraftBusComponents());
		
		Engine engine = (Engine) simpleSpacecraft.getSystemComputer().findBusComponent(Engine.categoryID).get(0);
		
		System.out.println(simpleSpacecraft.getCurrentPowerRequirement() + " " + engine.getOperatingPower());
		
		
		
		ThrustDriveInterface engineInterface = ((PropulsionManagementSoftware)simpleSpacecraft.getSystemComputer().getSoftware(PropulsionManagementSoftware.typeID));
		
		engineInterface.callDrive(70, engine.getId());
		
		engine.execute();
		
		
		System.out.println(simpleSpacecraft.getCurrentPowerRequirement() + " " + engine.getOperatingPower());
		
	}
	
	
	
	
	
	
	
	public static void main(String args[]) {
		new Driver();
	}
	
	

}
