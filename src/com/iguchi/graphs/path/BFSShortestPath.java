package com.iguchi.graphs.path;

import java.util.ArrayList;
import java.util.HashMap;

import com.iguchi.graphs.model.Edge;
import com.iguchi.graphs.model.WeightedGraph;

public class BFSShortestPath {
	
	private static ArrayList<Character> shortestPath(WeightedGraph graph, char s, char t) {
		
		char[] next = new char[graph.getAllVertex().size()];
		int iNext = 0;
		int sizeNext = 0;
		
		HashMap<Character, Character> parents = new HashMap<Character,Character>();
		
		next[sizeNext++] = s;
		parents.put(s, ' ');
		char v = ' ';
		
		while(iNext < sizeNext) {
			v = next[iNext++];
			for (Edge e : graph.getAllEdges(v)) {
				char n = e.getV2();
				if (!parents.containsKey(n)) {
					parents.put(n, v);
					if (n == t)
						break;
					next[sizeNext++] = n;
				}
			}
		}
		
		ArrayList<Character> resp = new ArrayList<Character>();
		char c = t;
		while (parents.containsKey(c)) {
			resp.add(0, c);
			c = parents.get(c);
		}
		
		return resp;
	}

	public static void main(String[] args) {
		WeightedGraph graph = new WeightedGraph();
		
		graph.addEdge('A', 'B', 0);
		graph.addEdge('A', 'C', 0);
		graph.addEdge('A', 'I', 0);
		graph.addEdge('B', 'C', 0);
		graph.addEdge('C', 'I', 0);
		graph.addEdge('C', 'D', 0);
		graph.addEdge('D', 'E', 0);
		graph.addEdge('D', 'F', 0);
		graph.addEdge('F', 'G', 0);
		graph.addEdge('G', 'H', 0);
		graph.addEdge('H', 'I', 0);

		System.out.println(shortestPath(graph, 'A', 'A'));
		System.out.println(shortestPath(graph, 'A', 'B'));
		System.out.println(shortestPath(graph, 'A', 'C'));
		System.out.println(shortestPath(graph, 'A', 'D'));
		System.out.println(shortestPath(graph, 'A', 'E'));
		System.out.println(shortestPath(graph, 'A', 'F'));
		System.out.println(shortestPath(graph, 'A', 'G'));
		System.out.println(shortestPath(graph, 'A', 'H'));
		System.out.println(shortestPath(graph, 'A', 'I'));
		System.out.println(shortestPath(graph, 'A', 'J'));
	}

}

