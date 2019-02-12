package com.magenic.masters.oop.models.car;

import com.magenic.masters.oop.models.Engine;
import com.magenic.masters.oop.models.Engine.CarStatus;
import com.magenic.masters.oop.models.FuelTank;
import com.magenic.masters.oop.models.PanelDisplay;
import com.magenic.masters.oop.models.Timer;
import com.magenic.masters.oop.models.pedal.Pedal;
import com.magenic.masters.oop.monitor.FuelMonitor;
import com.magenic.masters.oop.monitor.SpeedMonitor;

public class SimpleCar extends Car {
	
	public SimpleCar(Engine engine, Pedal gasPedal, Pedal brakePedal, Timer timer, PanelDisplay display, 
			FuelTank fuelTank, FuelMonitor fuelMonitor, SpeedMonitor speedMonitor) {
		super(engine, gasPedal, brakePedal, timer, display, fuelTank, fuelMonitor, speedMonitor);
	}
	

	@Override
	public void start() {
		System.out.println("-- car.start() --" + "\n");
		// TODO Auto-generated method stub
		if (getEngine().getStatus() == CarStatus.STOPPED) {
			getEngine().setStatus(CarStatus.STARTED);
			double newFuelLevel = getEngine().consume(getSpeedMonitor().getSpeed(), getFuelTank().getFuelLevel());
			getFuelTank().setFuelLevel(newFuelLevel);
			displayInfo();
		} else {
			System.out.println("Car is already started");
		}
	}

	@Override
	public void stop() {
		System.out.println("-- car.stop() --" + "\n");
		// TODO Auto-generated method stub
		if (getEngine().getStatus() != CarStatus.STOPPED) {
			if (getSpeedMonitor().getSpeed() == 0) {
				getEngine().setStatus(CarStatus.STOPPED);
				displayInfo();
			} else {
				System.out.println("Car cannot stop while it is running");
			}
		} else {
			System.out.println("Car is already stopped");
		}

	}

	@Override
	public void brake(int timeElapsed) {
		System.out.println("-- car.brake() --" + "\n");
		pressPedal(CarPedal.BRAKE, timeElapsed);
	}

	@Override
	public void pressGas(int timeElapsed) {
		System.out.println("-- car.pressGas() --" + "\n");
		// TODO Auto-generated method stub
		// every accelerate call when fuel is 0 will freewheel
		FuelLevel currentFuelLevel = getfuelMonitor().checkFuelLevel(getFuelTank().getFuelLevel());
		switch(currentFuelLevel) {
			case EMPTY:
				System.out.println("Fuel tank is empty. Car unable to accelerate. Begin freewheeling...");
				freewheel();
				break;
			case IN_RESERVE:
				System.out.println("Car running in reserve");
				pressPedal(CarPedal.GAS, timeElapsed); // running in reserve should still consume gas and increase speed
				break;
			case NORMAL:
				pressPedal(CarPedal.GAS, timeElapsed);
				break;
		}
	
	}

	@Override
	public void relesaseBreak() {
		releasePedal(CarPedal.BRAKE);
	}

	@Override
	public void relesaseGas() {
		releasePedal(CarPedal.GAS);
	}

	@Override
	public void displayInfo() {
		// TODO Auto-generated method stub
		getDisplay().display(this, getSpeedMonitor().getSpeed(), getFuelTank().getFuelLevel());
	}

	@Override
	public void releasePedal(CarPedal pedal) {
		// TODO Auto-generated method stub
		System.out.print("-- car.releasePedal() = ");
		if (pedal == CarPedal.GAS) {
			System.out.println("GAS --" + "\n");
			getGasPedal().release(getSpeedMonitor());
		} else {
			System.out.println("BRAKE --" + "\n");
			getBrakePedal().release(getSpeedMonitor());
		}

		// check if both pedals are released
		if (areBothPedalsReleased()) {
			System.out.println("FREEWHEELING");
			freewheel();
		}
	}

	@Override
	protected void freewheel() {
		// TODO Auto-generated method stub
		// compute current speed
		int currentSpeed;

		// freewheeling will reduce speed by 1 km/h
		currentSpeed = getSpeedMonitor().getSpeed() - 1;
		if (currentSpeed <= 0) {
			currentSpeed = 0;
		}
		getSpeedMonitor().setSpeed(currentSpeed);

		// compute consumption
		double fuelLevel = getEngine().consume(getSpeedMonitor().getSpeed(), getFuelTank().getFuelLevel());
		getFuelTank().setFuelLevel(fuelLevel);
	}


	@Override
	protected void pressPedal(CarPedal pedal, int timeElapsed) {
		// TODO Auto-generated method stub
		System.out.print("-- car.pressPedal() = ");
		if (pedal == CarPedal.GAS) {
			if (!getBrakePedal().isPressed()) {
				if (timeElapsed > getTimer().timeElapsed()) {
					getGasPedal().press(getEngine(), getSpeedMonitor(), getfuelMonitor(), getFuelTank(), getTimer(), timeElapsed);
				} else {
					System.out.println("Time cannot be less than or equal to previous time");
				}
			} else {
				System.out.println("Release the brake first");
			}
		} else {
			if (!getGasPedal().isPressed()) {
				if (timeElapsed > getTimer().timeElapsed()) {
					getBrakePedal().press(getEngine(), getSpeedMonitor(), getfuelMonitor(), getFuelTank(), getTimer(), timeElapsed);
				} else {
					System.out.println("Time cannot be less than or equal to previous time");
				}
			} else {
				System.out.println("Release the gas first");
			}
		}

	}

}
