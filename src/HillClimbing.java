import java.util.ArrayList;
import java.util.Random;

/**
 * Steepest Hill Climbing Algorithm
 */
public class HillClimbing {
    private int N=22;
    private Queen[] initState;
    private Node start;

    public HillClimbing() {
        start = new Node();
        initState = new Queen[N];
        initialState();
    }

    public HillClimbing(Queen[] s) {
        N = s.length;
        start = new Node();
        initState = new Queen[N];
        for(int i = 0; i < s.length ; i++) {
            initState[i] = new Queen(s[i].getRow(), s[i].getCol());
        }
        start.setState(initState);
        start.computeHeuristic();
    }

    /**
     * Sets initial state
     */
    public void initialState() {
        Random rand = new Random();
        for(int i = 0 ; i < N ; i++) {
            initState[i] = new Queen(rand.nextInt(N), i);
        }
        start.setState(initState);
        start.computeHeuristic();
    }

    /**
     * Hill Climbing Algorithm
     * @return Solution Node
     */
    public Node hillClimbing() {
        Node curNode = start;
        while(true) {
            ArrayList<Node> successors = curNode.generateNeighbors(curNode);
            Node next = null;
            for(int i = 0; i < successors.size() ; i++) {
                if(successors.get(i).compareTo(curNode) < 0) {
                    next = successors.get(i);
                }
            }
            if(next == null) {
                return curNode;
            }
            curNode = next;
        }
    }
}
