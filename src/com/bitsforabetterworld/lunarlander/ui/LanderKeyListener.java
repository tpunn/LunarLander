package com.bitsforabetterworld.lunarlander.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.bitsforabetterworld.lunarlander.Lander;
import com.bitsforabetterworld.lunarlander.Lander.RotationDirection;

public class LanderKeyListener implements KeyListener {
	
	private Lander m_lander;

	public LanderKeyListener(Lander lander) {
		m_lander = lander;
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
			m_lander.turnOnThruster();
			break;
		case 'a':
			m_lander.turnOnRotationMotor(RotationDirection.CounterClockwise);
			break;
		case 'd':
			m_lander.turnOnRotationMotor(RotationDirection.Clockwise);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'w':
			m_lander.turnOffThruster();
			break;
		case 'a':
			m_lander.turnOffRotationMotor();
			break;
		case 'd':
			m_lander.turnOffRotationMotor();
			break;
		}
		
	}
}