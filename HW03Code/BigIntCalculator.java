/* File: BigIntCalculator.java
 * Author: Wayne Snyder
 * Date: 2/6/13
 * Purpose: This program is a client program for a set of big integer methods to be
 * packaged as static method in a class BigInt; see HW 03, Spring 2013 for details. 
 * Associated files: Stack.java, Stackable.java, BigInt.java
 */

import java.io.*;
import java.util.*;

public class BigIntCalculator {
    
    public static void main (String[] args) {  
        
        Scanner sc = new Scanner(System.in);
        
        BigInt B = new BigInt(); 
        
        Stackable<Node<Integer>> S = new Stack<Node<Integer>>(); 
        Stackable<Node<Integer>> T = new Stack<Node<Integer>>(); 
        
        
        while (sc.hasNext()) {     
            String command = sc.next();                 // read next line of file
            
            try { 
                if(command.equals("quit")) { 
                    System.out.println("Goodbye!"); 
                    System.exit(1); 
                }
                if(command.equals("help")) {             
                    
                    System.out.println("Welcome to the Big Integer Calculator. You may enter any of the following:");
                    System.out.println("   <int> :  An integer (as long as you like) will be pushed onto ");
                    System.out.println("              the evaluation stack;");
                    System.out.println("   add   :  The top two integers will be popped off the stack,");
                    System.out.println("              added, and the sum will be pushed back onto the stack;"); 
                    System.out.println("   show  :  The contents of the stack will be printed out; or");
                    System.out.println("   quit  :  The program will be terminated."); 
                    
                }
                else if(command.equals("show")) { 
                    
                    System.out.println(); 
                    
                    // transfer all the elements of S to T (which will be reversed)
                    while(!S.isEmpty()) { 
                        System.out.println(BigInt.listToString(S.top())); 
                        T.push(S.pop());
                    }
                    
                    // now print out the numbers as transfer them back
                    while(!T.isEmpty())          
                        S.push(T.pop()); 
                    
                    System.out.println("----------"); 
                }
                else if(command.equals("add")) {             // For add, pop stack twice and push sum 
                    
                    S.push(BigInt.add(S.pop(), S.pop()));
                    System.out.println(BigInt.listToString(S.top())); 
                    
                } else if(BigInt.legalBigInt(command)) {     // verify that is string of digits
                    // if it is, push on stack
                    S.push(BigInt.stringToList(command));
                    System.out.println(BigInt.listToString(S.top()));
                    
                } else {
                    
                    System.out.println("Illegal input, re-enter!"); 
                    
                } 
            }
            catch(StackUnderflowException e) {
                
                System.out.println("Stack Error: too few operands for add! I'm bailing...."); 
                System.exit(1); 
                
            }
            
        }
    }
}

