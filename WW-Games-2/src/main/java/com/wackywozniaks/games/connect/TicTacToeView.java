package com.wackywozniaks.games.connect;

import java.util.Observable;
import java.util.Observer;

/**
 * A view of a Tic Tac Toe game, updated after each change.
 * 
 * @author WackyWozniaks Company
 * @version 04/07/2017
 */
public class TicTacToeView implements Observer
{
	@Override
	public void update(Observable o, Object arg)
	{
		int[][] game = ((TicTacToe) o).getBoard(), highlight = ((TicTacToe) o).getHighlight();
		if(highlight != null)
		{
			for(int i = 0; i < highlight.length; i++)
			{
				game[highlight[i][0]][highlight[i][1]] = 5;
			}
		}
		for(int row = 0; row < game.length; row++)
		{
			for(int col = 0; col < game[0].length; col++)
			{
				if(game[row][col] == Connect.PLAYER2) System.out.print("O");
				else if(game[row][col] == Connect.PLAYER1) System.out.print("X");
				else if(game[row][col] == 5) System.out.println("*");
				else System.out.print("-");
			}
			System.out.println();
		}
	}
}