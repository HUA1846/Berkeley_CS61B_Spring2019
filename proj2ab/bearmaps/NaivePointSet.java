package bearmaps;
import java.util.*;

public class NaivePointSet implements PointSet{
    List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }
    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        double best = Double.POSITIVE_INFINITY;
        Point nearest = points.get(0);
        for(Point p: points) {
            if(p.distance(p, goal) - best < 0) {
                nearest = p;
                best = p.distance(p, goal);
            }
        }
        return nearest;
    }


    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        ret.getX(); // evaluates to 3.3
        ret.getY(); // evaluates to 4.4

        System.out.println(ret.getX());
        System.out.println(ret.getY());
    }
}
