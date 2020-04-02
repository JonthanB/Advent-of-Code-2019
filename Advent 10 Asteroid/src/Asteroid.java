import java.awt.geom.Point2D;

public class Asteroid {
	
	private Point2D position;
	public double radianFromStation = 0;
	public double distanceFromStation = 0;
	
	public Asteroid(Point2D pos) {
		position = (Point2D) pos.clone();
	}
	
	public Point2D getPosition() {
		return position;
	}
	
}
