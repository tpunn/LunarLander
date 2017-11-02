package com.bitsforabetterworld.lunarlander;

import java.util.EnumSet;

public class AutonomousControl implements Control {
	public EnumSet<Command> getCommand(Position position, Velocity velocity) {
		Command thrustCommand = Command.None;
		Command rotateCommand = Command.None;
		
		return EnumSet.of(thrustCommand, rotateCommand);
	}
}

