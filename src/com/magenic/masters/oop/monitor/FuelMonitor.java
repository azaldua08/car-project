package com.magenic.masters.oop.monitor;

import com.magenic.masters.oop.models.Engine;
import com.magenic.masters.oop.models.FuelTank;
import com.magenic.masters.oop.models.car.Car.FuelLevel;

public class FuelMonitor {
	public FuelLevel checkFuelLevel(double fuelLevel) {
		if (fuelLevel == 0) {
			return FuelLevel.EMPTY;
		} 
		else if (fuelLevel < 5) {
			return FuelLevel.IN_RESERVE;
		}
		else {
			return FuelLevel.NORMAL;
		}
	}
	
	public void computeConsumption(Engine engine, SpeedMonitor speedMonitor, FuelTank fuelTank) {
		double fuelLevel = engine.consume(speedMonitor.getSpeed(), fuelTank.getFuelLevel());
		fuelTank.setFuelLevel(fuelLevel);
	}
}
