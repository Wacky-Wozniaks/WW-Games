package com.wackywozniaks.games.connect;

import java.util.LinkedList;

import com.wackywozniaks.games.Move;

/**
 * A game of tic tac toe.
 * 
 * @author WackyWozniaks Company
 * @version 04/26/2017
 */
public class TicTacToe extends Connect
{
	public static final int LENGTH = 3, WIDTH = 3, CONNECTION = 3;
	
	/**
	 * Constructor to be called from the front end after it implements its move.
	 * @param board The current state of the game.
	 */
	public TicTacToe(int[][] board)
	{
		super(LENGTH, WIDTH, CONNECTION, "Tic Tac Toe", board);
	}
	
	protected TicTacToe insert(int row, int col)
	{
		int[][] board = getBoard();
		if(board[row][col] != EMPTY) return null;
		board[row][col] = PLAYER2; //only ever called by AI
		return new TicTacToe(board);
	}
	
	@Override
	public TicTacToe doMove(Move m)
	{
		ConnectMove move = (ConnectMove) m;
		return insert(move.getRow(), move.getCol());
	}

	@Override
	public LinkedList<Move> getLegalActions()
	{
		int[][] board = getBoard();
		LinkedList<Move> list = new LinkedList<Move>();
		for(int row = 0; row < board.length; row++)
		{
			for(int col = 0; col < board[0].length; col++)
			{
				if(board[row][col] == EMPTY)
				{
					list.add(new ConnectMove(row, col));
				}
			}
		}
		return list;
	}
}