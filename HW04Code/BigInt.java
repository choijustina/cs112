/* File: BigInt.java (Recursive version for HW4)
 * Date: 2/20/13
 * Author:  Justina Choi (justinac@bu.edu)
 * Class: CS 112, Spring 2013
 * References: Used Prof. Snyder's iterative version of BigInt.java
 *             to edit into a recursive version of BigInt.java
 * Purpose: a class which is a container for several static methods
 *          for manipulating strings of digits as linked lists, and
 *          adding and multiplying them.
 * Related classes:  interface Stackable.java; Stack.java; Node.java
 */

public class BigInt  { 
    
    /* stringToList - turn a String of digits into a reverse list of
     * ints (one int = one digit); throws an exception if string not entirely
     * composed of digits
     */
    public static Node<Integer> stringToList(String s)
        throws IllegalStringForNumber {  
        
        for (int i = 0; i < s.length(); i++) {
            if(!Character.isDigit(s.charAt(i)))
                throw new IllegalStringForNumber();
        }
        
        return stringToListAux(s,s.length() - 1);
    }
    
    /* stringToListAux - helper method to stringToList
     */
    private static Node<Integer> stringToListAux(String s, int i)
        throws IllegalStringForNumber {
        
        if (i < 0)       //base case
            return null;    
        
        else {
            Node<Integer> Q = stringToListAux(s, i - 1);
            Q = new Node<Integer>(Character.digit(s.charAt(i),10), Q);
            return Q;
        }
    }
    
    
    /* listToString - returns a String representation of a list of
     * digits, un-reversing them
     */
    public static String listToString(Node<Integer> p)
        throws IllegalListForNumber {
        
        String s = "";
        
        if (p == null) {    // base case
            return "";
        }
        
        s = listToString(p.next) + String.valueOf(p.item);
        return s;
    }
    
    /* add - adds two big integers represented by linked lists of
     * digits assumes that the Nodes passed in as parameters are not
     * null (reversed, so tens digit is at front of list)
     * referenced: Prof. Snyder's "Hints/Suggestions..." section of HW assignment
     */
    public static Node<Integer> add(Node<Integer> p, Node<Integer> q) {
        return addWithCarry(p, q, 0);
    }
    
    /* addWithCarry - helper method for add
     */
    private static Node<Integer> addWithCarry(Node<Integer> p, Node<Integer> q,
                                              int k) {
        int sum;
        
        if (p == null) {
            sum = q.item + k;
            q = q.next;
        } else if (q == null) {
            sum = p.item + k;
            p = p.next;
        } else {   // neither are null
            sum = p.item + q.item + k;
            p = p.next;
            q = q.next;
        }
        
        int sumDigit = sum % 10;
        k = sum / 10;               // int k is the carry
        
        // base case
        if (p == null && q == null) {
            Node<Integer> A = new Node<Integer>(sumDigit);
            if (k > 0)
                A.next = new Node<Integer>(k);
            return A;
        }
        
        while (p != null || q != null) {            // if there is another digit
            if (p == null) {
                Node<Integer> A = addWithCarry(null, q, k);
                A = new Node<Integer>(sumDigit, A);
                return A;
                
            } else if (q == null) {
                Node<Integer> A = addWithCarry(p, null, k);
                A = new Node<Integer>(sumDigit, A);
                return A;
                
            } else {                                // neither are null
                Node<Integer> A = addWithCarry(p, q, k);
                A = new Node<Integer>(sumDigit, A);
                return A;
            }
        }
        return null;
    }
    
    
    /* mult - multiplies big ints p and q; assumes q is not null
     * referenced: Prof. Snyder's "Hints/Suggestions..." section of HW assignment
     */
    public static Node<Integer> mult(Node<Integer> p, Node<Integer> q) {
        Node<Integer> M = multByScalarAddCarry(p, q.item, 0);
        q = q.next;
        int counter = 1;
        
        while (q != null) {
            counter = counter * 10;
            Node<Integer> N = multByScalarAddCarry(p, q.item*counter, 0);
            M = add(M, N);
            q = q.next;
        }
        return M;
    }
    
