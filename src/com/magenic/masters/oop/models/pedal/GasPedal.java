package com.magenic.masters.oop.models.pedal;

import com.magenic.masters.oop.models.Engine;
import com.magenic.masters.oop.models.Engine.CarStatus;
import com.magenic.masters.oop.models.FuelTank;
import com.magenic.masters.oop.models.Timer;
import com.magenic.masters.oop.monitor.FuelMonitor;
import com.magenic.masters.oop.monitor.SpeedMonitor;

public class GasPedal extends Pedal {

	@Override
	public void press(Engine engine, SpeedMonitor speedMonitor, FuelMonitor fuelMonitor, FuelTank fuelTank, 
			Timer timer, int timeElapsed) {
		// TODO Auto-generated method stub
		setPressed(true);
		System.out.println("GAS PRESSED");
		if (engine.getStatus() == CarStatus.STARTED) {
			engine.setStatus(CarStatus.RUNNING);
		}
		else if(engine.getStatus() == CarStatus.STOPPED){
			System.out.println("Can't accelerate while car is stopped. Start it first");
		}
		if(engine.getStatus() == CarStatus.RUNNING || engine.getStatus() == CarStatus.IDLE) {
			if(engine.getStatus() == CarStatus.IDLE) {
				engine.setStatus(CarStatus.RUNNING);
			}
			speedMonitor.setMaxAcceleration(speedMonitor.getMaxAcceleration() + speedMonitor.accelerationIncrement());
			
			//compute current speed
			int timeDiff = timeElapsed - timer.timeElapsed();
			speedMonitor.computeSpeedAcceleration(engine, timeDiff);
			
			//set new time
			timer.setTimeElapsed(timeElapsed);
			
			
			
			//compute consumption
			fuelMonitor.computeConsumption(engine, speedMonitor, fuelTank);

		}
	}
	
	@Override
	public void release(SpeedMonitor speedMonitor) {
		// retain the same max acceleration during last accelerate call
		// decrement by 15 during release so that the next accelerate call will retain the current max
		speedMonitor.setMaxAcceleration(speedMonitor.getMaxAcceleration() - speedMonitor.accelerationIncrement());
		super.release(speedMonitor);
		System.out.println("GAS RELEASED");
	}

}
