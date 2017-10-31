package com.bitsforabetterworld.lunarlander.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;


public class Display {
	
	public void paintScene(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(20, 40, 600, 200);
	}
	
   public void createAndShowGUI() {
       //Create and set up the window.
       JFrame frame = new JFrame("HelloWorldSwing");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       JPanel panel = new JPanel() {
    	   // You don't even want to know...	
    	   private static final long serialVersionUID = -5750610174709683930L;

    	   
    	   @Override
    	   public void paintComponent(Graphics g) {
    		   Display.this.paintScene(g);
    	   }
       };
       panel.setPreferredSize(new Dimension(800, 600));
       frame.getContentPane().add(panel);

       //Display the window.
       frame.pack();
       frame.setVisible(true);
   }
}
