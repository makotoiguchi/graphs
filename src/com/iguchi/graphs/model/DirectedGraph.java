package com.iguchi.graphs.model;

import java.util.ArrayList;
import java.util.HashMap;

public class DirectedGraph {
	private HashMap<Character, ArrayList<Character>> adj = new HashMap<Character, ArrayList<Character>>();
	
	public void addEdge(char s, char t) {
		if (!adj.containsKey(s)) {
			adj.put(s, new ArrayList<Character>());
		}
		adj.get(s).add(t);
	}
	
	public ArrayList<Character> getVertex() {
		return new ArrayList<Character>(adj.keySet());
	}

	public ArrayList<Character> getVertexChildren(char c) {
		return adj.containsKey(c)? adj.get(c) : new ArrayList<Character>();
	}
}
