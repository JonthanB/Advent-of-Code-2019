import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class ImageDecode {

	private static Integer imageWidth = 25;
	private static Integer imageHeight = 6;
	
	private static Integer count = 0;
	
	private static List<ImageLayer> layers;
	
	public static void main(String[] args) throws IOException {
		
		layers = new ArrayList<ImageLayer>();
		layers.add(new ImageLayer(imageWidth, imageHeight));
		
		loadInputData(new File("C:\\Users\\Berry\\eclipse-workspace\\Advent 8 Image\\io\\roverImage.txt"));
		
		Integer lowestCountIndex = 0;
		Integer lowestCount = 2000;
		
		for (int i = 0; i < layers.size(); i++) {
			Integer zeroCount;
			zeroCount = layers.get(i).countIntegers(0);
			
			if (zeroCount < lowestCount) {
				lowestCountIndex = i;
				lowestCount = zeroCount;
			}
		}
		
		int ones = layers.get(7).countIntegers(1);
		int twos = layers.get(7).countIntegers(2);
		
		System.out.print("Total Layers (Should be 100): " + count + "\n"
						+ "Index: " + lowestCountIndex + "\n"
						+ "Zeroes: " + lowestCount + "\n"
						+ "Ones: " + ones + "\n"
						+ "Twos: " + twos + "\n"
						+ "Nr. of 1s multiplied by 2s = " + ones * twos + "!" + "\n" + "\n");
		
		List<Integer> decodedImage = new ArrayList<Integer>();
		
		// Loop through all pixels! Start from the first layer. 
		for (int j = 0; j < imageHeight; j++) {
			for (int k = 0; k < imageWidth; k++) {
				
				Integer layerIndex = 0;
				Integer pixel = layers.get(layerIndex).getIntAtPos(j, k);
				
				if (pixel != 2) {
					decodedImage.add(pixel);
				} else {
				
					while (pixel == 2) {		
						layerIndex++;
						pixel = layers.get(layerIndex).getIntAtPos(j, k);
						
						if (pixel != 2) {
							decodedImage.add(pixel);
							break;
						} else if (layerIndex >= layers.size()) {
							decodedImage.add(pixel);
							break;
						}
					}
				}
			}
		}
		
		// Print the image.
		for (int i = 0; i < decodedImage.size(); i++) {
			System.out.print(decodedImage.get(i));
			Integer mod = i % 25;
			if (mod == 24) {
				System.out.println();
			}
		}
	}
	
	private static void loadInputData(File file) throws IOException {
		
		FileReader fr = new FileReader(file); 
		BufferedReader br = new BufferedReader(fr);
		
		ImageLayer activeLayer = layers.get(0);
		
		Integer i; 
		while ((i = br.read()) != -1) {
			i = i - 48;
			if (activeLayer.isFull()) {
				layers.add(activeLayer = new ImageLayer(imageWidth, imageHeight));
				count++;
			}
			activeLayer.loadInput(i);
		} 
		br.close();
	}
}
