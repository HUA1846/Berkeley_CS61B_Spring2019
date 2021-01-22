public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private double G = 6.67 * 1e-11;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img) {
            xxPos = xP;
            yyPos = yP;
            xxVel = xV;
            yyVel = yV;
            mass = m;
            imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double rsquare = Math.pow(xxPos - b.xxPos, 2) + Math.pow(yyPos - b.yyPos, 2);
        return Math.sqrt(rsquare);
    }

    public double calcForceExertedBy(Body b) {
        return G * mass * b.mass / Math.pow(calcDistance(b), 2);
    }

    public double calcForceExertedByX(Body b) {
        return calcForceExertedBy(b) * (b.xxPos - xxPos) / calcDistance(b);
    }

    public double calcForceExertedByY(Body b) {
        return calcForceExertedBy(b) * (b.yyPos - yyPos) / calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] nbodies) {
        double force = 0;
        for(int i = 0; i < nbodies.length; i++) {
            if(!this.equals(nbodies[i])) {
                force += calcForceExertedByX(nbodies[i]);
            }
        }
        return force;
    }

    public double calcNetForceExertedByY(Body[] nbodies) {
        double force = 0;
        for(int i = 0; i < nbodies.length; i++) {
            if(!this.equals(nbodies[i])) {
                force += calcForceExertedByY(nbodies[i]);
            }
        }
        return force;
    }

    public void update(double t, double fx, double fy) {
        xxVel += t * fx / mass;
        yyVel += t * fy / mass;
        xxPos += t * xxVel;
        yyPos += t * yyVel;

    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
    }
}
