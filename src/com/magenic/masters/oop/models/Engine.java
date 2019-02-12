package com.magenic.masters.oop.models;

import com.magenic.masters.oop.utils.NumberUtils;

public class Engine {
	private CarStatus status = CarStatus.STOPPED;
	
	public enum CarStatus {
		STARTED,
		STOPPED,
		IDLE,
		RUNNING
	}
	
	public double consume(int speed, double fuelLevel) {
		if(status == CarStatus.STARTED) {
			fuelLevel -= 0.001;
		}
		else if(status == CarStatus.IDLE) {
			fuelLevel -= 0.002;
		}
		else if(status == CarStatus.RUNNING) {
			if(NumberUtils.isBetween(speed, 1, 60)) {
				fuelLevel -= 0.02;
			}
			else if(NumberUtils.isBetween(speed, 61, 100)) {
				fuelLevel -= 0.014;
			}
			else if(NumberUtils.isBetween(speed, 101, 140)) {
				fuelLevel -= 0.025;
			}
			else if(NumberUtils.isBetween(speed, 141, 180)) {
				fuelLevel -= 0.030;
			}
		}
		
		if(fuelLevel <= 0) {
			return 0;
		}

		return fuelLevel;
	}
	
	public CarStatus getStatus() {
		return status;
	}
	
	public void setStatus(CarStatus status) {
		this.status = status;
	}
}
