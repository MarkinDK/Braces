public class PairOfIndex implements Comparable<PairOfIndex> {
    private int open;
    private int closed;
    private boolean correctly;

    PairOfIndex(int open, int closed) {
        this.open = open;
        this.closed = closed;
        if (open < closed)
            correctly = true;
    }

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public boolean isCorrectly() {
        return correctly;
    }


    public int getDistance() {
        return closed - open;
    }

    @Override
    public int compareTo(PairOfIndex o) {
        return getDistance()-o.getDistance();
    }
}
