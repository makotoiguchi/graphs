package com.iguchi.graphs.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.iguchi.graphs.model.Edge;
import com.iguchi.graphs.model.Graph;

public class FindCycle {

	private static boolean findCycle(Graph graph) {
		HashSet<Character> visitedV = new HashSet<Character>();
		boolean[] visitedE = new boolean[graph.getEdgesSize()];
		HashMap<Character, ArrayList<Integer>> adj = new HashMap<Character, ArrayList<Integer>>();
		
		ArrayList<Edge> edges = graph.getAllEdges();
		for (int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);
			if (!adj.containsKey(edge.getV1()))
				adj.put(edge.getV1(), new ArrayList<Integer>());
			adj.get(edge.getV1()).add(i);
			if (!adj.containsKey(edge.getV2()))
				adj.put(edge.getV2(), new ArrayList<Integer>());
			adj.get(edge.getV2()).add(i);
		}
		
		ArrayList<Character> vertices = graph.getAllVertex(); 
		for (int i = 0; i < vertices.size(); i++) {
			char v = vertices.get(i);
			if (!visitedV.contains(v)) {
				if (visit(edges, visitedV, visitedE, adj, v))
					return true;
			}
		}
		return false;
	}
	
	
	private static boolean visit(ArrayList<Edge> edges, HashSet<Character> visitedV, boolean[] visitedE,
			HashMap<Character, ArrayList<Integer>> adj, char v) {
		visitedV.add(v);
		for (int idxE : adj.get(v)) {
			if (!visitedE[idxE]) {
				Edge edge = edges.get(idxE);
				char v2 = (edge.getV1() == v ? edge.getV2() : edge.getV1());
				visitedE[idxE] = true;
				if (visitedV.contains(v2))
					return true;
				if (visit(edges, visitedV, visitedE, adj, v2))
					return true;
			}
		}
		return false;
	}


	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.addEdge('A', 'B');
		graph.addEdge('B', 'C');
		graph.addEdge('B', 'D');
		graph.addEdge('C', 'F');
		graph.addEdge('C', 'G');
		graph.addEdge('D', 'E');
		graph.addEdge('E', 'H');
		
		graph.addEdge('I', 'J');
		graph.addEdge('K', 'J');
		graph.addEdge('K', 'I');
		
		if (findCycle(graph)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

}
