 /* File: QueueClient.java
  * Date: 1/30/13
  * Author:  Wayne Snyder (snyder@bu.edu)
  * Class: CS 112, Spring 2013
  * Purpose: This is a sample client to make sure your code runs with a client
  * Related classes:  ResizingQueue, Queue, Point (all three in file ResizingQueue.java)
  */

public class QueueClient {
  
  public static void main(String [] args) {
    
    Queue<Integer> Q = new ResizingQueue<Integer>(); 
    
    System.out.println("\nShould print out:  -3  5  9:"); 

    Q.enqueue(-3); 
    Q.enqueue(5); 
    Q.enqueue(9);

    System.out.print( Q.dequeue() + "  "); 
    System.out.print( Q.dequeue() + "  "); 
    System.out.println( Q.dequeue() ); 
    
    Queue<Point> S = new ResizingQueue<Point>(); 
    
    System.out.println("\nShould print out:  (2,3)  (4,5)"); 
    S.enqueue(new Point(2,3)); 
    S.enqueue(new Point(4,5)); 
  
    System.out.print( S.dequeue().toString() + "  "); 
    System.out.println( S.dequeue().toString() ); 
  
    
  } 
  
}

