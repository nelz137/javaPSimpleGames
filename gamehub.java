import java.util.Scanner;


public class gamehub {
    public static void main(String[] args) {
        System.out.println("Welcome to the Game Hub!");
        System.out.println("1. Tic Tac Toe");
        System.out.println("2. Rock Paper Scissors");
        System.out.println("3. Guess the Number");
        System.out.println("2. Exit");
        System.out.print("Choose a game to play (1-2): ");
        
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                tic.main(args);
                break;
            case 2:
                System.out.println("Exiting the Game Hub. Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
        scanner.close();
    }
}
