# Programming Project: The Opoly Game

For this assignment, your job is to create a simple game called Opoly.
The objectives of this assignment are to:

- Break down a problem into smaller, easier problems.
- Write Java methods that call upon other methods to accomplish tasks.
- Use a seed value to generate random a sequence of random numbers.
- Learn the coding practice of writing methods that perform a specific task.

## Clone Project

Begin by cloning the provided project and importing it into your workspace.

`git clone <url for github repository>`

After cloning this repository you want to run these commands

`git checkout -b development`

This will create a branch named development and switch you to it. The development branch is where you will make all your commits.

## Import into Eclipse

You should then go to Eclipse, which hopefully has finished installing.

In the top toolbar, click File --> Import. Double-click on General and then `Projects from Folder or Archive`. Click `Directory` and find the folder inside the repository named `Opoly` and click Open. The project should pop up in the text box Projects. Click finish and you should be good to go!

## Complete Opoly Assignment

Opoly works this way: The board is a circular track of variable length (the user determines the length when the game app runs). There is only one player, who begins the game at position 0.
Thus, if the board length is 20, then the board locations start at position 0 and end at position 19. The player starts with a reward of 100, and the goal of the game is to reach or exceed reward value 1000. When this reward value is reached or exceeded, the game is over. When the game ends, your program should report the number of turns the player has taken, and the final reward amount attained.

In Opoly the game piece advances via a spinner - a device that takes on one of the values 1, 2, 3, 4, 5 at random, with each of the five spin values equally likely. The spin method you will write simulates a spinner.

The circular nature of the board means that if the player advances to a position beyond the board size, the position will "wrap" around to the beginning. For example, if the board size was 20, the first position would be 0, and the last position would be 19. If a player was on position 18 and the spin result was 4, the new position would be 2. Although there are several ways to calculate this, a convenient way uses modular arithmetic: (position + spinResult) mod boardSize.

Although the board is circular, you should draw the state of the board as a single "line", using an 'o' to represent the current player position, and * represent all other positions.
Thus if the board size is 10, then this board drawing:

```
**o*******
```

means that the player is at location 2 on the board.
Here are the other Opoly game rules:
NOTE: Use the position index for rule calculations. The index starts at 0 and ends at boardLength-1.

- If your board piece lands on a board cell that is evenly divisible by 7, your reward doubles.

- If you land on the final board cell, you must go back 3 spaces. Thus if the board size is 20, the last position is position 19, and if you land there, you should go back to position 16. (If the position of the last cell is evenly divisible by 7, no extra points are added to the reward, but if the new piece location, 3 places back, IS evenly divisible by 7, then extra points ARE added to the reward).

- If you make it all the way around the board, you get 100 reward points. Note that if you land exactly on location 0, you first receive 100 extra reward points (for making it all the around), and then your reward score is doubled, since 0 is evenly divisible by 7,

- Every tenth move (that is, every tenth spin of the spinner, move numbers 10,20,30,... etc.), reduces the reward by 50 points. This penalty is applied before any other rules; as soon as the 10th or 20th or 30th move is made, even if other actions at that instant also apply. Notice that with this rule it's possible for the reward amount to become negative.

Here is the driver class for the game:

```java
import java.util.*;

public class OpolyDriver{

  public static void main(String[] args){
    System.out.println("Enter an int > 3 - the size of the board");
    Scanner scanner = new Scanner(System.in);
    int boardSize = scanner.nextInt();
    System.out.println("Board Size: " + boardSize);
    Opoly game = new Opoly(boardSize);
    game.playGame();
  }
}
```

Here is a sample run:

```
Enter an int - the size of the board:

> 17

Board Size: 17
o**************** 100
*o*************** 100
*****o*********** 100
**********o****** 100
************o**** 100
o**************** 400  // 100 added to reward, then score is doubled at location 0
****o************ 400
********o******** 400
**********o****** 400
*************o*** 400
o**************** 900  // 10th turn - so reward reduced by 50; then 100 added; then reward doubled
***o************* 900
*******o********* 1800 // at location 7, so reward doubled
game over
rounds of play: 12
final reward: 1800
```

The above example demonstrates a completely random run of Opoly. You must also allow for a seed value to be used to control the sequence of random numbers. This is helpful in debugging a game and in studying the game's properties.
The java.util.Random class has two constructors:

```java
Random rand;
rand = new Random();

//OR
rand = new Random(123456);
```

The first initializes the Random variable to an object that produces (pseudo) random numbers; The second uses a seed value, and will produce the same sequence of random numbers each time the code is run. This allows you to observe the behavior of your game and to debug it if necessary.

Therefore, declare a variable of the Random class in your Opoly class. Add another constructor (besides the single parameter constructor) that has another parameter for a seed value. That constructor will use the seed to initialize the instance of Random to a new Random class object using the seed to the Random constructor.

```java
import java.util.*;

public class OpolyDriver{

  public static void main(String[] args){
    System.out.println("Enter an int > 3 - the size of the board");
    Scanner scanner = new Scanner(System.in);
    int boardSize = scanner.nextInt();
    System.out.println("Board Size: " + boardSize);
     int seed = 123456;
    Opoly game = new Opoly(boardSize, seed);
    game.playGame();
  }
}
```

## REQUIRED CODE STRUCTURE

Your Opoly class must include the following methods and must implement the method calls as specified:

1. You must write two constructors: one that takes a single int parameter- the board size, and another constructor that takes two int parameters: the board size, and the seed for the random number generator. The one parameter constructor initializes an instance of the Random class using the Random no parameter constructor, and the second constructor initializes the Random instance using the seed value passed in.

2. playGame - The top-level method that controls the game. No return value, no parameters. Must call drawBoard, spinAndMove, isGameOver.

3. spinAndMove - spins the spinner and then advances the piece according to the rules of the game. No return value, no parameters. Must call spin and move.

4. spin - generates an integer value from 1 to 5 at random- all equally likely. Returns an integer, no parameters. Use the java.util.Random class to generate your random numbers. You may NOT use Math.random() for this project (80% off).

5. move - advances the piece according to the rules of the game. No return value, takes an integer parameter that is a value from 1 to 5.

6. isGameOver - checks if game termination condition has been met. Returns true if game is over, false otherwise. No parameters.

7. drawBoard - draws the board using \*'s and an o to mark the current board position. Following each board display you should also report the current reward. No return value, no parameters.

8. displayReport - reports the end of the game, and gives the number of rounds of play, and the final reward. No return value, no parameters.

Development tips:

- Define the attributes for the Opoly class. At any instant, what is the "state" of the board? What data do you need to keep track of to give the final report? The answers to these questions will help you define the attributes of the Opoly class.
- Write the Opoly constructors. Use the seed value during development.
- Write "stub code" for all of the methods listed above. Stub code is the method header with no body. You can simply return some value if required, such as return true; for a method that returns a boolean. Of course, methods that return void don't need a return statement. Then, write the playGame method calling the methods outlined above. You can then start to implement the methods so that you can run the code, increasing the functionality a little at a time. (This is called incremental development). Another simplification to start with: just implement the rule that increases the reward by 100 every time you circle the board. You can add the other rules later.
- Think of what the playGame method does in pseudocode:

```
  display the initial board
  while the game is not over
     spin and move
     display the board
  display the winner report
```

## Notes

- You need to comment your methods according to this [Javadoc Guide](https://github.com/jd12/liferay-portal/blob/master/readme/ADVANCED_JAVADOC_GUIDELINES.markdown).

- Use the [Game50 Example](./ProgrammingSimpleGames.md) as a guide to approaching this project
