package com.wackywozniaks.games.checkers;

import com.wackywozniaks.games.Move;

public abstract class MoveAI {
	protected Board board;
	protected boolean isWhite;
	public abstract Move makeMove();
	public MoveAI(Board board, boolean isWhite) {
		this.board = board;
		this.isWhite = isWhite;
	}
}
