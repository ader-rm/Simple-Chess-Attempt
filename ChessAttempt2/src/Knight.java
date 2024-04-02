import java.util.ArrayList;

public class Knight extends Pieces {
    public Knight(int color,int myPos) {
        super(3, color, "Knight");
        super.moveTableForPiece = new int[]{-17, -15, -10, -6, 6, 10, 15, 17};
        super.myPos = myPos;
    }
    public ArrayList<Integer> getPossibleMoves(Pieces[] board){
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        for(int j : moveTableForPiece){
            if (helperFunc(j, board)) {
                possibleMoves.add(j);
                if(!isNotCapture(board,myPos+j)){break;}
            }
        }
        return possibleMoves;
    }

    private boolean helperFunc(int temporaryMove, Pieces[] board){
        if(myPos + temporaryMove < 0 || myPos + temporaryMove>=64){
            return false;}
        if(isNotCapture(board, myPos + temporaryMove)){
            //weak solution, hopefully will find a better one!
            if(((myPos-7)%8 == 0 && (((myPos+temporaryMove)%8 == 0 ) || (myPos+temporaryMove-1)%8 == 0)) || (myPos%8 == 0 && (((myPos+temporaryMove-7)%8 == 0) || (myPos+temporaryMove-6)%8 == 0))){
                return false;}
            if(((myPos-6)%8 == 0 && (myPos+temporaryMove)%8 == 0) || ((myPos-1)%8 == 0 && (myPos+temporaryMove-7)%8 == 0)){return false;}
            return true;
        }
        return board[myPos + temporaryMove].color != color;
    }
    @Override
    public String getSymbol() {
        return (color == 0) ? "♘" : "♞"; // White knight or black knight
    }
}
