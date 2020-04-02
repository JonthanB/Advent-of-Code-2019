import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	
	String id;
	Integer orbits;
	List<TreeNode> branches;
	TreeNode root;
	
	public TreeNode(String id, TreeNode root) {
		this.id = id;
		this.root = root;
		init();
	}
	
	public TreeNode(String id) {
		this.id = id;
		this.root = null;
		init();
	}
	
	public void init() {
		
		branches = new ArrayList<TreeNode>();
		
		if (root != null) {
			root.addBranch(this);
		}
	}
	
	public void setupOrbitAround(TreeNode treeNode) {
		// Set this node's root and add this as a branch to the root.
		setRoot(treeNode);
		getRoot().addBranch(this);
	}
	
	private void setRoot(TreeNode treeNode) {
		root = treeNode;
	}
	
	private void addBranch(TreeNode treeNode) {
		branches.add(treeNode);
	}
	
	public TreeNode getBranchByIndex(int index) {
		
		if (index >= 0 && index < branches.size()) {
			return branches.get(index);
		} else {
			return null;
		}
	}
	
	public String getID() {
		return id;
	}
	
	public Integer getOrbits() {
		return orbits;
	}
	
	public TreeNode getRoot() {
		return root;
	}
	
}
