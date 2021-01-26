package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void testNearest() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree kdt = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        Point ret = kdt.nearest(0, 7); // return p6
        assertEquals(1, ret.getX(), 0.01);
        assertEquals(5, ret.getY(), 0.01);

    }

    @Test
    public void testNearestRandom() {
        List<Point> points = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            Random r1 = new Random();
            Random r2 = new Random();
            points.add(new Point(r1.nextDouble(), r2.nextDouble()));
        }
        KDTree kdt = new KDTree(points);
    }
}
