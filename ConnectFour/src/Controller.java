/**
 * This file is to be completed by you.
 *
 * @author <s2071662>
 */
public final class Controller
{
	private final Model model;
	private final TextView view;
	
	public Controller(Model model, TextView view)
	{
		this.model = model;
		this.view = view;
	}

	public void newgame (int i)
	{
		if (i == 9)
		{
			ConnectFour.newgame();
		}
	}

	public void startSession()
	{
		// Tell the user that the game has started.
		view.displayNewGameMessage();

		// sets the default values for the game to function properly
		int move;
		boolean isValid;
		int i = 0;
		boolean breakorno = false;
		view.displayBoard(model);
		int gameovernum = model.getNrCols() * model.getNrRows();


		// main loop of the game, allows a player to make a move
		while (true) {

			move = view.askForMove(model);

			// game is paused when a player enters number 0
			while (move == 0)
			{
				System.out.println("What would you like to do?");
				System.out.println("[Press 1] Save the game");
				System.out.println("[Press 2] Give up");
				System.out.println("[Press Any Other Number] Go back to the game");
				int d = InputUtil.readIntFromUser();
				// if 1 is pressed, game is saved and players continue playing
				if (d == 1)
				{
					model.save();
				}
				// when 2 is pressed player gives up, his rival wins and game ends unless the player chooses to player another one
				if (d == 2)
				{
					int turn = model.getTurn();
					// If player one gives up on his turn, player 2 wins and vice-versa
					if (turn == 2){turn = turn - 1;}else{turn = turn + 1;}
					System.out.println("Player " + turn + " wins the game!");
					// replays the game from the start based on user input
					System.out.println("Would you like to play another game? yes -> 9 / no -> any other number");
					int yesorno = InputUtil.readIntFromUser();
					newgame(yesorno);
					breakorno = true;
					break;
				}
				// Unpauses the game for next round and asks for move
				view.displayBoard(model);
				move = view.askForMove(model);

			}
			// When a player decides to give up, the loop will terminate and therefore the game will end
			if (breakorno)
			{
				break;
			}

			isValid = model.isMoveValid(move);
			// loop that allows for validating that the correct move is entered
			while (!isValid)
			{
				System.out.println("Invalid move, please re-enter");
				move = view.askForMove(model);
				isValid = model.isMoveValid(move);
			}
			// checks whether the game is paused or not and prints out different options for the player

			model.makeMove(move);
			view.displayBoard(model);

			// Check whether a player has won
			if (model.checkWin())
			{
				int turn = model.getTurn();
				System.out.println("Player " + turn + " won the game!");
				System.out.println("Would you like to play another game? yes -> 9 / no -> any other number");
				int yesorno = InputUtil.readIntFromUser();
				newgame(yesorno);
				break;
			}

			i++;
			// when board fills up fully, game ends in draw
			if (i == gameovernum)
			{
				System.out.println("It is a draw! No player wins.");
				System.out.println("Would you like to play another game? yes -> 9 / no -> any other number");
				int yesorno = InputUtil.readIntFromUser();
				newgame(yesorno);
				break;
			}
		}

	}
}