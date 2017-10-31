package com.bitsforabetterworld.lunarlander;

/**
 * Position is relative to the target landing zone.
 * Position is in meters. Note that the surface of
 * the moom is at altitude 0.0, and the top of the screen is at altitude 1000 m
 * @author cpdupuis@gmail.com
 *
 */
class Position {
	public static final double TOP_OF_SCREEN = 1000.0; // meters
	public static final double WIDTH_OF_SCREEN = 1500.0; // meters
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