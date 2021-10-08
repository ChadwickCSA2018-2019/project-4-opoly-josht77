import java.util.Random;

public class Opoly {
	  private static int size; //how big the board is
	  private static int spin; //value of the spinner
	  private static int reward; //total points
	  private static int turnNumber; //how many turns have passed
	  private static char[] board; //the array of the board and holding the position of the player
	  private static boolean first; //temp variable to create the array with *'s and o
	  private static Random random;

	  public Opoly(int s){ //constructor
	    size = s; //sets the size passed by the main method defined by the user
	    reward = 100; //startes player with 100 points
	    turnNumber = 0; //sets turn number to 0
	    board = new char[size]; //creates the array
	    first = true; //temp variable to create the array with *'s and o
	  }

	  public void playGame(){
	    Opoly.drawBoard(); //prints out board for the first time
	    while (Opoly.isGameOver()){ //checks when the player has recieved 1000 points or more
	      Opoly.spinAndMove(); //spins, moves, and adds points
	      Opoly.drawBoard(); //prints out the updated board and reward
	    }
	    Opoly.displayReport(); //displays the stats when the game is over
	  }

	  public static void spin(){
	    spin = (random.nextInt(5)) + 1; //generates a number from 1 to 5
	  }

	  public static void move(){
	    if (turnNumber % 10 == 0) //RULE #4 - Every tenth move, reduces the reward by 50 points.
	      reward = reward - 50;

	    for (int k = 0; k < size; k++){ //finds the position of the player
	      if (board[k] == 'o'){
	        board[k] = '*';

	        if (k == (size - 1)){ //RULE #2 (condition 1) - If you land on the final board cell, you must go back 3 spaces.
	          board[k] = '*';
	          board[k - 3] = 'o';
	          if (((k - 3) % 7 == 0) && (k - 3 != 0)) //RULE #2 (condition 2 & 3) - If the position of the last cell is evenly divisible by 7, no extra points are added. If the new piece location, 3 places back, IS evenly divisible by 7, then extra points ARE doubled
	            reward = reward * 2;

	          if (((k - 3) + spin) >= size){ //brings the array back in bounds to cirlce the position of the player
	            board[k - 3] = '*';
	            reward = reward + 100; //RULE #3 - If you make it all the way around the board, you get 100 points.
	            board[((k - 3) + spin) - size] = 'o';
	          }
	          else if (((k - 3) + spin) <= size){ //moves the position when player is in bounds of array
	            board[k - 3] = '*';
	            board[(k - 3) + spin] = 'o';
	          }
	        }
	        else if ((k + spin) >= size){ //brings the array back in bounds to cirlce the position of the player
	          reward = reward + 100; //RULE #3 - If you make it all the way around the board, you get 100 points.
	          board[(k + spin) - size] = 'o';
	        }
	        else if ((k + spin) <= size) //moves the position when player is in bounds of array
	          board[k + spin] = 'o';

	        k = size; //resets k 
	      }
	    }
	  }

	  public static void spinAndMove(){
	    turnNumber++; //adds a turn
	    Opoly.spin(); //sets a number to the spin variable
	    Opoly.move(); //moves the position
	    for (int k = 0; k < size; k++){ //adds points 
	      if (board[k] == 'o'){
	        if (k == 0) //RULE #1 - Score is doubled, since 0 is evenly divisible by 7,
	          reward = reward * 2;
	        else if ((k % 7 == 0) && (k != (size - 1))) //RULE #1 - Score is doubled when it is evenly divisible by 7,
	          reward = reward * 2;
	      }
	    }
	  }

	  public static boolean isGameOver(){
	    boolean isOver = true; //checks if game is over
	    if (reward >= 1000) //if the reward is 1000 points or over than the game is over
	      isOver = false;
	    return isOver;
	  }

	  public static void drawBoard(){
	    if (first){ //temp variable is used to create the board for the first time
	      board[0] = 'o';
	      for(int i = 1; i < size; i++)
	        board[i] = '*';
	    }

	    for(int i = 0; i < size; i++) //for loop that prints out the updated board
	      System.out.print(board[i]);

	    System.out.println(" " + reward); //prints out the reward

	    first = false; //temp variable set to flase so it wont recreate the board again
	  }

	  public static void displayReport(){ //displays stats
	    System.out.println("game over"); 
	    System.out.println("rounds of play: " + turnNumber);
	    System.out.println("final reward: " + reward);
	  }
	}

