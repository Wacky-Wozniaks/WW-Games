package com.wackywozniaks.games.checkers;
import java.util.ArrayList;
import java.util.LinkedList;

import com.wackywozniaks.games.Move;


/**
 * 
 * @author Ted Pyne, Hyun Choi, Patrick Forelli 
 */
public class Board
{
	private Piece[][] board;                                            //Board state, represented by a 2D array of Piece objects
	private boolean whiteTurn;                                          //Current player turn is tracked in board to enable double-move logic
	private boolean lastMoveDouble;                                     //If the last move allowed a double move, store persistently across turn
	private int lastX, lastY;                                           //During the second phase of a double move, the player must select the piece they moved in the first phase
	private int blackLeft, whiteLeft;                                   //Number of pieces remaining

	private ArrayList<Move> moveList = new ArrayList<Move>();

	public Board(){
		board = new Piece[8][8];
		whiteTurn = true;   //Start with white
		addPieces(); 
	}

	public Board(Board base){
		board = new Piece[8][8];
		whiteTurn = base.isWhiteTurn();
		copyPieces(base);

	}

	private void addPieces(){
		for(int i = 0; i < 3; i++)  //Correct white rows
			for(int j = i%2; j < board[0].length; j += 2){
				board[i][j] = new Piece(true);                          //Initialize White pieces
				whiteLeft++;
			}

		for(int i = 7; i > 4; i--)  //Correct black rows
			for(int j = i%2; j < board[0].length; j += 2){
				board[i][j] = new Piece(false);                          //Initialize black pieces
				blackLeft++;
			}
	}

