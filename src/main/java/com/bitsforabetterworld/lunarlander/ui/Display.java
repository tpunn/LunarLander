package com.bitsforabetterworld.lunarlander.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

import com.bitsforabetterworld.lunarlander.Lander;
import com.bitsforabetterworld.lunarlander.LanderLevel;
import com.bitsforabetterworld.lunarlander.Position;
import com.bitsforabetterworld.lunarlander.Velocity;


public class Display {
		
	public static final int LANDING_PAD_WIDTH = 80;
	private Lander m_lander;
	private JPanel m_panel = null;

	public void setLander(Lander lander) {
		m_lander = lander;
	}
	public void update() {
		if (m_panel != null) {
			RepaintManager repaintManager = RepaintManager.currentManager(m_panel);
			repaintManager.markCompletelyDirty(m_panel);
			repaintManager.paintDirtyRegions();
		}
	}
	public void paintScene(Graphics2D g2, Rectangle2D windowRect) {
		if (m_lander == null) {
			return;
		}
		g2.setColor(Color.BLACK);
		g2.fill(windowRect);

		int surfaceY = 9 * (int)windowRect.getHeight() / 10;
		g2.setColor(Color.GRAY);
		g2.fillRect(0,  surfaceY, (int)windowRect.getWidth(), (int)(windowRect.getHeight() / 10.0));
		g2.setColor(Color.YELLOW);
		g2.fillRect((int)(windowRect.getWidth() - LANDING_PAD_WIDTH) / 2, surfaceY, LANDING_PAD_WIDTH, (int)(windowRect.getHeight()/100.0));
		
		if (m_lander.isCrashed())
		{
			g2.setColor(Color.RED);
			Font font = g2.getFont();
			Font bigFont = font.deriveFont(Font.BOLD, 96.0f);
			g2.setFont(bigFont);
			g2.drawString("CRASHED", 100, 200);
			g2.setFont(font);
		}
		else if (m_lander.isLanded()) {
			g2.setColor(Color.GREEN);
			Font font = g2.getFont();
			Font bigFont = font.deriveFont(Font.BOLD, 96.0f);
			g2.setFont(bigFont);
			g2.drawString("LANDED", 100, 200);
			g2.setFont(font);
		}

		Position landerPosition = m_lander.getPosition();
		Velocity landerVelocity = m_lander.getVelocity();
		double landerFuel = m_lander.getFuelRemaining();
		
		showStats(g2, windowRect, landerPosition, landerVelocity, landerFuel);
		LanderSprite.drawLander(g2, windowRect, landerPosition, m_lander.isThrusterOn());
	}
	
	
	void showStats(Graphics2D g2, Rectangle2D rect, Position position, Velocity velocity, double fuel) {
		int xPos = (int)rect.getWidth() - 120;
		g2.drawString("x: "+position.getX(), xPos, 20);
		g2.drawString("y: "+position.getY(), xPos, 40);
		g2.drawString("theta: "+position.getTheta(), xPos, 60);
		g2.drawString("dx: "+velocity.getDx(), xPos, 80);
		g2.drawString("dy: "+velocity.getDy(), xPos, 100);
		g2.drawString("dtheta: "+velocity.getDtheta(), xPos, 120);
		g2.drawString("fuel: "+fuel, xPos, 140);
		g2.drawString("SCORE: "+LanderLevel.getScore(), xPos, 160);
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
				Rectangle2D rect = frame.getBounds();
				Display.this.paintScene((Graphics2D)g, rect);
			}
       };   
       panel.setPreferredSize(new Dimension(1200, 800));
       panel.setDoubleBuffered(true);
       frame.getContentPane().add(panel);
       //Display the window.
       frame.pack();
       frame.setVisible(true);
       m_panel = panel;
   }
}
