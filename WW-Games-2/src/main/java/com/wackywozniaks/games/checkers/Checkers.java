package com.wackywozniaks.games.checkers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import com.wackywozniaks.games.Game;
import com.wackywozniaks.games.Move;
import com.wackywozniaks.games.checkers.CheckersMove;

/**
 * Represents a game of checkers
 * 
 * @author WackyWozniaks Company
 * @version 05/14/2017
 */
public class Checkers extends Game {
	
	public static final int EMPTY = 0;
	public static final int RED = 1;
	public static final int RED_KING = 2;
	public static final int WHITE = 3;
	public static final int WHITE_KING = 4;
	public static final int RED_PLAYER = 1;
	public static final int WHITE_PLAYER = 2;
	
	private static final int AGGRESSION = 2;
	
	private int[][] board;
	
	/**
	 * Initializes the object with the default board.
	 */
	public Checkers() {
		this.board = new int[][]{
			{WHITE, EMPTY, WHITE, EMPTY, WHITE, EMPTY, WHITE, EMPTY},
			{EMPTY, WHITE, EMPTY, WHITE, EMPTY, WHITE, EMPTY, WHITE},
			{WHITE, EMPTY, WHITE, EMPTY, WHITE, EMPTY, WHITE, EMPTY},
			{EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
			{EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
			{EMPTY, RED, EMPTY, RED, EMPTY, RED, EMPTY, RED},
			{RED, EMPTY, RED , EMPTY, RED, EMPTY, RED, EMPTY},
			{EMPTY, RED, EMPTY, RED, EMPTY, RED, EMPTY, RED}
		};
	}
	
	/**
	 * Creates a new Checkers with the given board.
	 * 
	 * @param board The board used to initialize the checkers object.
	 */
	public Checkers(int[][] board) {
		this.board = board;
	}

	@Override
	public Checkers doMove(Move m) {
		CheckersMove move = (CheckersMove) m;
		int[][] board2 = makeMove(board, move);
		if(board2 == null) {
			
			for(int row = 0; row < board.length; row++)
			{
				for(int col = 0; col < board[0].length; col++)
				{
					if(board[row][col] == Checkers.RED) System.out.print("O");
					else if(board[row][col] == Checkers.RED_KING) System.out.print(".");
					else if(board[row][col] == Checkers.WHITE) System.out.print("X");
					else if(board[row][col] == Checkers.WHITE_KING) System.out.print("*");
					else System.out.print("-");
				}
				System.out.println();
			}

			
			System.out.println("NULL");
		}
		return new Checkers(board2);
	}
	
	/**
	 * Gets a map of all legal actions for a player with the key being the starting cell and the values a list of
	 * the possible moves for that piece.
	 * 
	 * @param player The player who will move
	 * @return A map of the possible moves
	 */
	public Map<String, ArrayList<CheckersMove>> getMapOfMoves(int player) {
		LinkedList<Move> moves = getLegalActions(player);
		HashMap<String, ArrayList<CheckersMove>> map = new HashMap<String, ArrayList<CheckersMove>>();
		
		for(Move m: moves) {
			CheckersMove m2 = (CheckersMove) m;
			String cellName = "cell-" + m2.getOriginalRow() + m2.getOriginalCol();
			ArrayList<CheckersMove> list = map.get(cellName);
			if(list != null) {
				list.add(m2);
			}
			else {
				list = new ArrayList<CheckersMove>();
				list.add(m2);
			}
			map.put(cellName, list);
		}
		
		return map;
	}

	@Override
	public LinkedList<Move> getLegalActions(int player) {
		if(this.gameOver()) return new LinkedList<Move>();
		LinkedList<Move> normalMoves = new LinkedList<Move>();
		LinkedList<Move> jumpMoves = new LinkedList<Move>();
		//System.out.println(board);
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				//System.out.println(board[i][j] + ", " + i + ", " + j);
				if(isPlayersPiece(board[i][j], player)) {
					normalMoves.addAll(getNormalMoves(i, j, player, getBoard()));
					jumpMoves.addAll(getJumpMoves(i, j, player, getBoard(), null));
				}
			}
		}
		
		//if jump moves are possible, they must be done
		if(jumpMoves.size() > 0) {
			return jumpMoves;
		}
		else {
			return normalMoves;
		}
	}
	
