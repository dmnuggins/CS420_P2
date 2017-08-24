import java.util.Random;

public class SimulatedAnnealing {
    private int N = 22;
    private Queen[] startState;
    private Node start;

    /**
     * Constructor
     */
    public SimulatedAnnealing(Queen[] state) {

        start = new Node();
        startState = new Queen[N];

        for(int i = 0; i < state.length ; i++) {
            startState[i] = new Queen(state[i].getRow(), state[i].getCol());
        }
        start.setState(startState);
        start.computeHeuristic();
    }

    public void startState() {
        start = new Node();
        startState = new Queen[N];
        Random rand = new Random();

        for(int i = 0; i < N ; i++) {
            startState[i] = new Queen(rand.nextInt(N), i);
        }
        start.setState(startState);
        start.computeHeuristic();
    }

    /**
     * Simulated Annealing Algorithm
     * @return
     */
    public Node simulatedAnnealing(double initTemp, double step) {
        Node current = start;
        double temp = initTemp;
        double val = step;
        double prob;
        int delta;
        double determine;

        Node next;

        while(current.getHeuristic() != 0 && temp > 0) {
            next = current.getRandomNeighbor(current);

            if(next.getHeuristic() == 0) {
                return next;
            }

            delta = current.getHeuristic() - next.getHeuristic();

            if(delta > 0) {
                current = next;
            } else {
                prob = Math.exp(delta/temp);
                determine = Math.random();

                if(determine <= prob) {
                    current = next;
                }
            }
            temp = temp - val;
        }
        return current;
    }
}
