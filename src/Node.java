import java.util.*;

/**
 * Created by Dylan Nguyen on 8/22/2017.
 *
 */
public class Node implements Comparable<Node> {

    private int N = 8; //N queens
    public Queen[] state;
    private ArrayList<Node> neighbors;
    private int heuristic;

    public Node(int n) {
        N = n;
        state = new Queen[N];
        neighbors = new ArrayList<Node>(); // empty neighbor list
    }

    public Node(Node n) {
        state = new Queen[N];
        neighbors = new ArrayList<Node>();
        for(int i = 0 ; i < N ; i++) {
            state[i] = new Queen(n.state[i].getRow(), n.state[i].getCol());
        }
        heuristic = 0;
    }

    public ArrayList<Node> generateNeighbors(Node initialState) {
        int count = 0;

        if(initialState == null) {
            System.out.println("WARNING");
        } else {
            // empty
        }

        for(int i = 0 ; i < N ; i ++) {
            for(int j = 1 ; j < N ; j++) {
                neighbors.add(count, new Node(initialState));
                neighbors.get(count).state[i].shiftQueen(j, N - 1);
                neighbors.get(count).computeHeuristic();

                count++;
            }
        }
        return neighbors;
    }

    public int computeHeuristic() {
        for(int i = 0; i < N - 1 ; i++) {
            for(int j = i + 1 ; j < N ; j++) {
                if(state[i].inRange(state[j])) {
                    heuristic++;
                }
            }
        }
        return heuristic;
    }



    public int getHeuristic() {
        return heuristic;
    }

    public int compareTo(Node n) {
        if(this.heuristic < n.getHeuristic())
            return -1;
        else if(this.heuristic > n.getHeuristic())
            return 1;
        else
            return 0;
    }

    /**
     * sets the state
     * @param s
     */
    public void setState(Queen[] s) {
        for(int i = 0 ; i < N ; i++) {
            state[i] = new Queen(s[i].getRow(), s[i].getCol());
        }
    }

    public Queen[] getState() {
        return state;
    }

    public String toString() {
        return null;
    }

}