	/**
	 * Returns true if the given piece belongs to the given player.
	 * 
	 * @param piece The piece to test
	 * @param player The player to test
	 * @return Whether the piece belongs to the player
	 */
	private static boolean isPlayersPiece(int piece, int player) {
		if(player == RED_PLAYER) {
			return piece == RED || piece == RED_KING;
		}
		else if(player == WHITE_PLAYER) {
			return piece == WHITE || piece == WHITE_KING;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Gets the moves that involve moving one square diagonally (no jumping) for a given piece.
	 * 
	 * @param row The row of the piece to move
	 * @param col The column of the piece to move
	 * @param player The player who the piece belongs to
	 * @return A list of possible normal moves for a given piece.
	 */
	private static LinkedList<CheckersMove> getNormalMoves(int row, int col, int player, int[][] board2) {
		LinkedList<CheckersMove> moves = new LinkedList<CheckersMove>();
		
		if((player == RED_PLAYER || board2[row][col] == WHITE_KING) && row > 0) {
			if(col >= 1 && board2[row - 1][col - 1] == EMPTY) {
				if(row - 1 == 0 || board2[row][col] == WHITE_KING || board2[row][col] == RED_KING) {
					moves.add(new CheckersMove(row, col, row - 1, col - 1, true, player, false, null));
				}
				else {
					moves.add(new CheckersMove(row, col, row - 1, col - 1, false, player, false, null));
				}
			}
			if(col <= 6 && board2[row - 1][col + 1] == EMPTY) {
				if(row - 1 == 0 || board2[row][col] == WHITE_KING || board2[row][col] == RED_KING) {
					moves.add(new CheckersMove(row, col, row - 1, col + 1, true, player, false, null));
				}
				else {
					moves.add(new CheckersMove(row, col, row - 1, col + 1, false, player, false, null));
				}
			}
		}
		if((player == WHITE_PLAYER || board2[row][col] == RED_KING) && row < 7) {
			if(col >= 1 && board2[row + 1][col - 1] == EMPTY) {
				if(row + 1 == 7 || board2[row][col] == RED_KING || board2[row][col] == WHITE_KING) {
					moves.add(new CheckersMove(row, col, row + 1, col - 1, true, player, false, null));
				}
				else {
					moves.add(new CheckersMove(row, col, row + 1, col - 1, false, player, false, null));
				}
			}
			if(col <= 6 && board2[row + 1][col + 1] == EMPTY) {
				if(row + 1 == 7 || board2[row][col] == RED_KING || board2[row][col] == WHITE_KING) {
					moves.add(new CheckersMove(row, col, row + 1, col + 1, true, player, false, null));
				}
				else {
					moves.add(new CheckersMove(row, col, row + 1, col + 1, false, player, false, null));
				}
			}
		}
		
		return moves;
	}
	
	/**
	 * Gets the moves that involve jumping over an enemy's piece (jumping moves).
	 * 
	 * @param row The row of the piece to move
	 * @param col The column of the piece to move
	 * @param player The player who the piece belongs to
	 * @param board2 The board to test on
	 * @param lastMove The CheckersMove that got to board2, null if nothing.
	 * @return A list of possible normal moves for a given piece.
	 */
	private LinkedList<CheckersMove> getJumpMoves(int row, int col, int player, int[][] board2, CheckersMove lastMove) {
		LinkedList<CheckersMove> moves = new LinkedList<CheckersMove>();
		
		if((player == RED_PLAYER || board2[row][col] == WHITE_KING) && row > 1) {
			if(col >= 2 && board2[row - 2][col - 2] == EMPTY && board2[row - 1][col - 1] != EMPTY && !isPlayersPiece(board2[row - 1][col - 1], player)) {
				if(row - 2 == 0 || board2[row][col] == WHITE_KING || board2[row][col] == RED_KING) {
					CheckersMove cm = new CheckersMove(row, col, row - 2, col - 2, true, player, true, lastMove);
					moves.add(cm);
					moves.addAll(getJumpMoves(row - 2, col - 2, player, makeMove(board2, cm), cm));
				}
				else {
					CheckersMove cm = new CheckersMove(row, col, row - 2, col - 2, false, player, true, lastMove);
					moves.add(cm);
					moves.addAll(getJumpMoves(row - 2, col - 2, player, makeMove(board2, cm), cm));
				}
			}
			if(col <= 5 && board2[row - 2][col + 2] == EMPTY && board2[row - 1][col + 1] != EMPTY && !isPlayersPiece(board2[row - 1][col + 1], player)) {
				if(row - 2 == 0 || board2[row][col] == WHITE_KING || board2[row][col] == RED_KING) {
					CheckersMove cm = new CheckersMove(row, col, row - 2, col + 2, true, player, true, lastMove);
					moves.add(cm);
					moves.addAll(getJumpMoves(row - 2, col + 2, player, makeMove(board2, cm), cm));
				}
				else {
					CheckersMove cm = new CheckersMove(row, col, row - 2, col + 2, false, player, true, lastMove);
					moves.add(cm);
					moves.addAll(getJumpMoves(row - 2, col + 2, player, makeMove(board2, cm), cm));
				}
			}
		}
		if((player == WHITE_PLAYER || board2[row][col] == RED_KING) && row < 6) {
			if(col >= 2 && board2[row + 2][col - 2] == EMPTY && board2[row + 1][col - 1] != EMPTY && !isPlayersPiece(board2[row + 1][col - 1], player)) {
				if(row + 2 == 7 || board2[row][col] == RED_KING || board2[row][col] == WHITE_KING) {
					CheckersMove cm = new CheckersMove(row, col, row + 2, col - 2, true, player, true, lastMove);
					moves.add(cm);
					moves.addAll(getJumpMoves(row + 2, col - 2, player, makeMove(board2, cm), cm));
				}
				else {
					CheckersMove cm = new CheckersMove(row, col, row + 2, col - 2, false, player, true, lastMove);
					moves.add(cm);
					moves.addAll(getJumpMoves(row + 2, col - 2, player, makeMove(board2, cm), cm));
				}
			}
			if(col <= 5 && board2[row + 2][col + 2] == EMPTY && board2[row + 1][col + 1] != EMPTY && !isPlayersPiece(board2[row + 1][col + 1], player)) {
				if(row + 2 == 7 || board2[row][col] == RED_KING || board2[row][col] == WHITE_KING) {
					CheckersMove cm = new CheckersMove(row, col, row + 2, col + 2, true, player, true, lastMove);
					moves.add(cm);
					moves.addAll(getJumpMoves(row + 2, col + 2, player, makeMove(board2, cm), cm));
				}
				else {
					CheckersMove cm = new CheckersMove(row, col, row + 2, col + 2, false, player, true, lastMove);
					moves.add(cm);
					moves.addAll(getJumpMoves(row + 2, col + 2, player, makeMove(board2, cm), cm));
				}
			}
		}
		
		return moves;
	}
	
	/**
	 * Takes the given board and performs the given move on it.
	 * 
	 * @param board2 The board to use
	 * @param move The move to perform
	 * @return The new board with the move performed.
	 */
	public static int[][] makeMove(int[][] board2, CheckersMove move) {
		//System.out.println(move);
		/*if(move.getLastMove() != null) {
			System.out.println("HELLO!!!!!!");
			board2 = makeMove(copyBoard(board2), move.getLastMove());
		}*/
		//System.out.println(move.getOriginalRow() + ", " + move.getOriginalCol() + ", " + move.getRow() + ", " + move.getCol() + ", " + move.isJump() + ", " + board2[move.getRow()][move.getCol()] + ", " + move.getLastMove());
		if(board2[move.getRow()][move.getCol()] != EMPTY) {
			return null;
		}
		board2 = copyBoard(board2);
		
		board2[move.getRow()][move.getCol()] = board2[move.getOriginalRow()][move.getOriginalCol()];
		if(move.getRow() == 0 && board2[move.getRow()][move.getCol()] == RED) board2[move.getRow()][move.getCol()] = RED_KING;
		else if(move.getRow() == 7 && board2[move.getRow()][move.getCol()] == WHITE) board2[move.getRow()][move.getCol()] = WHITE_KING;
		board2[move.getOriginalRow()][move.getOriginalCol()] = EMPTY;
		if(move.isJump()) {
			board2[(move.getOriginalRow() + move.getRow()) / 2][(move.getOriginalCol() + move.getCol()) / 2] = EMPTY;
		}
		
		return board2;
	}
	
	/**
	 * Adapted from Pyne/Choi/Forelli
	 */
	@Override
	public int evaluate() {
		int redScore = 0;
		int whiteScore = 0;

		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++){
				if(isPlayersPiece(board[x][y], WHITE_PLAYER)){
					if(board[x][y] == WHITE_KING) {
						whiteScore++;
					}
					whiteScore += 2;
				}
				else if(isPlayersPiece(board[x][y], RED_PLAYER)){
					if(board[x][y] == RED_KING) {
						redScore++;
					}
					redScore += 2;
				}
			}
		
		return -(redScore * AGGRESSION) + whiteScore;
	}

	@Override
	public boolean gameOver() {
		int numRed = 0;
		int numWhite = 0;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(isPlayersPiece(board[i][j], RED_PLAYER)) {
					numRed++;
				}
				else if(isPlayersPiece(board[i][j], WHITE_PLAYER)) {
					numWhite++;
				}
			}
		}
		
		winner = (numRed == 0) ? WHITE_PLAYER : (numWhite == 0) ? RED_PLAYER : 0;
		return numRed == 0 || numWhite == 0;
	}

	/**
	 * @return A deep copy of the board
	 */
	public int[][] getBoard() {
		return copyBoard(board);
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	/**
	 * Create a deep copy of a checkers board.
	 * 
	 * @param board The board to copy.
	 * @return A deep copy of the given board
	 */
	public static int[][] copyBoard(int[][] board) {
		int[][] newBoard = new int[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				newBoard[i][j] = board[i][j];
			}
		}
		return newBoard;
	}
	
}
