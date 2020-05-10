/* File: Calculator.java
  * Author: Justina Choi (justinac@bu.edu)
  * Date: 4/3/2013
  * Purpose: CS112 HW7 Part 2: a calculator for Reverse Polish
  *          Notation (RPN) expressions which are created from a
  *          syntax tree for an arithmetic expression using postorder
  *          traversal
  * Associated files: BSTSymbolTable.java
  */

public class Calculator {
    
    private Stack<Double> S = new Stack<Double>();
    private BSTSymbolTable<String,Double> ST = new BSTSymbolTable <String,Double>();
    
    public void evaluate(String s) throws undefinedVariableException,
        malformedExpressionException, StackUnderflowException {
        
        String[] tokens = s.split("[ ]+");
            // tokens is now an array of the tokens in the input
        
        if (tokens == null) {     // i.e., s was an empty string
            throw new malformedExpressionException();
        }
        else if (tokens[0].equals("assign")) {  // assign <identifer> <double>
            if ( !(isIdentifier(tokens[1])) || !(isDouble(tokens[2])) ) 
                throw new malformedExpressionException();
            else {
                String firstToken = tokens[1];
                double secondToken = Double.parseDouble(tokens[2]);
                ST.put(firstToken, secondToken);
            }
        }
        else if (tokens[0].equals("symbols")) { // lists contents of symbol table
            if (tokens.length > 1)
                throw new malformedExpressionException();
            else
                ST.listKeys();
        }
        else if (tokens[0].equals("eval")) {
            // evaluate the rest of the tokens as an RPN expression, using a stack
            int N = tokens.length;
            
            // loop to trace through each token in the array
            for (int i = 1; i < N; i++) {
                if (isDouble(tokens[i])) {          // the token is a double
                    
                    // if i is 1, 2, or even - to ensure order of expression
                    if (i == 1 || i == 2 || i%2 == 0) {
                        double value = Double.parseDouble(tokens[i]);
                        S.push(value);
                    } else
                        throw new malformedExpressionException();
                }
                else if (isIdentifier(tokens[i])) { // the token is an identifer
                    
                    // if i is 1, 2, or even - to ensure order of expression
                    if (i == 1 || i == 2 || i%2 == 0) {
                        S.push(ST.get(tokens[i]));
                        ST.delete(tokens[i]);
                    } else
                        throw new malformedExpressionException();
                }
                else if ( tokens[i].equals("+") || tokens[i].equals("-") || 
                         tokens[i].equals("*") || tokens[i].equals("/") ) {
                    
                    if (i%2 == 0)
                        throw new malformedExpressionException();
                    
                    double double1 = S.pop(); // number that was expressed second
                    double double2 = S.pop(); // number that was expressed first
                    double result;
                    if (tokens[i].equals("+"))
                        result = double2 + double1;
                    else if (tokens[i].equals("-"))
                        result = double2 - double1;
                    else if (tokens[i].equals("*"))
                        result = double2*double1;
                    else  // tokens[i].equals("/")
                        result = double2/double1;
                    
                    S.push(result);
                }
            }
            System.out.println(S.pop());
            
        } else
            throw new malformedExpressionException();      
        
        
    }
    
    public boolean isDouble(String str) {
        try {
        double value = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    public boolean isIdentifier(String str) {
        if ( (str.equals("assign")) || (str.equals("eval")) || (str.equals("symbols")) ||
             (str.equals("num_chars")) || (Character.isDigit(str.charAt(0))) )
            return false;
        else if ( (Character.isLetter(str.charAt(0))) )
            return true;
        else
            return false;
    }
    
    
    
    // UNIT TEST
    public static void main(String[] args) throws undefinedVariableException,
        malformedExpressionException, StackUnderflowException {
        
        Calculator C = new Calculator();
        
        // testing additional methods (isDouble and isIdentifier)
        System.out.println("T: " + C.isDouble("23"));
        System.out.println("F: " + C.isDouble("23kd93"));
        System.out.println("F: " + C.isDouble("sldfjsi"));
        System.out.println("T: " + C.isIdentifier("happy"));
        System.out.println("F: " + C.isIdentifier("eval"));
        System.out.println("F: " + C.isIdentifier("34it"));
        
        
        try {
            System.out.print("Symbol Table should be empty: ");
            C.evaluate("symbols");
            C.evaluate("assign a 4.0");
            C.evaluate("assign x 2.0");
            C.evaluate("assign c 6.2");
            C.evaluate("assign pi 3.14");
            C.evaluate("assign d 291.2");
            C.evaluate("assign alpha 23");
            
            System.out.print("Listing symbols: ");
            C.evaluate("symbols");
            
            System.out.print("4.0 + 2.0 = 6.0 = ");
            C.evaluate("eval a x +");
            
            //C.evaluate("eval 8.2 2.0 / 4.8 3.4 *");
            
            System.out.print("Listing symbols: ");
            C.evaluate("symbols");
            
            System.out.print("6.2 - 23 = -16.8 = ");
            C.evaluate("eval c alpha -");
            
            System.out.print("9.0/3.0 = 3.0 = ");
            C.evaluate("eval 9.0 3.0 /");
            
//            System.out.print("exception here: ");
//            C.evaluate("eval z 3.0 -");
            
            System.out.print("exception here: ");
            C.evaluate("eval 8.2 2.0 / 4.8 3.4 *");
            
        } catch (malformedExpressionException e) {
            System.out.println("Malformed Expression");
        } catch (undefinedVariableException e) {
            System.out.println("Undefined variable");
        } catch (StackUnderflowException e) {
            throw new malformedExpressionException();
        }
    }
}

class malformedExpressionException extends Exception {
    public malformedExpressionException() {
    }
}
        