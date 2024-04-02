import java.util.*;

abstract public class Pieces {
    int pieceValue;
    int color;
    String pieceType; // this can potentially be an integer from 1-6 (0-5)
    int[] moveTableForPiece;
    ArrayList<Integer> possibleMoves;
    int myPos;
    boolean hasMoved;
    public Pieces(int pieceValue, int color, String pieceType) {
        this.pieceValue = pieceValue;
        this.color = color;
        this.pieceType = pieceType;
        this.hasMoved = false;
    }
    public boolean isNotCapture(Pieces[] board, int squareToMoveTo) {
        return board[squareToMoveTo] == null;
    }

    @Override
    public String toString() { return color + pieceType + '\''; }
    abstract public String getSymbol();
    abstract public ArrayList<Integer> getPossibleMoves(Pieces[] board);
}
