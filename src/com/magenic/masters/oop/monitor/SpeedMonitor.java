package com.magenic.masters.oop.monitor;

import com.magenic.masters.oop.models.Engine;
import com.magenic.masters.oop.models.Engine.CarStatus;

public class SpeedMonitor {

	// max speed in km/h
	private int speed;
	private int speedIncrement = 3;
	private int brakeIncrement = 5;
	protected static final int MAX_SPEED = 180;

	// max acceleration in km/h
	private int maxAcceleration = 0;
	private int accelerationIncrement = 15;

	// max deceleration in km/h
	private int maxDeceleration = 0;
	private int decelerationIncrement = 20;

	public void computeSpeedAcceleration(Engine engine, int timeDiff) {
		this.speed = computeSpeedAcceleration(timeDiff);
		
		if(this.speed > MAX_SPEED) {
			this.speed = MAX_SPEED;
		}
	}
	
	public int computeSpeedAcceleration(int timeDiff) {
		int currentSpeed = speed + timeDiff * speedIncrement;
		
		if (currentSpeed > maxAcceleration) {
			currentSpeed = maxAcceleration;
		}
		
		return currentSpeed;
	}

	public void computeSpeedDeceleration(Engine engine, int timeDiff) {
		int deceleration = timeDiff * brakeIncrement;
		deceleration = deceleration > maxDeceleration ? maxDeceleration : deceleration;

		int currentSpeed = speed - deceleration;
		
		if (currentSpeed <= 0) {
			currentSpeed = 0;
			engine.setStatus(CarStatus.IDLE);
		}

		this.speed = currentSpeed;
	}

	public int computeTimeDifference(int currentTime, int previousTime) {
		return currentTime - previousTime;
	}

	public int computeMaxAcceleration(int maxAcceleration, int accelerationIncrement) {
		return maxAcceleration + accelerationIncrement;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getMaxAcceleration() {
		return maxAcceleration;
	}

	public void setMaxAcceleration(int maxAcceleration) {
		this.maxAcceleration = maxAcceleration;
	}

	public int getMaxDeceleration() {
		return maxDeceleration;
	}

	public void setMaxDeceleration(int maxDeceleration) {
		this.maxDeceleration = maxDeceleration;
	}

	public int speedIncrement() {
		return speedIncrement;
	}

	public int brakeIncrement() {
		return brakeIncrement;
	}

	public int accelerationIncrement() {
		return accelerationIncrement;
	}

	public int decelerationIncrement() {
		return decelerationIncrement;
	}
}
