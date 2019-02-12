package com.magenic.masters.oop.application;

import com.magenic.masters.oop.models.car.Car;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class CarCommands {

	public final Map<Integer, String> commandsMap = new TreeMap<>();
	
	private final String START = "START";
	private final String IDLE = "IDLE";
	private final String FREEWHEELING = "FREEWHEELING";
	private final String PRESS_GAS_PEDAL = "PRESS_GAS_PEDAL";
	private final String RELEASE_GAS_PEDAL = "RELEASE_GAS_PEDAL";
	private final String PRESS_BREAK_PEDAL = "PRESS_BREAK_PEDAL";
	private final String RELEASE_BREAK_PEDAL = "RELEASE_BREAK_PEDAL";
	private final String STOP = "STOP";
	
	public Car car;

	public CarCommands(Car car) {
		this.car = car;
		int ctr = 0;
		commandsMap.put(++ctr, START);
		commandsMap.put(++ctr, PRESS_GAS_PEDAL);
		commandsMap.put(++ctr, RELEASE_GAS_PEDAL);
		commandsMap.put(++ctr, PRESS_BREAK_PEDAL);
		commandsMap.put(++ctr, RELEASE_BREAK_PEDAL);
		commandsMap.put(++ctr, IDLE);
		commandsMap.put(++ctr, FREEWHEELING); 
		commandsMap.put(++ctr, STOP);
	}
	
	public boolean isApplicable() {
		return car != null;
	}
	
	public boolean isValid(int commandNumber) {
		return commandsMap.containsKey(commandNumber);
	}
	
	public String getCommand(int commandNumber) {
		String command = null;
		if(isValid(commandNumber)) {
			command = commandsMap.get(commandNumber);
		} else {
			System.out.println("Invalid Command!\n");
		}
		
		return command;
	}
	
	public void displayCommands() {
		for(Entry<Integer, String> entry : commandsMap.entrySet()) {
			System.out.println(String.format("%d - %s", entry.getKey(), entry.getValue()));
		}
		System.out.println();
	}
	
	public boolean askForDuration(int commandNumber) {
		boolean needsDuration = false;
		String command = getCommand(commandNumber);
		if(command != null) {
			switch(command) {
				case PRESS_GAS_PEDAL :
				case PRESS_BREAK_PEDAL :
				case IDLE :
				case FREEWHEELING :
					needsDuration = true;
					break;
				default:
					break;
			}
		}

		return needsDuration;
	}
	
	public void executeCommand(Integer commandNumber, Integer duration) {
		String command = getCommand(commandNumber);
		switch(command) {
			case START : 
				car.start();
				break;
			case PRESS_GAS_PEDAL : 
				car.pressGas(duration);
				break;
			case RELEASE_GAS_PEDAL : 
				car.relesaseGas();
				break;
			case PRESS_BREAK_PEDAL :
				car.brake(duration);
				break;
			case RELEASE_BREAK_PEDAL : 
				car.relesaseBreak();
				break;
			case IDLE : 
				// TODO do implementation
				// TODO we have no timing/duration for the idle.... computes fuel consumed during idle but no duration yet
				break;
			case FREEWHEELING : 
				// TODO do implementation
				// TODO we have no timing/duration for the free wheel.... computes fuel consumed during freewheel but no duration yet
				break;
			case STOP : 
				car.stop();
				break;
			default: 
				System.out.println("Invalid Command!");
				break;
		}
	}
}
