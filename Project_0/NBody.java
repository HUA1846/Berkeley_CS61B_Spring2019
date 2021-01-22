import edu.princeton.cs.algs4.StdDraw;

public class NBody {

    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        return in.readDouble();
    }

    public static Body[] readBodies(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        in.readDouble();
        Body[] bodies = new Body[N];
        int i = 0;
        while(i < N) {
          bodies[i] = new Body(
                  in.readDouble(),
                  in.readDouble(),
                  in.readDouble(),
                  in.readDouble(),
                  in.readDouble(),
                  in.readString());

          i += 1;
        }
        return bodies;
    }
    private static String backgroundImg = "./images/starfield.jpg";

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double R = readRadius(filename);
        Body[] b = readBodies(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-R, R);
        StdDraw.clear();

//        StdDraw.picture(0, 0, backgroundImg);
//        StdDraw.picture(0, 0, "./images/earth.gif");
//        StdDraw.show();

        double time = 0.0;
        int count = b.length;
        double[] xForces = new double[count];
        double[] yForces = new double[count];

        while(time < T) {
            for(int i = 0; i < count; i ++) {
                xForces[i] = b[i].calcNetForceExertedByX(b);
                yForces[i] = b[i].calcNetForceExertedByY(b);
            }

            for(int i = 0; i < count; i ++) {
                b[i].update(dt, xForces[i], yForces[i]);
            }


            StdDraw.picture(0, 0, backgroundImg);
            for(int i = 0; i < count; i ++) {
                StdDraw.picture(b[i].xxPos, b[i].yyPos, "./images/" + b[i].imgFileName);
            }

            /* the draw() method from Body class does not work here */
//            for (Body p : b) {
//                p.draw();
//            }

            StdDraw.show();
            StdDraw.pause(10);

            time += dt;
        }

        StdOut.printf("%d\n", b.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < b.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    b[i].xxPos, b[i].yyPos, b[i].xxVel,
                    b[i].yyVel, b[i].mass, b[i].imgFileName);
        }

    }


}
