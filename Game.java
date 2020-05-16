import java.util.Scanner;
import java.util.Random;

/*
 * This strategy board game is played by two players (Black and White) on a default 4 x 4 board.
 * The winner is the player who has more discs of his color than the opponent at the end of the game.
 */

/*
 * The Game class creates the game to be run.
 * It includes the following methods: main, endGame, playerVsComputer, validPositions, turnCount, turn.  
 */

public class Game {
	private static Board game;
	private ManualUser playerOne;
	private Computer playerComputer;
	private static char opponentColor;
	private static Scanner input;
	private Random random = new Random();

	/* Method to run the game */
	public static void main(String[] args) {
		input = new Scanner(System.in);
		char colorInput = ' ';

		// Initialize Player 1's color
		System.out.print("Enter Player 1's color (B or W): ");
		while(!(colorInput=='B')&&!(colorInput=='W')){
			colorInput = input.next().charAt(0);
			
			// If input is not a valid color
			if(!(colorInput=='B')&&!(colorInput=='W')){
				System.out.println("ERROR: Not a valid color. Please enter B or W: ");
			}
		}
		
		// Initialize the Computer's color
		if (colorInput == 'B')
			opponentColor = 'W';
		else
			opponentColor = 'B';

		boolean gameInProgress = true;
		while (gameInProgress) {
			Game start = new Game(colorInput, opponentColor, 4);
			start.playerVsComputer();
		}
		if (gameInProgress = false) {
			System.exit(0);
			endGame();
		}
	}

	/* Constructor for the Game class */
	public Game(char player, char computer, int size) {
		game = new Board(size);
		playerOne = new ManualUser(player);
		playerComputer = new Computer(computer);
	}
	
	public static void endGame() {
		System.exit(0);
	}

