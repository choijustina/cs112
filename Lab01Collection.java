/* File: Lab01.java
 * Date: 1/28/13
 * Author: Wayne Snyder (snyder@bu.edu); Justina Choi (justinac@bu.edu)
 * Purpose: Simple example of collection that allows insertion and member; in the lab we 
 *          will prevent problems with overflow by resizing the array.
 */

import java.util.*;

public class Lab01Collection {
    
    private int [] A = new int[2];     // initialize A with small number of slots
    
    private int next = 0;          // location of next available slot in A; 
    // note that next also = size of collection!
    
    // member returns true iff n is in the list A 
    public boolean member(int n) {
        
        for(int i = 0; i < A.length; ++i) {
            if(A[i] == n) 
                return true; 
        }   
        return false;  
        
    } 
    
    // insert puts n at the end of the array and doesn't check for errors
    public void insert(int n) {
        int [] temp = new int[(next+1)*2];
        
        for (int i = 0; i < A.length; i++) {
            temp[i] = A[i];
        }
        temp[next] = n;
        A = temp;
        next++;
    }
    
    // test just checks for membership and prints appropriate message to simplify main
    public void test(int n) {
        if( member(n) )
            System.out.println( n + " is in the collection");
        else
            System.out.println( n + " is NOT in the collection");
    }
    
    public static void main(String [] args) {
        
        Lab01Collection C = new Lab01Collection(); 
        
        C.insert(3);
        C.insert(2);
        
        C.test(2);
        C.test(5); 
        
        
        // everything is fine unless you uncomment the next line
        
        C.insert(5);
        C.test(5);
        
    }
    
}