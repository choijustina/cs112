public class Node {
    public int item; 
    public Node next; 
    
    Node() { 
        item = 0; 
        next = null; 
    } 
    
    Node(int n) { 
        item = n; 
        next = null; 
    } 
    
    Node(int n, Node p) { 
        item = n; 
        next = p; 
    } 
}