package org.spacebits.components.sensors;

import java.math.BigDecimal;

public interface SignalPropagationModel {

	double getSignal(double initalPower, BigDecimal distance);

}
