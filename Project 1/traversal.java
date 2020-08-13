/**Performs the search algorithm**/

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class traversal {
 
 /** UninformedSearch **/

	public void UninformedSearch(ArrayList<String> list, String source, String destination) {
		
		create_node parentNode = new create_node (null, 0, 0, source, 0);
		ArrayList<create_node> frontier = new ArrayList<create_node>();
		
		frontier.add(parentNode);
		 
		create_node goal = null;
		
		/**Count keeps track of nodes expanded while finding the destination**/
		int count = 0;
		int maxNodesinmemory=0;
		int nodesGenerated=0;
		/** Keeps the track of visited nodes **/
		ArrayList<String> closedList = new ArrayList<String>();
		
		/** Runs the loop till the frontier is empty**/
		 
		while (!(frontier.isEmpty()) && goal == null) {
			
			 
			create_node current = frontier.remove(0);
			count++;
		     
		 
			if (current.currentState.equals(destination)) {
				goal = current;
				 
			}
			else {
				    
				if(closedList.contains(current.currentState)){}
				
				else {
					closedList.add(current.currentState);
					 for (String temp : list) {
						if (temp.contains(current.currentState)) 
						 {
							/**Converts input text file to string by using StringTokenizer**/
							StringTokenizer tokenString = new StringTokenizer(temp, " ");
							String city1 = tokenString.nextToken();
							String city2 = tokenString.nextToken();
							float cost = (float) Integer.parseInt(tokenString.nextToken());
						    
							if (current.currentState.equals(city1)) {
								 create_node city = new create_node(current, current.depth+1, current.stepCost+cost, city2, 0);
								 nodesGenerated++;
								frontier.add(city);
								}
							else {
								 create_node city = new create_node(current, current.depth+1, current.stepCost+cost, city1, 0);
								  nodesGenerated++;
								 frontier.add(city);
								 }
							
						 } 
					}
					/** Sorting the frontier **/
					Collections.sort(frontier);
					if(maxNodesinmemory<frontier.size())
						maxNodesinmemory=frontier.size();	
				}
			}
		}
		
		/** Print the output if path does not exists**/
		
		if (goal == null) {
			System.out.println("Nodes Expanded: " + count);
			System.out.println("Nodes Generated : "+nodesGenerated);
			System.out.println("Max Nodes in Memory: "+ maxNodesinmemory);
			System.out.println("Distance: Infinity \nRoute : \nNone");
		}
		/** Print the output if path exists**/
		
		else {
			goal.child = null;
			System.out.println("Nodes Expanded: " + count);
			System.out.println("Nodes Generated : "+nodesGenerated);
			System.out.println("Max Nodes in Memory: "+ (maxNodesinmemory));
			System.out.println("Distance: " + goal.stepCost + " km " + "\nRoute : ");
			while (goal.parent != null) {
				goal.parent.child = goal;
				goal = goal.parent;
			}
			while (goal.child != null) {
				System.out.println(goal.currentState.toUpperCase() + " to " + goal.child.currentState.toUpperCase() + " : " + (goal.child.stepCost - goal.stepCost));
				goal = goal.child;
			}
		}
		
	}

	/** InformedSearch **/
	
	public void InformedSearch(ArrayList<String> inputData, String source, String destination,
			ArrayList<String> heuristicData) {

		
		create_node parentNode = new create_node (null, 0, 0, source, 0);
		ArrayList<create_node> frontier = new ArrayList<create_node>();
		
		frontier.add(parentNode);
		create_node goal = null;
		int maxNodesinmemory=0;
		int nodesGenerated=0;
		/**CountNodes keeps track of nodes expanded while finding the destination**/
		int countNodes = 0;
		float heuristicValue = 0;
		
		/** Keeps the track of visited nodes **/
		
		ArrayList<String> exploredList = new ArrayList<String>();
		
		while (!(frontier.isEmpty()) && goal == null) {
			
			create_node current = frontier.remove(0);
			countNodes++;
			
			if (current.currentState.equals(destination)) {
				goal = current;
				
			}
			else {
				if(!(exploredList.contains(current.currentState))){
					exploredList.add(current.currentState);
					float cost;
					for (String temp : inputData) {
						if (temp.contains(current.currentState)) {
						
						/**Converts input text file to string by using StringTokenizer**/
						    StringTokenizer tokenString = new StringTokenizer(temp, " ");
							String city1 = tokenString.nextToken();
							String city2 = tokenString.nextToken();
							cost = (float) Integer.parseInt(tokenString.nextToken());
							if (current.currentState.equals(city1)) {
								for (String hueristic : heuristicData) {
									StringTokenizer heuristicTokenString = new StringTokenizer(hueristic, " ");
									String city = heuristicTokenString.nextToken();
									float heuristicCost = (float) Integer.parseInt(heuristicTokenString.nextToken());
									if (city.equalsIgnoreCase(city2)) {
										heuristicValue = heuristicCost;
										create_node cityValue = new create_node(current, (int)(current.stepCost+cost), current.stepCost+cost, city2, current.stepCost+cost+heuristicValue);
										 nodesGenerated++;
										frontier.add(cityValue);
									}
								}
							}
							else if (current.currentState.equals(city2)) {
								for (String hueristic : heuristicData) {
									StringTokenizer heuristicTokenString = new StringTokenizer(hueristic, " ");
									String city = heuristicTokenString.nextToken();
									float heuristicCost = (float) Integer.parseInt(heuristicTokenString.nextToken());
									if (city.equalsIgnoreCase(city1)) {
										heuristicValue = heuristicCost;
										create_node cityValue = new create_node(current, (int)(current.stepCost+cost), current.stepCost+cost, city1, current.stepCost+cost+heuristicValue);
										nodesGenerated++;
										frontier.add(cityValue);
									}
								}
							}
						}
					}
					 
					Collections.sort(frontier, new node_compare());
					if(maxNodesinmemory<frontier.size())
						maxNodesinmemory=frontier.size();
				}

			}
		}
		
		/** Print the output if path does not exists**/
		if (goal == null) {
			System.out.println("Nodes Expanded: " + countNodes);
			System.out.println("Nodes Generated :"+nodesGenerated);
			System.out.println("Max nodes in Memory :"+maxNodesinmemory);
			System.out.println("Distance : Infinity \nRoute : \nNone");
		}
		
		/** Print the output if path exists**/
		else {
			goal.child = null;
			System.out.println("Nodes Expanded: " + countNodes);
			System.out.println("Nodes Generated :"+nodesGenerated);
			System.out.println("Max nodes in Memory :"+maxNodesinmemory);
			System.out.println("Distance : " + (goal.stepCost) + " km " + "\nRoute : ");
			while (goal.parent != null) {
				goal.parent.child = goal;
				goal = goal.parent;
			}
			while (goal.child != null) {
				System.out.println(goal.currentState.toUpperCase() + " to " + goal.child.currentState.toUpperCase() + " : " + (goal.child.stepCost - goal.stepCost));
				goal = goal.child;
			}
		}
	}
}