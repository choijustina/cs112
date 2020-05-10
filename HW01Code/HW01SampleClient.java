/* File: HW01SampleClient.java
 * Date: 1/26/13
 * Author: Wayne Snyder (snyder@bu.edu)
 * Purpose: This is a partial example of a client class for CS 112 HW01, P4, 
 *          serving mostly as collection of test cases.
 * Uses : MutableCollection.java (interface), MutIntCollection.java, MutIntBSCollection.java
 */

import java.util.*;

public class HW01SampleClient {
    
    public static void main(String [] args) {
        
        MutableCollection C = new MutIntCollection(); 
        MutableCollection D = new MutIntBSCollection(); 
        
        int n;    // just a temp to keep track of what member we are testing
        
        System.out.println("Testing MutIntCollecction...."); 
        
        // test insert, including case of inserting when it is full
        for (int i = 0; i < 11; ++i) {
            n = i + 10; 
            System.out.print("Insert  \t" + n + "\t"); 
            System.out.print(C.insert(n)); 
            System.out.println();  
        }
        
        // test size
        System.out.print("Size?    \t\t");  
        System.out.println(C.size());   
        
        // Put your other test cases here!
        for (int i = 0; i < 11; ++i) {
            n = i + 10; 
            System.out.print("Delete  \t" + n + "\t"); 
            System.out.print(C.delete(n)); 
            System.out.println();  
        }
        
        System.out.print("Size?    \t\t");  
        System.out.println(C.size());
        
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