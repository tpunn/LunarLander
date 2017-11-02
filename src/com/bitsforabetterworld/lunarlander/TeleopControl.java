package com.bitsforabetterworld.lunarlander;

import java.util.EnumSet;

import com.bitsforabetterworld.lunarlander.ui.LanderKeyListener;
import com.bitsforabetterworld.lunarlander.ui.LanderKeyListener.RotationDirection;

public class TeleopControl implements Control {
	
	private final LanderKeyListener m_listener;
	
	public TeleopControl(LanderKeyListener listener) {
		this.m_listener = listener;
	}
	
	
	public EnumSet<Command> getCommand(Position position, Velocity velocity) {
		Command thrustCommand = Command.None;
		Command rotateCommand = Command.None;
		if (m_listener.isThrusterOn()) {
			thrustCommand = Command.Thrust;
		}
		if (m_listener.isRotationMotorOn()) {
			if (m_listener.getRotationMotorDirection() == RotationDirection.Clockwise) {
				rotateCommand = Command.RollClockwise;
			}
			else {
				rotateCommand = Command.RollCounterclockwise;
			}
		}
		return EnumSet.of(thrustCommand, rotateCommand);
	}
}
