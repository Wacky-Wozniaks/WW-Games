package com.wackywozniaks.games.checkers;

import com.wackywozniaks.games.Game;
import com.wackywozniaks.games.PlayerAI;

/**
 * The AI for Checkers
 * 
 * @author WackyWozniaks Company
 * @version 05/13/2017
 */
public class CheckersAI extends PlayerAI {
	
	public static final int RANDOM = 0;
	public static final int MINIMAX = 1;
	public static final int MINIMAX2 = 2;
	
	public static CheckersMove chooseMove(Game g, int difficulty){
		if(difficulty == RANDOM) return (CheckersMove)random(g);
		else if(difficulty == MINIMAX) return (CheckersMove)minimax(g, 2);
		else if(difficulty == MINIMAX2) return (CheckersMove)minimax(g, 4);
		else return null;
	}

}
