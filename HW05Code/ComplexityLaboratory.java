/* File: ComplexityLaboratory.java
 * Author: Justina Choi (justinac@bu.edu)
 * Date: 2/26/13
 * Class: CS 112, Spring 2013, HW 5
 * Purpose: Compares four algorithms (linear search, binary search, selection
 *          sort, and insertion sort) using worst case scenarios and seeing how
 *          they compare asymptotically.
 * 
 * Associated files: Grapher.java, JCanvas.java  (must be run in same directory)
 * Referenced: Algorithms textbook 
 */

import java.awt.*;

public class ComplexityLaboratory {
    
    private static int counter = 0;  // keeps track of number of comparisons
    
    
    /* generateInorderArray - creates an array containing {0, 1, 2, ..., size-1}
     * i.e., A[i] = i; used as the worst case scenario for search algorithms
     */
    private static int[] generateInorderArray (int size) {
        int[] A = new int[size];
        for (int i = 0; i < size; i++)
            A[i] = i;
        return A;
    }
    
    
    /* generateReversedArray - creates an array containing {size-1, ..., 2, 1, 0}
     * i.e., A[i] = (size-1)-i; used as the worst case scenario for sort algorithms
     */
    private static int[] generateReversedArray(int size) {
        int[] A = new int[size];
        for (int i = 0; i < size; i++)
            A[i] = (size-1)-i;
        return A;
    }
    
    
    /* less - adds to counter & returns true if int i < int j; referenced from
     * homework assignment page
     */
    private static boolean less(int i, int j) {
        counter++;
        return (i < j);
    }

    
    /* greater - adds to counter & returns true if int i > int j
     */
    private static boolean greater(int i, int j) {
        counter++;
        return (i > j);
    }
    
    
    /* exch - a[i] and a[j] switch places; referenced from Algorithms textbook
     * page 245
     */
    private static void exch(int[] a, int i, int j) {
        counter++;
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    
    /* equals - adds to counter and returns true if int i == int j 
     */
    private static boolean equals(int i, int j) {
        counter++;
        if (i == j) 
            return true;
        return false;
    }
    
    
    
    /* insertionSort - starting from left to right, considers each entry in the
     * array one at a time, sorting each one into the array of items already
     * sorted; running time depends upon the intial order of the items in the
     * input (description taken from page 250 of Algorithms textbook; actual
     * algorithm from Snyder's HW5 assignment notes)
     */
    private static void insertionSort(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }
    
    
    /* selectionSort - a simple sorting algorithm which finds the smallest item in
     * the array and exchanges it with the first entry, then finds the next
     * smallest item and exchanges it with the second entry until the entire array
     * is sorted (page 248 of Algorithms textbook; actual algorithm from Snyder's
     * HW5 assignment notes)
     */
    private static void selectionSort(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
        }
    }
    
    
    /* linearSearch - takes int[] a and int key as parameters to find key within
     * the array; returns true if key is in the array and false otherwise
     * processes each piece of input one by one; based on a single
     * for loop
     */
    private static boolean linearSearch(int[] a, int key) {
        for (int i = 0; i < a.length; i++) {
            if (equals(a[i], key))
                return true;
        }
        return false;
    }
    
    
    /* binarySearch - takes a sorted int[] a and int key as parameters and
     * returns true if key is in the array and false otherwise; processes the
     * array by continually splitting the array into halves until key is found
     * (from HW1 solution set)
     */
    private static boolean binarySearch(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        
        while (lo <= hi) {
            // key is in a[lo..hi] or not present
            int mid = lo + (hi - lo) / 2;
            if (less(key, a[mid]))
                hi = mid - 1;
            else if (greater(key, a[mid]))
                lo = mid + 1;
            else     // if not < and not > must be =!
                return true;
        }
        return false;
    }
    
    
    //UNIT TEST
    public static void main(String[] args) {
        
        Grapher G = new Grapher();
        
        // variables to draw the line
        int oldX = 0;
        int oldY = 0;
        int newX = 0;
        int newY = 0;
        
        G.setPaint(Color.red);
        G.drawString("Insertion Sort", 90, 80);
        //System.out.println("Insertion Sort");
        for (int N = 1; N <= 100; N++) {
            counter = 0;
            insertionSort(generateReversedArray(N));
            newX = N;
            newY = counter;
            G.drawLine(oldX, oldY, newX, newY);
            oldX = newX;
            oldY = newY;
            //System.out.println("(" + N + ", " + counter + ")");
        }
        
        oldX = 0;  // reset to 0 for new line starting from origin
        oldY = 0;
        
        G.setPaint(Color.green);
        G.drawString("Selection Sort", 90, 75);
        //System.out.println("Selection Sort");
        for (int N = 1; N <= 100; N++) {
            counter = 0;
            selectionSort(generateReversedArray(N));
            newX = N;
            newY = counter;
            G.drawLine(oldX, oldY, newX, newY);
            oldX = newX;
            oldY = newY;
            //System.out.println("(" + N + ", " + counter + ")");
        }
        
        oldX = 0;  // reset to 0 for new line starting from origin
        oldY = 0;
        
        G.setPaint(Color.magenta);
        G.drawString("Binary Search", 90, 70);
        //System.out.println("Binary Search");
        for (int N = 1; N <= 100; N++) {
            counter = 0;
            binarySearch(generateInorderArray(N), N);
            newX = N;
            newY = counter;
            G.drawLine(oldX, oldY, newX, newY);
            oldX = newX;
            oldY = newY;
            //System.out.println("(" + N + ", " + counter + ")");
        }
        
        oldX = 0;  // reset to 0 for new line starting from origin
        oldY = 0;
        
        G.setPaint(Color.cyan);
        G.drawString("Linear Search", 90, 65);
        //System.out.println("Linear Search");
        for (int N = 1; N <= 100; N++) {
            counter = 0;
            linearSearch(generateInorderArray(N), N);
            newX = N;
            newY = counter;
            G.drawLine(oldX, oldY, newX, newY);
            oldX = newX;
            oldY = newY;
            //System.out.println("(" + N + ", " + counter + ")");
        }
        
        oldX = 0;  // reset to 0 for new line starting from origin
        oldY = 0;
        
        G.setPaint(Color.blue);
        G.drawString("N^2", 90, 60);
        for (int N = 1; N <= 100; N++) {
            newX = N;
            newY = N*N;
            G.drawDashedLine(4, oldX, oldY, newX, newY);
            oldX = newX;
            oldY = newY;
            //System.out.println("(" + newX + ", " + newY + ")");
        }
        

    }
        
}