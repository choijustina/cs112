public class Player {
    
    // JUSTINA
    
    private static int D = 2;  // how far deep the minMax method will search to find the best move
    
    private static int MAX_THRESHOLD = Integer.MAX_VALUE;
    private static int MIN_THRESHOLD = -300;
    
    private static void printBoard(int[][] board) {
        
        for (int row = 0; row < board.length; ++row ) {
            for (int col = 0; col < board[row].length; ++col ) {
                
                if (board[row][col] < 10) 
                    System.out.print( board [row][col] + "  ");
                else
                    System.out.print( board [row][col] + " ");
            }
            System.out.println();
        }
    }
    
    /* move & minMax(helper) methods
     * source: slide 34 (Page 18) of Lecture 22 pdf and EnumerationExample.java
     */
    public static int move(int[][] board) {
        return move(board, 0);
    }
    public static int move(int[][] board, int player) {
        
//      System.out.println();
//      System.out.println("move");
        
        int best = -1;                    // corresponds to the column of the best move
        int max = Integer.MIN_VALUE;
        
        for (int col = 0; col < 8; col++) {     // column loop
            System.out.println("If make computer's first move in column... " + col);
            
            if (board[0][col] != 0) {
                System.out.println("Column is full! Moving on...");
                continue;
            }
            
            int row;
            for (row = 7; row >= 0; row--) {
                if (board[row][col] == 0)
                    break;       // but remembers row value
            }
            
            System.out.println("  col is: " + col);
            System.out.println("  row is: " + row);
            
            int val = minMax(board, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 10);
            if (val >= max) {
                best = col;
                max = val;
                System.out.println("  updated val of best move: " + val);
                System.out.println("  updated best move is: " + best);
            }
        }
        printBoard(board);
        return best;
    }
    
    
    // returns the value of the evaluation function -- largest is the best possible move
    // when move = 1, player's turn (minimizer); move = 2, computer's turn (maximizer)
    private static int minMax(int[][] board, int depth, int alpha, int beta, int move) {
        
        System.out.println(" minMax method being called, depth = " + depth);
        int e = eval(board);
        
        if ( boardIsFull(board) || depth == D || eval(board) >= MAX_THRESHOLD || eval(board) <=MIN_THRESHOLD ) {      // leaf node
            
            System.out.print("       base case, ");
            System.out.println("eval = " + e);
            
            printBoard(board);
            return eval(board);
            
        } else if (move == 10) {      // max node
            
            int val = Integer.MIN_VALUE;
            
            for (int col = 0; col < 8; col++) {       // column loop
                if (board[0][col] != 0)
                        continue;
                
                int row;
                for (row = 7; row >= 0; row--) {  // to find lowest row in column
                    if (board[row][col] == 0)
                        break;
                }
                board[row][col] = 10;    // COMPUTER'S MOVE (blue)
                int temp = minMax(board, depth+1, alpha, beta, 1);
                val = Math.max(val, temp);
                
                System.out.println("    If max node");
                System.out.println("    row: " + row + ", column: " + col);
                System.out.println("    temp will be " + temp);
                System.out.println("    value will be " + val + " and should be larger than temp");
                System.out.println();
                
                board[row][col] = 0;
                
                // alpha-beta pruning
                alpha = Math.max(alpha, val);  // updates alpha with the max
                if (beta < alpha)
                    return val;
            }
            
            printBoard(board);
            return val;
            
        } else {    // move == 1,   min node
            
            int val = Integer.MAX_VALUE;
            
            for (int col = 0; col < 8; col++) {       // column - i
                if (board[0][col] != 0)
                        continue;
                
                int row;
                for (row = 7; row >= 0; row--) { // row - j
                    if (board[row][col] == 0)
                        break;
                }
                board[row][col] = 1;   // PLAYER'S MOVE (red)
                int temp = minMax(board, depth+1, alpha, beta, 10);
                val = Math.min(val,temp);
                
                System.out.println("    If min node");
                System.out.println("    row: " + row + ", column: " + col);
                System.out.println("    temp will be " + temp);
                System.out.println("    value will be " + val + " and should be larger than temp");
                System.out.println();
                
                board[row][col] = 0;
                
                // alpha-beta pruning
                beta = Math.min(beta, val);  // updates beta with the min
                if (beta < alpha)
                    return val;
            }
            printBoard(board);
            return val;
        }
    }
    
    private static boolean boardIsFull(int[][] board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
    
    
    // WEI YIING
    
    
    
    private static boolean win;
    private static boolean lose;
    
    private static int threeInARow;
    private static int twoInARow;
    private static int oneInARow;
    
    public static void sumSequence (int[][] board) {
        
        win = false;
        lose = false;
        threeInARow = 0;
        twoInARow = 0;
        oneInARow = 0;
        
        int sum = 0;
        
        // check horizontal
        for (int row = 7; row >= 0; --row) {
            for (int col = 0; col < 5; ++col) {
                
                for (int i = 0; i < 4; ++i)
                    sum += board [row][col + i];
                
                if (sum == 40) 
                    win = true; 
                else if (sum == 4)
                    lose = true;
                else if (sum == 30)
                    ++threeInARow;
                else if (sum == 3)
                    --threeInARow;
                else if (sum == 20)
                    ++twoInARow;
                else if (sum == 2)
                    --twoInARow;
                else if (sum == 10)
                    ++oneInARow;
                else if (sum == 1)
                    --oneInARow;
                
                sum = 0;
                
            }
        }
//        System.out.println("After horizontal check, three in a row = " + threeInARow);
//        System.out.println("After horizontal check, two in a row = " + twoInARow);
//        System.out.println("After horizontal check, one in a row = " + oneInARow);
        
        // check vertical
        for (int col = 0; col < board.length; ++col) {
            for (int row = 7; row > 2; --row) {
                
                for (int i = 0; i < 4; ++i) 
                    sum += board [row - i][col];
                
                if (sum == 40) 
                    win = true; 
                else if (sum == 4)
                    lose = true;
                else if (sum == 30)
                    ++ threeInARow;
                else if (sum == 3)
                    -- threeInARow;
                else if (sum == 20)
                    ++ twoInARow;
                else if (sum == 2)
                    -- twoInARow;
                else if (sum == 10)
                    ++ oneInARow;
                else if (sum == 1)
                    -- oneInARow;
                
                sum = 0;
            }
        }
        
        
//        System.out.println("After vertical check, three in a row = " + threeInARow);
//        System.out.println("After vertical check, two in a row = " + twoInARow);
//        System.out.println("After vertical check, one in a row = " + oneInARow);
        
        // check diagonal NorthEast direction
        for ( int row = 7; row > 2; --row ) {
            for ( int col = 0; col < 5; ++col ) {
                
                for ( int i = 0; i < 4; ++i )           
                    sum += board [row-i][col+i];
                
                if (sum == 40) 
                    win = true; 
                else if (sum == 4)
                    lose = true;
                else if (sum == 30)
                    ++ threeInARow;
                else if (sum == 3)
                    -- threeInARow;
                else if (sum == 20)
                    ++ twoInARow;
                else if (sum == 2)
                    -- twoInARow;
                else if (sum == 10)
                    ++ oneInARow;
                else if (sum == 1)
                    -- oneInARow;
                
                sum = 0;       
            }
        }
        
        
//        System.out.println("After diagonalUp check, three in a row = " + threeInARow);
//        System.out.println("After diagonalUp check, two in a row = " + twoInARow);
//        System.out.println("After diagonalUp check, one in a row = " + oneInARow);
        
        // check diagonal SouthEast direction
        for ( int row = 7; row > 2; --row ) {
            for ( int col = 3; col < 8; ++col ) {
                
                for ( int i = 0; i < 4; ++i )   
                    sum += board [row-i][col-i];
                
                if (sum == 40) 
                    win = true; 
                else if (sum == 4)
                    lose = true;
                else if (sum == 30)
                    ++ threeInARow;
                else if (sum == 3)
                    -- threeInARow;
                else if (sum == 20)
                    ++ twoInARow;
                else if (sum == 2)
                    -- twoInARow;
                else if (sum == 10)
                    ++ oneInARow;
                else if (sum == 1)
                    -- oneInARow;
                
                sum = 0;      
            }
        }    
        
//        System.out.println("After diagonalDown check, three in a row = " + threeInARow);
//        System.out.println("After diagonalDown check, two in a row = " + twoInARow);
//        System.out.println("After diagonalDown check, one in a row = " + oneInARow);
        
        
    }
    
    
    public static int eval(int [][] board) {
        
        sumSequence(board);
        
        if      (win)  return Integer.MAX_VALUE;
        else if (lose) return Integer.MIN_VALUE;
        else {
            
            return (1000 * threeInARow + 100 * twoInARow + 10 * oneInARow); 
//      return threeInARow + twoInARow + oneInARow; 
            
        }
    }   
}


