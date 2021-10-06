import java.util.Scanner;

public class OpolyDriver {

	public static void main(String[] args) {
		System.out.println("Enter an int > 3 - the size of the board");
		Scanner scanner = new Scanner(System.in);
		int boardSize = scanner.nextInt();
		System.out.println("Board Size: " + boardSize);
		int seed = 123456;
		Opoly game = new Opoly(boardSize, seed);
		game.playGame();
	}
}