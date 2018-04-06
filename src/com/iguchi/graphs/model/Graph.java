package com.iguchi.graphs.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Graph {

	private ArrayList<Edge> allEdges = new ArrayList<Edge>();
	private ArrayList<Character> allVertex = new ArrayList<Character>();
	private HashMap<Character, ArrayList<Edge>> adj = new HashMap<Character, ArrayList<Edge>>();   

	public void addEdge(char v1, char v2, int weight) {
		addOneWayEdge(new Edge(v1, v2, weight));
	}
	
	public void addEdge(char v1, char v2) {
		addEdge(v1, v2, 1);
	}

	private void addOneWayEdge(Edge edge) {
		char v1 = edge.getV1();
		if (!allVertex.contains(v1))
			allVertex.add(v1);
		allEdges.add(edge);
		if (!adj.containsKey(v1)) {
			adj.put(v1, new ArrayList<Edge>());
		}
		adj.get(v1).add(edge);
		Collections.sort(adj.get(v1));
	}

	public ArrayList<Character> getAllVertex() {
		return allVertex;
	}

	public ArrayList<Edge> getAllEdges() {
		return allEdges;
	}
	
	public ArrayList<Edge> getAllEdges(char s) {
		return adj.get(s);
	}

	public int getEdgesSize() {
		return allEdges.size();
	}
}
