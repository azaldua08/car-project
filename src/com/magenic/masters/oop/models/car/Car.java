package com.magenic.masters.oop.models.car;

import com.magenic.masters.oop.models.Engine;
import com.magenic.masters.oop.models.FuelTank;
import com.magenic.masters.oop.models.PanelDisplay;
import com.magenic.masters.oop.models.Timer;
import com.magenic.masters.oop.models.pedal.Pedal;
import com.magenic.masters.oop.monitor.FuelMonitor;
import com.magenic.masters.oop.monitor.SpeedMonitor;

public abstract class Car {

	
	public enum CarPedal {
		GAS,
		BRAKE
	}
	
	public enum FuelLevel {
		NORMAL,
		IN_RESERVE,
		EMPTY
	}
	
	public Car(Engine engine, Pedal gasPedal, Pedal brakePedal, Timer timer, PanelDisplay display, 
			FuelTank fuelTank, FuelMonitor fuelMonitor, SpeedMonitor speedMonitor) {
		this.engine = engine;
		this.gasPedal = gasPedal;
		this.brakePedal = brakePedal;
		this.timer = timer;
		this.display = display;
		this.fuelMonitor = fuelMonitor;
		this.fuelTank = fuelTank;
		this.speedMonitor = speedMonitor;
	}
	
	//fuel tank
	private FuelTank fuelTank;

	//pedals
	private Pedal brakePedal;
	private Pedal gasPedal;
	
	//engine
	private Engine engine;
	
	//display
	private PanelDisplay display;
	
	//fuelMonitor
	private FuelMonitor fuelMonitor;
	
	//speedMonitor
	private SpeedMonitor speedMonitor;
	
	
	//timer
	private Timer timer;
	
	// behaviors
	public abstract void start();
	public abstract void stop();
	public abstract void pressGas(int timeElapsed);
	public abstract void brake(int timeElapsed);
	public abstract void relesaseGas();
	public abstract void relesaseBreak();
	public abstract void releasePedal(CarPedal pedal);
	protected abstract void pressPedal(CarPedal pedal, int timeElapsed);
	public abstract void displayInfo();
	protected abstract void freewheel();
	
	protected Pedal getBrakePedal() {
		return brakePedal;
	}
	protected void setBrakePedal(Pedal brakePedal) {
		this.brakePedal = brakePedal;
	}
	
	protected Pedal getGasPedal() {
		return gasPedal;
	}
	protected void setGasPedal(Pedal gasPedal) {
		this.gasPedal = gasPedal;
	}
	
	protected Timer getTimer() {
		return timer;
	}
	protected void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	protected Engine getEngine() {
		return engine;
	}
	protected void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	protected PanelDisplay getDisplay() {
		return display;
	}
	protected void setDisplay(PanelDisplay display) {
		this.display = display;
	}
	
	protected FuelTank getFuelTank() {
		return fuelTank;
	}
	
	protected FuelMonitor getfuelMonitor() {
		return fuelMonitor;
	}
	
	protected SpeedMonitor getSpeedMonitor() {
		return speedMonitor;
	}
	
	protected void setDisplay(FuelMonitor fuelMonitor) {
		this.fuelMonitor = fuelMonitor;
	}
	
	protected boolean areBothPedalsReleased() {
		return !gasPedal.isPressed() && !brakePedal.isPressed();
	}

}