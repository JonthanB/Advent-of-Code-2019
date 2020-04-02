
public class Vector3 {

	public float x,y,z;
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3(Vector3 v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	public void add(Vector3 v) {
		this.x = this.x + v.x;
		this.y = this.y + v.y;
		this.z = this.z + v.z;
	}
	
	public void add(float x, float y, float z) {
		this.x = this.x + x;
		this.y = this.y + y;
		this.z = this.z + z;
	}
	
	public void subtract(Vector3 v) {
		this.x = this.x - v.x;
		this.y = this.y - v.y;
		this.z = this.z - v.z;
	}
	
	public void subtract(float x, float y, float z) {
		this.x = this.x - x;
		this.y = this.y - y;
		this.z = this.z - z;
	}
	
	public Boolean equals (Vector3 v) {
		return (this.x == v.x && this.y == v.y && this.z == v.z);
	}
	
	public float absoluteSum() {
		return Math.abs(x) +  Math.abs(y) + Math.abs(z);
	}
	
	public String toString() {
		return "X: " + x + " Y: " + y + " Z: " + z;
	}
	
}
