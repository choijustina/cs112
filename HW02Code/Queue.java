 /* File: Queue.java
  * Date: 1/30/13
  * Author:  Wayne Snyder (snyder@bu.edu)
  * Class: CS 112, Spring 2013
  * Purpose: This is the interface for the ResizingQueue class in HW 02.
  * Note: This file is just for distribution purposes, the code should be copied
  *       into your ResizingQueue.java file. 
  */

public interface Queue<Item> {
  void enqueue(Item item);
  Item dequeue();
  boolean isEmpty();
  int size();
}

