package com.bitsforabetterworld.lunarlander;
import java.io.IOException;
import java.util.EnumSet;

import javax.swing.SwingUtilities;

import com.bitsforabetterworld.lunarlander.ui.Display;
import com.bitsforabetterworld.lunarlander.ui.LanderKeyListener;

// TODO: Make multiple levels
// TODO: Points for fuel left, successfully landing on pad
public class LanderLevel {
	
	public static void main(String[] args) {
		final Lander lander = new Lander.Builder()
				.x(750.0)
				.y(900.0)
				.thrusterAcceleration(4.0)
				.gravityAcceleration(-1.0)
				.fuel(10.0)
				.build();
		final Display display = new Display(lander);
		final LanderKeyListener landerKeyListener = new LanderKeyListener();
		final Control control = new TeleopControl(landerKeyListener);
		final Control autonomousControl = new AutonomousControl();
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	try {
            		display.createAndShowGUI(landerKeyListener);
            	}
            	catch (IOException exp) {
            		System.err.println("Exception: "+exp);
            		exp.printStackTrace(System.err);
            	}
            }
		});
		try {
			long lastUpdateNanos = System.nanoTime();
			while (!lander.isLanded()) {
				Thread.sleep(60L);
        		long now = System.nanoTime();
        		if (lastUpdateNanos < now) {
        			double dtSeconds = ((double)(now - lastUpdateNanos)) / 1000000000.0;
        			EnumSet<Command> commands = control.getCommand(lander.getPosition(), lander.getVelocity());
        			lander.clockTick(dtSeconds, commands);
        		}
        		lastUpdateNanos = now;
				
				SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		            	display.update();
		            }
				});
			}
		}
		catch (InterruptedException ex) {
			System.err.println("Interrupted");
		}
	}

}
