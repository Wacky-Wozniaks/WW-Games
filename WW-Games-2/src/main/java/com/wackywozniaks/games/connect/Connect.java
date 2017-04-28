package com.wackywozniaks.games.connect;

import com.wackywozniaks.games.Game;

/**
 * An abstract framework for games like Connect4 and Tic Tac Toe.
 * 
 * @author WackyWozniaks Company
 * @version 04/28/2017
 */
public abstract class Connect extends Game
{
	public static final int EMPTY = 0, PLAYER1 = 1, PLAYER2 = 2, NUM_PLAYERS = 2;
	
	private int[][] board, highlight;
	private int connection;
	
	protected Connect(int length, int width, int connection, String name, int[][] board)
	{
		this.connection = connection;
		this.board = board;
	}
	
	protected Connect(int[][] board, int connection, String name, int count, int numPlayer)
	{
		this.board = board;
		this.connection = connection;
	}
	
	/**
	 * If the game has a winner, the highlight is all of the indices of the winning chain.
	 * @return The highlight, or null if the game isn't over.
	 */
	public int[][] getHighlight()
	{
		return highlight;
	}
	
	@Override
	public boolean gameOver()
	{
		int filled = 0;
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[0].length; c++)
			{
				if(board[r][c] != EMPTY)
				{
					filled++;
					int i = board[r][c];
					
					boolean row = r <= board.length - connection, col = c <= board[0].length - connection, reverse = c >= connection - 1, gameOver = false;
					
					if(row && col)
					{
						highlight = new int[connection][2];
						for(int rt = r, ct = c, count = 0; count < connection; count++, rt++, ct++)
						{
							if(board[rt][ct] == i)
							{
								highlight[count][0] = rt;
								highlight[count][1] = ct;
							}
							else
							{
								highlight = null;
								break;
							}
						}
						if(highlight != null) gameOver = true;
					}
					if(!gameOver && row && reverse)
					{
						highlight = new int[connection][2];
						for(int rt = r, ct = c, count = 0; count < connection; count++, rt++, ct--)
						{
							if(board[rt][ct] == i)
							{
								highlight[count][0] = rt;
								highlight[count][1] = ct;
							}
							else
							{
								highlight = null;
								break;
							}
						}
						if(highlight != null) gameOver = true;
					}
					if(!gameOver && row)
					{
						highlight = new int[connection][2];
						for(int rt = r, count = 0; count < connection; count++, rt++)
						{
							if(board[rt][c] == i)
							{
								highlight[count][0] = rt;
								highlight[count][1] = c;
							}
							else
							{
								highlight = null;
								break;
							}
						}
						if(highlight != null) gameOver = true;
					}
					if(!gameOver && col)
					{
						highlight = new int[connection][2];
						for(int ct = c, count = 0; count < connection; count++, ct++)
						{
							if(board[r][ct] == i)
							{
								highlight[count][0] = r;
								highlight[count][1] = ct;
							}
							else
							{
								highlight = null;
								break;
							}
						}
						if(highlight != null) gameOver = true;
					}
					
					if(gameOver)
					{
						winner = i == PLAYER1 ? 1 : 2;
						return true;
					}
				}
			}
		}
		if(filled == board.length * board[0].length)
		{
			winner = 0;
			highlight = new int[0][0];
			return true;
		}
		else return false;
	}
	
	@Override
	public int evaluate()
	{
		// TODO improve evaluation
		if(this.gameOver())
		{
			if(getWinner() == 2) return Integer.MAX_VALUE;
			else if(getWinner() == 0) return 0;
			else return Integer.MIN_VALUE;
		}
		return 0;
	}
	
	/**
	 * Makes a copy of the current configuration of the board.
	 * @return The current state of the board.
	 */
	public int[][] getBoard()
	{
		int[][] boardCopy = new int[board.length][board[0].length];
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[0].length; c++)
			{
				boardCopy[r][c] = board[r][c];
			}
		}
		return boardCopy;
	}
}