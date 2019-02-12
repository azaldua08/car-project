package com.magenic.masters.oop.application;

import java.util.Scanner;

import com.magenic.masters.oop.factory.SimpleCarFactory;

public class CarProgram {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SimpleCarFactory carFactory = new SimpleCarFactory();
		CarCommands carCommands = new CarCommands(carFactory.create());
		while (carCommands.isApplicable()) {
			System.out.println("Please select the number of the command...");
			carCommands.displayCommands();
			int commandNumber = sc.nextInt();
			int duration = 0;
			if(carCommands.askForDuration(commandNumber)) {
				System.out.println(String.format("Please input the duration of the %s command...", carCommands.getCommand(commandNumber)));
				duration = sc.nextInt();
			}

			if(carCommands.isValid(commandNumber)) {
				carCommands.executeCommand(commandNumber, duration);
			}
		}
		sc.close();
	}
}