    /* multByScalarAddCarry - helper method for mult
     */
    private static Node<Integer> multByScalarAddCarry(Node<Integer> p, int digit,
                                                      int carry) { 
        if (p == null) {
            if (carry > 0) {
                Node<Integer> M = new Node<Integer>(carry);
                return M;
            }
            return null;
        }
        
        int total = (digit * p.item) + carry;
        carry = total / 10;
        int productDigit = total % 10;
        p = p.next;
        
        
        
        Node<Integer> M = multByScalarAddCarry(p, digit, carry);
        M = new Node<Integer>(productDigit, M);
        return M;
    }
    
    
    
    
    /* printList - just a debugging print method for LLs
     */
    private static void printList(Node<Integer> p) {
        for(Node<Integer> q = p; q != null; q = q.next)
            System.out.print(q.item + " -> ");
        System.out.println("."); 
    }
    
    
    // UNIT TEST
    public static void main(String [] args) throws IllegalStringForNumber,
        IllegalListForNumber {
        BigInt B = new BigInt(); 
        
        
        System.out.println("\nTesting printList...."); 
        
        printList( null ); 
        printList( new Node<Integer>( 1, new Node<Integer>(2))); 
        
        System.out.println("\nTesting stringToList...."); 
        
        try {
            Node<Integer> q = stringToList("12345"); 
            printList(q); 
            q = stringToList(""); 
            printList(q); 
            q = stringToList("1"); 
            printList(q); 
            q = stringToList("12345123451234512345123451234512345123451234512345"); 
            printList(q);
        }
        catch (IllegalStringForNumber e) {
            System.out.println("IllegalStringForNumber exception!");
        }
        
        
        System.out.println("\nTesting printlnBigInt...."); 
        
        try {
            Node<Integer> q = stringToList("12345"); 
            System.out.println(listToString(q)); 
            q = stringToList(""); 
            System.out.println(listToString(q)); 
            q = stringToList("1"); 
            System.out.println(listToString(q)); 
            q = stringToList("12345123451234512345123451234512345123451234512345"); 
            System.out.println(listToString(q));
        }
        catch (IllegalListForNumber e) {
            System.out.println("IllegalListForNumber exception!");
        }
        
        
        System.out.println("\nTesting add...."); 
        int i = 1, j = 2, k = i+j; 
        Node<Integer> q = add(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + " + " + j + " = " + k);
        System.out.println(listToString(q));
        
        i = 2; j = 3; k = i+j;
        q = add(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + " + " + j + " = " + k);
        System.out.println(listToString(q)); 
        
        i = 13; j = 27; k = i+j;
        q = add(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + " + " + j + " = " + k);
        System.out.println(listToString(q));
        
        i = 41; j = 7; k = i+j;
        q = add(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + " + " + j + " = " + k);
        System.out.println(listToString(q));
        
        i = 98; j = 2; k = i+j;
        q = add(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + " + " + j + " = " + k);
        System.out.println(listToString(q));
        
        
        i = 2349474; j = 482723; k = i+j;
        q = add(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + " + " + j + " = " + k);
        System.out.println(listToString(q)); 
        
        
        i = 9999; j = 1; k = i+j;
        q = add(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + " + " + j + " = " + k);
        System.out.println(listToString(q)); 
        
        i = 99898; j = 0; k = i+j;
        q = add(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + " + " + j + " = " + k);
        System.out.println(listToString(q)); 
        
        
        j = 234947400; i = 482723; k = i+j;
        q = add(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + " + " + j + " = " + k);
        System.out.println(listToString(q)); 
        
        
        j = 9999; i = 1; k = i+j;
        q = add(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + " + " + j + " = " + k);
        System.out.println(listToString(q)); 
        
        j = 99898; i = 0; k = i+j;
        q = add(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + " + " + j + " = " + k);
        System.out.println(listToString(q));     
        
        q = add(stringToList("2023842985345029340235983450923029384802934"),
                stringToList("203940293424230420394203940293424230420394")); 
        System.out.println("Should be:" );
        System.out.println("2227783278769259760630187391216453615223328");
        System.out.println("output: ");
        System.out.println(listToString(q));
        
        
        System.out.println("\nTesting mult....");
        i = 3; j = 2; k = i*j;
        q = mult(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + "*" + j + " = " + k);
        System.out.println(listToString(q));
        
        i = 2; j = 6; k = i*j;
        q = mult(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + "*" + j + " = " + k);
        System.out.println(listToString(q));
        
        i = 25; j = 3; k = i*j;
        q = mult(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + "*" + j + " = " + k);
        System.out.println(listToString(q));
        
        i = 99; j = 3; k = i*j;
        q = mult(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + "*" + j + " = " + k);
        System.out.println(listToString(q));
        
        i = 25; j = 10; k = i*j;
        q = mult(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + "*" + j + " = " + k);
        System.out.println(listToString(q));
        
        i = 234; j = 23; k = i*j;
        q = mult(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + "*" + j + " = " + k);
        System.out.println(listToString(q));
        
        i = 900; j = 65; k = i*j;
        q = mult(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + "*" + j + " = " + k);
        System.out.println(listToString(q));
        
        i = 3425; j = 588; k = i*j;
        q = mult(stringToList(String.valueOf(i)), stringToList(String.valueOf(j))); 
        System.out.println(i + "*" + j + " = " + k);
        System.out.println(listToString(q));
        
    }
}


// Exceptions

/* exception raised when LL does not correspond to a positive integer in
 * listToString method
 */
class IllegalListForNumber extends Exception {
    public IllegalListForNumber() {
    }
}

/* exception raised when string does not correspond to a positive integer in
 * stringToList method
 */
class IllegalStringForNumber extends Exception {
    public IllegalStringForNumber() {
    }
}