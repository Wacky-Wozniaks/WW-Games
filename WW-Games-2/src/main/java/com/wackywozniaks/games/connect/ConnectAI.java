package com.wackywozniaks.games.connect;

import com.wackywozniaks.games.Game;
import com.wackywozniaks.games.PlayerAI;

/**
 * An AI for Connect games
 * 
 * @author WackyWozniaks Company
 * @version 05/06/2017
 */
public class ConnectAI extends PlayerAI
{
	public static final int RANDOM = 0, MINIMAX = 1, MINIMAX2 = 2;
	
	public static ConnectMove chooseMove(Game g, int difficulty)
	{
		if(difficulty == RANDOM) return (ConnectMove)random(g);
		else if(difficulty == MINIMAX) return (ConnectMove)minimax(g, 2);
		else if(difficulty == MINIMAX2) return (ConnectMove)minimax(g, 4);
		else return null;
	}
}