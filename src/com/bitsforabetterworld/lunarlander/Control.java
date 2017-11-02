package com.bitsforabetterworld.lunarlander;

import java.util.EnumSet;

public interface Control {
	public EnumSet<Command> getCommand(Position position, Velocity velocity);
}
