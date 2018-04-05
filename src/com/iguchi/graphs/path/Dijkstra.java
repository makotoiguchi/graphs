package com.iguchi.graphs.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.iguchi.graphs.model.Edge;
import com.iguchi.graphs.model.MinBinaryHeap;
import com.iguchi.graphs.model.Vertex;
import com.iguchi.graphs.model.WeightedGraph;

public class Dijkstra {

	private static ArrayList<Character> findShortestPath(WeightedGraph graph, char s, char e) {
		ArrayList<Character> allVertex = graph.getAllVertex();
		MinBinaryHeap minHeap = new MinBinaryHeap(allVertex.size());
		HashMap<Character, Character> parents = new HashMap<Character, Character>();
		HashMap<Character, Integer> distances = new HashMap<Character, Integer>();
		
		for(char key : allVertex) {
			minHeap.insert(new Vertex(key, Integer.MAX_VALUE));
		}
		minHeap.decreaseKey(s, 0);
		parents.put(s, ' ');
		
		//
		while (minHeap.size() > 0) {
			Vertex min = minHeap.extractMin();
			distances.put(min.getKey(), min.getValue());

			if (min.getKey() == e)
				break;
			ArrayList<Edge> nextList = graph.getAllEdges(min.getKey());
			for (Edge edge : nextList) {
				char vNext = edge.getV2();
				if (minHeap.containsKey(vNext)) {
					if (minHeap.decreaseKey(vNext, min.getValue() + edge.getWeight())) {
						parents.put(vNext, min.getKey());
					}
				}
			}
		}
		//
		
		ArrayList<Character> list = new ArrayList<Character>();
		char c = e;
		while (c != ' ') {
			list.add(c);
			c = parents.get(c);
		}
		Collections.reverse(list);
		return list;
	}
	
	public static void main(String[] args) {
		WeightedGraph graph = new WeightedGraph();
		
		graph.addEdge('a', 'b', 5);
		graph.addEdge('a', 'd', 9);
		graph.addEdge('a', 'e', 2);
		graph.addEdge('b', 'c', 2);
		graph.addEdge('e', 'f', 3);
		graph.addEdge('c', 'd', 3);
		graph.addEdge('d', 'f', 2);
		
		print(findShortestPath(graph, 'a', 'b'));
		print(findShortestPath(graph, 'a', 'c'));
		print(findShortestPath(graph, 'a', 'd'));
		print(findShortestPath(graph, 'a', 'e'));
		print(findShortestPath(graph, 'a', 'f'));
	}

	private static void print(ArrayList<Character> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + (i+1 < list.size()? " -> " : ""));
		}
		System.out.println();
	}

}
