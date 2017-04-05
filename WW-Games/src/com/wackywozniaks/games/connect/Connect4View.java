package com.wackywozniaks.games.connect;

import java.util.Observable;
import java.util.Observer;

public class Connect4View implements Observer
{

	@Override
	public void update(Observable o, Object arg)
	{
		int[][] game = ((Connect4) o).getBoard(), highlight = ((Connect4) o).getHighlight();
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
				System.out.print(game[row][col]);
			}
			System.out.println();
		}
	}
}