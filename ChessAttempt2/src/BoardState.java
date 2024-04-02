
import java.util.*;

public class BoardState {
    Pieces[] board;
    int[] kingPos;
    boolean isCheck;

    ///////////////the below doesn't work yet prob
    public BoardState(Pieces[] board) {
//        if (board.length == 64-1) {
        this.board = board;
        isCheck = false;
        this.kingPos = new int[2];
//        }
//        throw new RuntimeException("Board size is incorrect, can't initialize board");
    }

    /////////////////
    public BoardState() {
        this.board = new Pieces[64];
        isCheck = false;
        this.kingPos = new int[2];
        startingBoard();
    }

    private void startingBoard() {
        // Place black pieces
        board[0] = new Rook(0, 0); // a1
        board[1] = new Knight(0, 1); // b1
        board[2] = new Bishop(0, 2); // c1
        board[3] = new Queen(0, 3); // d1
        board[4] = new King(0, 4);
        board[5] = new Bishop(0, 5); // f1
        board[6] = new Knight(0, 6); // g1
        board[7] = new Rook(0, 7); // h1
        for (int i = 8; i < 16; i++) {
            board[i] = new Pawn(0, i);
        }
        // Place white pieces
        board[56] = new Rook(1, 56); // a8
        board[57] = new Knight(1, 57); // b8
        board[58] = new Bishop(1, 58); // c8
        board[59] = new Queen(1, 59); // d8
        board[60] = new King(1, 60);
        board[61] = new Bishop(1, 61); // f8
        board[62] = new Knight(1, 62); // g8
        board[63] = new Rook(1, 63); // h8
        for (int i = 48; i < 56; i++) {
            board[i] = new Pawn(1, i);
        }
        kingPos[0] = 4;
        kingPos[1] = 60;
    }

    public void printBoard() {
        // Print the board layout
        for (int i = 0; i < 64; i++) {
            String symbol = (board[i] != null) ? board[i].getSymbol() : "-";
            System.out.printf("%-3s", symbol);
            if ((i + 1) % 8 == 0) {
                System.out.println();
            }
        }
    }

    public void getMoves(int printColor) {
        for (int i = 0; i < 64; i++) {
            if (board[i] == null || board[i].getPossibleMoves(board).isEmpty()) {
                continue;
            }
            if (board[i].color != printColor) {
                for (int k : board[i].getPossibleMoves(board)) {
                    if (k + board[i].myPos == kingPos[0] || k + board[i].myPos == kingPos[1]) {
                        isCheck = true;
                        break;
                    }
                }
            }
            if(isCheck){
                System.out.println("it's check btw");
                break;
            }
            if (board[i].color == printColor) {
                System.out.print(board[i].pieceType + " " + which(board[i].myPos) + which1(board[i].myPos) + ": ");
                for (int k : board[i].getPossibleMoves(board)) {
                    System.out.print(which(k + board[i].myPos) + which1(k + board[i].myPos) + ", ");
                }
                System.out.println();
            }
        }

    }

    public String which(int number) {
        int remainder = number % 8;
        char letter = (char) ('a' + remainder);
        return String.valueOf(letter);
    }

    public int which1(int number) {
        return 8 - (number / 8);
    }

    public int squareToNumber(String s) {
        if (s.length() != 2)
            //chess error exception
            return -1;
        int first, second;
        first = s.charAt(0) - 97;
        second = (56 - (int) s.charAt(1)) * 8;
        return first + second;
    }


    public void PlayerMovesPiece(String move) {
        int from = squareToNumber(move.substring(0, 2)), to = squareToNumber(move.substring(2, 4));
        board[to] = board[from];
        board[to].myPos = to;
        board[from] = null;
        board[to].hasMoved = true;
        if (board[to].pieceValue == 10000) {
            if (board[to].color == 0) kingPos[0] = to;
            else kingPos[1] = to;
        }
    }

    public void PlayerMovesPiece(int from,int to) {
        board[to] = board[from];
        board[to].myPos = to;
        board[from] = null;
        board[to].hasMoved = true;
        if (board[to].pieceValue == 10000) {
            if (board[to].color == 0) kingPos[0] = to;
            else kingPos[1] = to;
        }
    }

    public int returnMaterial() {
        int material = 0;
        for (int i = 0; i < 64; i++) {
            if (board[i] == null) {
                continue;
            }
            if (board[i].color == 1) {
                material += board[i].pieceValue;
            }
            if (board[i].color == 0) {
                material -= board[i].pieceValue;
            }
        }
            return material;
    }

    public void EngineMove(){
        BoardState boardState;
        for(int i=0; i<64; i++){
            if (board[i] == null || board[i].getPossibleMoves(board).isEmpty()) {
                continue;
            }
            for(int k: board[i].getPossibleMoves(board)){
                if((board[i]) == null){continue;}
                boardState = this;
                PlayerMovesPiece(boardState.board[i].myPos,boardState.board[i].myPos+k);
                if(boardState.returnMaterial() < returnMaterial()){
                    board = boardState.board;
                }
            }
        }
    }
}
//    @Override
//    public String toString() {
//        return Arrays.toString(board);
//    }

