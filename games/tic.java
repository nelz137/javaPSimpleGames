package games;
import java.util.Scanner;






public class tic {
    public static void main(String[] aStrings) {
       // System.out.println("Hello, World!");
       boolean playAgain = true;
       Scanner scanner = new Scanner(System.in);


       while(playAgain){

//i make board 
       char[][] board = new char[3][3];
       for(int row=0; row < board.length;row++){
        for(int col =0;col<board[row].length;col++){
            board[row][col]=' ';
        }
       }
    
char player = 'X';
boolean gameOver = false;
int moves=0;


while (!gameOver && moves<9) {
   
    printBoard(board);
    System.out.println("Player "+ player +" enter your move (row and col 0-2) ");
int row = scanner.nextInt();
int col = scanner.nextInt();


if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid position! Enter values between 0 and 2.");
                continue;
            }

if (board[row][col]==' ') {
    //place the element
    board[row][col]=player;
    moves++;
    gameOver = haveWon(board,player);
if (gameOver) {
    printBoard(board);
    System.out.println("player "+ player +" has won!");
    break;
}
     if (moves==9) {
        printBoard(board);
        System.out.println("🤝 It's a draw!");
        break;
}
     player = (player == 'X') ? 'O' : 'X';

}
else{
    System.out.println("Invalid move! Try again.");
    continue;

    
}
}

System.out.print("Do you want to play again? (y/n): ");
            char choice = scanner.next().charAt(0);
            if (choice != 'y' && choice != 'Y') {
                playAgain = false;
                System.out.println(" Thanks for playing!");
    
    
}
    
    }
}

    public static boolean haveWon(char[][] board,char Player) {
//check the rows

for(int row=0; row < board.length;row++){
        if (board[row][0]==Player && board[row][1]==Player && board[row][2]==Player) {
            return true;
            
        }
       }

//check the columns

         for(int col=0; col < board.length;col++){
          if (board[0][col]==Player && board[1][col]==Player && board[2][col]==Player) {
                return true;
                
          }
         }

//check the diagonals
if (board[0][0]==Player && board[1][1]==Player && board[2][2]==Player) {
    return true ;

    }
if (board[0][2]==Player && board[1][1]==Player && board[2][0]==Player) {
    return true ;

    }
    return false;
}    
    
    
    public static void printBoard(char[][] board) {
        System.out.println("  ----------");
        for(int row=0; row < board.length;row++){
            for(int col =0;col<board[row].length;col++){
               // System.out.print(board[row][col] + " | ");
               //System.out.print(board[row][col]);
               System.out.print(" " + board[row][col] + " ");
                if (col < 2) System.out.print(" |");
            }
            System.out.println();
            if (row < 2) System.out.println("  ----------");
        }
    }


}
