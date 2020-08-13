/**Sorts the frontier on basis of path cost**/

import java.util.Comparator;

public class node_compare implements Comparator<create_node> { 

 
	public int compare(create_node n1, create_node n2) {
		return (int) (n1.heuristic - n2.heuristic);
		  
	}
}