	/* 
	 * Game Version 1: Player vs. Computer.
	 * Gets the Player's and Computer's moves.
	 * Turns or flips the appropriate discs.
	 * Displays the game board and score.
	 * Black always begins the game, and the two players subsequently take turns moving.
	 */
	public void playerVsComputer() {
		boolean inProgress = true;
		int rowPlayer, columnPlayer, rowComputer, columnComputer;
		
		// Display the players' colors for the game.
		System.out.println();
		System.out.println("Player 1's color is: " + playerOne.getColorName());
		System.out.println("Computer's color is: " + playerComputer.getColorName());
		System.out.println();
		System.out.println("Let the game begin!");
		System.out.println();
		game.displayBoard();

		// If Player 1 is Black and the Computer is White.
		if (playerOne.getColorName() == 'B') {
			while (inProgress) {
				// If there are no more valid moves for Player 1 (Black).
				if (validPositions(playerOne.getColorName()) == 0) {
					System.out.println("Game over. No moves found for Black" + ".");
					System.out.println();
					System.out.println("FINAL SCORE: "
							+ "Player 1: " + playerOne.getScore('B') + ". Computer: " + playerComputer.getScore('W') + ".");
					inProgress = false;
					endGame();
				}
				
				// Prompt Player 1 (Black) for his or her move.
				do {
					System.out.println();
					System.out.print("Enter the row for Player 1 (Black): ");
					rowPlayer = input.nextInt();
					System.out.print("Enter the column for Player 1 (Black): ");
					columnPlayer = input.nextInt();
					
					if (turnCount(playerOne.getColorName(), rowPlayer, columnPlayer) < 0)
						System.out.println("ERROR: Not a valid position.");
					else {
						System.out.println("SUCCESS: " + playerOne.getColorName() + " move at (" + rowPlayer + ", " + columnPlayer +")");
						System.out.println();
					}
				} while (turnCount(playerOne.getColorName(), rowPlayer, columnPlayer) < 0); // Do-while loop runs as long as turnCount > 0.
				
				// Flip the Computer's discs
				turn(playerOne.getColorName(), rowPlayer, columnPlayer);
				
				// Display the game board and score
				game.displayBoard();
				System.out.println("SCORE:");
				System.out.println("Player 1: " + playerOne.getScore('B'));
				System.out.println("Computer: " + playerComputer.getScore('W'));
				System.out.println();
				
				// If there are no more valid moves for the Computer (White).
				if (validPositions(playerComputer.getColorName()) == 0) {
					System.out.println("Game over. No moves found for White"  + ".");
					System.out.println("FINAL SCORE: "
							+ "Player 1: " + playerOne.getScore('B') + ". Computer: " + playerComputer.getScore('W') + ".");
					inProgress = false;
					endGame();
				}
				
				// Get the moves for Computer (White).
				do {
					do {
						rowComputer = random.nextInt(Board.getSize());
						columnComputer = random.nextInt(Board.getSize());
					} while (turnCount(playerComputer.getColorName(), rowComputer, columnComputer) < 0);
					
					System.out.println();
					System.out.print("Computer (White)'s row: ");
					System.out.println(rowComputer);
					System.out.print("Computer (White)'s column: ");
					System.out.println(columnComputer);
					System.out.println("SUCCESS: " + playerComputer.getColorName() + " move at (" + rowComputer + ", " + columnComputer + ")");
					System.out.println();
				} while (turnCount(playerComputer.getColorName(), rowComputer, columnComputer) < 0);
				
				// Flip the Computer's (White) discs
				turn(playerComputer.getColorName(), rowComputer, columnComputer);
				
				// Display the game board and score
				game.displayBoard();
				System.out.println("SCORE:");
				System.out.println("Player 1: " + playerOne.getScore('B'));
				System.out.println("Computer: " + playerComputer.getScore('W'));
				System.out.println();
			}
		}
		
		// Else if Player 1 is White and Computer is Black.
		else {
			while (inProgress) {
				// If there are no more valid moves for the Computer (Black).
				if (validPositions(playerComputer.getColorName()) == 0) {
					System.out.println("Game over. No moves found for Black" + ".");
					System.out.println("FINAL SCORE: "
							+ "Player 1: " + playerOne.getScore('W') + ". Computer: " + playerComputer.getScore('B') + ".");
					inProgress = false;
					endGame();
				}
				
				// Get the moves for Computer (White).
				do {
					do {
						rowComputer = random.nextInt(Board.getSize());
						columnComputer = random.nextInt(Board.getSize());
					} while (turnCount(playerComputer.getColorName(), rowComputer, columnComputer) < 0);
					
					System.out.println();
					System.out.print("Computer (Black)'s row: ");
					System.out.println(rowComputer);
					System.out.print("Computer (Black)'s column: ");
					System.out.println(columnComputer);
					System.out.println("SUCCESS: " + playerComputer.getColorName() + " move at (" + rowComputer + ", " + columnComputer +")");
					System.out.println();
				} while (turnCount(playerComputer.getColorName(), rowComputer, columnComputer) < 0);
				
				// Flip Player 1's (Black) discs.
				turn(playerComputer.getColorName(), rowComputer, columnComputer);
				
				// Display the game board and score.
				game.displayBoard();
				System.out.println("SCORE:");
				System.out.println("Player 1: " + playerOne.getScore('W'));
				System.out.println("Computer: " + playerComputer.getScore('B'));
				System.out.println();
				
				// If there are no more valid moves for Player 1.
				if (validPositions(playerOne.getColorName()) == 0) {
					System.out.println("Game over. No moves found for White" + ".");
					System.out.println("FINAL SCORE: "
							+ "Player 1: " + playerOne.getScore('B') + ". Computer: " + playerComputer.getScore('W') + ".");
					inProgress = false;
					endGame();
				}
				
				// Prompt Player 1 (White) for his or her move.
				do {
					System.out.println();
					System.out.print("Enter the row for Player 1 (White): ");
					rowPlayer = input.nextInt();
					System.out.print("Enter the column for Player 1 (White): ");
					columnPlayer = input.nextInt();
					if (turnCount(playerOne.getColorName(), rowPlayer, columnPlayer) < 0)
						System.out.println("ERROR: Not a valid position.");
					else {
						System.out.println("SUCCESS: " + playerOne.getColorName() + " move at (" + rowPlayer + ", " + columnPlayer +")");
						System.out.println();
					}

				} while (turnCount(playerOne.getColorName(), rowPlayer, columnPlayer) < 0);
				
				// Flip the Computer's (Black) discs.
				turn(playerOne.getColorName(), rowPlayer, columnPlayer);
				
				// Display the game board and score.
				game.displayBoard();
				System.out.println("SCORE:");
				System.out.println("Player 1: " + playerOne.getScore('W'));
				System.out.println("Computer: " + playerComputer.getScore('B'));
				System.out.println();
			}
		}
	}

	/*
	 * Returns the number of valid positions that Player 1 or Computer can move to.
	 * Displays all valid positions.
	 */
	public int validPositions(char color) {
		int validPositionsCount = 0;
				
		System.out.println("Valid position(s): ");

		// Iterate throughout the entire game board to find valid positions
		for (int row = 0; row < Board.getSize(); row++) {
			for (int column = 0; column <= Board.getSize() - 1; column++) {
				if (turnCount(color, row, column) > 0) {
					System.out.print("(" + row + "," + column + ") ");
					++validPositionsCount;
				}
			}
		}
		
		while (validPositionsCount == 0) {
			System.out.println("N/A");
			break;
		}
		
		System.out.println();
		return validPositionsCount;
	}

