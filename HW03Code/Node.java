 /* File: Node.java
  * Date: 2/6/13
  * Author:  Wayne Snyder (snyder@bu.edu)
  * Class: CS 112, Spring 2013
  * Purpose: Just a generic Node class for linked lists, such as on p.142 in textbook, but with constructors 
  */

public class Node<Item> {

       public Item item;
       public Node<Item> next;

       Node() {                 // this would be the default, but if you define another constructor
          item = null;             // this is not available, so have to provide!
          next = null;      
       } 

       Node(Item i) {
          item = i;
          next = null;
       }

       Node(Item i, Node<Item> p) {
          item = i;
          next = p;
       }
}
       
