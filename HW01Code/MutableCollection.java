/* File: MutableCollection.java
  * Date: 1/29/13
  * Author: Justina Choi (justinac@bu.edu)
  * Other related files: MutIntCollection.java and MutIntBSCollection.java
  */

public interface MutableCollection {
    
    public boolean member(int n);  //returns true iff n is in collection
    
    public int size();             //returns number of integers in the collection
    
    public boolean insert(int n);  //inserts the integer into the collection if
                                   //there is room, and does nothing otherwise;
                                   //returns true iff successfully inserted
    
    public boolean delete(int n);  //deletes the first occurrence of the integer
                                   //if it exists, and does nothing otherwise;
                                   //returns true iff successfully deleted
    
    
}
    
    