	/*
	 * Returns the number of discs that can be turned at a current valid position.
	 */
	public int turnCount(char playerColor, int row, int column) {
		int turnCount = 0;
		int invalidCount = 0;
		
		if (playerColor == 'B')
			opponentColor = 'W';
		else
			opponentColor = 'B';		

		// If empty space
		if (Board.getBoard()[row][column] == 'B' || Board.getBoard()[row][column] == 'W')
			return -1;
		
		// If position is out of bounds
		if (row < 0 || column < 0 || row > Board.getSize() - 1 || column > Board.getSize() - 1)
			return -1;
		
		// Case 1: Up
		if (row < Board.getSize()) {
			for (int rowIndex = row + 1; rowIndex <= Board.getSize() - 1; rowIndex++) {
				// If the color at the current position (row 3 for a default 4 x 4 board) is the same color as the opponent's disc
				if (rowIndex == Board.getSize() - 1 && Board.getBoard()[rowIndex][column] == opponentColor) {
					++invalidCount; // Then no discs will be turned
					break;
				}
				
				// Else if the color at the current position is the same color as the opponent's disc.
				else if (Board.getBoard()[rowIndex][column] == opponentColor) {
					turnCount++; // Then able to flip a disc
					
					// Handles exception
					try {
						// If the color at the next position is the same color as the player's disc
						if (Board.getBoard()[rowIndex + 1][column] == playerColor)
							break; // Then not able to turn any more discs
					} 
					catch (ArrayIndexOutOfBoundsException e) {
					}
					
					// If the color at the next position (rows 0-1) is the same color as the player's disc
					if (rowIndex < Board.getSize() - 2) {
						// If the color at the next position is the same color as the player's disc
						if (Board.getBoard()[rowIndex + 1][column] == playerColor) {
							break; // Then no discs will be turned
						}
						// Else if the next position is empty, then no discs will be turned
						else if (Board.getBoard()[rowIndex + 1][column] == '_') {
							++invalidCount;
							break;
						} 
						// Else if the color at the next position is the same color as the opponent's disc
						else if (Board.getBoard()[rowIndex + 1][column] == opponentColor) {
							// If the color in the final row is the same color as the opponent's disc, then no discs will be turned
							if (Board.getBoard()[Board.getSize() - 1][column] == opponentColor) {
								++invalidCount;
								break;
							} 
							// Else if the color in the final row is the same color as the player's disc, then no discs will be turned
							else if (Board.getBoard()[Board.getSize() - 1][column] == playerColor) {
								break;
							}
						} 
					}
				} 
				
				// Else if the color at the current position is not the same color as the disc below
				else {
					++invalidCount;
					break;
				}
			}
		}

		// Case 2: Left
		if (column < Board.getSize()) {
			for (int columnIndex = column + 1; columnIndex <= Board.getSize() - 1; columnIndex++) {
				if (Board.getBoard()[row][columnIndex] == opponentColor && columnIndex == Board.getSize() - 1) {
					++invalidCount;
					break;
				}
				
				else if (Board.getBoard()[row][columnIndex] == opponentColor) {
					turnCount++;
					
					try {
						if (Board.getBoard()[row][columnIndex + 1] == playerColor)
							break;
					} 
					catch (ArrayIndexOutOfBoundsException e) {
					}
					
					if (columnIndex < Board.getSize() - 2) {
						if (Board.getBoard()[row][columnIndex + 1] == playerColor) {
							break;
						}
						else if (Board.getBoard()[row][columnIndex + 1] == '_') {
							++invalidCount;
							break;
						} 
						else if (Board.getBoard()[row][columnIndex + 1] == opponentColor) {
							if (Board.getBoard()[row][Board.getSize() - 1] == opponentColor) {
								++invalidCount;
								break;
							} 
							else if (Board.getBoard()[row][Board.getSize() - 1] == playerColor) {
								break;
							}
						}
					}
				} 
				
				else {
					++invalidCount;
					break;
				}
			}
		}

		// Case 3: Down
		if (row > 0) {
			for (int rowIndex = row - 1; rowIndex >= 0; rowIndex--) {
				if (Board.getBoard()[rowIndex][column] == opponentColor && rowIndex == 0) {
					++invalidCount;
					break;
				}
				
				else if (Board.getBoard()[rowIndex][column] == opponentColor) {
					turnCount++;
					
					try {
						if (Board.getBoard()[rowIndex - 1][column] == playerColor)
							break;
					} 
					catch (ArrayIndexOutOfBoundsException e) {
					}
					
					if (rowIndex > 1) {
						if (Board.getBoard()[rowIndex - 1][column] == playerColor) {
							break;
						}
						else if (Board.getBoard()[rowIndex - 1][column] == '_') {
							++invalidCount;
							break;
						} else if (Board.getBoard()[rowIndex - 1][column] == opponentColor) {
							if (Board.getBoard()[0][column] == opponentColor) {
								++invalidCount;
								break;
							} else if (Board.getBoard()[0][column] == playerColor) {
								break;
							}
						} 
					}
				} 
				
				else {
					++invalidCount;
					break;
				}
			}
		}

		// Case 4: Right
		if (column > 0) {
			for (int columnIndex = column - 1; columnIndex >= 0; columnIndex--) {
				if (Board.getBoard()[row][columnIndex] == opponentColor && columnIndex == 0) {
					++invalidCount;
					break;
				}
				
				else if (Board.getBoard()[row][columnIndex] == opponentColor) {
					turnCount++;
					try {
						if (Board.getBoard()[row][columnIndex - 1] == playerColor)
							break;
					} 
					catch (ArrayIndexOutOfBoundsException e) {
					}
					
					if (columnIndex > 1) {
						if (Board.getBoard()[row][columnIndex - 1] == playerColor) {
							break;
						}
						else if (Board.getBoard()[row][columnIndex - 1] == '_') {
							++invalidCount;
							break;
						} 
						else if (Board.getBoard()[row][columnIndex - 1] == opponentColor) {
							if (Board.getBoard()[row][0] == opponentColor) {
								++invalidCount;
								break;
							} 
							else if (Board.getBoard()[row][0] == playerColor) {
								break;
							}
						}
					}
				} 
				
				else {
					++invalidCount;
					break;
				}
			}
		}

		if ((row == 0 || row == Board.getSize() - 1) && (column == 0 || column == Board.getSize() - 1) && invalidCount > 1) {
			turnCount = 0;
			return -1;
		}
		
		else if (invalidCount >= 3) {
			turnCount = 0;
			return -1;
		}
		
		// Else if there exist disks that can be turned, return turnCount.
		else
			return turnCount;
	}
	
