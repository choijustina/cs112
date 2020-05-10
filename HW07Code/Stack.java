 /* File: Stack.java
  * Contains: generic class Stack and Exception class StackUnderflowException
  * Date: 2/6/13
  * Author:  Wayne Snyder (snyder@bu.edu)
  * Class: CS 112, Spring 2013
  * Purpose: This is a standard generic linked-list stack, similar to p.149 in textbook
  * Throws: StackUnderflowException
  */

public class Stack<Item> {

  private class Node {                     // inner class for linked list inside the stack
    Item item;
    Node next;
    public Node(Item i, Node n) { item = i; next = n; }
  }
  
  private Node top = null; 


// Return true if Stack is empty, false otherwise

 public  boolean isEmpty() {
   return top == null;
 }


// Remove top item and return it; if stack is empty throw an exception

 public Item pop() throws StackUnderflowException {
   if (top == null)
     throw new StackUnderflowException(); 
   Item temp = top.item;
   top = top.next;
   return temp;
 }

// Return top item in stack without removing it; if stack is empty throw an exception

 public Item top() throws StackUnderflowException {
   if (top == null)
     throw new StackUnderflowException(); 
   return top.item;
 }
 
// Push an item on the top of the stack. 
 
 public void push(Item item) {
   top = new Node(item, top); 
 }
 
}

class StackUnderflowException extends Exception {
}

