package com.magenic.masters.oop.factory;

import com.magenic.masters.oop.models.Engine;
import com.magenic.masters.oop.models.FuelTank;
import com.magenic.masters.oop.models.PanelDisplay;
import com.magenic.masters.oop.models.Timer;
import com.magenic.masters.oop.models.car.Car;
import com.magenic.masters.oop.models.car.SimpleCar;
import com.magenic.masters.oop.models.pedal.BrakePedal;
import com.magenic.masters.oop.models.pedal.GasPedal;
import com.magenic.masters.oop.models.pedal.Pedal;
import com.magenic.masters.oop.monitor.FuelMonitor;
import com.magenic.masters.oop.monitor.SpeedMonitor;

public class SimpleCarFactory {
	public Car create() {
		
		Engine engine = new Engine();
		PanelDisplay display = new PanelDisplay();
		Pedal gasPedal = new GasPedal();
		Pedal brakePedal = new BrakePedal();
		Timer timer = new Timer(0);
		FuelMonitor fuelMonitor = new FuelMonitor();
		FuelTank fuelTank = new FuelTank();
		SpeedMonitor speedMonitor = new SpeedMonitor();
		Car car = new SimpleCar(engine, gasPedal, brakePedal, timer, display, fuelTank, fuelMonitor, speedMonitor);
		
		return car;
	}
}
