package com.bitsforabetterworld.lunarlander;
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
                display.createAndShowGUI();
            }
		});
		System.err.println("Hey there!");
	}

}