	/* Turns the necessary discs. Called if there are discs that can be turned (turnCount > 0). */
	public void turn(char move, int row, int column) {
		if (turnCount(move, row, column) > 0) {
			// Case 1: Up
			if (row < Board.getSize() - 1) {
				Board.setColor(move, row, column);
				
				for (int rowIndex = row + 1; rowIndex < (Board.getSize()); rowIndex++) {
					// If blank space, then break
					if (Board.getBoard()[rowIndex][column] == '_')
						break;
					// Else if matching colors
					else if ((Board.getBoard()[rowIndex][column] == opponentColor) && rowIndex < Board.getSize() - 1) {
						// If blank space at next position, then break
						if (Board.getBoard()[rowIndex + 1][column] == '_')
							break;
						// Call the color setter
						Board.setColor(move, rowIndex, column);
					}
				}
			}

			// Case 2: Left
			if (column < Board.getSize() - 1) { 
				Board.setColor(move, row, column);
				for (int columnIndex = column + 1; columnIndex < (Board.getSize()); columnIndex++) {
					if (Board.getBoard()[row][columnIndex] == '_')
						break;
					else if (Board.getBoard()[row][columnIndex] == opponentColor && columnIndex < Board.getSize() - 1) {
						if (Board.getBoard()[row][columnIndex + 1] == '_')
							break;
						Board.setColor(move, row, columnIndex);
					}
				}
			}

			// Case 3: Down
			if (row > 1) {
				Board.setColor(move, row, column);
				for (int rowIndex = row - 1; rowIndex > 0; rowIndex--) {
					if (Board.getBoard()[rowIndex][column] == '_')
						break;
					else if (Board.getBoard()[rowIndex][column] == opponentColor && rowIndex < Board.getSize() - 1) {
						if (Board.getBoard()[rowIndex - 1][column] == '_')
							break;
						Board.setColor(move, rowIndex, column);
					}
				}
			}
			
			// Case 4: Right
			if (column > 1) {
				Board.setColor(move, row, column);
				for (int columnIndex = column - 1; columnIndex > 0; columnIndex--) {
					if (Board.getBoard()[row][columnIndex] == '_')
						break;
					else if (Board.getBoard()[row][columnIndex] == opponentColor && columnIndex < Board.getSize() - 1) {
						if (Board.getBoard()[row][columnIndex - 1] == '_')
							break;
						Board.setColor(move, row, columnIndex);
					}
				}
			}
		}
	}
}