package com.wackywozniaks.games;

/**
 * Manages a game and its players.
 * 
 * @author WackyWozniaks Company
 * @version 04/07/2017
 */
public class Controller
{
	private Game g;
	private Player[] players;
	
	public Controller(Game g, Player[] players)
	{
		this.g = g;
		this.players = players;
	}
	
	public Player play()
	{
		boolean gameOver = false;
		for(int player = 0; !gameOver; player++, player %= players.length)
		{
			Move next = null;
			next = players[player].move(g);
			boolean moved = g.doMove(next);
			if(!moved)
			{
				System.out.println("Invalid move");
				player--;
			}
			else gameOver = g.gameOver();
		}
		return g.getWinner();
	}
}