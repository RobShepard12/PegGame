package peggame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BoardReader {

    public PegGame readBoard(String filename) throws IOException{
        FileReader fr = new FileReader(filename);
        BufferedReader reader = new BufferedReader(fr);

        String line = reader.readLine(); //first line
        int rowLen = Integer.parseInt(line); 

        line = reader.readLine(); //second line
        int colLen = line.length();
        
        if(line.length() == 1){
            TriangularBoard gameBoard = new TriangularBoard(rowLen);
            int row = 0;
            while (line != null){
                //loop through and put in location of pegs
                for (int i = 0; i < line.length(); i++){
                    gameBoard.board[row][i] = line.charAt(i);
                    if (line.charAt(i) == 'o'){
                        gameBoard.pegs++;
                    }
                }
                line = reader.readLine();
                row++;
            }
            fr.close();
            reader.close();
            return gameBoard;
        }
        else {
            RectangularBoard gameBoard = new RectangularBoard(rowLen, colLen);
            int row = 0;
            while (line != null){
                //loop through and put in location of pegs
                for (int i = 0; i < line.length(); i++){
                    gameBoard.board[row][i] = line.charAt(i);
                    if (line.charAt(i) == 'o'){
                        gameBoard.pegs++;
                    }
                }
                line = reader.readLine();
                row++;
            }
            fr.close();
            reader.close();
            return gameBoard;
        }


    }
}
