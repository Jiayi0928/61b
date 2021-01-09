public class Planet {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		double distance = Math.hypot(dx,dy);
		return distance;
	}

	public double calcForceExertedBy(Planet p) {
		double f = G * this.mass * p.mass / (calcDistance(p) * calcDistance(p));
		return f;
	}

	public double calcForceExertedByX(Planet p){
		double fx = calcForceExertedBy(p) * (p.xxPos - this.xxPos) / calcDistance(p);
		return fx;
	}

	public double calcForceExertedByY(Planet p){
		double fy = calcForceExertedBy(p) * (p.yyPos - this.yyPos) / calcDistance(p);
		return fy;
	}

	public double calcNetForceExertedByX(Planet[] ps){
		double Fxnet = 0;
		for (Planet p : ps){
			if(!this.equals(p)){
				Fxnet += this.calcForceExertedByX(p);
			}
		}
		return Fxnet;
	}

	public double calcNetForceExertedByY(Planet[] ps){
		double Fynet = 0;
		for (Planet p : ps){
			if(!this.equals(p)){
				Fynet += this.calcForceExertedByY(p);
			}
		}
		return Fynet;
	}

	public void update(double dt, double fX, double fY){
		double aX = fX / mass;
		double aY = fY / mass;
		double xxNewVel = xxVel + aX * dt;
		double yyNewVel = yyVel + aY * dt;
		double xxNewPos = xxPos + xxNewVel * dt;
		double yyNewPos = yyPos + yyNewVel * dt;
		xxVel = xxNewVel;
		yyVel = yyNewVel;
		xxPos = xxNewPos;
		yyPos = yyNewPos;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
	}
}