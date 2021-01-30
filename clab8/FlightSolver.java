import java.util.*;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    PriorityQueue<Flight> startpq;
    Map<Integer, Integer> timeToPassenger;

    public FlightSolver(ArrayList<Flight> flights) {
        startpq = new PriorityQueue<>((f1, f2) -> f1.startTime() - f2.startTime());
        timeToPassenger = new HashMap<>();
        for(Flight f : flights) {
            startpq.add(f);
            timeToPassenger.put(f.startTime(), f.passengers());
        }

    }

    public int solve() {
       int sum = 0;
       int i = 0;
       int count = startpq.size();
       int[] sums = new int[startpq.size()];
        Flight current = startpq.poll();
       while(count > 0) {
           if(startpq.size() == 0) {
               sums[i] = current.passengers(); break;
           }
           Flight next = startpq.peek();
           if(current.endTime() > next.startTime()) {
               sum += current.passengers() + next.passengers;
               sums[i] = sum;
               startpq.poll();
           } else {
               sum = 0;
               sum += current.passengers();
               sums[i] = sum;
               current = startpq.poll();
               i += 1;
           }
           count -= 1;
       }
       int max = 0;
        for(int n : sums) {
            max = Math.max(max, n);
        }
        return max;
    }

}
