public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        return Math.sqrt(Math.pow((this.xxPos - p.xxPos), 2) + Math.pow((this.yyPos - p.yyPos), 2));
    }

    public double calcForceExertedByX(Planet p){
        double G = 6.67e-11;
        return this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        double G = 6.67e-11;
        return this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
    }

    public double calcForceExertedBy(Planet p){
        double G = 6.67e-11;
        return G * this.mass * p.mass / Math.pow(this.calcDistance(p), 2);
    }

    public double calcNetForceExertedByX(Planet[] p){
        double totalForce = 0;
        for (Planet planet : p) {
            if (this.equals(planet)) {
                continue;
            }
            totalForce += this.calcForceExertedBy(planet) * (planet.xxPos - this.xxPos) / this.calcDistance(planet);
        }
        return totalForce;
    }

    public double calcNetForceExertedByY(Planet[] p){
        double totalForce = 0;
        for (Planet planet : p) {
            if (this.equals(planet)) {
                continue;
            }
            totalForce += this.calcForceExertedBy(planet) * (planet.yyPos - this.yyPos) / this.calcDistance(planet);
        }
        return totalForce;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
