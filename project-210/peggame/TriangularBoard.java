package peggame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class TriangularBoard implements PegGame {
    private int rows;
    private int moves;
    private LinkedList<Move> movesMade;
    protected int pegs;
    protected char [][] board;
    

    public TriangularBoard (int rows){
        this.rows = rows;

        this.board = new char[rows][];

        for (int row = 0; row < rows; row++) {
            board[row] = new char[row + 1];
        }

        this.movesMade = new LinkedList<>();

        this.moves = 0;
        this.pegs = 0;
        
    }

    @Override
    public PegGame deepCopy() {
        TriangularBoard copy = new TriangularBoard(rows);
        copy.pegs = this.pegs;
        copy.moves = this.moves;
        for (int row = 0; row < rows; row++){
            for (int col = 0; col < row + 1; col++){
                copy.board[row][col] = board[row][col];
            }
        }
        return copy;
    }

    @Override
    public Collection<Move> getPossibleMoves() {
        Collection<Move> possibleMoves = new ArrayList<>();
        int[][] around = new int[][] {
            {-1, -1}, {-1, 0},
            {0, -1}, {0, 1},
            {1, 0}, {1, 1}};
        
        for (int startRow = 0; startRow < rows; startRow++) {
            for (int startCol = 0; startCol < startRow + 1; startCol++) {

                if (board[startRow][startCol] == 'o') {

                    for (int[] jump : around) {
                        int jumpRow = jump[0];
                        int jumpCol = jump[1];

                        // Checks to make sure landing spot is not outside the board
                        if (startRow + jumpRow * 2 >= 0 && startRow + jumpRow * 2 < rows &&
                            startCol + jumpCol * 2 >= 0 && startCol + jumpCol * 2 < (startRow + jumpRow * 2) + 1) {
                                
                            // Checks to see if the jump spot has a peg to jump over
                            if (board[startRow + jumpRow][startCol + jumpCol] == 'o') {
        
                                // Checks if landing spot is open (not a peg)
                                if (board[startRow + jumpRow * 2][startCol + jumpCol * 2] == '-') {
                                    possibleMoves.add(new Move(new Location(startRow, startCol), new Location(startRow + jumpRow * 2, startCol + jumpCol * 2)));
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return possibleMoves;
    }

    @Override
    public GameState getGameState() {
        if (moves == 0) {
            return GameState.NOT_STARTED;
        } else if (pegs == 1) {
            return GameState.WON;
        } else if (getPossibleMoves().isEmpty()) {
            return GameState.STALEMATE;
        } else {
            return GameState.IN_PROGRESS;
        }
    }

    @Override
    public void makeMove(Move move) throws PegGameException {
        moves += 1;
        pegs -= 1;

        movesMade.add(move);

        Location from = move.from;
        Location to = move.to;

        if (board[from.row][from.col] == '-') {
            throw new PegGameException("Not a peg");
        }
        
        if (board[to.row][to.col] == 'o') {
            throw new PegGameException("Space is Occupied");
        }

        board[from.row][from.col] = '-';
        board[to.row][to.col] = 'o';

        int jumpRow;
        int jumpCol;

        if (from.row < to.row) {
            jumpRow = from.row + 1;
        } else if (from.row > to.row) {
            jumpRow = from.row - 1;
        } else {
            jumpRow = from.row;
        }

        if (from.col < to.col) {
            jumpCol = from.col + 1;
        } else if (from.col > to.col) {
            jumpCol = from.col - 1;
        } else {
            jumpCol = from.col;
        }

        board[jumpRow][jumpCol] = '-';
    } 

    @Override
    public String toString() {
        String boardStr = "";
        for (int i = 0; i < rows; i ++){
            for (int k = rows - i; k > 0; k--) {
                boardStr += " ";
            }
            for (int j = 0; j < i + 1; j++){
                boardStr += board[i][j];
                boardStr += " ";
            }
            boardStr += "\n";
        }

        return boardStr;
    }

    @Override
    public LinkedList<Move> getMovesMade() {
        return movesMade;
    }
    
}