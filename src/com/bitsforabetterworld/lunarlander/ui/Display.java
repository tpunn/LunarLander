package com.bitsforabetterworld.lunarlander.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Display {
		
	private static final int LANDING_PAD_WIDTH = 80;
	BufferedImage bufferedImage = null;
	
	public void paintScene(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle rect = g2.getDeviceConfiguration().getBounds();
		g2.setColor(Color.BLACK);
		g2.fillRect(0,  0, rect.width, rect.height);
		int surfaceY = 9 * rect.height / 10;
		g2.setColor(Color.GRAY);
		g2.fillRect(0,  surfaceY, rect.width, rect.height / 10);
		g2.setColor(Color.YELLOW);
		g2.fillRect((rect.width - LANDING_PAD_WIDTH) / 2, surfaceY, LANDING_PAD_WIDTH, rect.height/100);
		g2.setColor(Color.PINK);
		g2.rotate(0.4);
		g2.drawImage(bufferedImage, 400, 400, null);
	}
	
   public void createAndShowGUI() throws IOException {
       //Create and set up the window.
       JFrame frame = new JFrame("Lunar Lander");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       bufferedImage = ImageIO.read(new File("C:\\Users\\cpdup\\Pictures\\LunarLander.png"));
       JPanel panel = new JPanel() {
    	   private static final long serialVersionUID = -5750610174709683930L;

    	   
    	   @Override
    	   public void paintComponent(Graphics g) {
    		   Display.this.paintScene(g);
    	   }
       };
       panel.setPreferredSize(new Dimension(1440, 900));
       frame.getContentPane().add(panel);

       //Display the window.
       frame.pack();
       frame.setVisible(true);
   }
}
