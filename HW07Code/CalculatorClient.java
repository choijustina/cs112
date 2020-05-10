 /*File: CalculatorClient 
  * Author: Wayne Snyder (snyder@cs.bu.edu) 
  * Date: 3/23/13 
  * Purpose: This is a test client for the calculator class created for
  * homework 7 in CS 112. It simply applies the evaluate method to lines
  * read from the terminal input. 
  * Uses files: Calculator.java, malformedExpressionException.java,
  *     undefinedVariableException.java 
  * Platform: This was developed on Mac OS X 10.6.5 in Dr. Java
  * 
  */

import java.util.Scanner; 

public class CalculatorClient {

 public static void main(String[] args) throws undefinedVariableException,
        malformedExpressionException, StackUnderflowException {
  
  Calculator C = new Calculator();
  
  Scanner scan = new Scanner(System.in);
  System.out.println("Interactive Calculator: Commands must be of the form");
  System.out.println("   assign <ident> <double>      // assign a value to a symbol");
  System.out.println("   symbols                      // list contents of symbol table"); 
  System.out.println("   eval <postfix expression>    // find value of expression");
  System.out.println("   help                         // print out this message");
  System.out.println("   quit                         // terminate the program");
  
  while(scan.hasNextLine()) {
    String line = scan.nextLine(); 
    if(line.equals("quit")) {
      System.out.println("Bye!"); 
      return; 
    }
    if(line.equals("help")) {
        System.out.println("Interactive Calculator: Commands must be of the form");
        System.out.println("   assign <ident> <double>      // assign a value to a symbol");
        System.out.println("   symbols                      // list contents of symbol table"); 
        System.out.println("   eval <postfix expression>    // find value of expression");
        System.out.println("   help                         // print out this message");
        System.out.println("   quit                         // terminate the program");
        continue; 
    }
    try {
      C.evaluate(line);  
    } catch (malformedExpressionException e) {
      System.out.println("Malformed Expression: " + line);
    } catch (undefinedVariableException e) {
      System.out.println("Undefined variable in: " + line);
    } 
  }
 }
}
