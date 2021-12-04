package peggame;

public enum GameState {
    NOT_STARTED, //no moves have been made yet
    IN_PROGRESS, //atleast one move has been made
    STALEMATE, //two or more pegs on the board, but there are no more valid moves
    WON; //one peg left, game over
}
