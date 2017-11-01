package com.bitsforabetterworld.lunarlander.ui;
import com.bitsforabetterworld.lunarlander.Lander;
import com.bitsforabetterworld.lunarlander.Position;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Display {
		
	private static final int LANDING_PAD_WIDTH = 80;
	BufferedImage bufferedImage = null;
	private final Lander m_lander;
	private long m_lastPaintNanos = 0;
	
	public Display(Lander lander) {
		m_lander = lander;
	}
	public void paintScene(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		long now = System.nanoTime();
		if (m_lastPaintNanos != 0 && m_lastPaintNanos < now) {
			double dtSeconds = ((double)(now - m_lastPaintNanos)) / 1000000000.0;
			System.err.println("dt = "+dtSeconds);
			m_lander.clockTick(dtSeconds);
		}
		m_lastPaintNanos = now;
		System.err.println("lastPaintNanos: "+m_lastPaintNanos);
		Rectangle rect = g2.getDeviceConfiguration().getBounds();
		g2.setColor(Color.BLACK);
		g2.fillRect(0,  0, rect.width, rect.height);
		int surfaceY = 9 * rect.height / 10;
		g2.setColor(Color.GRAY);
		g2.fillRect(0,  surfaceY, rect.width, rect.height / 10);
		g2.setColor(Color.YELLOW);
		g2.fillRect((rect.width - LANDING_PAD_WIDTH) / 2, surfaceY, LANDING_PAD_WIDTH, rect.height/100);
		g2.setColor(Color.PINK);
		Position landerPosition = m_lander.getPosition();
		double theta = landerPosition.getTheta();
		double x = landerPosition.getX();
		double y = landerPosition.getY();
//		AffineTransform scaleTransform = AffineTransform.getScaleInstance(rect.width / Position.WIDTH_OF_SCREEN, rect.height/Position.TOP_OF_SCREEN);
		AffineTransform scaleTransform = AffineTransform.getScaleInstance(0.1, 0.1);
		AffineTransform rotationTransform = AffineTransform.getRotateInstance(theta);
		AffineTransform translateTransform = AffineTransform.getTranslateInstance(x, y);
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.concatenate(translateTransform);
		affineTransform.concatenate(rotationTransform);
		affineTransform.concatenate(scaleTransform);
		g2.drawImage(bufferedImage, affineTransform, null);
	}
	
   public void createAndShowGUI() throws IOException {
       //Create and set up the window.
       final JFrame frame = new JFrame("Lunar Lander");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       bufferedImage = ImageIO.read(new File("C:\\Users\\cpdup\\Pictures\\LunarLander.png"));
       final JPanel panel = new JPanel() {
    	   private static final long serialVersionUID = -5750610174709683930L;

    	   
    	   @Override
    	   public void paintComponent(Graphics g) {
    		   Display.this.paintScene(g);
    		   repaint();
    	   }
       };
       
       panel.setPreferredSize(new Dimension(1440, 900));
       panel.setDoubleBuffered(true);
       frame.getContentPane().add(panel);

       //Display the window.
       frame.pack();
       frame.setVisible(true);
   }
}
