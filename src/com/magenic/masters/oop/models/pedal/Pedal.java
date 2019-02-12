package com.magenic.masters.oop.models.pedal;

import com.magenic.masters.oop.models.Engine;
import com.magenic.masters.oop.models.FuelTank;
import com.magenic.masters.oop.models.Timer;
import com.magenic.masters.oop.monitor.FuelMonitor;
import com.magenic.masters.oop.monitor.SpeedMonitor;

public abstract class Pedal {
	private boolean pressed;
	
	public abstract void press(Engine engine, SpeedMonitor speedMonitor, FuelMonitor fuelMonitor, 
			FuelTank fuelTank, Timer timer, int timeElapsed);
	
	public void release(SpeedMonitor speedMonitor) {
		this.pressed = false;
	}
	
	public boolean isPressed() {
		return this.pressed;
	}
	
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}
}
