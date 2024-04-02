import java.util.ArrayList;

public class Pawn extends Pieces{
    public Pawn(int color,int myPos) {
        super(1, color, "Pawn");
        if (color == 1)
            super.moveTableForPiece = new int[]{-8,-16,-7,-9};
        if (color == 0)
            super.moveTableForPiece = new int[]{8,16,9,7};
        super.myPos = myPos;
    }
    public ArrayList<Integer> getPossibleMoves(Pieces[] board){
        ArrayList<Integer> possibleMoves = new ArrayList<>();
        if(isNotCapture(board, myPos + moveTableForPiece[0])){
            possibleMoves.add(moveTableForPiece[0]);
        if(!hasMoved) {
            if (isNotCapture(board, myPos + moveTableForPiece[1])) {
                possibleMoves.add(moveTableForPiece[1]);
            }
        }}
        if(!isNotCapture(board,myPos+moveTableForPiece[2]) && (myPos+moveTableForPiece[2]-7)%8!=0 && board[myPos + moveTableForPiece[2]].color != color){
            possibleMoves.add(moveTableForPiece[2]);
        }
        if(!isNotCapture(board,myPos+moveTableForPiece[3]) && (myPos+moveTableForPiece[3])%8!=0 &&  board[myPos + moveTableForPiece[3]].color != color){
            possibleMoves.add(moveTableForPiece[3]);
        }
        return possibleMoves;
    }
    @Override
    public String getSymbol() {
        return (color == 0) ? "♙" : "♟"; // White pawn or black pawn
    }
}
