package com.bitsforabetterworld.lunarlander.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LanderKeyListener implements KeyListener {
	public enum RotationDirection {
		Clockwise,
		CounterClockwise
	}
	// Is the thruster currently on?
	private boolean m_isThrusterOn = false;
	
	// Is the rotational motor currently on?
	private boolean m_isRotationMotorOn = false;
	
	private RotationDirection m_rotationMotorDirection = RotationDirection.Clockwise;

	
	public void turnOnThruster() {
		m_isThrusterOn = true;
	}
	public void turnOffThruster() {
		m_isThrusterOn = false;
	}
	
	public void turnOnRotationMotor(RotationDirection rotationDirection) {
		m_rotationMotorDirection = rotationDirection;
		m_isRotationMotorOn = true;
	}
	public void turnOffRotationMotor() {
		m_isRotationMotorOn = false;
	}

	
	public boolean isThrusterOn() {
		return m_isThrusterOn;
	}
	
	public boolean isRotationMotorOn() {
		return m_isRotationMotorOn;
	}
	
	public RotationDirection getRotationMotorDirection() {
		return m_rotationMotorDirection;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// Nothing to do here.
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar())
		{
		case 'w':
			turnOnThruster();
			break;
		case 'a':
			turnOnRotationMotor(RotationDirection.CounterClockwise);
			break;
		case 'd':
			turnOnRotationMotor(RotationDirection.Clockwise);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'w':
			turnOffThruster();
			break;
		case 'a':
			turnOffRotationMotor();
			break;
		case 'd':
			turnOffRotationMotor();
			break;
		}
		
	}
}
