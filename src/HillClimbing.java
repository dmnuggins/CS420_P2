import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Dylan Nguyen on 8/22/2017.
 *
 */
public class HillClimbing {
    private int N;
    private Queen[] initState;
    private Node start;
    private int nodesGenerated;

    public HillClimbing() {
        start = new Node(N);
        initState = new Queen[N];
        initialState();
        nodesGenerated = 0;

    }

    public HillClimbing(Queen[] s, int B) {
        N = s.length;
        start = new Node(B);
        initState = new Queen[N];
        for(int i = 0; i < N ; i++) {
            initState[i] = new Queen(s[i].getRow(), s[i].getCol());
        }
        start.setState(initState);
        start.computeHeuristic();

        nodesGenerated = 0;
    }

    public void initialState() {
        Random rand = new Random();
        for(int i = 0 ; i < N ; i++) {
            initState[i] = new Queen(rand.nextInt(N), i);
        }
        start.setState(initState);
        start.computeHeuristic();
    }

    public Node hillClimbing() {
        Node curNode = start;

        while(true) {
            ArrayList<Node> successors = curNode.generateNeighbors(curNode);
            nodesGenerated += successors.size();

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

    public Node getStart() {
        return start;
    }

    public int getNodesGenerated() {
        return nodesGenerated;
    }

}
