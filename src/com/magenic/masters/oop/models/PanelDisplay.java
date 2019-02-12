package com.magenic.masters.oop.models;

import com.magenic.masters.oop.models.car.Car;

public class PanelDisplay {

	public void display(Car car, int speed, double fuelLevel) {

		System.out.println("SPEED = " + speed + " km/h");
		System.out.println("FUEL = " + fuelLevel + "L" + "\n");

	}
}
