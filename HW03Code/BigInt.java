/* File: BigInt.java
 * Date: 2/12/13
 * Class: Boston University CS 112, Spring 2013
 * Author: Justina Choi (justinac@bu.edu) 
 * Client code: BigIntCalculator.java
 * Other associated files: Node.java, Stack.java, Stackable.java, BigInt.class
 */

@SuppressWarnings("unchecked")

public class BigInt {
    
    // returns true iff the string consists of only the characters '0' - '9'
    public static boolean legalBigInt(String s) {
        if (s.equals("")) {
            System.out.println("empty strings illegal");
            return false;
        }
        if (s.length() == 1) {
            if (s.charAt(0) == '0' || s.charAt(0) == '1' || s.charAt(0) == '2' ||
                s.charAt(0) == '3' || s.charAt(0) == '4' || s.charAt(0) == '5' ||
                s.charAt(0) == '6' || s.charAt(0) == '7' || s.charAt(0) == '8' ||
                s.charAt(0) == '9') 
                return true;
            return false;
        }
        if (legalBigInt(s.substring(1))) {
            if (s.charAt(0) == '0' || s.charAt(0) == '1' || s.charAt(0) == '2' ||
                s.charAt(0) == '3' || s.charAt(0) == '4' || s.charAt(0) == '5' ||
                s.charAt(0) == '6' || s.charAt(0) == '7' || s.charAt(0) == '8' ||
                s.charAt(0) == '9') 
                return true;
        }
        return false;
    }
    
    
    //turns a String of digits into a linked list of ints
    //with ints in reverse order than in the String (one int = one digit)
    
    public static Node<Integer> stringToList(String s) {
        if (s.equals(""))
            return null;
        String str = String.valueOf((s.charAt(s.length() - 1)));
        Node<Integer> head = new Node(str);
        
        
        for (int i = 0; i < s.length() - 1; i++) {
            head.next = new Node(s.charAt(i), head.next);
        }
        return head;
    }
    
    
    //returns a string representation of a list
    //in reverse order so that it puts the digits back into the right order
    public static String listToString(Node<Integer> p) {
        if (p == null)
            return "";
        
        String str = "";
        
        for(Node<Integer> h = p; h != null; h = h.next) {
            //System.out.println(h.item);
            str = h.item + str;
        }
        return str;
        
    } 
    
    
    // adds big ints p and q, assuming are legal big ints and that p and q
    // are not null
    public static Node<Integer> add(Node<Integer> A, Node<Integer> B) {
        int carry = 0;
        int sumdigit = Integer.valueOf(String.valueOf(A.item)) +
            Integer.valueOf(String.valueOf(B.item));
        
        
        Node<Integer> sumList = new Node<Integer>(sumdigit % 10);
        Node<Integer> pointer = sumList;
        A = A.next;
        B = B.next;
        
        if (sumdigit > 9)
            carry = 1;
        
        while(A != null || B != null) { //if both empty, then done
            pointer.next = new Node<Integer>();
            pointer = pointer.next;
            
            if (A == null) {
                sumdigit = Integer.valueOf(String.valueOf(B.item)) + carry;
                B = B.next;
            } else if (B == null) {
                sumdigit = Integer.valueOf(String.valueOf(A.item)) + carry;
                A = A.next;
            }
            
            if (A != null && B != null) {
                sumdigit = Integer.valueOf(String.valueOf(A.item)) +
                    Integer.valueOf(String.valueOf(B.item)) + carry;
                A = A.next;
                B = B.next;
            }
            
            pointer.item = sumdigit % 10;
            if (sumdigit < 10)
                carry = 0;
            if (sumdigit > 9)
                carry = 1;
        }
        
        if (carry != 0) {
            pointer.next = new Node<Integer>(carry);
            pointer = pointer.next;
        }
        return sumList;

  }
    
    
    
    public static void main(String[] args) {
        //testing legalBigInt method
        System.out.println(legalBigInt(""));
        System.out.println(legalBigInt("236934"));
        System.out.println(legalBigInt("2369w34"));
        System.out.println(legalBigInt("essiwd"));
        
        //testing stringToList method
        Node head = stringToList("123456789");
        System.out.println("stringToList method: ");
        for (Node p = head; p != null; p = p.next)
            System.out.println(p.item);
        
        //testing listToString method
        String str = listToString(head);
        System.out.println("listToString: " + str);
        
        //testing add method
        Node p = new Node(8, new Node (7, new Node (9, new Node(1))));
        Node q = new Node(4, new Node (3));
        System.out.println(listToString(add(p, q)));
        
        p = new Node(5, new Node (7, new Node (9, new Node(3))));
        q = new Node(9, new Node (2));
        System.out.println(listToString(add(p, q)));
        
        p = new Node(3, new Node(2, new Node(1)));
        q = new Node(1, new Node(2, new Node(3)));
        System.out.println(listToString(add(p, q)));
        
        p = new Node(9, new Node(9, new Node(9, new Node(9))));
        q = new Node(1);
        System.out.println(listToString(add(p, q)));
        
        p = new Node(3, new Node(9, new Node(9, new Node(1))));
        q = new Node(0, new Node(2));
        System.out.println(listToString(add(p, q)));
        
    }
    
}