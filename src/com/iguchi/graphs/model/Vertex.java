package com.iguchi.graphs.model;

public class Vertex implements Comparable<Vertex> {
	private char key;
	private int value;
	
	public Vertex() {
	}
	
	public Vertex(char key) {
		this.key = key;
	}

	public Vertex(char key, int value) {
		this.key = key;
		this.value = value;
	}

	public char getKey() {
		return key;
	}
	
	public void setKey(char key) {
		this.key = key;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(Vertex v) {
		return (this.value - v.getValue());
	}

	@Override
	public String toString() {
		return this.key+"-"+this.value;
	}
	
}
