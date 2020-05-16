/*
 * The Player class returns the score and color of the player.
 * It is created as an abstract class for the ManualUser and Computer subclasses.
 */

public abstract class Player {
	private static char color;
	private static int score;
	
	/* Constructor for color and score */
	Player(char color, int score){
		Player.color = color;
		Player.score = score;
	}
	
	/* Returns the color */
	public char getColor() {
		return color;
	}

	/* Returns the score */
	public int getScore(char player) {
		score = 0;
		
		for(int row = 0; row < Board.getSize(); row++){
			for(int column = 0; column < Board.getSize(); column++){
					if(Board.getBoard()[row][column] == player)
						score++;
			}
		}
		return score;
	}
}