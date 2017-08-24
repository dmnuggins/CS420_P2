import java.util.Random;

public class SimulatedAnnealing {
    private int N = 22;
    private Queen[] startState;
    private Node head;

    public SimulatedAnnealing(Queen[] state) {

        head = new Node();
        startState = new Queen[N];

        for(int i = 0; i < state.length ; i++) {
            startState[i] = new Queen(state[i].getRow(), state[i].getCol());
        }
        head.setState(startState);
        head.computeHeuristic();
    }

    public void startState() {
        head = new Node();
        startState = new Queen[N];
        Random rand = new Random();

        for(int i = 0; i < N ; i++) {
            startState[i] = new Queen(rand.nextInt(N), i);
        }
        head.setState(startState);
        head.computeHeuristic();
    }

    /**
     * Simulated Annealing Algorithm
     * @param initTemp - Initial temperature
     * @param step
     * @return
     */
    public Node simulatedAnnealing(double initTemp, double step) {
        Node current = head;
        double temp = initTemp;
        double prob;
        double determine;
        int delta;
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
            temp = temp - step;
        }
        return current;
    }
}
