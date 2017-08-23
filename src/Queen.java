/**
 * Created by Dylan Nguyen on 8/22/2017.
 *
 */

public class Queen {
    private int row;
    private int col;

    public Queen(int r, int c) {
        row = r;
        col = c;
    }

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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int r) {
        row = r;
    }

    public void setCol(int c) {
        col = c;
    }
}
