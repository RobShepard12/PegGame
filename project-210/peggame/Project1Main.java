package peggame;

import java.io.IOException;
import java.util.Scanner;

public class Project1Main {
    public static void main(String[] args) throws IOException, PegGameException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a filename: ");
        String file = scan.nextLine();
        BoardReader br = new BoardReader();
        PegGame board = br.readBoard(file);
        CLI game = new CLI();
        game.playGame(board);
        
        scan.close();
    }
}
