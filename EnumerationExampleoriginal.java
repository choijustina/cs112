/* File: EnumerationExample.java
 * Author: Wayne Snyder
 * Date:   4/20/13
 * Purpose: This just illustrates an important implementation techniques for board games: recursing through
 *      board positions without creating explicit tree nodes. This technique should be used
 *      for HW 09.
 *      The program prints out all possible boards resulting from placing X's and O's in a
 *      "board" of three squares. Not a useful game, but a simple example of how to combine
 *      recursion and a for loop to make a move and then withdraw it and consider the next move. 
 * 
 *      The most important thing to notice is that each level of the recursion has a board
 *      to print out, but no explicit trees nodes are created. 
 * 
 *      Board is array of three ints: 0 means empty, 1 means "X", 2 means "O"
 */


public class EnumerationExampleoriginal {
  
  private static String [] C = { "_", "X", "O" };
  
  private static void printBoard(int [] b, int move) {
    for(int i = 0; i < move; ++i)
      System.out.print("   "); 
    for(int i = 0 ; i < 3; ++i) 
      System.out.print(C[b[i]] + " ");
    System.out.println(); 
    System.out.println(); 
  }
  
  // This method demonstrates how to combine a for loop with recursion to enumerate all possible
  // placements of X and O in a sequence of three moves. 
  
  private static void enumerate(int [] b, int move) {
    
    int piece; 
    
    if (move % 2 == 0)
      piece = 1;                      // X moves on even numbered moves (start at 0)
    else
      piece = 2;                      // O moves on odd numbered moves
    
    printBoard(b, move); 
    
    // THIS IS THE MOST IMPORTANT PART OF THE PROGRAM: Combines for loop and recursion
    
    for(int i = 0 ; i < 3; ++i) {         
      if(b[i] == 0)  {              // square is empty
        b[i] = piece;                     // make a move for the piece
        enumerate( b, move+1 );           // continue enumerate with this move in place
        b[i] = 0;                         // undo the move and continue
      }
    }
    
  }
  
  public static void main(String [] args) {
    
    int [] board = new int[3];
    enumerate(board, 0); 
    
  }
  
  
  
  
  
}