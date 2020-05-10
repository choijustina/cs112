/* File: SortingLaboratory.java
 * Modified by: Justina Choi (justinac@bu.edu), initial version by Wayne Snyder
 * Date: 2/27/13
 * Class: CS 112, Spring 2013
 * Purpose: This is the code for HW 06; it uses the Grapher class to show the asymptotic complexity
 *          of a number of different sorting algorithms for worst case: 
 *          Selection Sort, Insertion Sort, Merge Sort, and Quick Sort. 
 *          HW 06 involves adding the average case analysis and then
 *          a GUI that allows various experiments to be run from the window. 
 * 
 * Associated files: Grapher.java, JCanvas.java
 */

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;

public class SortingLaboratory {
    
    // Various parameters for running the program
    
    private static final boolean doUnitTest = false;     // print out counts for each algorithm to test
    
    private static boolean worstCase = true;       // determines if do worst or average case, changes to false
    
    private static final int upperBound = 30;            // run experiments by sizes up to this limit
    
    private static final int increment = 1;              // run experiment for sizes by this increment
    
    // Since need to refer to the graph in the methods, have to put here. 
    
    private static Grapher graph; 
    
    // counter for number of comparisons
    
    private static int counter = 0;
    
    // Random class so can generate random integers
    
    private static Random R = new Random();
    
    // the following methods simply do the indicated comparisons and increment the counter each time
    // versions are provided for ints only
    
    private static Boolean less(int i, int j) {
        ++counter;
        return ( i < j ); 
    }
    
    private static Boolean greater(int i, int j) {
        ++counter;
        return ( i > j ); 
    }
    
    private static Boolean equal(int i, int j) {
        ++counter;
        return ( i == j ); 
    } 
    
    
    // exch swaps the two slots in the array, used in all but merge sort
    
    private static void exch(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    } 
    
    //  The following two methods create worst cases for the sorting algorithms and random lists for average case
    //       Reversed lists are worst cases for selection, insertion, and quick sort
    //       Special-case "unmerged" lists are worst cases for merge sort
    
    
    // generate a random list to test the average case
    
    private static int[] genRandomList(int size) {
        int[] randomList = new int[size];
        for (int i = 0; i < size; i++)
            randomList[i] = R.nextInt(100);
        return randomList;
    }
    
    
    // generate an array of elements in order, A[i] contains i
    
    private static int[] genReversedArray( int size ) {
        int A [] = new int[size];
        
        for(int i = 0; i < size; i++) 
            A[i] = size-1-i;
        
        return A;   
    }
    
    
    // Create a worst-case array for merge sort by reversing a sorted list, doing opposite of merge sort
    
    private static int [] unMergeSort( int SIZE) {
        int [] A = new int[SIZE]; 
        for(int i = 0; i < SIZE; ++i)           // create sorted list
            A[i] = i; 
        int [] aux = new int[A.length];
        unMergeSort(A,aux,0,A.length-1);
        return A; 
    }
    
    private static void unMergeSort(int A [], int [] aux, int lo, int hi) {
        
        if(hi <= lo)          // base case, size is <= 2
            return;
        
        int mid = lo + (hi - lo)/2;    // m is rightmost element of left side
        
        distribute(A,aux, lo, mid, hi);
        
        unMergeSort(A,aux,lo,mid);
        unMergeSort(A,aux,mid+1,hi); 
    }
    
    private static void distribute(int A[], int aux[], int lo, int mid, int hi) {
        
        for(int i = lo; i <= hi; ++i)     // copy A to auxiliary array
            aux[i] = A[i]; 
        
        for(int i = lo, j = lo; i <= mid; i++, j += 2)       // fill  A[lo...m] with aux[lo], aux[lo + 2], etc.
            A[i] = aux[j];
        
        for(int i = mid+1, j = lo+1; i <= hi; i++, j += 2)      // fill A[m+1...hi] with aux[lo+1], aux[lo + 3], etc.
            A[i] = aux[j];
        
    } 
    
    
    
// The four sorting algorithms are all from Algorithms, by Sedgewick and Wayne,
    
    
    // insertion sort from Algorithms, Sedgewick and Wayne, p.251
    
    private static void insertionSort(int[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    } 
    
    
    // insertion sort from Algorithms, Sedgewick and Wayne, p.249
    
    private static void selectionSort(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
            //  assert isSorted(a, 0, i);
        }
        //  assert isSorted(a);
    }
    
