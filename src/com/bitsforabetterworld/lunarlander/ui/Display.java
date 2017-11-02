package com.bitsforabetterworld.lunarlander.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

import com.bitsforabetterworld.lunarlander.Lander;
import com.bitsforabetterworld.lunarlander.Position;


public class Display {
		
	private static final int LANDING_PAD_WIDTH = 80;
	private final Lander m_lander;
	private JPanel m_panel = null;
	
	private final Polygon landerPolygon = LanderSprite.createPolygon();
	
	public Display(Lander lander) {
		m_lander = lander;
	}
	
	public void update() {
		if (m_panel != null) {
			RepaintManager repaintManager = RepaintManager.currentManager(m_panel);
			repaintManager.markCompletelyDirty(m_panel);
			repaintManager.paintDirtyRegions();
		}
	}
	public void paintScene(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (m_lander.isCrashed())
		{
			g2.setColor(Color.RED);
			Font font = g2.getFont();
			Font bigFont = font.deriveFont(Font.BOLD, 96.0f);
			g2.setFont(bigFont);
			g2.drawString("CRASHED", 100, 200);
			return;
		}
		else if (m_lander.isLanded()) {
			g2.setColor(Color.GREEN);
			Font font = g2.getFont();
			Font bigFont = font.deriveFont(Font.BOLD, 96.0f);
			g2.setFont(bigFont);
			g2.drawString("LANDED", 100, 200);
			return;
		}
		Rectangle windowRect = g2.getDeviceConfiguration().getBounds();
		g2.setColor(Color.BLACK);
		g2.fillRect(0,  0, windowRect.width, windowRect.height);
		int surfaceY = 9 * windowRect.height / 10;
		g2.setColor(Color.GRAY);
		g2.fillRect(0,  surfaceY, windowRect.width, windowRect.height / 10);
		g2.setColor(Color.YELLOW);
		g2.fillRect((windowRect.width - LANDING_PAD_WIDTH) / 2, surfaceY, LANDING_PAD_WIDTH, windowRect.height/100);
		g2.setColor(Color.BLUE);
		Position landerPosition = m_lander.getPosition();
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
	
   public void createAndShowGUI(KeyListener keyListener) throws IOException {
       //Create and set up the window.
       final JFrame frame = new JFrame("Lunar Lander");
       if (keyListener != null) {
    	   frame.addKeyListener(keyListener);
       }
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       final JPanel panel = new JPanel() {
    	   private static final long serialVersionUID = -5750610174709683930L;
  	   
    	   @Override
    	   public void paintComponent(Graphics g) {
    		   Display.this.paintScene(g);
    	   }
       };   
       panel.setPreferredSize(new Dimension(1440, 900));
       panel.setDoubleBuffered(true);
       frame.getContentPane().add(panel);

       //Display the window.
       frame.pack();
       frame.setVisible(true);
       m_panel = panel;
   }
}
