import javax.print.attribute.standard.MediaSize;
import java.util.Scanner;

public class Main {
    static void play(BoardState board1){
        Scanner sc = new Scanner(System.in);
        int player = 0; //odwrotnie??
        Run game = new Run();
        board1.printBoard();
        while (game.running) {
            if(player==0){player=1;}else{player=0;
                board1.EngineMove(); continue;
            }
            board1.getMoves(player);
            System.out.println("Podaj ruch: ");
            String tempor = sc.nextLine();
            if(tempor.equals("material")){
                System.out.println(board1.returnMaterial()); System.out.println("Podaj ruch: "); tempor = sc.nextLine();}
            if(tempor.equals("resign")){
                System.out.println("Player " + player + " wins");
                System.exit(0);
            }
            board1.PlayerMovesPiece(tempor);
            board1.printBoard();
        }
    }

    public static void main(String[] args) {
        BoardState board1 = new BoardState();
        play(board1);
    }
}
/*
todo Checks
todo Pawn Promotion
remember king pos



Build Structure
Possible moves in given position - prio 100
Check for checks - 100 // not done
Working mate - 100 // not done
Board representation - 60 // done
Finding best move using good algorithm - 20 // never gon be done
Finding the move that leads to material advantage - 25 //
En passant


Mistakes made:
isNotCapture method
life
print == return!!!



Classes Necessary
Run class
Board representation class(maybe)
Board State class
Pieces classes
LegalMoves?
 */