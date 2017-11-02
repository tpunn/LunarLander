package com.bitsforabetterworld.lunarlander.ui;

import java.awt.Polygon;

public class LanderSprite {
	
	// the nose of the lander is at (0, 0)
	private static final int[] xPoints = { 0, 16, 16, 32, 24, 16, -16, -24, -32, -16, -16 };
	private static final int[] yPoints = { 0, 8, 16, 48, 48, 32, 32, 48, 48, 16, 8 };

	public static Polygon createPolygon() {
		return new Polygon(xPoints, yPoints, xPoints.length);
	}
}
