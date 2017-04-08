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
	private Player p1, p2;
	
	public Controller(Game g, Player p1, Player p2)
	{
		this.g = g;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public int play()
	{
		int state = Game.NOT_OVER;
		for(boolean turn1 = true; state == Game.NOT_OVER; turn1 = !turn1)
		{
			Move next = null;
			if(turn1) next = p1.move(g);
			else next = p2.move(g);
			boolean moved = g.doMove(next);
			if(!moved)
			{
				System.out.println("Invalid move");
				turn1 = ! turn1;
			}
			else state = g.gameOver();
		}
		return state;
	}
}