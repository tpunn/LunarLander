package com.bitsforabetterworld.lunarlander;

public class TeleopControlUnit implements ControlUnit {

	public TeleopControlUnit() {
		
	}
	
	@Override
	public Command getCommand(WorldView worldView) {
		
		return Command.None;
	}

}
