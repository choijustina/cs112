/* File: BSTSymbolTable.java
  * Author: Justina Choi (justinac@bu.edu)
  * Date: 4/3/2013
  * Purpose: CS112 HW7 Part 1: a generic binary search tree symbol table;
  *          will be used in HW7 Part 2 to store Strings and Doubles
  * Implements: SymbolTable.java
  */ 

public class BSTSymbolTable<Key extends Comparable<Key>, Value> implements 
    SymbolTable<Key, Value> {
    
    private Node first;      // first node in the linked list
    
    /* an "inner class" for Node
     * citation: pg 375 of "Algorithms" (Sedgewick/Wayne) textbook
     */
    private class Node {     //linked-list node
        Key key;
        Value val;
        Node left, right;
        Node next;           // a pointer to the next node for when traversing
        int N;               // # nodes in subtree
        
        public Node (Key key, Value val, Node left, Node right, Node next) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
            this.N = N;
        }
        public Node (Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.left = null;
            this.right = null;
            this.next = null;
            this.N = N;
        }
    }
    
    /* put(Key key, Value val) - searches for the key; if found, updates value or
     * grows table if new
     * citation: pg 399 of "Algorithms" (Sedgewick/Wayne) textbook
     */
    public void put(Key key, Value val) {
        first = put(first, key, val);
    }
    // aux method
    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1);
        
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    /* get(Key key) - returns the value associated with the key; throws an
     * exception if the key is not found within the symbol table
     * citation: pg 399 of "Algorithms" (Sedgewick/Wayne) textbook 
     */
    public Value get(Key key) throws undefinedVariableException {
        return get(first, key);
    }
    // aux method
    private Value get(Node x, Key key) throws undefinedVariableException {
        
        if (x == null)
            throw new undefinedVariableException("undefined variable!");

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.val;
    }
    
    /* contains(Key key) - returns true if the key is in the symbol table
     * and false if not found
     * used get() method as basis
     */
    public boolean contains(Key key) {
        return contains(first, key);
    }
    private boolean contains(Node x, Key key) {
        if (x == null)
            return false;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return contains(x.left, key);
        else if (cmp > 0)
            return contains(x.right, key);
        else
            return true;
    }
    
    /* delete(Key key) - deletes the key from the symbol table and does nothing
     * if the key is not in the table
     * citation: pg 411 of "Algorithms" (Sedgewick/Wayne) textbook
     */
    public void delete(Key key) {
        first = delete(first, key);
    }
    private Node delete(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else {
            if (x.right == null)
                return x.left;
            if (x.left == null)
                return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    
    /* isEmpty() - boolean method to see if symbol table is/isn't empty
     */
    public boolean isEmpty() {
        if (first == null)
            return true;
        return false;
    }
    
    /* size() - returns the size of the symbol table as an int
     * citation: pg 398 of "Algorithms" (Sedgewick/Wayne) textbook
     */
    public int size() {
        return size(first);
    }
    private int size(Node x) {
        if (x == null)
            return 0;
        return x.N;
    }
    
    /* listKeys() - threads a linked-list through the nodes of the symbol table which
     * follows an inorder traversal (left subtree, root, right subtree);
     * citation: CS112 Lecture 15 (BU-Prof.Snyder)
     */
    public void listKeys() {
        if (isEmpty())
            System.out.println("Symbol Table is empty!");
        traverse(first);
        System.out.println();
    }
    private void traverse(Node t) {
        if (t != null) {
            traverse(t.left);
            visit(t);
            traverse(t.right);
        }
    }
    private void visit(Node t) {
        System.out.print(t.key + " ");
    }
    
    
    /* min() - used in the delete method; traverses the left-most node to find
     * the minimum node
     * deleteMin() - used in the delete method; traverses the left-most node to 
     * delete the minimum node in the tree
     * citation: pg 407 & 411 (respectively) of "Algorithms" (Sedgewick/Wayne)
     * textbook
     */
    private Node min(Node x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }
    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        return x;
    }
        
    
    // UNIT TEST
    public static void main(String[] args) {
        BSTSymbolTable<String,Double> ST = new BSTSymbolTable<String,Double>();
        System.out.println("isEmpty? T " + ST.isEmpty());
        
        ST.put("a", 4.5);
        ST.put("b", 7.1);
        ST.put("l", 5.32);
        ST.put("c", 20.2);
        ST.put("e", 1.32);
        
        System.out.println("isEmpty? F " + ST.isEmpty());
        System.out.println("size should be 5: " + ST.size());
        
        System.out.println("contains a? T " + ST.contains("a"));
        System.out.println("contains z? F " + ST.contains("z"));
        
        try {
            double y = ST.get("a");
            System.out.println("y should be 4.5: " + y);
            ST.get("z");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // testing updating value of "a"
        ST.put("a", 14.2);
        
        try {
            double x = ST.get("a");
            System.out.println("value of a (14.2): " + x);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        ST.listKeys();
        ST.delete("a");
        System.out.println("contains a? F " + ST.contains("a"));
        ST.listKeys();
        System.out.println("size should be 4: " + ST.size());
    }
    }

class undefinedVariableException extends Exception {
    public undefinedVariableException(String msg) {
        super(msg);
    }
}