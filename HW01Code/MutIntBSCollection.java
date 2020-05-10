/* File: MutIntBSCollection.java
 * Date: 1/29/13
 * Author: Justina Choi (justinac@bu.edu)
 * Uses : MutableCollection.java (interface)
 */

import java.util.Arrays;

public class MutIntBSCollection implements MutableCollection {
    
    private static final int ARRAY_SIZE = 10;
    
    private int counter = 0; //counts number of integers (not 0) in the array
    
    private int[] A = new int[ARRAY_SIZE];
    
    // member returns true iff n is in the list A 
    public boolean member(int n) {
        boolean found = false;
        int left = 0;
        int right = counter;
        
        while (left <= right && !found) {
            int middle = (left + right) / 2;

            if (A[middle] == n) {
                found = true;
                break;
            } else if (n < A[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
            
        }
        if (found) {
            return found;
        } else {
            return !found;
        }
    }
    
    
    //size returns the number of integers in the collection
    public int size() {
        int size = 0;
        
        for (int i = 0; i < A.length; i++) {
            if (A[i] != 0) {
                size++;
            } else if (A[i] == 0 && i+1 == A.length) {
                return size;
            } else if (A[i] == 0 && A[i+1] != 0) {
                size++;
            }
        }
        return size++;
    }
    
    //inserts the integer into the collection if there is room, and does nothing
    //otherwise; returns true iff successfully inserted
    public boolean insert(int n) {
        if (counter == ARRAY_SIZE) {
            return false;
        }
        
        int left = 0;
        int right = counter;
        int index = 0;
        
        while (left <= right) {
            int middle = (left + right) / 2;
            if (A[middle] == n) {
                middle = index;
                break;
            }
            else if (n < A[middle])
                right = middle - 1;
            else
                left = middle + 1 ;
        }
        
        for (int i = A.length - 1; i > index; i--) {
            A[i] = A[i-1];
        }
        A[index] = n;
        counter++;
        return true; 
    }
    
    
//deletes the first occurence of the integer if it exists, and does nothing
//otherwise; returns true iff successfully deleted
    public boolean delete(int n) {
        int x = 0;
        
        if (this.member(n)) { 
            
            int left = 0;
            int right = counter;
            int index = 0;
            
            while (left <= right) {
                int middle = (left + right) / 2;
                
                if (A[middle] == n) {
                    middle = index;
                    x = middle;
                    break;
                }
                else if (n < A[middle])
                    right = middle - 1;
                else
                    left = middle + 1;
            }
            
            for (int j = x; j < counter - 1; j++) {
                A[j] = A[j + 1];
            }
            counter--;
            return true;
        }
        return false;
    }
    
    public static void main(String [] args) {
        
        MutableCollection D = new MutIntBSCollection(); 
        
        int n;    // just a temp to keep track of what member we are testing
        
        
        System.out.println("\nTesting MutIntBSCollecction...."); 
        
        // test insert, including case of inserting when it is full
        for (int i = 0; i < 11; ++i) {
            n = i + 10; 
            System.out.print("Insert  \t" + n + "\t"); 
            System.out.print(D.insert(n)); 
            System.out.println();  
        }
        
        // test size
        System.out.print("Size?    \t\t");  
        System.out.println(D.size());   
        
        // Put your other test cases here!
        for (int i = 0; i < 11; ++i) {
            n = i + 10; 
            System.out.print("Delete  \t" + n + "\t"); 
            System.out.print(D.delete(n)); 
            System.out.println();  
        }
        
        // test size
        System.out.print("Size?    \t\t");  
        System.out.println(D.size()); 
        
    }
}