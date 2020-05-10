 /* File: Point.java
  * Date: 1/30/13
  * Author:  Wayne Snyder (snyder@bu.edu)
  * Class: CS 112, Spring 2013
  * Purpose: This is just a simple class to test the HW 02 code
  * Note: This file is just for distribution purposes, the code should be copied
  *       into your ResizingQueue.java file. 
  */

class Point {
  
  public int x;
  public int y;
  
  public Point(int x, int y) {     // constructor
    this.x = x;
    this.y = y;
  }
  
  public String toString() {       // print out as a pair of integers
    return "(" + x + "," + y + ")"; 
  }
  
}