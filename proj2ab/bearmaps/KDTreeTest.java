package bearmaps;

import org.junit.Test;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

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
        Point p8 = new Point(2, 7);

        KDTree kdt = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7, p8));
        Point ret = kdt.nearest(0, 7); // return p8
        assertEquals(2, ret.getX(), 0.01);
        assertEquals(7, ret.getY(), 0.01);

    }

    @Test
    public void testNearestRandom() {
        List<Point> points = new ArrayList<>();
        for(int i = 0; i < 1000; i++) {
            Random r1 = new Random();
            Random r2 = new Random();
            points.add(new Point(r1.nextDouble(), r2.nextDouble()));
        }
        KDTree kdt = new KDTree(points);
        NaivePointSet nn = new NaivePointSet(points);
        Point kd = kdt.nearest(0, 7);
        Point n = nn.nearest(0, 7);
        assertEquals(n.getX(), kd.getX(), 0.001);
        assertEquals(n.getY(), kd.getY(), 0.001);

    }
    @Test
    public void testSpeed() {
        List<Point> points = new ArrayList<>();
        for(int i = 0; i < 100000; i++) {
            Random r1 = new Random();
            Random r2 = new Random();
            points.add(new Point(r1.nextDouble(), r2.nextDouble()));
        }

        Stopwatch sw = new Stopwatch();
        KDTree kdt = new KDTree(points);
        for(int i = 0; i < 10000; i ++ ) {
            Random r1 = new Random();
            Random r2 = new Random();
            kdt.nearest(r1.nextDouble(), r2.nextDouble());
        }
        double t1 = sw.elapsedTime();
//        System.out.println("KDTree: " + sw.elapsedTime());

        Stopwatch sw2 = new Stopwatch();
        NaivePointSet nn = new NaivePointSet(points);
        for(int i = 0; i < 10000; i ++ ) {
            Random r1 = new Random();
            Random r2 = new Random();
            nn.nearest(r1.nextDouble(), r2.nextDouble());
        }
        double t2 = sw2.elapsedTime();
//        System.out.println("NaivePointSet: " + sw2.elapsedTime());

        assertTrue(t1/t2 < 0.1);
    }
    public static void main(String[] args) {
        KDTreeTest test = new KDTreeTest();
        test.testSpeed();
    }
}
