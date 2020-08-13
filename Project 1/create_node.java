/** Each node of graph is implemeneted by this class **/

public class create_node implements Comparable<create_node> {
	
	/**Node parameters**/
	
	create_node parent;
	create_node child;
	int depth;
	float stepCost;
	float heuristic;
	String currentState;
	
	public create_node (create_node parent, int depth, float stepCost, String currentState, float heuristic) {
		
		this.parent = parent;
		this.depth = depth;
		this.stepCost = stepCost;
		this.heuristic = heuristic;
		this.currentState = currentState;
	}

	/** Sorts the fringe on basis of path cost**/
	@Override
	public int compareTo(create_node o) {
		return Float.compare(this.stepCost, o.stepCost);
	}

}
