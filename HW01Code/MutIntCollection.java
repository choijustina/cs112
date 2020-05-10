/* File: MutIntCollection.java
 * Date: 1/29/13
 * Author: Justina Choi (justinac@bu.edu)
 * Uses : MutableCollection.java (interface)
 */


public class MutIntCollection implements MutableCollection {
    
    private int[] A = new int[10];
    
    private int counter = 0; //counts number of integers (not 0) in the array
    
    // member returns true iff n is in the list A
    public boolean member(int n) {
        
        for(int i = 0; i < A.length; ++i) {
            if(A[i] == n) 
                return true; 
        }
        return false; 
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
        
        if (A[A.length - 1] == 0) {
            int size = size();
            A[size] = n;
            counter++;
            return true;    
        } else {
            return false;
        }
    }
    
    //deletes the first occurence of the integer if it exists, and does nothing
    //otherwise; returns true iff successfully deleted
    public boolean delete(int n) {
        int x = 0;
        
        if (member(n)) {
            for (int i = 0; i < A.length; i++) {
                if (A[i] == n) {
                    x = i;
                    break;
                }
            }
            for (int j = x; j < counter - 1; j++) {
                A[j] = A[j+1];
            }
            counter--;
            return true;
        }
        return false;
    }
    
    public static void main(String [] args) {
        
        MutableCollection C = new MutIntCollection(); 
        
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
        
        
    }
}