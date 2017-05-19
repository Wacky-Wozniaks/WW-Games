package com.wackywozniaks.games;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Generic methods for minimax and expectimax.
 * 
 * @author WackyWozniaks Company
 * @version 05/18/2017
 */
public abstract class PlayerAI
{	
	protected static Move random(Game s)
	{
		LinkedList<Move> moves = s.getLegalActions(2);
		if(moves.isEmpty()) return null;
		int index = (int)(Math.random() * moves.size());
		return moves.get(index);
	}
	
	protected static Move minimax(Game s, int maxDepth)
	{
		return (Move) minimax(s, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, maxDepth)[1];
	}
	
	protected static Move expectimax(Game s, int maxDepth)
	{
		return (Move) expectimax(s, 0, 0, maxDepth)[1];
	}

	private static Object[] minimax(Game s, int agent, int depth, int alpha, int beta, int maxDepth)
	{
		if(agent == 2) //assumes a 2 player game
		{
			depth += 1;
			if(depth == maxDepth) return new Object[]{s.evaluate(), null};
			agent = 0;
		}
        
		LinkedList<Move> next = s.getLegalActions(2 - agent);
        if(next.isEmpty()) return new Object[]{s.evaluate(), null};
        
        if(agent == 0) return max(s, next, agent, depth, alpha, beta, maxDepth);
        else return min(s, next, agent, depth, alpha, beta, maxDepth);
	}
	
	protected static Object[] expectimax(Game s, int agent, int depth, int maxDepth)
	{
		if(agent == 2) //assumes a 2 player game
		{
			depth += 1;
			if(depth == maxDepth) return new Object[]{s.evaluate(), null};
			agent = 0;
		}
		
		LinkedList<Move> next = s.getLegalActions(2 - agent);
        if(next.isEmpty()) return new Object[]{s.evaluate(), null};
        
        if(agent == 0) return max(s, next, agent, depth, maxDepth);
        else return expect(s, next, agent, depth, maxDepth);
	}
	
	protected static Object[] max(Game s, LinkedList<Move> next, int agent, int depth, int alpha, int beta, int maxDepth)
	{
		int value = Integer.MIN_VALUE;
		ArrayList<Move> moves = new ArrayList<Move>();
		//Move m = null;
		for(Move action: next)
		{
			int newValue = (Integer) minimax(s.doMove(action), agent + 1, depth, alpha, beta, maxDepth)[0];
			if(newValue > value)
			{
				value = newValue;
				moves.clear();
				moves.add(action);
				//m = action;
			}
			else if(newValue == value) moves.add(action);
			if(newValue > beta) return new Object[]{newValue, action};
			alpha = Math.max(alpha, newValue);
		}
		return new Object[]{value, moves.get((int)(Math.random() * moves.size()))};
	}
	
	protected static Object[] max(Game s, LinkedList<Move> next, int agent, int depth, int maxDepth)
	{
		int value = Integer.MIN_VALUE;
		ArrayList<Move> moves = new ArrayList<Move>();
		//Move m = null;
		for(Move action: next)
		{
			int newValue = (Integer) expectimax(s.doMove(action), agent + 1, depth, maxDepth)[0];
			if(newValue > value)
			{
				value = newValue;
				//m = action;
				moves.clear();
				moves.add(action);
			}
		}
		return new Object[]{value, moves.get((int)(Math.random() * moves.size()))};
	}
	
	protected static Object[] min(Game s, LinkedList<Move> next, int agent, int depth, int alpha, int beta, int maxDepth)
	{
		int value = Integer.MAX_VALUE;
		ArrayList<Move> moves = new ArrayList<Move>();
		//Move m = null;
		for(Move action: next)
		{
			int newValue = (Integer) minimax(s.doMove(action), agent + 1, depth, alpha, beta, maxDepth)[0];
			if(newValue < value)
			{
				value = newValue;
				//m = action;
				moves.clear();
				moves.add(action);
			}
			if(newValue < alpha) return new Object[]{newValue, action};
			beta = Math.min(beta, newValue);
		}
		return new Object[]{value, moves.get((int)(Math.random() * moves.size()))};
	}
	
	protected static Object[] expect(Game s, LinkedList<Move> next, int agent, int depth, int maxDepth)
	{
		int total = 0;
		int count = 0;
		for(Move action: next)
		{
			total += (Integer) expectimax(s.doMove(action), agent + 1, depth, maxDepth)[0];
			count++;
		}
		return new Object[]{total / count, null};
	}
}