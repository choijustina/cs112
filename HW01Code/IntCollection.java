 /* File: IntCollection.java
  * Date: 1/29/13
  * Author: Justina Choi (justinac@bu.edu)
  * Purpose: This is a simple example of an ADT for HW 01, P1, which is expanded in problems 2-4
  * Uses : Collection.java (interface)
  * Other related files: CollectionClient.java
  */

public class IntCollection implements Collection {
 
  // Just a simple example of a collection
  private int [] A = {38, 25, 2, 15, 19, 26, 2, 7, 45,  12, 78};  
  
  // member returns true iff n is in the list A
  public boolean member(int n) {
 
    for(int i = 0; i < A.length; ++i) {
      if(A[i] == n) 
        return true; 
    }
    
    return false; 
    
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
    
    
  }
  
}