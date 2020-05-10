/* File: Grapher.java
 * Author: Wayne Snyder
 * Date: 2/21/13
 * Class: CS 112, Spring 2013
 * Purpose: This program is a simpling graphing class based on the JCanvas class; it 
 *          draws x and y axes, based on given scales (indicated in pixels per unit), 
 *          labeled tick marks, and rescales and reorients input x and y values to
 *          correspond to expected placement in the Cartesian first quadrant of a graph. 
 *          The class is fairly flexible in that it will do something reasonable with
 *          changes to the parameters (private final variables at top of class). 
 *          Several useful public methods are provided from the JCanvas class, and
 *          additional ones can be added. Currently included (definitions are same as in JCanvas,
 *          except as noted): 
 * 
 *                  public void setPaint(Color p); 
 *                  public void drawLine(int x, int y, int x2, int y2); 
 *                  public void drawLine(int x, int y, int x2, int y2); 
 *                  public void drawDashedLine( int d, int x, int y, int x2, int y2); 
 *                  public void drawString( String s, int x, int y);   // (x,y) is lower left corner
 *                  public void drawPoint( int d, int x, int y );      // new: draws circle of diam d with 
 *                                                                     //      center at (x,y)
 * 
 * Associated files: JCanvas  (must be run with JCanvas in same directory)
 * Known bugs:  None (yet!)
 */

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Grapher {
   
  private final int height = 800;            // window dimensions
  private final int width  = 800; 
  
  private final int leftMargin   = 75;       // interior margins for axes
  private final int bottomMargin = 100; 
  private final int rightMargin  = 50;
  private final int topMargin    = 50; 
  
  private final double xScale = 6.5;         // how many pixels per unit on x axis?
  private final double yScale = 6.5;         // on y axis?
  
  private final int xTick = 10;              // how many units on x axis between each tick mark
  private final int yTick = 10;              // on y axis? 
  
  private JFrame frame; 
  private JCanvas canvas; 
  
  // rescale and reorient points 
  // so that Cartesian point (x,y) is displayed where we
  // would expect in the window
  
  private int x(int xIn) {
    return (leftMargin + (int)(xIn*xScale)); 
  }
  
  private  int y(int yIn) {
    return (height-bottomMargin - (int)(yIn*yScale)); 
  }

 
  // Constructor!
  
  public Grapher() {
   
     frame=new JFrame();
     frame.setSize(width,height);
     frame.setTitle("Analysis of Algorithms Grapher");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     canvas = new JCanvas();
     frame.add(canvas);
     frame.setBackground(Color.white); 
     frame.setVisible(true);
     
     // draw axes
     
     canvas.drawLine(leftMargin, topMargin, leftMargin, height-bottomMargin);
     canvas.drawLine(leftMargin, height-bottomMargin, width-rightMargin, height-bottomMargin); 
     canvas.drawString("Comparisons &", 15,topMargin/2-5); 
     canvas.drawString("Exchanges", 15,topMargin/2+10); 
     canvas.drawString("Input Size", width/2-20,height-bottomMargin/2); 
     
     // draw tick marks on x axis
     for(int n = 0; x(n) <= (width-rightMargin); n += xTick) {
       canvas.drawLine(x(n),y(0), x(n), y(0)-10);
       canvas.drawString(n+" ", x(n)-5, y(0)+20); 
     }
     // draw tick marks on y axis
     for(int n = 0; y(n) >= topMargin; n += yTick) {
       canvas.drawLine(x(0),y(n), x(0)+10, y(n));
       canvas.drawString(n+" ", x(0)-40, y(n)+5); 
     }
     
  }
  
  // the following methods are a selection of those available for the JCanvas; you can
  // add more if you like, following the pattern
  
  public void setPaint(Color p) {
    canvas.setPaint(p); 
  }
  
  public void drawLine(int x, int y, int x2, int y2) {
       canvas.drawLine(x(x),y(y), x(x2), y(y2));
  }
  
  public void drawDashedLine( int d, int x, int y, int x2, int y2) {
       canvas.drawDashedLine(d, x(x),y(y), x(x2), y(y2));
  }
  
  // Not exactly the same as original: because of rescaling, (x,y) is lower left corner of s
  public void drawString( String s, int x, int y) {    
       canvas.drawString(s, x(x), y(y)); 
  }
  
  // Not in original, but seemed useful, point is centered on (x,y)
  public void drawPoint( int d, int x, int y ) {
       canvas.fillOval(x(x)-d/2,y(y)-d/2, d, d);
  }
  
  // just a demonstration of the basic public methods
  
  public static void main(String [] args) {
    
    Grapher G = new Grapher(); 
 
    G.setPaint(Color.red); 
    G.drawLine(10, 10, 20, 40); 
    G.drawPoint(4,20,40); 
    G.drawString("(20,40)", 21, 38);
    G.drawString("Drop me a line!", 22, 42);
    G.setPaint(Color.blue); 
    G.drawDashedLine(3, 40,50, 75, 80); 
    G.drawString("Sorry, gotta dash...", 77,82);
    G.setPaint(Color.black); 
    G.drawPoint(4, 30, 20); 
    G.drawString("Get the point?", 32, 18);
    
  }
  
}