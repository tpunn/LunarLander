package com.bitsforabetterworld.lunarlander;

/**
 * Velocity is relative to the target landing zone.
 * @author cpdupuis@gmail.com
 *
 */
class Velocity {
	private final double dx;
	private final double dy;
	
	public Velocity(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public double getDx() {
		return dx;
	}
	
	public double getDy() {
		return dy;
	}
}