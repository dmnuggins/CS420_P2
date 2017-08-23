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
        int numberOfRuns = 5000;
        int hillClimbNodes = 0;
        int hillClimbSuccesses = 0;


        for(int i = 0 ; i < numberOfRuns ; i++) {
            Queen[] initBoard = board.generateBoard(8);

            HillClimbing hillClimbing = new HillClimbing(initBoard, 8);

            Node hillSolved = hillClimbing.hillClimbing();

            if(hillSolved.getHeuristic() == 0) {
                hillClimbSuccesses++;
            }

            hillClimbNodes += hillClimbing.getNodesGenerated();
        }

        System.out.println("Hill climb successes: " + hillClimbSuccesses);

        double hillClimbPercent = (double)hillClimbSuccesses/(double)numberOfRuns;
        System.out.println(hillClimbPercent);

        NumberFormat fmt = NumberFormat.getPercentInstance();

        System.out.println("Hill climbing: \nNodes: " + hillClimbNodes);
        System.out.println("Percent successes: " + fmt.format(hillClimbPercent));
    }

    public Queen[] generateBoard(int n) {
        Queen[] start = new Queen[n];
        Random rand = new Random();

        for(int i = 0 ; i < n ; i ++) {
            start[i] = new Queen(rand.nextInt(n), i);
        }

        return start;
    }
}
