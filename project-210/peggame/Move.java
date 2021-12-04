package peggame;

public class Move {
    Location from;
    Location to;

    public Move (Location from, Location to) {
        this.from = from;
        this.to = to;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof Move)) {
            return false;
        }
        Move other = (Move) o;
        return (from.row == other.from.row && from.col == other.from.col &&
                to.row == other.to.row && to.col == other.to.col);
    }

    @Override
    public int hashCode() {
        return from.hashCode() + to.hashCode();
    }

    @Override
    public String toString() {
        return "From: " + from + " to: " + to;
    }

}
