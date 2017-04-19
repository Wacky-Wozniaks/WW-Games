package com.wackywozniaks.games;

import com.wackywozniaks.games.connect.Connect4;
import com.wackywozniaks.games.connect.ConnectPlayer;
import com.wackywozniaks.games.connect.TicTacToe;

/**
 * Creates a new instance of a game and calls a Controller to play it.
 * 
 * @author WackyWozniaks Company
 * @version 04/11/2017
 */
public class GameRunner
{
	public static void connect4()
	{
		Player[] players = {new ConnectPlayer("Player 1"), new ConnectPlayer("Player 2")};
		Connect4View view = new Connect4View();
		Connect4 game = new Connect4(players, view);
		Controller c = new Controller(game, players);
		play(c);s
	}
	
	public static void tictactoe()
	{
		Player[] players = {new ConnectPlayer("Player 1"), new ConnectPlayer("Player 2")};
		TicTacToeView view = new TicTacToeView();
		TicTacToe game = new TicTacToe(players, view);
		Controller c = new Controller(game, players);
		play(c);
	}
	
	private static void play(Controller c)
	{
		Player winner = c.play();
		if(winner == null) System.out.println("It's a tie!");
		else System.out.println(winner.getName());
	}
	
	public static void main(String[] args)
	{
		tictactoe();
	}
}