    // Merge sort from textbook pp.271 - 273, with Comparable changed to int
    
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        
        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }
        
        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }
    
    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void mergeSort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }
    
    private static void mergeSort(int[] a) {
        int[] aux = new int[a.length];
        mergeSort(a, aux, 0, a.length-1);
    }
    
    // Quick sort from textbook pp.289 - 91, with Comparable changed to int
    
    private static void quickSort(int[] a) {
        // StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }
    
    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(int[] a, int lo, int hi) { 
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
        //   assert isSorted(a, lo, hi);
    }
    
    // partition the subarray a[lo .. hi] by returning an index j
    // so that a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
    private static int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) { 
            
            // find item on lo to swap
            while (less(a[++i], v))
                if (i == hi) break;
            
            // find item on hi to swap
            while (less(v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            
            // check if pointers cross
            if (i >= j) break;
            
            exch(a, i, j);
        }
        
        // put v = a[j] into position
        exch(a, lo, j);
        
        // with a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }
    
    // averages the number of comparisons (returns averaged counter)
    private static int determineAverage(String alg, int size) {
        int sumOfIterations = 0;
        for (int i = 0; i < 1000; i++) {
            counter = 0;
            if (alg.equals("Selection Sort")) {
                selectionSort(genRandomList(size));
                sumOfIterations = sumOfIterations + counter;
            }
            else if (alg.equals("Insertion Sort")) {
                insertionSort(genRandomList(size));
                sumOfIterations = sumOfIterations + counter;
            }
            else if (alg.equals("Merge Sort")) {
                mergeSort(genRandomList(size));
                sumOfIterations = sumOfIterations + counter;
            }
            else if (alg.equals("Quick Sort")) {
                quickSort(genRandomList(size));
                sumOfIterations = sumOfIterations + counter;
            }
        }
        
        int average = (int)Math.round(sumOfIterations/1000);
        return average;
    }
    
    
    // wrappers to generate counts for comparisons and exchanges
    // flag worstCase is true if doing worst case, false if doing average case
    
    
    private static int testSelectionSort(int size) {
        counter = 0; 
        if (worstCase)
            selectionSort(genReversedArray(size)); 
        else
            counter = determineAverage("Selection Sort", size);
        return counter; 
        
    }
    
    private static int testInsertionSort(int size) {
        counter = 0; 
        if (worstCase)
            insertionSort(genReversedArray(size)); 
        else
            counter = determineAverage("Insertion Sort", size);
        return counter; 
    }
    
    private static int testMergeSort(int size) {
        counter = 0; 
        if (worstCase)
            mergeSort(unMergeSort(size));
        else
            counter = determineAverage("Merge Sort", size);
        return counter; 
    }
    
    
    private static int testQuickSort(int size) {
        counter = 0; 
        if (worstCase)
            quickSort(genReversedArray(size)); 
        else
            counter = determineAverage("Quick Sort", size);
        return counter; 
    }
    
    // just a debug method
    
    private static void printArray(int A[]) {
        for (int i = 0; i < A.length; i++)
            System.out.print(" " + A[i]);
        System.out.println("");
    }
    
    // log method
    
    private static int log(int x, int base) {
        double num = Math.log(x)/Math.log(base);
        return (int)num;
    }
    
    // draw the performance curve for the given algorithm, in color c, and add label to graph
    
    private static void drawCurve(String alg, Color c) {
        
        graph.setPaint( c );
        
        if(doUnitTest)
            System.out.println(alg + " test:\nx\ty"); 
        int xPrev = 0; 
        int yPrev = 0; 
        int x = 0; 
        int y = 0; 
        for(int n = increment; n <= upperBound; n += increment) {
            xPrev = x; 
            yPrev = y; 
            x = n; 
            if(alg.equals("Selection Sort")) 
                y = testSelectionSort(n); 
            else if(alg.equals("Insertion Sort")) 
                y = testInsertionSort(n);
            else if(alg.equals("Merge Sort")) 
                y = testMergeSort(n);
            else if(alg.equals("Quick Sort")) 
                y = testQuickSort(n);
            else if(alg.equals("N^2"))
                y = n*n;
            else if(alg.equals("N Log N"))
                y = n*log(n,2);
            
            if (doUnitTest)
                System.out.println(x + "\t" + y);
            
            if (worstCase)
                graph.drawLine( xPrev, yPrev, x, y);
            else
                graph.drawDashedLine(10, xPrev, yPrev, x, y);
            
        }
        if (worstCase)
            graph.addLabel(alg + " (worst)", c); 
        else
            graph.addLabel(alg + " (average)", c);
        
    }
    
    
    
    public static void main(String [] args) { 
        
        // create the Sorting Laboratory GUI
        
        JFrame frame=new JFrame("Sorting Laboratory");
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel label1 = new JLabel("Sorting Laboratory");
        JLabel label2 = new JLabel("Window Controls");
        JLabel maxX = new JLabel("Maximum X");
        JLabel maxY = new JLabel("Maximum Y");
        
        JSlider maxXslider = new JSlider(SwingConstants.HORIZONTAL,20,500,20);
        maxXslider.setMajorTickSpacing(20);
        maxXslider.setPaintLabels(true);
        maxXslider.setPaintTicks(true);
        maxXslider.setSnapToTicks(true);
        maxXslider.setValue(100);
        
        JSlider maxYslider = new JSlider(SwingConstants.HORIZONTAL,20,500,20);
        maxYslider.setMajorTickSpacing(20);
        maxYslider.setPaintLabels(true);
        maxYslider.setPaintTicks(true);
        maxYslider.setSnapToTicks(true);
        maxYslider.setValue(100);
        
        JLabel label3 = new JLabel("Sorting Algorithms");
        JLabel label4 = new JLabel("Experiments");
        
        JButton openGraph = new JButton("Open Graph");
        JButton closeGraph = new JButton("Close Graph");
        JButton draw = new JButton("Draw");        
        JButton clearGraph = new JButton("Clear Graph");
        
        JRadioButton selectionButton = new JRadioButton("Selection Sort");
        JRadioButton insertionButton = new JRadioButton("Insertion Sort");
        JRadioButton mergeButton = new JRadioButton("Merge Sort");
        JRadioButton quickButton = new JRadioButton("Quick Sort");
        JRadioButton nsquaredButton = new JRadioButton("N^2");
        JRadioButton nlogButton = new JRadioButton("N Log N");
        
        JRadioButton worstButton = new JRadioButton("Worst Case");
        JRadioButton averageButton = new JRadioButton("Average Case");
        
        
        JBox body =
            JBox.vbox(
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(),label1,JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(),label2,JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(),maxX,JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(maxXslider),
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(),maxY,JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(maxYslider),
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(),openGraph,closeGraph,draw,
                                clearGraph,JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(
                                JBox.hglue(),
                                JBox.vbox(label3,selectionButton,insertionButton,
                                          mergeButton,quickButton,nsquaredButton,
                                          nlogButton, JBox.vglue()),
                                JBox.hglue(),
                                JBox.vbox(label4,worstButton,averageButton,
                                          JBox.vglue()),
                                JBox.hglue()
                               ),
                      JBox.vglue(),
                      JBox.vglue(),
                      JBox.vglue(),
                      JBox.vglue()
                     );
        
        Color lightYellow = new Color(245,231,163);
        body.setBackground(lightYellow);
        frame.add(body); 
        frame.setVisible(true);
        
        JEventQueue events = new JEventQueue();
        events.listenTo(openGraph, "open");
        events.listenTo(closeGraph, "close");
        events.listenTo(draw, "draw");
        events.listenTo(clearGraph, "clear");
        
        boolean graphOpened = false;
        
        while(true) {
            EventObject event = events.waitEvent();
            String name = events.getName(event);
            
            if(name.equals("open")) {
                graph = new Grapher(maxXslider.getValue(),maxYslider.getValue());
                graphOpened = true;
                
            } else if(name.equals("close")) {
                graph.close();
                graphOpened = false;
                
            } else if(name.equals("draw")) {
                if (!graphOpened) {
                    graph = new Grapher(maxXslider.getValue(),maxYslider.getValue());
                    graphOpened = true;
                }
                
                if (selectionButton.isSelected()) {
                    if (worstButton.isSelected()) {
                        worstCase = true;
                        drawCurve("Selection Sort", Color.green);
                    } if (averageButton.isSelected()) {
                        worstCase = false;
                        drawCurve("Selection Sort", Color.green);
                    }
                }
                if (insertionButton.isSelected()) {
                    if (worstButton.isSelected()) {
                        worstCase = true;
                        drawCurve("Insertion Sort", Color.red);
                    }
                    if (averageButton.isSelected()) {
                        worstCase = false;
                        drawCurve("Insertion Sort", Color.red);
                    }
                }
                if (mergeButton.isSelected()) {
                    if (worstButton.isSelected()) {
                        worstCase = true;
                        drawCurve("Merge Sort", Color.blue);
                    }
                    if (averageButton.isSelected()) {
                        worstCase = false;
                        drawCurve("Merge Sort", Color.blue);
                    }
                }
                if (quickButton.isSelected()) {
                    if (worstButton.isSelected()) {
                        worstCase = true;
                        drawCurve("Quick Sort", Color.cyan);
                    }
                    if (averageButton.isSelected()) {
                        worstCase = false;
                        drawCurve("Quick Sort", Color.cyan);
                    }
                }
                if (nsquaredButton.isSelected()) {
                    worstCase = false;
                    drawCurve("N^2", Color.black);
                }
                if (nlogButton.isSelected()) {
                    worstCase = false;
                    drawCurve("N Log N", Color.gray);
                }
                
            } else if(name.equals("clear")) {
                if (graphOpened)
                    graph.clear();
                selectionButton.setSelected(false);
                insertionButton.setSelected(false);
                mergeButton.setSelected(false);
                quickButton.setSelected(false);
                nsquaredButton.setSelected(false);
                nlogButton.setSelected(false);
                worstButton.setSelected(false);
                averageButton.setSelected(false);
            }
        }
    }
}

