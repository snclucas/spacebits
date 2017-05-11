package org.spacebits.spacecraft;

public class BusComponentSpecification {
	
	private PhysicalSpecification physicalSpecification;
	private OperationalSpecification operationalSpecification;
	
	
	public BusComponentSpecification() {
		super();
		this.physicalSpecification = new PhysicalSpecification(0.0, 0.0);
		this.operationalSpecification = new OperationalSpecification(0.0, 0.0);
	}
	
	
	public BusComponentSpecification(PhysicalSpecification physicalSpecification, OperationalSpecification operationalSpecification) {
		super();
		this.physicalSpecification = physicalSpecification;
		this.operationalSpecification = operationalSpecification;
	}

	

	public double getLength() {
		return physicalSpecification.getLength();
	}
	
	
	public void setLength(double length) {
		physicalSpecification.setLength(length);
	}
	
	
	public double getWidth() {
		return physicalSpecification.getWidth();
	}
	
	
	public void setWidth(double width) {
		physicalSpecification.setWidth(width);
	}


	public double getHeight() {
		return physicalSpecification.getHeight();
	}
	
	
	public void setHeight(double height) {
		physicalSpecification.setHeight(height);
	}


	public double getMass() {
		return physicalSpecification.getMass();
	}
	
	
	public void setMass(double mass) {
		physicalSpecification.setMass(mass);
	}

	
	public double getVolume() {
		return physicalSpecification.getVolume();
	}
	
	
	public void setVolume(double volume) {
		physicalSpecification.setVolume(volume);
	}


	public double getNominalPower() {
		return operationalSpecification.getNominalPower();
	}
	
	public double getNominalPower(double unit) {
		return operationalSpecification.getNominalPower() / unit;
	}
	
	
	public void setNominalPower(double nominalPower) {
		operationalSpecification.setNominalPower(nominalPower);
	}


	public double getNominalCPUThroughout() {
		return operationalSpecification.getNominalCPUThroughout();
	}
	
	
	public void setNominalCPUThroughout(double nominalCPUThroughout) {
		operationalSpecification.setNominalCPUThroughout(nominalCPUThroughout);
	}


	public double getMaximumOperationalPower() {
		return operationalSpecification.getMaximumOperationalPower();
	}
	
	public double getMaximumOperationalPower(double unit) {
		return operationalSpecification.getMaximumOperationalPower() / unit;
	}
	
	
	public void setMaximumOperationalPower(double maximumOperationalPower) {
		operationalSpecification.setMaximumOperationalPower(maximumOperationalPower);
	}


	public double getMaximumOperationalCPUThroughput() {
		return operationalSpecification.getMaximumOperationalCPUThroughput();
	}
	
	
	public void setMaximumOperationalCPUThroughput(double maximumOperationalCPUThroughput) {
		operationalSpecification.setMaximumOperationalCPUThroughput(maximumOperationalCPUThroughput);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((operationalSpecification == null) ? 0
						: operationalSpecification.hashCode());
		result = prime
				* result
				+ ((physicalSpecification == null) ? 0 : physicalSpecification
						.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusComponentSpecification other = (BusComponentSpecification) obj;
		if (operationalSpecification == null) {
			if (other.operationalSpecification != null)
				return false;
		} else if (!operationalSpecification
				.equals(other.operationalSpecification))
			return false;
		if (physicalSpecification == null) {
			if (other.physicalSpecification != null)
				return false;
		} else if (!physicalSpecification.equals(other.physicalSpecification))
			return false;
		return true;
	}


	
}
