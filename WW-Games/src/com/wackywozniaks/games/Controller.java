package com.wackywozniaks.games;

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
	
	public Player play()
	{
		Player winner = null;
		boolean turn1 = false;
		while(winner == null)
		{
			turn1 = !turn1;
			Move next = null;
			if(turn1) next = p1.move(g);
			else next = p2.move(g);
			boolean moved = g.doMove(next);
			if(!moved)
			{
				System.out.println("Invalid move");
				turn1 = ! turn1;
			}
			else winner = g.gameOver();
		}
		return winner;
	}
}