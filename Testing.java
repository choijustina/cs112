import java.util.Arrays;


public class Testing {
    public static void main (String [] args) {
        Node head = new Node(28);
        head.next = new Node(17, new Node(15, new Node(12)));
        printList(head);
        Node newList = copy(head);
        System.out.println("printing copy: ");
        printList(newList);
    }
    
    
    public static void printList(Node p) {
        if (p != null) {
            System.out.println(p.item);
            printList(p.next);
        }
    }
    
    public static Node construct (Node p) {
        if (p==null)
            return null;
        else {
            p.next = construct(p.next);
            return p;
        }
    }
    
    public static Node copy(Node p) {
        if (p == null)
            return null;
        else 
            return new Node(p.item, copy(p.next));
    }
}