/* 
 * The Computer class is a subclass of the Player class that gets the color of the Computer.
 */

public class Computer extends Player{
	private char computerColor;
	
	/* Constructor that calls the superclass Player constructor */
	Computer(char colorName) {
		super(colorName, 2);
		computerColor = colorName;
	}
	
	/* Returns the Computer color */
	public char getColorName() {
		return computerColor;
	}
}