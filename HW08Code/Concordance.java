/* Name: Concordance.java
 * Author: Justina Choi
 * Class: CS112 - Snyder; Homework 8
 * Date: 4/17/13
 * Interface: FrequencyCounter.java
 * Purpose: Creates a hash table using the "separate chaining" technique. The nodes
 *          in the buckets have a key and a value, which is the number of times
 *          that key has been inserted into the table.
 * Resources: page 464-465 of "Algorithms" by Sedgewick, Wayne (4th ed);
 *            StringHash.java file written by Wayne Snyder
 * Notes:
 */

public class Concordance implements FrequencyCounter {
    
    // first node in the linked list
    private Node first;
    // linked-list Node
    private class Node {
        int key;
        int val;
        Node next;
        public Node(int key, int val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    
    private int N;    // number of key-value pairs
    private int M;    // hash table size
    private Node[] hashtable;    // array of Node objects
    
    // creates M linked lists
    public Concordance() {
        this.M = 137;
        hashtable = new Node[M];
    }
    
    public int getNumPairs() {
        return N;
    }
    
    private int hash(String s) {
        int sum = 1;
        for (int i = 0; i < s.length(); i++)
            sum *= s.charAt(i);
        return (int) (Math.abs(sum) % M);
    }
    
    public String printString() {
        String str = "";
        for (int i = 0; i < M; i++)
            str = str + hashtable[i];
        return str;
    }
    
    public void insert(String key) {
        int function = hash(key);
        N++;
        for (int i = 0; i < M; i++) {
            if (hashtable[i] == null) {
                hashtable[i] = new Node(function, 1, null);
                return;
            } else if (hashtable[i].key == function) {
                hashtable[i].val++;
                return;
            }
        }
    }
    
    public int frequency(String key) {
        int function = hash(key);
        for (int i = 0; i < M; i++) {
            if (hashtable[i] == null)
                return 0;
            else if (hashtable[i].key == function)
                return hashtable[i].val;
            }
        return 0;
    }
    
    // UNIT TEST
    public static void main(String [] args) {
        
        String s1 = "today";
        String s2 = "computers";
        String s3 = "bananas";
        String s4 = "sweater";
        String s5 = "keyboard";
        String s6 = "lauren";
        String s7 = "river";
        
        
        Concordance C = new Concordance();
        C.insert(s1);
        C.insert(s2);
        C.insert(s3);
        C.insert(s4);
        C.insert(s5);
        C.insert(s6);
        C.insert(s7);
        
        System.out.println("frequency of " + s1 + ": " + C.frequency(s1));
        System.out.println("frequency of " + s2 + ": " + C.frequency(s2));
        System.out.println("frequency of " + s3 + ": " + C.frequency(s3));
        System.out.println("frequency of " + s4 + ": " + C.frequency(s4));
        System.out.println("frequency of " + s5 + ": " + C.frequency(s5));
        System.out.println("frequency of " + s6 + ": " + C.frequency(s6));
        System.out.println("frequency of " + s7 + ": " + C.frequency(s7));
        
    }
}
    