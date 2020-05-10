 /* File: SymbolTable.java 
  * Author: Wayne Snyder (snyder@cs.bu.edu) 
  * Date: 3/23/13 
  * Purpose: This is the interface for the BSTSymbolTable class in HW 07 in CS 112.
  * Platform: This was developed on Mac OS X 10.6.5 in Dr. Java
  */ 

interface SymbolTable<Key extends Comparable<Key>, Value> {
    public void put(Key key, Value val);                         // insert (key,val) pair into symbol table
                                                                 //    if key is already present, update the val
    public Value get(Key key) throws undefinedVariableException; // return the value for this key, if key not
                                                                 //    in table, throw exception
    public boolean contains(Key key);                            // return T/F whether key is in table
    public void delete(Key key);                                 // remove key from table, do nothing if not present
    public boolean isEmpty();                       
    public int size(); 
    public void listKeys();                                      // list all (key,value) pairs in table
 }
