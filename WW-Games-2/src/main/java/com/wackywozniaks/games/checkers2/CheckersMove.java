package com.wackywozniaks.games.checkers2;

import com.wackywozniaks.games.Move;

/**
 * Represents a move for Checkers
 * 
 * @author WackyWozniaks Company
 * @version 05/10/2017
 */
public class CheckersMove extends Move {
	
	private int row;
	private int col;
	private int originalRow;
	private int originalCol;
	private boolean king;
	private boolean jump;
	private CheckersMove lastMove;

	public CheckersMove(int originalRow, int originalCol, int row, int col, boolean king, int player, boolean jump, CheckersMove lastMove) {
		super(player);
		this.originalRow = originalRow;
		this.originalCol = originalCol;
		this.row = row;
		this.col = col;
		this.king = king;
		this.jump = jump;
		this.lastMove = lastMove;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @return the king
	 */
	public boolean isKing() {
		return king;
	}

	/**
	 * @param king the king to set
	 */
	public void setKing(boolean king) {
		this.king = king;
	}

	/**
	 * @return the originalRow
	 */
	public int getOriginalRow() {
		return originalRow;
	}

	/**
	 * @param originalRow the originalRow to set
	 */
	public void setOriginalRow(int originalRow) {
		this.originalRow = originalRow;
	}

	/**
	 * @return the originalCol
	 */
	public int getOriginalCol() {
		return originalCol;
	}

	/**
	 * @param originalCol the originalCol to set
	 */
	public void setOriginalCol(int originalCol) {
		this.originalCol = originalCol;
	}

	/**
	 * @return the jump
	 */
	public boolean isJump() {
		return jump;
	}

	/**
	 * @param jump the jump to set
	 */
	public void setJump(boolean jump) {
		this.jump = jump;
	}

	/**
	 * @return the lastMove
	 */
	public CheckersMove getLastMove() {
		return lastMove;
	}

	/**
	 * @param lastMove the lastMove to set
	 */
	public void setLastMove(CheckersMove lastMove) {
		this.lastMove = lastMove;
	}

}
