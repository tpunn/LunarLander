package com.bitsforabetterworld.lunarlander;

import java.util.EnumSet;

public class AutonomousControl implements Control {
	@Override
	public EnumSet<Command> getCommand(Position position, Velocity velocity) {
		Command thrustCommand = Command.None;
		Command rotateCommand = Command.None;
	

		//System.out.println(position.getY());
		//System.out.println(velocity.getDx());

		//thrustCommand = Command.Thrust;
		//rotateCommand = Command.RollClockwise;

		if (position.getTheta() > 0.2) {
			if (velocity.getDtheta() > -0.16)
				rotateCommand = Command.RollCounterclockwise;
		}
		else if (position.getTheta() < -1 * 0.2) {
			if (velocity.getDtheta() < 0.15)
				rotateCommand = Command.RollClockwise;
		}
		
		if (position.getY() < 180) {
			if (velocity.getDy() < -7.9)
				thrustCommand = Command.Thrust;
		}

		//
		// Use the Position and Velocity of the lander to decide what action (if any) to take.
		//
		// The goal is to have a successful landing, which means that your lander gets to a Y position of 0
		// with the following requirements:
		//
		// - Vertical velocity (dy) less than -8 m/s
		// - Horizontal velocity (dx) between -5 and 5 m/s
		// - Angle (theta) between -0.3 and 0.3 radians
		// - Change in angle (dtheta) between -0.2 and 0.2 radians/second
		//
		// Try to get a high score! You get points for finishing a level, for fuel left at the
		// end of a level, and for landing on the landing pad.
		return EnumSet.of(thrustCommand, rotateCommand);
	}
	
	@Override
	public void reset() {
		// If there's anything you need to clean up when we start a new level, clean it up here.
	}
}

