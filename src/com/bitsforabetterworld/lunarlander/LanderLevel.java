package com.bitsforabetterworld.lunarlander;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.EnumSet;

import javax.swing.SwingUtilities;

import com.bitsforabetterworld.lunarlander.ui.Display;
import com.bitsforabetterworld.lunarlander.ui.LanderKeyListener;

// TODO: Make multiple levels
// TODO: Points for fuel left, successfully landing on pad
public class LanderLevel {
	
	private static Display display;
	private static Control control;
	private static Lander lander;
	private static int level = 0;
	private static final SecureRandom rand = new SecureRandom();
	public static void main(String[] args) {
		setupDisplay();
		nextLevel();
		runLoop();
	}

	
	
	public static void runLoop() {
		
		try {
			long lastUpdateNanos = System.nanoTime();
			while (lander != null) {
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

	public synchronized static void nextLevel() {
		double initialVelocity = 5.0 * level;
		double fuel = (12 - level) * 10;
		double initialDirection = rand.nextDouble() * 2.0 * Math.PI;
		double initialHeading = rand.nextDouble() * 2.0 * Math.PI;
		
		lander = new Lander.Builder()
				.x(750.0)
				.y(900.0)
				.thrusterAcceleration(5.0)
				.gravityAcceleration(-1.0)
				.fuel(fuel)
				.theta(initialHeading)
				.dx(initialVelocity * Math.sin(initialDirection))
				.dy(initialVelocity * Math.cos(initialDirection))
				.build();
		++level;
		display.setLander(lander);
	}
	
	public static void setupDisplay() {
		
		display = new Display();
		final LanderKeyListener landerKeyListener = new LanderKeyListener();
		final Control teleopControl = new TeleopControl(landerKeyListener);
		final Control autonomousControl = new AutonomousControl();
		control = teleopControl;
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
	}

}