	private void copyPieces(Board other){         //Produces an exact copy of a "parent" board
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				board[i][j] = other.getPiece(i,j);
			}
		}
	}

	public boolean isValidSelection(int xpos, int ypos){                 //If the selected piece is owned by the current player's turn
		if(xpos > 7 || xpos < 0 || ypos > 7 || ypos < 0) return false;
		if(board[xpos][ypos]==null) return false;
		return board[xpos][ypos].getIsWhite()==whiteTurn;                  //Compare piece color to current turn
	}

	public boolean isEmpty(int xpos, int ypos){ //return if a selection is empty
		return board[xpos][ypos]==null;
	}

	public boolean isWhiteTurn(){ return whiteTurn;}

	public Piece getPiece (int xpos, int ypos)
	{
		return board[xpos][ypos];   
	}

	/**
	 * makeMove does NOT perform array bounds checking; all input params are assumed to be 0<=i<=7
	 */
	public boolean makeMove(Move move){
		CheckersMove cmove = (CheckersMove) move;
		return makeMove(cmove.getX(), cmove.getY(), cmove.getNewX(), cmove.getNewY());
	}

	public boolean makeMove(int xpos, int ypos, int newXPos, int newYPos){
		TurnProcessor turnProc = new TurnProcessor(xpos, ypos, newXPos, newYPos, this);             //Create new turnProcessor
		if(lastMoveDouble){                                     //If this move is the second phase of a double move
			if(xpos!=lastX && ypos !=lastY) return false;       //If the player selects a different piece, return false
			turnProc.isValidTurn();                             //Process coordinates
			if(!turnProc.wasMoveCapture()) return false;        //If the move was not a capture move, return false
		}
		else if(!isValidSelection(xpos, ypos)) return false;    //If the move selection is invalid, return false
		if(!isEmpty(newXPos,newYPos)) return false;             //If the move target is not empty, return false
		if(turnProc.isValidTurn()){ //If a valid move
			lastMoveDouble = false;
			doMove(xpos, ypos, newXPos, newYPos);   //Actually perform the moving of the piece
			kingPromoter(newXPos, newYPos);         //If the piece has reached the end, promote to king
			if(turnProc.wasMoveCapture()) makeCapture(turnProc);    //If the move involved a capture, make that capture

			if(turnProc.wasMoveCapture() && doubleMove(newXPos, newYPos)){  //If a double move is possible
				lastMoveDouble = true;
				lastX = newXPos;    //Store the Piece's coordinates
				lastY = newYPos;
			}
			else nextPlayer();      //Else change player turn
			moveList.add(0, new CheckersMove(whoseTurn(), xpos,ypos,newXPos,newYPos));
			return true;
		}
		return false;
	}

	public Move getLastMove(){ return moveList.get(0);}

	public double getBoardScore(boolean isWhite, double aggression){
		int blackScore=0, whiteScore=0;

		for(int x = 0; x < 8; x++)
			for(int y = 0; y < 8; y++){
				Piece piece = board[x][y];
				if(piece!=null && piece.getIsWhite()){
					if(piece.getIsKing()) whiteScore++;
					whiteScore+=2;
				}
				else if(piece!=null){
					if(piece.getIsKing()) blackScore++;
					blackScore+=2;
				}

			}
		if(isWhite) return (whiteScore * aggression) - blackScore;
		else return (blackScore * aggression) - whiteScore;

	}

	private void doMove(int xpos, int ypos, int newXPos, int newYPos){          //No checks whatsoever, just move the piece
		board[newXPos][newYPos] = board[xpos][ypos];
		board[xpos][ypos] = null;
	}

	private boolean doubleMove(int xpos, int ypos){                             //Return true if another capture is possible
		int[] newXP = {xpos + 2, xpos - 2}; //Generate possible future moves
		int[] newYP = {ypos + 2, ypos - 2};
		for(int x: newXP)
			for(int y: newYP)
				if(x > -1 && y > -1 && x < board.length && y < board.length && isEmpty(x,y)){   //Make sure the x and y are valid indices
					TurnProcessor turnProc = new TurnProcessor(xpos, ypos, x, y, this);         //Check if the move is valid
					if(turnProc.isValidTurn() && turnProc.wasMoveCapture()) return true;        //If a move is a valid capture move, return true
				}
		return false;
	}

	private void kingPromoter(int xpos, int ypos){  //If a piece has reached the opposite side, promote it to a king
		if(board[xpos][ypos].getIsWhite() && xpos == board.length -1) board[xpos][ypos] = new Piece(true, true);
		else if(!board[xpos][ypos].getIsWhite() && xpos == 0) board[xpos][ypos] = new Piece(false, true);
	}

	private void nextPlayer(){
		whiteTurn = !whiteTurn;
	}

	private void makeCapture(TurnProcessor turnProc){ //Delete the target of a capture
		int[] middle = turnProc.getCaptureTarget();
		if(board[middle[0]][middle[1]].getIsWhite()) whiteLeft--;
		else blackLeft--;
		board[middle[0]][middle[1]]=null;
	}

	public Piece gameIsWon(){                                            //If white has won, return a white piece, if black has won, return black, else return null
		LinkedList<Move> validMoves = getValidMoves();
		boolean validMove = !validMoves.isEmpty();
		
		if((!validMove && !whiteTurn) || blackLeft==0) return new Piece(true);
		if((!validMove && whiteTurn) || whiteLeft==0) return new Piece(false);
		return null;
	}

	public LinkedList<Move> getValidMoves(){     //Find if a player has any possible moves left
		LinkedList<Move> legalMoves = new LinkedList<Move>();
		for(int xpos = 0; xpos < 8; xpos++){
			for(int ypos = 0; ypos < 8; ypos++){
				int[] newXP = {xpos + 2, xpos - 2, xpos + 1, xpos - 1};     //Generate all possible move choices
				int[] newYP = {ypos + 2, ypos - 2, ypos + 1, ypos - 1};
				for(int x: newXP)
					for(int y: newYP)
						if(x > -1 && y > -1 && x < 8 && y < 8 && isEmpty(x,y) && !isEmpty(xpos,ypos)){   //Make sure the x and y are valid indices
							TurnProcessor turnProc = new TurnProcessor(xpos, ypos, x, y, this);         //Check if the move is valid
							if(getPiece(xpos,ypos).getIsWhite() == whiteTurn && turnProc.isValidTurn()) {
								int player = whoseTurn();
								legalMoves.add(new CheckersMove(player, xpos, ypos, x, y));
								
							}
						}
			}
		}
		return legalMoves;
	}
	
	public int whoseTurn() {
		if (whiteTurn) //return 0 if white's turn
			return 0;
		else
			return 1;
	}
	
	public String toString(){
		String str="";
		for(Piece[] row: board){
			for(Piece p: row){
				str+="|";
				if(p==null) str+=" ";
				else if(p.getIsWhite()) str += "W";
				else str += "B";
			}
			str+= "\n";
		}
		return str;
	}
}