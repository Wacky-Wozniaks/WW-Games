package com.wackywozniaks.games;

public abstract class PlayerAI extends Player
{
	private int depth;
	
	/**
	 * Creates a new AI agent which searches to the specified depth.
	 * @param depth The maximum depth the agent should search to.
	 */
	public PlayerAI(int depth)
	{
		super("Computer");
		this.depth = depth;
	}
	
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
        
		Move[] next = s.getLegalActions();
        if(next.length == 0) return new Object[]{s.evaluate(), null};
        
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
		
		Move[] next = s.getLegalActions();
        if(next.length == 0) return new Object[]{s.evaluate(), null};
        
        if(agent == 0) return max(s, next, agent, depth);
        else return expect(s, next, agent, depth);
	}
	
	protected Object[] max(Game s, Move[] next, int agent, int depth, int alpha, int beta)
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
	
	protected Object[] max(Game s, Move[] next, int agent, int depth)
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
	
	protected Object[] min(Game s, Move[] next, int agent, int depth, int alpha, int beta)
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
	
	protected Object[] expect(Game s, Move[] next, int agent, int depth)
	{
		int total = 0;
		for(Move action: next)
		{
			total += (Integer) expectimax(s.doMove(action), agent + 1, depth)[0];
		}
		return new Object[]{total / next.length, null};
	}
}