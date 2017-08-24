import java.util.*;

/**
 * Node class
 */
public class Node implements Comparable<Node> {

    private int N = 22; //N queens
    private Queen[] state;
    private ArrayList<Node> neighbors;
    private int heuristic;

    /**
     * Constructors
     */
    public Node() {
        state = new Queen[N];
        neighbors = new ArrayList<>(); // empty neighbor list
    }

    public Node(Node n) {
        state = new Queen[N];
        neighbors = new ArrayList<>();
        for(int i = 0 ; i < N ; i++) {
            state[i] = new Queen(n.state[i].getRow(), n.state[i].getCol());
        }
        heuristic = 0;
    }

    /**
     * Generates the neighbor states of the passed in state
     * @param initialState
     * @return
     */
    public ArrayList<Node> generateNeighbors(Node initialState) {
        int count = 0;

        if(initialState == null) {
            System.out.println("WARNING, EMPTY STATE");
        }

        for(int i = 0 ; i < N ; i ++) {
            for(int j = 1 ; j < N ; j++) {
                neighbors.add(count, new Node(initialState));
                neighbors.get(count).state[i].shiftQueen(j, N);
                neighbors.get(count).computeHeuristic();
                count++;
            }
        }
        return neighbors;
    }

    /**
     * Computes the heuristic value of the current Node
     * @return
     */
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

    /**
     * Returns the heuristic value
     * @return
     */
    public int getHeuristic() {
        return heuristic;
    }


    /**
     * Picks from a random neighbor generated from the passed in node
     * @param start
     * @return
     */
    public Node getRandomNeighbor(Node start) {
        Random rand = new Random();

        int col = rand.nextInt(N);
        int d = rand.nextInt(N-1) + 1;

        Node neighbor = new Node(start);
        neighbor.state[col].shiftQueen(d, N);
        neighbor.computeHeuristic();

        return neighbor;
    }

    /**
     * Compares current Node heuristic to passed in Node heuristic
     * @param n
     * @return
     */
    public int compareTo(Node n) {
        if(this.heuristic < n.getHeuristic())
            return -1;
        else if(this.heuristic > n.getHeuristic())
            return 1;
        else
            return 0;
    }

    /**
     * Sets the state with current Queen array
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
}
