/**
 * The main class of the Connect Four game.
 * You should not have to touch this code (except maybe for advanced features).
 *
 * @author David Symons
 */
public final class ConnectFour
{
	/**
	 * The code provided for this assignment follows a design pattern called
	 * Model-View-Controller (MVC). The main method instantiates each of these
	 * components and then starts the game loop.
	 *
	 * @param 'args No arguments expected.
	 */

	// Allows to start a brand new game with default values
	public static void newgame()
	{
		// Determines whether a player would like to load a previously saved game
		System.out.print("Would you like to load the game (yes -> 9 / no -> any other number): ");
		int d = InputUtil.readIntFromUser();
		if (d == 9)
		{
			Model model = new Model();

			// This text-based view is used to communicate with the user.
			// It can print the state of the board and handles user input.
			TextView view = new TextView();

			// The controller facilitates communication between model and view.
			// It also contains the main loop that controls the sequence of events.
			Controller controller = new Controller(model, view);

			// Start a new session.
			controller.startSession();
		}
		else
			{
				// Creates a model representing the state of the game based on two variables for number of rows and columns.
				System.out.println("Please input the desired values for number of Rows and Columns respectively:");
				System.out.print("Rows: ");
				int a = InputUtil.readIntFromUser();
				while (a > 100 | a <= 0)
				{
					System.out.println("Please make sure the value is within 0 and 100:");
					System.out.print("Rows: ");
					a = InputUtil.readIntFromUser();
				}
				System.out.print("Columns: ");
				int b = InputUtil.readIntFromUser();
				while (b > 100 | b <= 0)
				{
					System.out.println("Please make sure the value is within 0 and 100:");
					System.out.print("Rows: ");
					b = InputUtil.readIntFromUser();
				}
				// User inputs how many tiles in a row a player should get to win
				System.out.print("How many in a row to win: ");
				int c = InputUtil.readIntFromUser();
				// Validates that the game is winnable in at least one way
				while (c > a & c > b)
				{
					System.out.print("Please make sure the game is winnable: ");
					c = InputUtil.readIntFromUser();
				}
				Model model = new Model(a, b, c);

				// This text-based view is used to communicate with the user.
				// It can print the state of the board and handles user input.
				TextView view = new TextView();

				// The controller facilitates communication between model and view.
				// It also contains the main loop that controls the sequence of events.
				Controller controller = new Controller(model, view);

				// Start a new session.
				controller.startSession();
			}
	}
	public static void main(String[] args)
	{
		newgame();
	}
}
