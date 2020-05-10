/* File: CircularQueue.java
 * Date: 1/31/13
 * Class: Boston University CS 112, Spring 2013
 * Author: Wayne Snyder (snyder@bu.edu)
 * Purpose: This is the code shown in Lecture 4, standard implementation of a circular buffer, 
 *          using the "fill count" technique
 * Reference:  Wikipedia article on "circular buffer"
 */

import java.util.Arrays;

public class CircularQueue {
    
    private int [] A = new int[10];
    
    private int next = 0;
    private int front = 0; 
    private int size = 0;  
    
    // return the next location, wrapping around the sequence [0 .. A.length-1]
    
    int nextSlot(int k) { 
        return ((k + 1) % A.length); 
    } 
    
    void enqueue(int n) { 
        if(size != A.length) {         // just do nothing if no room
            A[next] = n; 
            next = nextSlot(next); 
            ++size;  
        }
        System.out.println("Enqueue " + n + ": " + Arrays.toString(A));
    } 
    
    int size() { 
        return size;                   // we keep an explicit count of number of elements in queue
    }  
    
    int dequeue() { 
        // if(isEmpty()) return null;             //  Can not do this for the int version, but should use this when it is made generic
        int temp = A[front];           
        front = nextSlot(front);  
        --size;  
        System.out.print("Dequeue  : " + Arrays.toString(A));
        System.out.println("new front: " + A[front]);
        return temp; 
    } 
    
    boolean isEmpty() { 
        return (size == 0); 
    } 
    
    public static void main(String [] args) {
        CircularQueue Q = new CircularQueue(); 
        Q.enqueue(5); 
        Q.enqueue(4); 
        Q.enqueue(7); 
        Q.enqueue(8); 
        Q.dequeue(); 
        Q.dequeue(); 
        Q.dequeue(); 
        Q.enqueue(2); 
        Q.enqueue(3); 
        Q.dequeue(); 
        Q.enqueue(8); 
        Q.enqueue(1); 
        Q.dequeue(); 
        Q.enqueue(2); 
        Q.enqueue(3); 
        Q.dequeue(); 
        Q.enqueue(4); 
        Q.enqueue(4); 
        Q.dequeue(); 
        Q.enqueue(9);
        Q.enqueue(9);
        
        /*while(!Q.isEmpty() ) {
            System.out.println(Q.dequeue()); 
        }*/
    }
    
    
    
    
    
    
}