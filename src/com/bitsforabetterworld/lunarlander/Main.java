package com.bitsforabetterworld.lunarlander;
import java.io.IOException;

import javax.swing.SwingUtilities;

import com.bitsforabetterworld.lunarlander.ui.Display;

public class Main {

	void mainEventLoop(SensorUnit sensorUnit, ControlUnit controlUnit) {
		boolean isDone = false;
		while (!isDone) {
			WorldView worldView = sensorUnit.getWorldView();
			Command command = controlUnit.getCommand(worldView);
		}
	}
	
	public static void main(String[] args) {
		final Display display = new Display();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	try {
            		display.createAndShowGUI();
            	}
            	catch (IOException exp) {
            		System.err.println("Exception: "+exp);
            		exp.printStackTrace(System.err);
            	}
            }
		});
		System.err.println("Hey there!");
	}

}
