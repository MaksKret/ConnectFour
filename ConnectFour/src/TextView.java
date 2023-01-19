/**
 * This file is to be completed by you.
 *
 * @author <s2071662>
 */
public final class TextView
{
	public TextView()
	{
	
	}
	
	public final void displayNewGameMessage()
	{
		System.out.println("----- NEW GAME STARTED -----");
		System.out.println("Tips: ");
		System.out.println("Make sure your move is valid!");
		System.out.println("Press 0 to Pause the game");
	}
	
	public final int askForMove(Model model)
	{
		int turn = model.getTurn();
		System.out.print("Player " + turn + ", select a free column: ");
		return InputUtil.readIntFromUser();
	}
	
	public final void displayBoard(Model model)
	{
		int nrRows = model.getNrRows();
		int nrCols = model.getNrCols();
		int[][] board = model.getBoard();
		// Get your board representation.

		// This can be used to print a line between each row.
		// You may need to vary the length to fit your representation.
		String rowDivider = "-".repeat(5 * nrCols - 3);
		
		// A StringBuilder is used to assemble longer Strings more efficiently.
		StringBuilder sb = new StringBuilder();

		// Constructs a visual representation of the board
		sb.append(rowDivider);
		sb.append("\n");
		for (int i = 0; i < nrRows; i++)
		{
			for (int j = 0; j < nrCols; j++)
			{
				if (j == (nrCols - 1))
				{
					sb.append(" | " + board[i][j] + " |" );
					sb.append("\n");
				}else
					{
						sb.append(" | " + board[i][j]);
					}
			}
		}
		sb.append(rowDivider);

		// Print out the assembled String.
		System.out.println(sb);
	}
}
