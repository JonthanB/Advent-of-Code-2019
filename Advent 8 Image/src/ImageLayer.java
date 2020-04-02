import java.util.*;


public class ImageLayer {
	
	String layerID;
	
	Integer nrOfZeroes = 0;
	Integer imageWidth = 0;
	Integer imageHeight = 0;
	List<List<Integer>> imagePixels;
	
	Integer iteratorRow = 0;
	Integer iteratorCol = 0;
	
	Boolean layerFull = false;
	
	public ImageLayer(Integer width, Integer height) {
		imageWidth = width;
		imageHeight = height;
		imagePixels = new ArrayList<List<Integer>>();
		imagePixels.add(new ArrayList<Integer>());
	}
	
	public Boolean isFull() {
		return layerFull;
	}
	
	public void loadInput(Integer input) {
		
		if (layerFull) {
			System.err.println("Layer is full! Choose a new layer! Writing aborted.");
			return;
		}
		
		Integer listSize = imagePixels.get(iteratorCol).size();
		if (listSize >= imageWidth) {
			imagePixels.add(new ArrayList<Integer>());
			iteratorCol++;
			iteratorRow = 0;
		}
		
		imagePixels.get(iteratorCol).add(input);
		iteratorRow++;
		
		if ((iteratorCol+1 + iteratorRow) >= (imageWidth + imageHeight)) {
			layerFull = true;
		}
		
	}
	
	public Integer getIntAtPos(Integer x, Integer y) {
		return imagePixels.get(x).get(y);
	}
	
	public Integer countIntegers(Integer intToCount) {
		
		Integer count = 0;
		
		for (int i = 0; i < imagePixels.size(); i++) {
			for (int j = 0; j < imagePixels.get(i).size(); j++) {
				
				if (imagePixels.get(i).get(j) == intToCount) {
					count++;
				}
				
			}
		}
		return count;
	}
	
	public Integer countAllPixels() {
		
		Integer count = 0;
		
		for (int i = 0; i < imagePixels.size(); i++) {
			for (int j = 0; j < imagePixels.get(i).size(); j++) {
				
				count++;
			}
		}
		return count;
	}
}
