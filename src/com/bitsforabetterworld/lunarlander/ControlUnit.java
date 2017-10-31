package com.bitsforabetterworld.lunarlander;

interface ControlUnit {
	public Command getCommand(WorldView worldView);
}
