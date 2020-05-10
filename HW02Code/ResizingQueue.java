/* File: ResizingQueue.java
 * Date: 2/5/13
 * Author: Justina Choi
 * Class: CS 112, Spring 2013 (Snyder)
 * Purpose: Develops a generic array-based queue ADT
 * Note: Homework 2
 * Reference: Prof. Snyder's CircularQueue.java <http://www.cs.bu.edu/fac/snyder/cs112/CodeExamples/CircularQueue.java>
 */
import java.util.Arrays;
@SuppressWarnings("unchecked")

public class ResizingQueue<Item> implements Queue<Item> {
    
    private Item[] A = (Item[]) new Object[8];
    
    private int next = 0;   //location of the next empty array slot
    private int front = 0;  //front of the array
    private int size = 0;   //keeps track of the number of 'useful' numbers
    
    //return the next location, wrapping around the sequence [0... A.length - 1]
    
    private int nextSlot(int k) {
        return ((k + 1) % A.length);
    }
    
    public void enqueue(Item item) {
        if (front == next && size == A.length)
            expandArray();
        
        if (size != A.length) { 
            A[next] = item;
            next = nextSlot(next);
            size++;
        }
        
        //System.out.println("Enqueue " + item + ": " + Arrays.toString(A));
    }
    
    public Item dequeue() {
        if (isEmpty()) return null;  //not for int version, change when made generic
        Item temp = A[front];
        front = nextSlot(front);
        size--;
        
        //System.out.println("Dequeue: " + Arrays.toString(A));
        //System.out.println("    Front: " + A[front] + ", Length: " + A.length +
        //                   ", Size: " + size + ", Next: " + A[next]);
        
        //calls contractArray method if size is less than 1/4 of A.length
        if (A.length/4 > size && A.length/4 > 7)
            contractArray();
        
        return temp;
    }
    
    public boolean isEmpty() {
        return (size == 0);
    }
    
    public int size() {
        return size;
    }
    
    
    private void expandArray() {
        Item[] tempArray = (Item[]) new Object[size*2];
        
        for (int i = 0; i < size(); i++)
            tempArray[i] = A[i];
        A = tempArray;
        next = size;
    }
    
    
    private void contractArray() {
        Item[] tempArray = (Item[]) new Object[(A.length)/4];
        
        int j = 0;
        for (int i = front; i < (front + size()); i++) {
            tempArray[j] = A[i];
            j++;
        }
        
        A = tempArray;
        
        //resetting the pointer "next"
        for (int i = 0; i < A.length; i++) {
            if (A[i] == null)
                next = i;
        }
        //resetting the pointer "front"
        front = 0;
        
        //System.out.println(Arrays.toString(A));
        //System.out.println("Next is: " + A[next] + ", front is: " + A[front] + ", size is: " + size);
    }
    
    
    
    
    public static void main(String [] args) {
        
        ResizingQueue B = new ResizingQueue();
        
        System.out.println("is empty? " + B.isEmpty());
        B.enqueue(1);
        B.enqueue(2);
        System.out.println("size: " + B.size());
        B.enqueue(3);
        B.enqueue(4);
        B.enqueue(5);
        B.enqueue(6);
        B.enqueue(7);
        B.enqueue(8);
        System.out.println("is empty? " + B.isEmpty());
        System.out.println("size: " + B.size());
        
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        System.out.println("cleared array");
        System.out.println("is empty? " + B.isEmpty());
        System.out.println("size: " + B.size());
        
        B.enqueue(10);
        B.enqueue(11);
        B.enqueue(12);
        B.enqueue(13);
        B.enqueue(14);
        B.enqueue(15);
        B.enqueue(16);
        B.enqueue(17);       
        B.enqueue(18);
        B.enqueue(19);
        B.enqueue(20);
        B.enqueue(21);
        B.enqueue(22);
        B.enqueue(23);
        B.enqueue(24);
        B.enqueue(25);
        System.out.println("is empty? " + B.isEmpty());
        System.out.println("size: " + B.size());
        
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        
        B.enqueue(26);
        B.enqueue(27);
        B.enqueue(28);
        B.enqueue(29);
        B.enqueue(30);
        B.enqueue(31);
        B.enqueue(32);
        B.enqueue(33);
        B.enqueue(34);
        B.enqueue(35);
        B.enqueue(36);
        B.enqueue(37);
        B.enqueue(38);
        B.enqueue(39);
        B.enqueue(40);
        B.enqueue(41);
        B.enqueue(42);
        System.out.println("is empty? " + B.isEmpty());
        System.out.println("size: " + B.size());
        
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        B.dequeue();
        System.out.println("is empty? " + B.isEmpty());
        System.out.println("size: " + B.size());
        
        
    }
    
}

interface Queue<Item> {
    void enqueue(Item item);
    Item dequeue();
    boolean isEmpty();
    int size();
}

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





