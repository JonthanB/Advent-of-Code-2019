
public class Moon {

	public Vector3 position;
	public Vector3 velocity;
	
	public Moon(Vector3 position) {
		this.position = new Vector3(position);
		velocity = new Vector3(0,0,0);
	}
	
	public void tickMoon( ) {
		position.add(velocity);
	}
	
	public void updateVelocity (Moon moon) {
		float xvel = compareAxis(position.x, moon.position.x);
		float yvel = compareAxis(position.y, moon.position.y);
		float zvel = compareAxis(position.z, moon.position.z);
		
		velocity.add(xvel, yvel, zvel);
		
	}
	
	private float compareAxis(float axis1, float axis2) {
		if (axis1 < axis2) {
			return 1;
		} else if (axis1 > axis2) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
