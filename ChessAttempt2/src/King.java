import java.util.*;

public class King extends Pieces{
    public King(int color,int myPos) {
        super(10000,color,"King");
        super.moveTableForPiece = new int[]{-9,-8,-7,-1,1,7,8,9};
        super.myPos = myPos;
    }
    public ArrayList<Integer> getPossibleMoves(Pieces[] board){
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        for (int j : moveTableForPiece) {
            if (helperFunc(j, board)) {
                possibleMoves.add(j);
            }
        }
        return possibleMoves;
    }
    private boolean helperFunc(int temporaryMove, Pieces[] board){
            if(myPos + temporaryMove < 0 || myPos + temporaryMove>=64){
                return false;}
            if(isNotCapture(board, myPos + temporaryMove)){
                //weak solution, hopefully will find a better one!
                if(((myPos-7)%8 == 0 && (myPos+temporaryMove)%8 == 0) || (myPos%8 == 0 && (myPos+temporaryMove-7)%8 == 0)){
                    return false;}
                return true;
            }
        return board[myPos + temporaryMove].color != color;
    }
    @Override
    public String getSymbol() {
        return (color == 0) ? "♔" : "♚"; // White king or black king
    }
}

