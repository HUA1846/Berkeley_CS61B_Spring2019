import java.util.LinkedList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {

    public LinkedList<Bear> bears = new LinkedList<>();
    public LinkedList<Bed> beds = new LinkedList<>();

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        this.bears.addAll(bears);
        this.beds.addAll(beds);
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        LinkedList<Bear> sortedBear = new LinkedList<>();
        LinkedList<Bed> sortedBed = new LinkedList<>();


        while(!beds.isEmpty() && !bears.isEmpty()) {
            LinkedList<Bear> less = new LinkedList<>();
            LinkedList<Bear> equal = new LinkedList<>();
            LinkedList<Bear> greater = new LinkedList<>();
            Bed pivot = beds.removeFirst();
            partition(bears, pivot, less, equal, greater);
            sortedBed.addLast(pivot);
            Bear e = equal.remove();
            bears.remove(e);
            sortedBear.add(e);

        }
        bears = sortedBear;
        beds = sortedBed;

        return sortedBear;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
//        solvedBears();
        return beds;
    }

    /* pick a pivot bed (remove from front of List<Bed>, store pivot to another bed list (addLast)
     * put bear < pivot in a list
     * bear = pivot in a list
     * bear > pivot in a list
     */
    private static void partition(List<Bear> bears, Bed pivot, List<Bear> less,
                                  List<Bear> equal, List<Bear> greater) {

        for(Bear b : bears) {
            if(b.compareTo(pivot) < 0) {
                less.add(b);
            }
            if(b.compareTo(pivot) > 0) {
                greater.add(b);
            } else if(b.compareTo(pivot) == 0) {
                equal.add(b);
            }
        }
    }

}
