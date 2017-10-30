package com.bitsforabetterworld.lunarlander;

/**
 * Position is relative to the target landing zone.
 * @author cpdupuis@gmail.com
 *
 */
class Position {
	private final double x;
	private final double y;
	
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}