package com.wackywozniaks.games.connect;

import java.util.LinkedList;
import java.util.Observer;

import com.wackywozniaks.games.Move;
import com.wackywozniaks.games.Player;

/**
 * A game of Connect 4.
 * 
 * @author WackyWozniaks Company
 * @version 04/11/2017
 */
public class Connect4 extends Connect
{
	public Connect4()
	{
		super(7, 6, 4, "Connect4");
	}
	
	private Connect4(int[][] board, int count, int turn)
	{
		super(board, 4, "Connect4", count, turn);
	}

	@Override
	public Connect4 doMove(Move m)
	{
		ConnectMove move = (ConnectMove) m;
		return drop(move.getCol());
	}
	
	protected Connect4 drop(int col)
	{
		int[][] board = getBoard();
		for(int r = board.length - 1; r >= 0; r--)
		{
			if(board[r][col] == EMPTY)
			{
				board[r][col] = (getPlayerTurn() == 0 ? PLAYER1 : PLAYER2);
				return new Connect4(board, getCount(), getPlayerTurn() + 1);
			}
		}
		return null;
	}

	@Override
	public Move[] getLegalActions()
	{
		int[][] board = getBoard();
		int count = 0;
		LinkedList<int[]> list = new LinkedList<int[]>();
		for(int col = 0; col < board[0].length; col++)
		{
			for(int row = board.length - 1; row >= 0; row--)
		}
	}

	@Override
	public int evaluate() {
		// TODO Auto-generated method stub
		return 0;
	}
}