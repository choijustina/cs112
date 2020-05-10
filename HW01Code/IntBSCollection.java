/* File: IntBSCollection.java
 * Date: 1/29/13
 * Author: Justina Choi (justinac@bu.edu)
 * Purpose: This is a simple example of an ADT for HW 01, P1, which is expanded in problems 2-4
 * Uses : Collection.java (interface)
 * Other related files: CollectionClient.java
 */

public class IntBSCollection implements Collection {
    
    // Just a simple, ordered example of a collection
    private int [] A = {2, 2, 7, 12, 15, 19, 25, 26, 38, 45, 78};
    
    // member returns true iff n is in the list A
    public boolean member(int n) {
        boolean found = false;
        int left = 0;
        int right = A.length - 1;
        int number = n;
        
        while (left <= right && !found) {
            int middle = (left + right) / 2;
            
            if (middle == number) {
                found = true;
            } else if (middle < number) {
                left = middle - 1;
            } else {
                right = middle - 1;
            }
        }
        if (found) {
            return found;
        } else {
            return !found;
        }
    }
    
    public static void main(String [] args) {
        
        Collection C = new IntCollection();       // note that C is a Collection, not an IntCollection !
        
        System.out.println("Is 38 in the collection? Should be true: ");
        System.out.println(C.member(38)); 
        
        System.out.println("Is 19 in the collection? Should be true: ");
        System.out.println(C.member(19)); 
        
        System.out.println("Is 2 in the collection? Should be true: ");
        System.out.println(C.member(2)); 
        
        System.out.println("Is 78 in the collection? Should be true: ");
        System.out.println(C.member(78)); 
        
        System.out.println("Is 5 in the collection? Should be false: ");
        System.out.println(C.member(5));
        
        
        //additional tests
        System.out.println("Is 39 in the collection? Should be false: ");
        System.out.println(C.member(39));
        
        System.out.println("Is 12 in the collection? Should be true: ");
        System.out.println(C.member(12));
        
        System.out.println("Is 10 in the collection? Should be false: ");
        System.out.println(C.member(10));
        
        
    }
    
}