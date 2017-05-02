package com.wackywozniaks.games.checkers;
/**
 *
 * @author Hyun Choi, Ted Pyne, Patrick Forelli
 */
import java.util.ArrayList;
public class AI extends MoveAI{

    private boolean madeCapture;
    private ArrayList<Move> posMoves;
    private final int BASE_RECUR = 20;
    public AI(Board board, boolean isWhite){     //Store a board to make moves on
        super(board,isWhite);
    }

    public AI(Board board){
        this(board, false);
    }

    public boolean makeMove(int recurLeft){
        madeCapture = false;
        if(board.isWhiteTurn()!=isWhite) return false;        
        //First attempt a capture move
        posMoves = new ArrayList<Move>();

        for(int x = 0; x < 8; x++){                   //Find possible captures
            for(int y = 0; y < 8; y++){
                if(board.isValidSelection(x,y)){
                    int[] newX = {x+2, x-2};        //Generate possible moves
                    int[] newY = {y+2, y-2};
                    for(int tryX: newX)
                        for(int tryY: newY)
                            if(validTarget(tryX, tryY) && calcCapture(x,y,tryX,tryY))
                            	return true;      //Use submethod to check for valid capture
                }
            
                    int[] newX = {x+1, x-1};        //Generate possible moves
                    int[] newY = {y+1, y-1};
                    for(int tryX: newX)
                        for(int tryY: newY)
                            if(validTarget(tryX, tryY)){
                                Board newMove = new Board(board);
                                if(newMove.makeMove(x,y,tryX,tryY)){ 
                                    if(recurLeft==0) {
                                    	board.makeMove(x,y,tryX,tryY); 
                                    	return true;}
                                    else posMoves.add(new Move(x,y,tryX,tryY));
                                }
                            }
                }
            }
        
        if(posMoves.size()==0) return false;   //If no move can be found, return false
        
        for(int i = 0; i < posMoves.size(); i++){
            Board test = new Board(board);
            test.makeMove(posMoves.get(i));
            AI tester = new AI(test, !isWhite);
            tester.makeMove(recurLeft-1);
            
            if(!tester.captureMade()){
                board.makeMove(posMoves.get(i));
                return true;
            }
        }
        int rand = (int)(Math.random()*posMoves.size());
        board.makeMove(posMoves.get(rand));
        return true;
    }

    public boolean makeMove(){
        return makeMove(BASE_RECUR);
    }

    private boolean calcCapture(int x, int y, int capX, int capY){
        if(board.makeMove(x,y,capX,capY)){   //If a possible capture (with double moves) is possible, perform that capture
            madeCapture = true;
            if(board.isWhiteTurn()!=isWhite) return true;    //If a double move is not possible, exit
            else {
                int[] newX = {x+2, x-2};        //Generate possible moves
                int[] newY = {y+2, y-2};
                for(int tryX: newX)
                    for(int tryY: newY)
                        if(validTarget(tryX, tryY) && calcCapture(x,y,tryX,tryY)) return true;  //If a double move is possible, try all follow up moves recursively
            }
        }
        return false;
    }

    private boolean validTarget(int x, int y){      //Bounds checking for move and capture targets
        return x > -1 && x < 8 && y > -1 && y < 8;
    }

    public boolean captureMade(){
        return madeCapture;
    }

}