package peggame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BoardConfiguration implements Configuration{
    PegGame game;
    Move[] moves;
    PegGame copy;

    public BoardConfiguration (PegGame game){
        this.copy = game.deepCopy();
        this.game = game;
        
    }

    @Override
    public Collection<Configuration> getSuccessors() {
        List <Configuration> successors = new ArrayList<>();
        Collection<Move> possibleMoves = game.getPossibleMoves();
        Object[] possibleMovesArray = possibleMoves.toArray();
        
        for (Object move : possibleMovesArray) {
            PegGame newGame = game.deepCopy();
            BoardConfiguration newBoard = new BoardConfiguration(newGame);
            try {
                newBoard.game.makeMove((Move)move);
                successors.add(newBoard);
            } catch (PegGameException e) {
            }
        }
        
        return successors;
    }

    public static Configuration getConfig(PegGame board) throws PegGameException{
        try{
            Backtracker backtracker = new Backtracker(false);
            PegGame copy = board.deepCopy();
            PegGame copy2 = board.deepCopy();
            
            BoardConfiguration config = new BoardConfiguration(copy); 
            Configuration solution = backtracker.solve(config);
            List<Move> moves = copy.getMovesMade();
        
            for (Move move : moves){
                copy2.makeMove((move));
            }

            //if there is a solution, return the config
            if (copy2.getGameState() == GameState.WON){
                return solution;
            }

            //if there is no solution, return null
            return null;

        }catch (NullPointerException npe){
            return null;
        }
            
        }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean isGoal() {
        return game.getGameState() == GameState.WON;
    }

    @Override
    public String toString() {
        return game.getMovesMade().toString()  + "\n" + copy;
    }
    
}
