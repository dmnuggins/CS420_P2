import java.text.NumberFormat;
import java.util.Random;

public class NQueens {

    public static void main(String[] args) {
        NQueens board = new NQueens();
        int numberOfRuns = 250; // number of runs
        int hillClimbSuccesses = 0; // to track hill climbing successes
        int annealSuccesses = 0; // to track simulated annealing successes
        long startTime, endTime, totalTime;
        int solutionCount = 0;
        NumberFormat fmt = NumberFormat.getPercentInstance();

        System.out.println("N-Queen Program");
        System.out.println("===============");
        System.out.println("[row,column]\n");
        // This loop tests for 3 potential solvable configurations and prints the solution state
        for(int i = 0; i < 100 ; i++) {
            Queen[] initialState = board.createBoard();

            SHC SHC = new SHC(initialState);
            SimulatedAnnealing simulAnnealing = new SimulatedAnnealing(initialState);
            Node hillSolved = SHC.hillClimbing();
            Node annealSolved = simulAnnealing.simulatedAnnealing(28, 0.0001);
            if(hillSolved.getHeuristic() == 0 && annealSolved.getHeuristic() == 0) {
                System.out.println("\n\n(Initial) Configuration " + i + ":");
                System.out.println("==========================");
                for(Queen q : initialState) {
                    System.out.print(q.toString() + " ");
                }
                System.out.println("\n\nSolution " + i);
                System.out.println("============");
                for(Queen q : hillSolved.getState()) {
                    System.out.print(q.toString() + " ");
                }
                solutionCount++;
            }

            if(solutionCount == 3)
                break;

        }
        // Hill Climbing
        System.out.println("\n\nHill Climbing");
        System.out.println("=============");
        startTime = System.currentTimeMillis();
        for(int i = 0 ; i < numberOfRuns ; i++) {
            Queen[] initBoard = board.createBoard();

            SHC SHC = new SHC(initBoard);

            Node hillSolved = SHC.hillClimbing();


            if(hillSolved.getHeuristic() == 0) {
                hillClimbSuccesses++;
            }
        }
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        System.out.println("Hill climbing successes: " + hillClimbSuccesses);
        System.out.println("Hill climbing total runs: " + numberOfRuns);
        double hillClimbPercent = (double)hillClimbSuccesses/(double)numberOfRuns;
        System.out.println("Hill climbing % success: " + fmt.format(hillClimbPercent));
        System.out.println("Total time: " + totalTime + "ms");
        // Simulated Annealing
        System.out.println("\nSimulated Annealing");
        System.out.println("===================");
        startTime = System.currentTimeMillis();
        for(int i = 0 ; i < numberOfRuns ; i++) {
            Queen[] initBoard = board.createBoard();

            SimulatedAnnealing anneal = new SimulatedAnnealing(initBoard);
            Node annealSolved = anneal.simulatedAnnealing(28, 0.0001);
            if(annealSolved.getHeuristic() == 0) {
                annealSuccesses++;
            }
        }
        endTime = System.currentTimeMillis();
        totalTime = (endTime - startTime)/1000;
        System.out.println("\nSimulated annealing successes: " + annealSuccesses);
        System.out.println("Simulated annealing total runs: " + numberOfRuns);
        double annealPercent = (double)annealSuccesses/(double)numberOfRuns;
        System.out.println("Percent successes: " + fmt.format(annealPercent));
        System.out.println("Total time: " + totalTime + "s");
    }

    /**
     * Creates a randomly arranged board
     * @return
     */
    public Queen[] createBoard() {
        Queen[] start = new Queen[22];
        Random rand = new Random();
        for(int i = 0 ; i < 22 ; i ++) {
            start[i] = new Queen(rand.nextInt(22), i);
        }
        return start;
    }
}
