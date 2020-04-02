import java.util.*;

public class MoonMonitor {

	private static List<Moon> moons = new ArrayList<Moon>();
	
	public static void main(String[] args) {
		
		moons.add(new Moon(new Vector3(-4,-14,8)));
		moons.add(new Moon(new Vector3(1,-8,10)));
		moons.add(new Moon(new Vector3(-15,2,1)));
		moons.add(new Moon(new Vector3(-17,-17,16)));
		
		for (int t = 0; t < 1000; t++) {
			//System.out.println();
			for (int i = 0; i < moons.size(); i++) {
				for (int j = 0; j < moons.size(); j++) {
					if (moons.get(i).equals(moons.get(j))) {
						continue;
					}
					moons.get(i).updateVelocity(moons.get(j));
				}
			}
			
			for (int k = 0; k < moons.size(); k++) {
				moons.get(k).tickMoon();
				//System.out.println("Position: " + moons.get(k).position.toString());
				//System.out.println("Velocity: " + moons.get(k).velocity.toString());
			}
		}
 		
		float moonEnergy1 = moons.get(0).position.absoluteSum() * moons.get(0).velocity.absoluteSum();
		float moonEnergy2 = moons.get(1).position.absoluteSum() * moons.get(1).velocity.absoluteSum();
		float moonEnergy3 = moons.get(2).position.absoluteSum() * moons.get(2).velocity.absoluteSum();
		float moonEnergy4 = moons.get(3).position.absoluteSum() * moons.get(3).velocity.absoluteSum();
		
		float totalSum = moonEnergy1 + moonEnergy2 + moonEnergy3 + moonEnergy4;
		
		System.out.println("Total sum of energy: " + totalSum);

	}
}
