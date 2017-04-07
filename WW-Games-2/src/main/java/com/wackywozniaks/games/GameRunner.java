package com.wackywozniaks.games;

import com.wackywozniaks.games.connect.Connect4;
import com.wackywozniaks.games.connect.Connect4View;
import com.wackywozniaks.games.connect.ConnectPlayer;
import com.wackywozniaks.games.connect.TicTacToe;
import com.wackywozniaks.games.connect.TicTacToeView;

public class GameRunner
{
	public static void connect4()
	{
		ConnectPlayer p1 = new ConnectPlayer();
		ConnectPlayer p2 = new ConnectPlayer();
		Connect4View view = new Connect4View();
		Connect4 game = new Connect4(p1, p2, view);
		Controller c = new Controller(game, p1, p2);
		
		Player winner = c.play();
		if(winner.equals(p1)) System.out.println("Player 1 wins!");
		else if(winner.equals(p2)) System.out.println("Player 2 wins!");
	}
	
	public static void tictactoe()
	{
		ConnectPlayer p1 = new ConnectPlayer();
		ConnectPlayer p2 = new ConnectPlayer();
		TicTacToeView view = new TicTacToeView();
		TicTacToe game = new TicTacToe(p1, p2, view);
		Controller c = new Controller(game, p1, p2);
		
		Player winner = c.play();
		if(winner.equals(p1)) System.out.println("Player 1 wins!");
		else if(winner.equals(p2)) System.out.println("Player 2 wins!");
	}
	
	public static void main(String[] args)
	{
		tictactoe();
	}
}