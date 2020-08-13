/** Find the route between source and destination **/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class find_route{
	
	/**Creates an object of traversal class**/
	
	private static traversal traverse;
	
	public static void main(String[] args) {
		
		traverse = new traversal();
		
		/** Command line arguments**/
		
		String inputFile = args[0];
		String source = args[1];
		String destination = args[2];
		
		ArrayList <String> inputData = new ArrayList<String>();
		ArrayList <String> heuristicData = new ArrayList<String>();
		
		try {
			FileReader inputFileReader = new FileReader(inputFile);
			BufferedReader inputBufferedReader = new BufferedReader(inputFileReader);
			
			String inputTextLine;
			
			while((inputTextLine = inputBufferedReader.readLine().toString()).equals("END OF INPUT") == false){
				inputData.add(inputTextLine.toLowerCase());
	    	}
			
			/** Takes input for heuristic file **/
			
			if (args.length == 4) {
				String heuristicFile = args[3];
				FileReader heuristicFileReader = new FileReader(heuristicFile);
				BufferedReader heuristicBufferedReader = new BufferedReader(heuristicFileReader);
				
				String heuristicTextLine;
				
				while ((heuristicTextLine = heuristicBufferedReader.readLine().toString()).equals("END OF INPUT") == false) {
					heuristicData.add(heuristicTextLine.toLowerCase());
				}
				heuristicBufferedReader.close();
			}
			inputBufferedReader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Unable to access file");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		if (args.length == 3) {
			traverse.UninformedSearch(inputData, source.toLowerCase(), destination.toLowerCase());
		}
		else {
			traverse.InformedSearch(inputData, source.toLowerCase(), destination.toLowerCase(), heuristicData);
		}
		
	}

}
