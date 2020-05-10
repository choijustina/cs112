/* File: BigIntCalculator.java
 * Author: Wayne Snyder
 * Date: 2/6/13
 * Class: CS 112, Spring 2013
 * Purpose: This program is a client program for a set of big integer methods to be
 * packaged as static method in a class BigInt; this is HW 04, continuing and modifying
 * HW 03. 
 * Associated files: Stack.java, Stackable.java, BigInt.java
 */

import java.io.*;
import java.util.*;

public class BigIntCalculator {
 
  public static void main (String[] args) {  
        
    Scanner sc = new Scanner(System.in);
    
    Stackable<Node<Integer>> S = new Stack<Node<Integer>>(); 
    Stackable<Node<Integer>> T = new Stack<Node<Integer>>(); 
    
        
    while (sc.hasNext()) {     
         String command = sc.next();                 // read next line of file
        
         if(command.equals("quit")) { 
          System.out.println("Goodbye!"); 
          System.exit(1); 
         }
         if(command.equals("help")) {             
              
           System.out.println("Welcome to the Big Integer Calculator. You may enter any of the following:");
           System.out.println("   <int> :  A positive integer (as long as you like) will be pushed onto ");
           System.out.println("              the evaluation stack;");
           System.out.println("   add   :  The top two integers will be popped off the stack,");
           System.out.println("              added, and the sum will be pushed back onto the stack;"); 
           System.out.println("   mult   :  The top two integers will be popped off the stack,");
           System.out.println("              multiplied, and the product will be pushed back onto the stack;"); 
           System.out.println("   show  :  The contents of the stack will be printed out; or");
           System.out.println("   quit  :  The program will be terminated."); 
                
         }
         else if(command.equals("show")) { 
              
                System.out.println(); 
                try { 
                // transfer all the elements of S to T (which will be reversed)
                while(!S.isEmpty()) {
                   Node<Integer> t = S.pop(); 
                   System.out.println(BigInt.listToString(t)); 
                   T.push(t);
                }
                
                // now  transfer them back
                while(!T.isEmpty())          
                  S.push(T.pop()); 
                
                System.out.println("----------"); 
                }
                catch(IllegalListForNumber e) {        // shouldn't happen but have to catch exception...
          
                 System.out.println("Somehow an illegal List make it onto the stack!!!   I'm bailing...."); 
                 System.exit(1); 
          
                } 
                catch(StackUnderflowException e) {     // shouldn't happen but have to catch exception...
          
                 System.out.println("Stack Error during Show operation!!!   I'm bailing...."); 
                 System.exit(1); 
          
                } 
            } else if(command.equals("add")) {             // For add, pop stack twice and push sum 
              try{
                Node<Integer> t = BigInt.add(S.pop(), S.pop()); 
                System.out.println(BigInt.listToString(t));
                S.push(t); 
              }
              catch( IllegalListForNumber e ) {       // shouldn't happen, but need to catch the exception....
                 System.out.println("Somehow an illegal list made it onto the stack after adding, I'm bailing...."); 
                 System.exit(1); 
              }
              catch(StackUnderflowException e) {
          
                 System.out.println("Stack Error: too few operands for add! I'm bailing...."); 
                 System.exit(1); 
          
              } 
                
            } else if(command.equals("mult")) {     // For mult, pop stack twice and push product
              try {
                Node<Integer> t = BigInt.mult(S.pop(), S.pop()); 
                System.out.println(BigInt.listToString(t));
                S.push(t); 
              }
              catch( IllegalListForNumber e ) {       // shouldn't happen, but need to catch the exception....
                
                 System.out.println("Somehow an illegal list made it onto the stack after multiplying, I'm bailing...."); 
                 System.exit(1);
                 
              }
              catch(StackUnderflowException e) {
          
                 System.out.println("Stack Error: too few operands for multiply! I'm bailing...."); 
                 System.exit(1); 
          
              }    
            } else {
                try {
                  
                  S.push(BigInt.stringToList(command)); 

                }
                catch( IllegalStringForNumber e ) {        
                  System.out.println("Illegal number input, must be a positive number, try again......"); 
                }
                
            }      
       }
    }
}
