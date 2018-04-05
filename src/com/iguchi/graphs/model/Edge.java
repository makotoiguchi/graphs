package com.iguchi.graphs.model;

public class Edge implements Comparable<Edge> {
	private char v1;
	private char v2;
	private int weight;

	public Edge(char v1, char v2, int weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}

	public char getV1() {
		return v1;
	}

	public void setV1(char v1) {
		this.v1 = v1;
	}

	public char getV2() {
		return v2;
	}

	public void setV2(char v2) {
		this.v2 = v2;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge e) {
		return this.weight - e.getWeight();
	}

}
