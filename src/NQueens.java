/**
 * Created by Dylan Nguyen on 8/22/2017.
 *
 */
import java.util.*;
import java.text.NumberFormat;

public class NQueens {

    public NQueens() {

    }

    public static void main(String[] args) {
        NQueens board = new NQueens();
        int numberOfRuns = 100;
        int hillClimbNodes = 0;
        int hillClimbSuccesses = 0;
        int annealNodes = 0;
        int annealSuccesses = 0;


        for(int i = 0 ; i < numberOfRuns ; i++) {
            Queen[] initBoard = board.generateBoard();

            HillClimbing hillClimbing = new HillClimbing(initBoard);
            SimulatedAnnealing anneal = new SimulatedAnnealing(initBoard);

            Node hillSolved = hillClimbing.hillClimbing();
            Node annealSolved = anneal.simulatedAnnealing(28, 0.0001);

            if(hillSolved.getHeuristic() == 0) {
                hillClimbSuccesses++;
            }
            if(annealSolved.getHeuristic() == 0) {
                annealSuccesses++;
            }
            hillClimbNodes += hillClimbing.getNodesGenerated();
            annealNodes += anneal.getNodesGenerated();
        }

        System.out.println("Hill climb successes: " + hillClimbSuccesses);
        System.out.println("Hill climb total runs: " + numberOfRuns);
        System.out.println("Simulated Annealing successes: " + annealSuccesses);
        System.out.println("Simulated Annealing total runs: " + numberOfRuns);
        double hillClimbPercent = (double)hillClimbSuccesses/(double)numberOfRuns;
        double annealPercent = (double)annealSuccesses/(double)numberOfRuns;

        NumberFormat fmt = NumberFormat.getPercentInstance();

        System.out.println("Hill climbing: \nNodes: " + hillClimbNodes);
        System.out.println("Percent successes: " + fmt.format(hillClimbPercent));
        System.out.println("Simulated annealing: \nNodes: " + annealNodes);
        System.out.println("Percent successes: " + fmt.format(annealPercent));
    }

    public Queen[] generateBoard() {
        Queen[] start = new Queen[22];
        Random rand = new Random();

        for(int i = 0 ; i < 22 ; i ++) {
            start[i] = new Queen(rand.nextInt(22), i);
        }

        return start;
    }

    public void setNValue() {
        Scanner scn = new Scanner(System.in);
        int nValue = scn.nextInt();

    }
}
