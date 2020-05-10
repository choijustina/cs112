 /* File: BouncingBall.java
  * Date: 2/12/13
  * Author:  Wayne Snyder (snyder@bu.edu)
  * Class: CS 112, Spring 2013
  * Purpose: This is a basic animation for Lab 04; students will modify this in various fun ways
  * Related Files:  JCanvas.java
  */

import java.awt.*;
import java.awt.geom.*;  
import javax.swing.*;

    
public class BouncingBall{ 
    public static void main(String args[]){  
      JFrame frame=new JFrame("BouncingBall");  
      frame.setSize(800, 600);  
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      JCanvas canvas=new JCanvas();  
      frame.add(canvas);  
      frame.setVisible(true); 
      
      // all units are in pixels!
                  
      int vx=6,    // velocity in x direction (positive is down)
        vy=6,      // ditto in y direction (positive is to right)
        x=0,       // position on x axis (0 is at top)
        y=50,      // position on y axis (0 is at left)
        d=100;     // diameter of ball   
      int ay = 0;  // acceleration in y direction
      int ax = 0;  // acceleration in x direction
      
      while(true){ 
        int mx=canvas.getWidth();       // in case user changed size of window
        int my=canvas.getHeight();  
        canvas.startBuffer();  
        canvas.clear(); 
        canvas.setPaint(Color.blue);  
        vy += ay; vx += ax;             // change velocities
        x+=vx; y+=vy;                   // change position
        if(x<0){                        // hit left edge, must reverse velocity to make it bounce
          x=0;        
          vx=-vx;
        }  
        if(y<0){                        // hit top edge, must reverse velocity to make it bounce
          y=0;
          vy=-vy;
        }  
        if(x+d>mx){                     // if right side of ball hits right edge of window
          x=mx-d;
          vx=-vx;
        }  
        if(y+d>my){                     // if bottom side of ball hits bottom edge of window
          y=my-d;
          vy=-vy;
        }  
        canvas.fillOval(x,y,d,d);  
        canvas.endBuffer();  
        canvas.sleep(20);               // pause for 20 milliseconds
       }  
    }  
  }