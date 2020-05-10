 /* File: CollectionClient.java
  * Date: 1/23/13
  * Author: Wayne Snyder (snyder@bu.edu)
  * Purpose: This is the client class for CS 112 HW01, serving mostly as collection of test cases.
  * Uses : Collection.java (interface)
  * Other related files: IntCollection.java
  */

public class CollectionClient {
  
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