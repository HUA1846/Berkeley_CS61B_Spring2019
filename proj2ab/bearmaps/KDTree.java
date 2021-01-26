package bearmaps;
import java.util.*;

public class KDTree implements PointSet {
    private List<Point> points;
    private Node root;

    public KDTree(List<Point> points) {
        this.points = points;
        root = new Node(points.get(0));
        for(int i = 1; i < points.size(); i += 1) {
            insert(points.get(i));
        }
    }
    public void insert(Point p) {
        double px = p.getX();
        double py = p.getY();
        Node current = root;
        while(current != null) {
            if(px == current.p.getX() && py == current.p.getY()){return;}
            if(current.isVertical) {
                if(px < current.p.getX()) {
                    if(current.left == null) {
                        current.left = new Node(p);
                        current.left.isVertical = !current.left.isVertical; return;
                    } else {
                        current = current.left;
                    }
                } else {
                    if(current.right == null) {
                        current.right = new Node(p);
                        current.right.isVertical = !current.right.isVertical; return;
                    } else {
                        current = current.right;
                    }
                }
            }
            if(px == current.p.getX() && py == current.p.getY()){return;}
            if(!current.isVertical) {
                if(py < current.p.getY()) {
                    if(current.left == null) {
                        current.left = new Node(p); return;
                    } else {
                        current = current.left;
                    }
                } else {
                    if(current.right == null) {
                        current.right = new Node(p); return;
                    } else {
                        current = current.right;
                    }
                }

            }
        }

    }

    private class Node {
        private Point p;
        private Node left;
        private Node right;
        private boolean isVertical;

        private Node(Point p) {
            this.p = p;
            this.left = null;
            this.right = null;
            isVertical = true;
        }
    }

    @Override
    public Point nearest(double x, double y) {
        double best = Double.POSITIVE_INFINITY;
        Node res = root;
        Point goal = new Point(x, y);
        while(res != null) {
            if(res.p.distance(res.p, goal) < best) {

            }
        }

    }

    public static void main(String[] args) {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);

        KDTree kdt = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        Point ret = kdt.nearest(0, 7); // return p6
        ret.getX();
        ret.getY();

        System.out.println(ret.getX());
        System.out.println(ret.getY());
    }
}
