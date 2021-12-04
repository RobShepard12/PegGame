package peggame;

public class PegGameException extends Exception{

    private static final long serialVersionUID = 1L;

    public PegGameException(String errorMessage){
        super(errorMessage);
    }

    public static void main(String[] args) throws PegGameException {
        throw new PegGameException("errorMessage");
    }
}

