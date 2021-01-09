public class NBody {

	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int num = in.readInt();
		in.readDouble();
		Planet[] ps = new Planet[num];
		for (int i = 0; i < num; i++){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			ps[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return ps;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "./images/starfield.jpg");
		StdDraw.show();

		for (Planet p:planets){
			p.draw();
		}
		StdDraw.enableDoubleBuffering();

		for(double time = 0; time < T; time += dt){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for(int i = 0; i < planets.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "./images/starfield.jpg");

			for (Planet p:planets){
				p.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
		}
	}
}
