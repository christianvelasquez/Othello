/*
 * The Board class displays the game board.
 * A default 4 x 4 game board has rows with indices 0-3 and columns 0-3.
 */

public class Board {
	private static char[][] board;
    private static int size;
	    
	/* Default constructor to create a default 4 x 4 game board */
    public Board () {
    	size = 4;
    	
        setBoard(new char[size][size]);
        
		/* Create an empty default 4 x 4 game board */
        for (int row = 0; row < size; row++)
            for (int col = 0; col < 4; col++)
                getBoard()[row][col] = '_';
        
		/* Starting Position: Initialize the beginning default 4 x 4 game board */
	    getBoard()[size/2][size/2] = 'W';
        getBoard()[(size/2) - 1][(size/2) - 1] = 'W';
		getBoard()[(size/2) - 1][size/2] = 'B';
		getBoard()[size/2][(size/2) - 1] = 'B';
    }
    
	/* Constructor to create an N x N game board */
    public Board (int N) {
    	size = N;
    	
        setBoard(new char[size][size]);
        
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++)
                getBoard()[row][col] = '_';
        
		/* Initialize the beginning N x N game board */
	    getBoard()[size/2][size /2] = 'W';
        getBoard()[(size/2) - 1][(size/2) - 1] = 'W';
		getBoard()[(size/2) - 1][size/2] = 'B';
		getBoard()[size/2][(size/2) - 1] = 'B';
    }
    
	/* Display the game board */
    public void displayBoard() {
    	int row = 0, column = 0;
    	for (row = 0; row < getBoard().length; row++) {
            for (column = 0; column < getBoard().length; column++) {
                System.out.print(getBoard()[row][column]);
                if (column < getBoard().length - 1)
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
	/* Returns the size */
	public static int getSize(){
		return size;
	}

    /* Getter for the game board */
	public static char[][] getBoard() {
		return board;
	}

	/* Setter for the game board */
	public static void setBoard(char[][] board) {
		Board.board = board;
	}
	
	/* Setter for the disc color */
	public static void setColor(char move, int row, int column){
		Board.board[row][column] = move;
	}
}