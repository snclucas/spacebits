package org.spacebits.software;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.spacebits.Configuration;
import org.spacebits.components.SpacecraftBusComponent;
import org.spacebits.components.TypeInfo;
import org.spacebits.components.computers.SystemComputer;
import org.spacebits.components.sensors.PositioningSensor;
import org.spacebits.components.sensors.Sensor;
import org.spacebits.components.sensors.SensorResult;
import org.spacebits.navigation.NavigationInterface;
import org.spacebits.universe.Coordinates;

public class NavigationSoftware extends AbstractSoftware implements Software, NavigationInterface {
	private static MathContext mc = new MathContext(Configuration.precision, RoundingMode.HALF_UP);
	
	public static TypeInfo typeID = new TypeInfo("NavigationSoftware");

	Map<Integer, Sensor> sensors;
	List<SensorResult> sensorResults;
	
	public NavigationSoftware(String name) {
		super(name);
	}

	public NavigationSoftware(String name, SystemComputer computer) {
		super(name, computer);
		sensors = new HashMap<Integer, Sensor>();
		sensorResults = new ArrayList<SensorResult>();
		populateSensors();
	}
	
	
	@Override
	public TypeInfo getTypeId() {
		return typeID;
	}


	private void populateSensors() {
		List<Sensor> sensorList = getSensors();
		for(Sensor sensor : sensorList)
			sensors.put(sensor.getId(), sensor);
	}



	@Override
	public String getDescription() {
		return "Engine management";
	}

	@Override
	public String toString() {
		return getDescription() + " software";
	}


	public List<SensorResult> scanAll(){
		List<Sensor> sensors = getSensors();
		for(Sensor sensor : sensors) 
			sensorResults.addAll(scan(sensor.getId()));
		return sensorResults;
	}


	public List<SensorResult> scan(int sensorId){
		Sensor sensor = sensors.get(sensorId);
		List<SensorResult> sensorResults = sensor.passiveScan(computer.getSpacecraftBus().getId(), 10.0, sensor.getSensorProfile());
		sensorResults.addAll(sensorResults);
		return sensorResults;
	}


	private List<Sensor> getSensors() {
		List<SpacecraftBusComponent> components = computer.findBusComponent(Sensor.categoryID);
		List<Sensor> sensors = new ArrayList<Sensor>();
		for(SpacecraftBusComponent sensor : components)
			sensors.add((Sensor)sensor);
		return sensors;
	}

	@Override
	public void getVectorToCoordinates(Coordinates coordinates) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Coordinates getSpacecraftLocation() {
		if(hasPositioningSensors() > 0)
			return new Coordinates();
		List<Coordinates> coordinates = new ArrayList<Coordinates>();
		for(Sensor sensor : getSensors())
			if(sensor instanceof PositioningSensor)
				coordinates.add(((PositioningSensor)sensor).calculatePosition());	
		return processPositioningSensorData(coordinates);
	}
	
	
	
	private Coordinates processPositioningSensorData(List<Coordinates> coordinates) {
		BigDecimal X = BigDecimal.ZERO;
		BigDecimal Y = BigDecimal.ZERO;
		BigDecimal Z = BigDecimal.ZERO;
		for(int i = 0; i<=coordinates.size();i++) {
			X = X.add(coordinates.get(i).get(0));
			Y = Y.add(coordinates.get(i).get(1));
			Z = Z.add(coordinates.get(i).get(2));
		}
		BigDecimal numberOfPositionsReturned = new BigDecimal(coordinates.size());
		X = X.divide(numberOfPositionsReturned, mc);
		Y = Y.divide(numberOfPositionsReturned, mc);
		Z = Z.divide(numberOfPositionsReturned, mc);
		return new Coordinates(X, Y, Z);
	}
	
	
	public int hasPositioningSensors() {
		int cnt = 0;
		for(Sensor sensor : getSensors())
			if(sensor instanceof PositioningSensor)
				cnt++;
		return cnt;
	}


}