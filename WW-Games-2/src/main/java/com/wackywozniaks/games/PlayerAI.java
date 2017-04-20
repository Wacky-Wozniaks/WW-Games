package com.wackywozniaks.games;

import java.util.LinkedList;

/**
 * Generic methods for minimax and expectimax.
 * 
 * @author WackyWozniaks Company
 * @version 04/20/2017
 */
public abstract class PlayerAI
{
	private int depth;
	
	/**
	 * Creates a new AI agent which searches to the specified depth.
	 * @param depth The maximum depth the agent should search to.
	 */
	public PlayerAI(int depth)
	{
		this.depth = depth;
	}
	
	/**
	 * Implementing classes must decide how to move based on the current game state.
	 * @param s The state of the game.
	 * @return The selected move.
	 */
	public abstract Move chooseMove(Game s);
	
	protected Move minimax(Game s)
	{
		return (Move) minimax(s, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE)[1];
	}
	
	protected Move expectimax(Game s)
	{
		return (Move) expectimax(s, 0, 0)[1];
	}

	private Object[] minimax(Game s, int agent, int depth, int alpha, int beta)
	{
		if(agent == s.getNumPlayers())
		{
			depth += 1;
			if(depth == this.depth) return new Object[]{s.evaluate(), null};
			agent = 0;
		}
        
		LinkedList<Move> next = s.getLegalActions();
        if(next.isEmpty()) return new Object[]{s.evaluate(), null};
        
        if(agent == 0) return max(s, next, agent, depth, alpha, beta);
        else return min(s, next, agent, depth, alpha, beta);
	}
	
	protected Object[] expectimax(Game s, int agent, int depth)
	{
		if(agent == s.getNumPlayers())
		{
			depth += 1;
			if(depth == this.depth) return new Object[]{s.evaluate(), null};
			agent = 0;
		}
		
		LinkedList<Move> next = s.getLegalActions();
        if(next.isEmpty()) return new Object[]{s.evaluate(), null};
        
        if(agent == 0) return max(s, next, agent, depth);
        else return expect(s, next, agent, depth);
	}
	
	protected Object[] max(Game s, LinkedList<Move> next, int agent, int depth, int alpha, int beta)
	{
		int value = Integer.MIN_VALUE;
		Move m = null;
		for(Move action: next)
		{
			int newValue = (Integer) minimax(s.doMove(action), agent + 1, depth, alpha, beta)[0];
			if(newValue > value)
			{
				value = newValue;
				m = action;
			}
			if(newValue > beta) return new Object[]{newValue, action};
			alpha = Math.max(alpha, newValue);
		}
		return new Object[]{value, m};
	}
	
	protected Object[] max(Game s, LinkedList<Move> next, int agent, int depth)
	{
		int value = Integer.MIN_VALUE;
		Move m = null;
		for(Move action: next)
		{
			int newValue = (Integer) expectimax(s.doMove(action), agent + 1, depth)[0];
			if(newValue > value)
			{
				value = newValue;
				m = action;
			}
		}
		return new Object[]{value, m};
	}
	
	protected Object[] min(Game s, LinkedList<Move> next, int agent, int depth, int alpha, int beta)
	{
		int value = Integer.MAX_VALUE;
		Move m = null;
		for(Move action: next)
		{
			int newValue = (Integer) minimax(s.doMove(action), agent + 1, depth, alpha, beta)[0];
			if(newValue < value)
			{
				value = newValue;
				m = action;
			}
			if(newValue < alpha) return new Object[]{newValue, action};
			beta = Math.min(beta, newValue);
		}
		return new Object[]{value, m};
	}
	
	protected Object[] expect(Game s, LinkedList<Move> next, int agent, int depth)
	{
		int total = 0;
		int count = 0;
		for(Move action: next)
		{
			total += (Integer) expectimax(s.doMove(action), agent + 1, depth)[0];
			count++;
		}
		return new Object[]{total / count, null};
	}
}