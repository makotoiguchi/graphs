package com.iguchi.graphs.sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

import com.iguchi.graphs.model.DirectedGraph;

public class TopologicalSort {

	public static ArrayList<Character> sort(DirectedGraph graph) {
		ArrayList<Character> vertexList = graph.getVertex();
		HashSet<Character> visited = new HashSet<Character>();
		Stack<Character> sorted = new Stack<Character>();
		
		for (char c : vertexList) {
			visit(graph, c, visited, sorted);
		}
		
		ArrayList<Character> sortedList = new ArrayList<Character>(); 
		while(!sorted.isEmpty()) {
			sortedList.add(sorted.pop());
		}
		return sortedList;
	}

	
	private static void visit(DirectedGraph graph, char c, HashSet<Character> visited, Stack<Character> sorted) {
		if (!visited.contains(c)) {
			visited.add(c);
			
			for (char child : graph.getVertexChildren(c)) {
				visit(graph, child, visited, sorted);
			}
			
			sorted.push(c);
		}
	}


	public static void main(String[] args) {
		DirectedGraph graph = new DirectedGraph();
		graph.addEdge('b', 'c');
		graph.addEdge('f', 'g');
		graph.addEdge('a', 'b');
		graph.addEdge('c', 'd');
		graph.addEdge('d', 'f');
		graph.addEdge('b', 'e');
		graph.addEdge('e', 'f');
		graph.addEdge('d', 'e');
		graph.addEdge('b', 'g');
		
		ArrayList<Character> list = sort(graph);
		
		for (char c : list) {
			System.out.println(c);
		}
	}

}
