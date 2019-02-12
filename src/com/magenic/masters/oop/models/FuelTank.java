package com.magenic.masters.oop.models;

public class FuelTank {
	//fuel in Liters
	private double fuelLevel = 20;
	private static final double MAX_FUEL_LEVEL = 60;
	
	public double getFuelLevel() {
		return fuelLevel;
	}
	public void setFuelLevel(double fuelLevel) {
		this.fuelLevel = fuelLevel;
	}
	
}
