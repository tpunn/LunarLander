package com.bitsforabetterworld.lunarlander;

/**
 * Position is relative to the target landing zone.
 * Position is in meters. Note that the surface of
 * the moom is at altitude 0.0, and the top of the screen is at altitude 1000 m
 * @author cpdupuis@gmail.com
 *
 */
public class Position {
	public static final double TOP_OF_SCREEN = 1000.0; // meters
	public static final double WIDTH_OF_SCREEN = 1500.0; // meters
	private final double x;
	private final double y;
	private final double theta;
	
	public Position(double x, double y, double theta) {
		this.x = x;
		this.y = y;
		this.theta = theta;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getTheta() {
		return theta;
	}
}
