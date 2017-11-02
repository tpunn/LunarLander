package com.bitsforabetterworld.lunarlander.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import com.bitsforabetterworld.lunarlander.Position;

public class LanderSprite {
	
	// the nose of the lander is at (0, 0)
	private static final int[] xPoints = { 0, 16, 16, 32, 24, 16, -16, -24, -32, -16, -16 };
	private static final int[] yPoints = { 0, 8, 16, 48, 48, 32, 32, 48, 48, 16, 8 };

	public static Polygon createPolygon() {
		return new Polygon(xPoints, yPoints, xPoints.length);
	}
	
	static Polygon landerPolygon = createPolygon();
	
	static void drawLander(Graphics2D g2, Rectangle windowRect, Position landerPosition) {
		g2.setColor(Color.BLUE);
		double theta = landerPosition.getTheta();
		double x = windowRect.getWidth() * landerPosition.getX() / Position.WIDTH_OF_SCREEN ;
		double y = (0.9 * windowRect.getHeight()) * (Position.TOP_OF_SCREEN - landerPosition.getY()) / Position.TOP_OF_SCREEN;
		AffineTransform rotationTransform = AffineTransform.getRotateInstance(theta);
		Rectangle landerBounds = landerPolygon.getBounds();
		AffineTransform translateTransform = AffineTransform.getTranslateInstance(x, y - landerBounds.getCenterY());
		AffineTransform offsetTransform = AffineTransform.getTranslateInstance(-landerBounds.getCenterX(), -landerBounds.getCenterY());
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.concatenate(translateTransform);
		affineTransform.concatenate(rotationTransform);
		affineTransform.concatenate(offsetTransform);
		g2.setTransform(affineTransform);
		g2.fillPolygon(landerPolygon);
		
		
	}

}
