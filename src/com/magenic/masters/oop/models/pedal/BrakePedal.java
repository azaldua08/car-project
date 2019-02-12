package com.magenic.masters.oop.models.pedal;

import com.magenic.masters.oop.models.Engine;
import com.magenic.masters.oop.models.Engine.CarStatus;
import com.magenic.masters.oop.models.FuelTank;
import com.magenic.masters.oop.models.Timer;
import com.magenic.masters.oop.monitor.FuelMonitor;
import com.magenic.masters.oop.monitor.SpeedMonitor;

public class BrakePedal extends Pedal {

	@Override
	public void press(Engine engine, SpeedMonitor speedMonitor, FuelMonitor fuelMonitor, FuelTank fuelTank,
			Timer timer, int timeElapsed) {
		System.out.println("BRAKE PRESSED");
		setPressed(true);
		// TODO Auto-generated method stub
		if(engine.getStatus() == CarStatus.RUNNING) {
			speedMonitor.setMaxDeceleration(speedMonitor.getMaxDeceleration() + speedMonitor.decelerationIncrement());
			
			int timeDiff = timeElapsed - timer.timeElapsed();
			speedMonitor.computeSpeedDeceleration(engine, timeDiff);
			//set new time
			timer.setTimeElapsed(timeElapsed);
			
			
			
			//compute consumption
			fuelMonitor.computeConsumption(engine, speedMonitor, fuelTank);
		}
		
	}
	
	@Override
	public void release(SpeedMonitor speedMonitor) {
		// retain the same max deceleration during last decelerate call
		// decrement by 15 during release so that the next decelerate call will retain the current max
		speedMonitor.setMaxDeceleration(speedMonitor.getMaxDeceleration() - speedMonitor.decelerationIncrement());
		super.release(speedMonitor);
		System.out.println("BRAKE RELEASED");
	}

}
