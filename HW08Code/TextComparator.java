/* Name: TextComparator.java
 * Author: Justina Choi
 * Class: CS112 - Snyder; Homework 8
 * Date: 4/17/13
 * Interface: 
 * Purpose: 
 * Resources: used heapSort algorithm in "Algorithms" textbook by Sedgewick/Wayne on
 *            pages 
 * Notes: 
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class TextComparator {
    
    private static String[] blackList = { "the", "of", "and", "a", "to", "in", "is", 
        "you", "that", "it", "he", "was", "for", "on", "are", "as", "with", 
        "his", "they", "I", "at", "be", "this", "have", "from", "or", "one", 
        "had", "by", "word", "but", "not", "what", "all", "were", "we", "when", 
        "your", "can", "said", "there", "use", "an", "each", "which", "she", 
        "do", "how", "their", "if", "will", "up", "other", "about", "out", "many", 
        "then", "them", "these", "so", "some", "her", "would", "make", "like", 
        "him", "into", "time", "has", "look", "two", "more", "write", "go", "see", 
        "number", "no", "way", "could", "people",  "my", "than", "first", "water", 
        "been", "call", "who", "oil", "its", "now", "find", "long", "down", "day", 
        "did", "get", "come", "made", "may", "part" };
    
    /* preprocess method takes the String s which is passed in as a parameter and
     * removes everything except blanks and letters, makes everything into
     * lowercase, and splits the string into an array of strings
     */
    private static String[] preprocess(String s) {
        s = s.toLowerCase();
        Scanner console = new Scanner(s);
        
        // counts the number of words to create the correct array size
        int words = 0;
        while (console.hasNext()) {
            console.next();
            words++;
        }
        
        String[] array = new String[words];
        console = new Scanner(s);
        int i = 0;
        while (console.hasNext()) {
            array[i] = console.next();
            i += 1;
        }
        
        // removing the punctuation
        // source: http://recurial.com/programming/removing-punctuation-and-spaces-from-java-string/
        for (int j = 0; j < words; j++) {
            String str = array[j];
            array[j] = str.replaceAll("\\W", "");
        }
        
        return array;
        
    }
    
    /* filter method takes an array of words and removes any words that appear on
     * the blacklist
     */
    private static String[] filterUpToN(String[] words, int N) {
        for (int i = 0; i < words.length; i++) {
            if (getIndex(words[i]) < N) {
                words = shiftArray(words, i);
                i--;
            }
        }
        return words;
    }
    
    /*
     * 
     String[] NEWblackList = new String[N];
     for (int i = 0; i < N; i++)
     NEWblackList[i] = blackList[i];
     return filter(words, NEWblackList);
     }
     private static String[] filter(String[] words, String[] NEWblackList) {
     for (int i = 0; i < words.length; i++) {
     for (int j = 0; j < NEWblackList.length; j++) {
     if (words[i].equals(NEWblackList[j])) {
     words = shiftArray(words, i);
     break;
     }
     }
     }
     return words;
     }*/
    private static String[] shiftArray(String[] words, int k) {
        // k is the location where the element must be deleted
        String[] NEWwords = new String[words.length-1];
        for (int i = 0; i < k; i++)
            NEWwords[i] = words[i];
        for (int i = k; i < NEWwords.length; i++)
            NEWwords[i] = words[i+1];
        return NEWwords;
    }
    private static int getIndex(String str) {
        for (int i = 0; i < 100; i++) {
            if (str.equals(blackList[i]))
                return i;
        }
        return 100;
    }
    
    
    
    
    /* heapSort method takes a term frequency list (words and their frequencies)
     * and sorts them into descending order (words with the same frequency should
     * appear in ascending alphabetic order)
     */
    private static TFV[] heapSort(TFV[] WordArray) {
        int N = WordArray.length;
        for (int k = N/2; k >= 1; k--)
            WordArray = sink(WordArray, k, N);
        while (N > 1) {
            WordArray = exch(WordArray, 1, N--);
            WordArray = sink(WordArray, 1, N);
        }
        return WordArray;
    }
    private static TextComparator.TFV[] exch(TFV[] WordArray, int i, int j) {
        TFV t = WordArray[i];
        WordArray[i] = WordArray[j];
        WordArray[j] = t;
        return WordArray;
    }
    private static boolean greater(TFV[] WordArray, int i, int j) {
        return WordArray[i].frequency > WordArray[j].frequency;
    }
    private static TFV[] swim(TFV[] WordArray, int k, int N) {
        while (k > 1 && greater(WordArray, k/2, k)) {
            WordArray = exch(WordArray, k/2, k);
            k = k/2;
        }
        return WordArray;
    }
    private static TFV[] sink(TFV[] WordArray, int k, int N) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && greater(WordArray, j, j+1))
                j++;
            if (!greater(WordArray, k, j))
                break;
            WordArray = exch(WordArray, k, j);
            k = j;
        }
        return WordArray;
    }
    
    
    
    
    
    public static void main(String args[]) {
        
        
        JFrame frame = new JFrame("Text Comparator");
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // the TextArea under Input Box A
        JTextArea inputbox = new JTextArea(); 
        inputbox.setLineWrap(true); 
        JScrollPane textArea = new JScrollPane(inputbox); 
        JBox.setSize(textArea,325,200); 
        textArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); 
        
        // the TextArea under Input Box B
        JTextArea inputbox2 = new JTextArea(); 
        inputbox2.setLineWrap(true); 
        JScrollPane textArea2 = new JScrollPane(inputbox2); 
        JBox.setSize(textArea2,325,200); 
        textArea2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textArea2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // the TextField under Word List A
        JTextField textbox3 = new JTextField();
        textbox3.setEditable(false);
        JScrollPane textArea3 = new JScrollPane(textbox3); 
        JBox.setSize(textArea3,325,200); 
        textArea3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textArea3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // the TextField under Word List B
        JTextField textbox4 = new JTextField(); 
        textbox4.setEditable(false);
        JScrollPane textArea4 = new JScrollPane(textbox4); 
        JBox.setSize(textArea4,325,200); 
        textArea4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textArea4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // slider
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL,0,100,10);
        slider.setMajorTickSpacing(5);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setValue(100);
        
        
        
        JLabel text1 = new JLabel("Text Comparator");
        JLabel text2 = new JLabel("Instructions: Insert texts in both input boxes" +
                                  " push Calculate to see the cosine similarity" +
                                  " index.");
        JLabel text3 = new JLabel("Input Box A");
        JLabel text4 = new JLabel("Input Box B");
        JButton clearButton1 = new JButton("Clear");
        JButton clearButton2 = new JButton("Clear");
        JLabel text5 = new JLabel("Word List A");
        JLabel text6 = new JLabel("Word List B");
        JLabel text7 = new JLabel("Common Word Filter: Filter out the N most" +
                                  "common English words");
        JButton calculateButton = new JButton("Calculate");
        JLabel text8 = new JLabel("Cosine similarity: ");
        
        
        JBox body = 
            JBox.vbox(
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(), text1, JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(), text2, JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(), text3, JBox.hglue(), JBox.hglue(),
                                text4, JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(),textArea,
                                JBox.hglue(), textArea2, JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(), clearButton1, JBox.hglue(),
                                JBox.hglue(), clearButton2, JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(), text5, JBox.hglue(), JBox.hglue(),
                                text6,JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(), textArea3,
                                JBox.hglue(), textArea4, JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(JBox.hglue(), text7, JBox.hglue()),
                      JBox.vglue(),
                      JBox.hbox(slider),
                      JBox.hbox(JBox.hglue(), calculateButton, JBox.hglue(),
                                JBox.hglue(), text8, JBox.hglue()),
                      JBox.vglue()
                     );
        
        Color lightYellow = new Color(255,240,180);
        body.setBackground(lightYellow);
        body.setFont(new Font("Calibri", Font.PLAIN, 16));
        frame.add(body);
        frame.setVisible(true);
        
        
        JEventQueue events = new JEventQueue();
        events.listenTo(clearButton1, "clearleft");
        events.listenTo(clearButton2, "clearright");
        events.listenTo(calculateButton, "calculate");
        
        
        while(true) {
            EventObject event = events.waitEvent();
            String name = events.getName(event);
            
            if (name.equals("clearleft"))
                inputbox.setText("");
            else if (name.equals("clearright"))
                inputbox2.setText("");
            
            else if (name.equals("calculate")) {
                int N = slider.getValue();
                
                String inputA = inputbox.getText();
                String inputB = inputbox2.getText();
                
                String[] A = filterUpToN(preprocess(inputA), N);
                String[] B = filterUpToN(preprocess(inputB), N);
                
                // Left side box
                FrequencyCounter WordListA = new Concordance();
                for (int i = 0; i < A.length; i++) {
                    WordListA.insert(A[i]);
                }
                
                // TFV: term frequency
                TFV[] WordArrayA = new TFV[A.length];
                for (int i = 0; i < A.length; i++) {
                    WordArrayA[i].word = A[i];
                    WordArrayA[i].frequency = WordListA.frequency(A[i]);
                } 
                WordArrayA = heapSort(WordArrayA);
                
                
                //Right side box
                FrequencyCounter WordListB = new Concordance();
                for (int i = 0; i < B.length; i++) {
                    WordListB.insert(B[i]);
                }
                
                // TFV: term frequency
                TFV[] WordArrayB = new TFV[B.length];
                for (int i = 0; i < B.length; i++) {
                    WordArrayB[i].word = B[i];
                    WordArrayB[i].frequency = WordListB.frequency(A[i]);
                }
                WordArrayB = heapSort(WordArrayB);
                
                String str = "";
                for (int i = 0; i < WordArrayA.length; i++) {
                    str += WordArrayA[i];
                }
                textbox3.setText(str);
                
                str = "";
                for (int i = 0; i < WordArrayB.length; i++) {
                    str += WordArrayB[i];
                }
                textbox4.setText(str);
            }
            
        }
        
    }
    public class TFV {
        public String word;
        public int frequency;
        
        public TFV(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }
    }
}


