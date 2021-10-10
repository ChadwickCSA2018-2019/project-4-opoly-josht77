import java.util.Random;

/**
 * @author Josh
 *
 */
public class Opoly {
	// The size of the board
	private int boardSize;
	// the reward amount
	private int reward;
	// the number of rounds to be played
	private int numRounds;
	// the position of the player
	private int position;
	// if position becomes > board size subtract
	private int overlap;
	// builds the board
	String board = "";
	private Random random;

	/**
	 * Constructs the opoly board size and random value 
	 * Opoly Class
	 * 
	 * @param bs the size of the board
	 */
	public Opoly(int bs) {
		boardSize = bs;
		random = new Random();
	}

	/**
	 * Constructs the opoly games board size and random seed for testing
	 * @param bs the size of the board
	 * @param seed for testing the random spin number
	 */
	public Opoly(int bs, int seed) {
		boardSize = bs;
		random = new Random(seed);
	}

	/**
	* Plays the game by calling to the other methods
	*/
	public void playGame() {
		initializeGame();
		while (isGameOver() == false) {
			drawBoard();
			spinAndMove();
		}
		drawBoard();
		spinAndMove();
		displayReport();
		isGameOver();
	}

	/**
	* Sets the default values for the game
	*/
	public void initializeGame() {
		position = 0;
		reward = 100;
	}

	/**
	 * will choose a value randomly from 1 to 5
	 * 
	 * @return a random value from 1 to 5
	 */
	public int spin() {
		return random.nextInt(5) + 1;
	}

	/**
	* Changes the position and gives the amount/reward based on the position of the spin
	*/
	private void move() {
		position = position + spin();
		if (position >= boardSize) {
			overlap = position - boardSize;
			position = overlap;
			reward = reward + 100;
		}
		if (position == boardSize - 1) {
			position = position - 3;
		}
		if (position % 7 == 0) {
			reward = reward * 2;
		}
		if (numRounds % 10 == 0) {
			reward = reward - 50;
		}

	}

	/**
	* Initialize the movements of the game
	*/
	public void spinAndMove() {
		numRounds++;
		spin();
		move();

	}

	/**
	 * Returns <code>true</code> if reward is >= 1000 then code is true 
	 * 
	 * @return <code>true</code> if reward is >= to 1000 then code is true and game ends
	 *         <code>false</code> otherwise continue to play
	 */
	private boolean isGameOver() {
		if (reward >= 1000) {
			return true;
		} else {
			return false;
		}

	}

	/**
	* Prints out either "o" or "*" on the game board
	*/
	private void drawBoard() {
		for (int i = 0; i < boardSize; i++) {
			if (i == position) {
				board += "o";
			} else {
				board += "*";
			}
		}
		System.out.println(board + " " + reward);
		board = "";
	}

	/**
	* Prints out the results when the game is over
	*/
	public void displayReport() {
		System.out.println("game over");
		System.out.println("rounds of play: " + numRounds);
		System.out.println("final reward: " + reward);
	}
}
