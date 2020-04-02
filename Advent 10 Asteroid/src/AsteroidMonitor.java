import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AsteroidMonitor {
	
	private static List<Asteroid> asteroids;
	
	public static void main(String[] args) throws IOException {
		
		asteroids = new ArrayList<Asteroid>();
		
		loadInputData(new File("C:\\Users\\Berry\\eclipse-workspace\\Advent 10 Asteroid\\io\\asteroidMap.txt"));
		
		Asteroid stationAsteroid = new Asteroid(new Point2D.Float(0,0));
		
		int size = 0;
		int highestSize = 0;
		Point2D stationPos = new Point2D.Float();
		for (int i = 0; i < asteroids.size(); i++) {
			size = findAllUniqueAngles(asteroids.get(i)).size();
			if (size > highestSize) {
				highestSize = size;
				stationAsteroid = asteroids.get(i);
			}
		}
		
		stationPos = stationAsteroid.getPosition();
		System.out.println("Optimal station position is at (" + stationPos.getX() + ", " + stationPos.getY() + ") where " + highestSize + " asteroids can be observed.");
		
		// Get angles to all visible asteroids from the new station.
		List<Double> asteroidAngles = new ArrayList<Double>();
		
		stationAsteroid.distanceFromStation = 0;
		stationAsteroid.radianFromStation = -0;
		
		asteroidAngles = findAllUniqueAngles(stationAsteroid);
		
		// Sort them...
		Collections.sort(asteroidAngles);
		
		
		outer: for (int i = 0; i < asteroidAngles.size(); i++) {
			if (asteroidAngles.get(i) == Math.atan2(-1, 0)) {
				
				while (asteroidAngles.get(0) != Math.atan2(-1, 0)) {
					asteroidAngles.add(asteroidAngles.get(0));
					asteroidAngles.remove(0);
				}
				
				break outer;
			}
		}

		int destroyedCount = 0;
		
		for (int i = 0; i < asteroidAngles.size(); i++) {
			double rad = asteroidAngles.get(i);
			Asteroid toBeDestroyed = null;
			double distance = 9999999;
			for (int j = 0; j < asteroids.size(); j++) {
				if (asteroids.get(j).radianFromStation == rad && asteroids.get(j).distanceFromStation < distance) {
					toBeDestroyed = asteroids.get(j);
				}
			}
			if (toBeDestroyed != null) {
				destroyedCount++;
				if (destroyedCount == 200) {
					System.out.println("200th asteroid is: " + toBeDestroyed.getPosition());
					int answer = ((int)toBeDestroyed.getPosition().getX() * 100) + (int)toBeDestroyed.getPosition().getY();
					System.out.println("Answer is: " + answer);
				}
				asteroids.remove(toBeDestroyed);
			}
		}
		
	}
	
	private static List<Double> findAllUniqueAngles(Asteroid asteroid) {
		
		List<Double> angles = new ArrayList<Double>();
		
		for (int i = 0; i < asteroids.size(); i++) {
			if (asteroids.get(i).equals(asteroid)) {
				continue;
			}
			
			double posX = asteroid.getPosition().getX();
			double posY = asteroid.getPosition().getY();
			double compX = asteroids.get(i).getPosition().getX();
			double compY = asteroids.get(i).getPosition().getY();
			
			asteroids.get(i).distanceFromStation = Point2D.distance(posX, posY, compX, compY);
			
			compX = compX - posX;
			compY = compY - posY;
			
			double atan = Math.atan2(compY, compX);
			
			asteroids.get(i).radianFromStation = atan;
			
			Boolean contains = false;
			
			for (int j = 0; j < angles.size(); j++) {
				if (angles.get(j).doubleValue() == atan) {
					contains = true;
					break;
				}
			}
			
			if (!contains) {
				angles.add(atan);
			}
			
		}
		
		return angles;
	}
	
	
	private static void loadInputData(File file) throws IOException {
		
		FileReader fr = new FileReader(file); 
		BufferedReader br = new BufferedReader(fr);
		
		String st;
		char[] charArray;
		float j = 0;
		
		while ((st = br.readLine()) != null) {
			
			charArray = st.toCharArray();
			
			for (int i = 0; i < charArray.length; i++) {
				
				if (charArray[i] == '#') {
					Point2D newPosition = new Point2D.Float(i, j);
					asteroids.add(new Asteroid(newPosition));
				}
			}
			j++;
		} 
		
		br.close();
	}
}
