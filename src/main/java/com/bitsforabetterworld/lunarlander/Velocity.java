package com.bitsforabetterworld.lunarlander;

/**
 * Velocity is relative to the target landing zone.
 * @author cpdupuis@gmail.com
 *
 */
public class Velocity {
	private final double m_dx;
	private final double m_dy;
	private final double m_dtheta;
	
	public Velocity(double dx, double dy, double dtheta) {
		m_dx = dx;
		m_dy = dy;
		m_dtheta = dtheta;
	}
	
	public double getDx() {
		return m_dx;
	}
	
	public double getDy() {
		return m_dy;
	}
	
	public double getDtheta() {
		return m_dtheta;
	}
}
