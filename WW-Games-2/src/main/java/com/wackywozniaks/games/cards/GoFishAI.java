package com.wackywozniaks.games.cards;

import java.util.ArrayList;
import java.util.LinkedList;

import com.wackywozniaks.games.Move;
import com.wackywozniaks.games.PlayerAI;

/**
 * Chooses moves for a go fish move.
 * 
 * @author WackyWozniaks Company
 * @version 05/09/2017
 */
public class GoFishAI extends PlayerAI
{
	public static GoFishMove chooseMove(GoFish g)
	{
		LinkedList<Move> list = g.getLegalActions(1);
		ArrayList<GoFishMove> moves = new ArrayList<GoFishMove>();
		for(Move move : list)
		{
			GoFishMove m = (GoFishMove) move;
			for(int i = 0; i < m.getCount(); i++)
			{
				moves.add(m);
			}
		}
		int index = (int)(Math.random() * moves.size());
		return moves.get(index);
	}
}