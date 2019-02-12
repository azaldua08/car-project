package com.magenic.masters.oop.models;

public class Timer {
	private int timeElapsed;
	
	public Timer(int timeElapsed) {
		this.timeElapsed = timeElapsed;
	}
	
	public int timeElapsed() {
		return this.timeElapsed;
	}
	
	public void setTimeElapsed(int timeElapsed) {
		this.timeElapsed = timeElapsed;
	}
}
