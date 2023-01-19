import java.io.*;

/**
 * This file is to be completed by you.
 *
 * @author <s2071662>
 */
public final class Model
{
	// ===========================================================================
	// ================================ CONSTANTS ================================
	// ===========================================================================
	// The most common version of Connect Four has 7 rows and 6 columns.

	public static final int DEFAULT_NR_ROWS = 7;
	public static final int DEFAULT_NR_COLS = 6;

	// ========================================================================
	// ================================ FIELDS ================================
	// ========================================================================
	// The size of the board.
	private int nrRows;
	private int nrCols;
	private int inarow;
	private int[][] board;
	private int[] player;
	private int turn;

	// =============================================================================
	// ================================ CONSTRUCTOR ================================
	// =============================================================================
	public Model()
	{
		player = new int[2];
		player[0] = 1;
		player[1] = 2;
		load();
	}

	public Model( int rows, int cols, int towin)
	{
		// Initialise the board size to its user-input values, and also the amount of tiles in a row to win.
		nrRows = rows;
		nrCols = cols;
		inarow = towin;
		// Set the player's numbers and the starting player
		player = new int[2];
		player[0] = 1;
		player[1] = 2;
		turn = player[0];
		// Sets the default values to the whole board
		board = new int[nrRows][nrCols];
		for (int i = 0; i < nrRows; i++)
		{

			for (int j = 0; j < nrCols; j++)
			{
				board[i][j] = 0;
			}

		}
	}

	
	// ====================================================================================
	// ================================ MODEL INTERACTIONS ================================
	// ====================================================================================

	public boolean isMoveValid(int move)
	{
		if (move > nrCols)
		{
			return false;
		}

		// if a player fails to input a correct move, then he will not be able to access the menu before doing so
		if (move <= 0)
		{
			return false;
		}


		// returns false, when the spot chosen by the player is not empty
		if (board[0][move - 1] != 0)
		{
			return false;
		}else
			{
				return true;
			}

	}

	public void makeMove(int move)
	{
		// makes a move based on who's turn it is
		if (turn == player[0])
		{
			for (int i = (nrRows - 1); i > -1; i--)
				if (board[i][move - 1] == 0)
				{
					board[i][move - 1] = 1;
					break;
				}
			turn = player[1];
		}else
			{
				for (int i = (nrRows - 1); i > -1; i--)
					if (board[i][move - 1] == 0)
					{
						board[i][move - 1] = 2;
						break;
					}
				turn = player[0];
			}
	}

	public boolean checkWin()
	{
		// Check Horizontal win
		int count = 0;
		for ( int i = 0; i < nrRows; i++)
		{
			for (int j = 0; j < nrCols; j++)
			{
				if (board[i][j] == turn)
				{
					count++;
					if (count >= inarow)
					{
						return true;
					}
				}
				else
					{
						count = 0;
					}
			}
		}

		// Check Vertical win
		count = 0;
		for ( int i = 0; i < nrCols; i++)
		{
			for (int j = 0; j < nrRows; j++)
			{
				if (board[j][i] == turn)
				{
					count++;
					if (count >= inarow)
					{
						return true;
					}
				}
				else
				{
					count = 0;
				}
			}
		}


		// Check Left-Right Diagonal win
		for ( int i = (nrRows - 1); i > -1; i--) {
			count = 0;
			for (int j = i, cols = 0; j < nrRows; j++, cols++)
			{
				if (cols < nrCols)
				{
					if (board[j][cols] == turn)
					{
						count++;
						if (count >= inarow)
						{
							return true;
						}
					}
					else
					{
						count = 0;
					}
				}
			}
		}
		for ( int i = 0; i < nrCols; i++) {
			count = 0;
			for (int j = i, rows = 0; j < nrCols; j++, rows++)
			{
				if (rows < nrRows)
				{
					if (board[rows][j] == turn)
					{
						count++;
						if (count >= inarow)
						{
							return true;
						}
					}
					else
					{
						count = 0;
					}
				}
			}
		}



		// Check Right-Left Diagonal win
		for ( int i = (nrRows - 1); i > -1; i--) {
			count = 0;
			for (int j = i, cols = (nrCols - 1); j < nrRows; j++, cols--)
			{
				if (cols > -1)
				{
					if (board[j][cols] == turn)
					{
						count++;
						if (count >= inarow)
						{
							return true;
						}
					}
					else
					{
						count = 0;
					}
				}
			}
		}

		for ( int i = (nrCols - 1); i > -1; i--) {
			count = 0;
			for (int j = i, rows = 0; j > -1; j--, rows++)
			{
				if (rows < nrRows)
				{
					if (board[rows][j] == turn)
					{
						count++;
						if (count >= inarow)
						{
							return true;
						}
					}
					else
					{
						count = 0;
					}
				}
			}
		}


		return false;
	}

	public void save()
	{
		try
		{
			String csv = "";
			csv = nrRows + "," + nrCols + "," + inarow + "," + turn + ",";
			for(int i = 0; i < nrRows; i++)
			{
				for(int j = 0; j < nrCols; j++)
				{
					csv += board[i][j] + ",";

				}

			}

			FileOutputStream fos = new FileOutputStream("save.txt");
			OutputStreamWriter out = new OutputStreamWriter(fos);
			out.write(csv);
			out.flush();
			fos.close();

			System.out.println("Game saved.");


		}catch(Exception e)
		{
			e.printStackTrace();

		}
	}

	public void load()
	{
		try {
			//get stuff out of file
			FileInputStream fis = new FileInputStream("save.txt");
			InputStreamReader in = new InputStreamReader(fis);
			BufferedReader buff = new BufferedReader(in);
			String csv = buff.readLine();

			//close stuff
			buff.close();
			in.close();
			fis.close();

			//split the string into array of Strings
			String[] strArr = csv.split(",");
			nrRows = Integer.parseInt(strArr[0]);
			nrCols = Integer.parseInt(strArr[1]);
			inarow = Integer.parseInt(strArr[2]);
			turn = Integer.parseInt(strArr[3]);
			board = new int[nrRows][nrCols];

			//Convert string array into int array
			int counter = 0;
			for (int i = 0; i < nrRows; i++) {

				for (int j = 0; j < nrCols; j++) {
					board[i][j] = Integer.parseInt(strArr[counter + 4]);
					counter += 1;
				}

			}



		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	// =========================================================================
	// ================================ GETTERS ================================
	// =========================================================================
	public int getNrRows() { return nrRows; }
	public int getNrCols() { return nrCols; }
	public int[][] getBoard() { return board; }
	public int getTurn() { return turn; }
}

