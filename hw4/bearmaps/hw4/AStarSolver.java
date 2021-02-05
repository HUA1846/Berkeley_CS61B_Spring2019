package bearmaps.hw4;
import java.util.*;

import bearmaps.proj2ab.*;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionWeight;
    private LinkedList<Vertex> solution;
    private double timeSpent;
    private ArrayHeapMinPQ<Vertex> fringe;
    private Map<Vertex, Double> distTo;
    private Map<Vertex, Vertex> edgeTo;
    private int numStatesExplored;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        fringe = new ArrayHeapMinPQ<>(input.neighbors(start).size());
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        solution = new LinkedList<>();
        numStatesExplored = 0;

        distTo.put(start, 0.0);
        fringe.add(start, input.estimatedDistanceToGoal(start, end));
        edgeTo.put(start, null);


        while(fringe.size() > 0 && !fringe.getSmallest().equals(end)) {
            timeSpent = sw.elapsedTime();
            if(timeSpent > timeout) {
                outcome = SolverOutcome.TIMEOUT; break;
            }

            Vertex current = fringe.removeSmallest();
            numStatesExplored += 1;
            for(WeightedEdge<Vertex> e : input.neighbors(current)) {
//                if(distTo.containsKey(end) && distTo.get(end) < e.weight()) break;
                if(!distTo.containsKey(e.to())) {
                    distTo.put(e.to(), Double.POSITIVE_INFINITY);
                    fringe.add(e.to(), distTo.get(e.to()) + input.estimatedDistanceToGoal(e.to(), end));
                    edgeTo.put(e.to(), current);

                }

                // relax edges
                if(distTo.get(e.to()) > distTo.get(current) + e.weight()) {
                    distTo.put(e.to(), distTo.get(current) + e.weight());
                    edgeTo.put(e.to(), current);

                    if(fringe.contains(e.to())) {
                        fringe.changePriority(e.to(), distTo.get(current) + e.weight()
                                + input.estimatedDistanceToGoal(e.to(), end));
                    }
                }
            }

        }
        if(fringe.size() == 0) {
            outcome = SolverOutcome.UNSOLVABLE;
            solutionWeight = 0;
            timeSpent = sw.elapsedTime();
            return;
        } else {
            outcome = SolverOutcome.SOLVED;
            solutionWeight = distTo.get(end);
        }

        /*
        add vertices of shortest path to the a list.
        reverse the order and add to solution list.
         */



        Vertex shortest = end;
        solution.addFirst(end);
        while(!shortest.equals(start)) {
            solution.addFirst(edgeTo.get(shortest));
            shortest = edgeTo.get(shortest);
        }

        timeSpent = sw.elapsedTime();

    }
    /*
    Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE.
    Should be SOLVED if the AStarSolver was able to complete all work in the time given.
    UNSOLVABLE if the priority queue became empty. TIMEOUT if the solver ran out of time.
    You should check to see if you have run out of time every time you dequeue.
     */
    public SolverOutcome outcome() {
        return outcome;

    }

    /* A list of vertices corresponding to a solution.
       Should be empty if result was TIMEOUT or UNSOLVABLE.
     */
    public List<Vertex> solution() {
        return solution;
    }

    /*
    The total weight of the given solution, taking into account edge weights.
    Should be 0 if result was TIMEOUT or UNSOLVABLE.
     */
    public double solutionWeight() {
        return solutionWeight;
    }
    /*
    The total number of priority queue dequeue operations.
     */
    public int numStatesExplored() {
        return numStatesExplored;
    }
    /*
    The total time spent in seconds by the constructor
     */
    public double explorationTime() {
        return timeSpent;
    }
}
