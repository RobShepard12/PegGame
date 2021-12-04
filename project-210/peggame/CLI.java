package peggame;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class CLI {
    
    public static void playGame(PegGame board) throws PegGameException{
        Boolean quit = false;
        System.out.println("Welcome to the Peg Game\nEnter Command: "); 
        while(!quit) {
            System.out.println(board); //print the board at the start and after each move
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            Collection<Move> possibleMoves = board.getPossibleMoves();
            String [] splitLine = line.split(" ");
 

            if (splitLine[0].equals("help")){
                //display commands
                System.out.println("Command list:\n1)'hint': displays an available move.\n2)'move r1 c1 r2 c2': attempts to move a peg from row1, column1 to row2, column2.\n3)'help': displays this message.\n4)'quit': stops the game.\n5)'solve': solves the game for you.");
            }

            else if (splitLine[0].equals("hint")){
                try{
                Backtracker backtracker = new Backtracker(false);
                PegGame copy = board.deepCopy();
                BoardConfiguration config = new BoardConfiguration(copy); 
                Configuration solution = backtracker.solve(config);
                if (solution != null){
                    //System.out.println(solution.toString());
                    PegGame copy2 = board.deepCopy();
                        for (Move move : copy2.getPossibleMoves()) {
                            Backtracker backtracker2 = new Backtracker(false);
                            PegGame copy3 = copy2.deepCopy();
                            copy3.makeMove(move);
                            BoardConfiguration config3 = new BoardConfiguration(copy3); 
                            Configuration solution3 = backtracker2.solve(config3);

                            if (solution3 != null) {
                                System.out.println(move);
                                System.out.println(copy3);
                                copy2.makeMove(move);
                                break;
                            }
                        }
                    }
                
                else{
                    System.out.println("There is no solution");
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Do you want to quit (y/n): ");
                    String answer = scanner2.nextLine();
                    if (answer.equals("y")){
                        quit = true;
                    }
                }
                
            } catch (NullPointerException npe){
            }
            }

            else if (splitLine[0].equals("solve")){
                try{
                Backtracker backtracker = new Backtracker(false);
                PegGame copy = board.deepCopy();
                PegGame copy2 = board.deepCopy();
                
                BoardConfiguration config = new BoardConfiguration(copy); 
                Configuration solution = backtracker.solve(config);

                System.out.println(board);
                if (solution != null){
                    //System.out.println(solution.toString());
                
                    while (copy2.getGameState() != GameState.WON) {
                        for (Move move : copy2.getPossibleMoves()) {
                            Backtracker backtracker2 = new Backtracker(false);
                            PegGame copy3 = copy2.deepCopy();
                            copy3.makeMove(move);
                            BoardConfiguration config3 = new BoardConfiguration(copy3); 
                            Configuration solution3 = backtracker2.solve(config3);

                            if (solution3 != null) {
                                System.out.println(move);
                                System.out.println(copy3);
                                copy2.makeMove(move);
                                break;
                            }
                        }
                    }
                }

                if (copy.getGameState() == GameState.STALEMATE){
                    System.out.println("There is no solution");
                }
                
            }catch (NullPointerException npe){
                System.out.println("There is no soluton");
            }
                
            }

            else if (splitLine[0].equals("quit")){
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Are you sure (y/n): ");
                String answer = scanner2.nextLine();
                if (answer.equals("y")){
                    quit = true;
                }
            }

            else if (splitLine[0].equals("move")){

                //sets up the move
                int rowStart = Integer.parseInt(splitLine[1]);
                int colStart = Integer.parseInt(splitLine[2]);
                int rowEnd = Integer.parseInt(splitLine[3]);
                int colEnd = Integer.parseInt(splitLine[4]);
                Location start = new Location(rowStart, colStart);
                Location end = new Location(rowEnd, colEnd);
                Move move = new Move(start, end);

                //checks if the move is valid
                if(possibleMoves.contains(move)){
                    board.makeMove(move);
                }
                else{
                    System.out.println("move is invalid, please input another move (ex. move 0 1 0 3).");
                }

                //checks if the game is won
                if(board.getGameState() == GameState.WON){
                    System.out.println("Game won");
                    quit = true;
                }

                //checks if the game is a stalemate (no moves left)
                else if (board.getGameState() == GameState.STALEMATE){
                    System.out.println("No more moves!");
                    System.out.println(board);
                    quit = true;
                }

                //continues loop
            }
        }
        System.out.println("Goodbye!");
    }
    public static void main(String[] args) throws PegGameException {
        PegGame board = new RectangularBoard(5, 5);
        PegGame triangularBoard = new TriangularBoard(5);
        playGame(triangularBoard);
    }
}
