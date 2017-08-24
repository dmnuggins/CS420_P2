/**
 * Queen object class
 */
public class Queen {
    private int row;
    private int col;

    public Queen(int r, int c) {
        row = r;
        col = c;
    }

    /**
     * Checks to see if a Queen is in attack range of another queen by checking for equal row/col values and if they
     * will meet on the diagonal
     * @param queen
     * @return
     */
    public boolean inRange(Queen queen) {
        boolean inRange = false;
        if(queen.getRow() == row || queen.getCol() == col) {
            inRange = true;
        } else if(Math.abs(col - queen.getCol()) == Math.abs(row - queen.getRow())) {
            inRange = true;
        }
        return inRange;
    }

    /**
     *
     * @param spaces - spaces to add to the queen's position. moves only in downard direction
     */
    public void shiftQueen(int spaces, int edge) {

        row += spaces;

        // bound check
        if(row > edge && row %edge != 0) {
            row = (row % edge) - 1;
        } else if(row > edge && row % edge == 0) {
            row = edge;
        }
    }

    /**
     * Returns row of Queen
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns column of Queen
     * @return
     */
    public int getCol() {
        return col;
    }

    public String toString() {
        return "[" + row + "," + col + "]";
    }
}
