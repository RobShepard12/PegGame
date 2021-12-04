package peggame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;


public interface PegGame {
    
    public Collection <Move> getPossibleMoves ();

    public GameState getGameState ();

    public void makeMove (Move move) throws PegGameException;

    public List<Move> getMovesMade();

    default PegGame deepCopy(){
        throw new UnsupportedOperationException();
    }
}
