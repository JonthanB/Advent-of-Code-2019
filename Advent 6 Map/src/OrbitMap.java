import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrbitMap {
	
	private static List<String> orbitList;
	
	private static List<TreeNode> treeList;
	
	private static List<TreeNode> YOUPath;
	
	private static List<TreeNode> SANPath;
	
	private static Integer orbitCount = 0;
	
	
	public static void main(String[] args) throws IOException {
		
		orbitList = new ArrayList<String>();
		
		treeList = new ArrayList<TreeNode>();
		
		SANPath = new ArrayList<TreeNode>();
		
		YOUPath = new ArrayList<TreeNode>();
		
		loadInputData(new File("C:\\Users\\Berry\\eclipse-workspace\\Advent 6 Map\\io\\orbits.txt"));
		
		for (int i = 0; i < orbitList.size(); i++) {
			
			String orbitString = orbitList.get(i);
			String body = orbitString.substring(0, 3);
			String orbitingBody = orbitString.substring(4);
			
			setupTreeNode(body, orbitingBody);
			
		}
		
		Integer count = 0;
		
		for (int i = 0; i < treeList.size(); i++) {
			if (treeList.get(i).getRoot() == null) {
				count++;
			}
		}
		
		traverseTree("COM");
		
		System.out.println("Number of Orbits: " + countAllOrbits());
		
		TreeNode nodeIt;
		nodeIt = findNodeByID("YOU");
		
		while (nodeIt.getID().compareTo("COM") != 0) {
			nodeIt = nodeIt.getRoot();
			YOUPath.add(nodeIt);
		}
		
		nodeIt = findNodeByID("SAN");
		
		while (nodeIt.getID().compareTo("COM") != 0) {
			nodeIt = nodeIt.getRoot();
			SANPath.add(nodeIt);
		}
		
		outer: for (int i = 0; i < YOUPath.size(); i++) {
			for (int j = 0; j < SANPath.size(); j++) {
				if (YOUPath.get(i).getID().compareTo(SANPath.get(j).getID()) == 0) {
					
					Integer distance = (findNodeByID("SAN").getOrbits() + findNodeByID("YOU").getOrbits()) - 2*(findNodeByID("D1Y").getOrbits());
					distance = distance - 2;
					System.out.println("Minimum distance to move to SAN object: " + distance);
					break outer;
				}
			}
		}
		
	}
	
	private static Integer countAllOrbits() {
		
		Integer totalOrbits = 0;
		
		for (int i = 0; i < treeList.size(); i++) {
			totalOrbits = totalOrbits + treeList.get(i).getOrbits();
		}
		return totalOrbits;
	}
	
	private static void traverseTree(String nodeID) {
		
		TreeNode node = findNodeByID(nodeID);
		if (node == null) {
			System.err.println("Node Doesn't Exist! ID: " + nodeID);
			return;
		}
		node.orbits = orbitCount;
		orbitCount++;
		
		
		for (int i = 0; i < node.branches.size(); i++) {
			traverseTree(node.branches.get(i).getID());
		}
		orbitCount--;
	}
	
	private static void setupTreeNode(String body, String orbitingBody) {
		
		// Find...
		TreeNode bodyNode = findNodeByID(body);
		// ... or create.
		if (bodyNode == null) {
			bodyNode = new TreeNode(body);
			treeList.add(bodyNode);
		}
		
		// Find...
		TreeNode orbitingBodyNode = findNodeByID(orbitingBody);
		// ... or create.
		if (orbitingBodyNode == null) {
			orbitingBodyNode = new TreeNode(orbitingBody, bodyNode);
			treeList.add(orbitingBodyNode);
		} else {
			orbitingBodyNode.setupOrbitAround(bodyNode);
		}
		
	}
	
	private static TreeNode findNodeByID(String id) {
		
		for (int i = 0; i < treeList.size(); i++) {
			TreeNode treeNode = treeList.get(i);
			if (treeNode.getID().compareTo(id) == 0) {
				return treeNode;
			}
			
		}
		
		return null;
	}
	
	private static void loadInputData(File file) throws IOException {
		
		FileReader fr = new FileReader(file); 
		BufferedReader br = new BufferedReader(fr);
		
		String st; 
		while ((st = br.readLine()) != null) {
			orbitList.add(st);
		} 
		
		br.close();
	}
}
