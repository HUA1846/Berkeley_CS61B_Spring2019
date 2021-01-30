import java.util.*;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    PriorityQueue<Flight> startpq;
    Map<Flight, Integer> timeToPassenger;

    public FlightSolver(ArrayList<Flight> flights) {
        startpq = new PriorityQueue<>((f1, f2) -> f1.startTime() - f2.startTime());
        timeToPassenger = new HashMap<>();
        for(Flight f : flights) {
            startpq.add(f);
            timeToPassenger.put(f, f.passengers());
        }

    }

    public int solve() {
       int sum = 0;
       int i = 0;
       int[] sums = new int[startpq.size()];
       /* iterate through each item in pq
        */
       while(startpq.size() > 0) {
           Flight current = startpq.poll();
           sum = current.passengers();
           for(Map.Entry<Flight, Integer> f : timeToPassenger.entrySet()) {
               if(current.endTime() > f.getKey().startTime() && f.getKey().startTime > current.startTime()) {
                   sum += f.getValue();
               }
           }
           sums[i] = sum;
           i += 1;
       }
       int max = 0;
       for(int n : sums) {
           max = Math.max(max, n);
       }
       return max;
    }

}
