package com.iguchi.graphs.model;

import java.util.HashMap;

public class MinBinaryHeap {
	private int size = 0;
	private Vertex[] heap;
	private HashMap<Character, Integer> position;

	public MinBinaryHeap(int size) {
		heap = new Vertex[size];
		position = new HashMap<Character, Integer>(size);
	}
	
	public int size() {
		return size;
	}

	public void insert(Vertex v) {
		if (size < heap.length) {
			set(size, v);
			heapifyUp(size);
			size++;
		}
	}
	
	private void set(int index, Vertex v) {
		heap[index] = v;
		position.put(v.getKey(), index);
	}

	private void heapifyUp(int i) {
		int current = i;
		int parent = getParent(i);
		while (parent >= 0) {
			if (heap[current].compareTo(heap[parent]) < 0) {
				swap(current, parent);

				current = parent;
				parent = getParent(current);
			} else {
				break;
			}
		}
	}

	private void swap(int i, int j) {
		Vertex tmp = heap[j];
		set(j, heap[i]);
		set(i, tmp);
	}

	private int getParent(int i) {
		return i == 0 ? -1 : (i - 1)/2 ;
	}

	public Vertex getMin() {
		return heap[0]; 
	}

	public Vertex extractMin() {
		Vertex min = heap[0];
		size--;
		set(0, heap[size]);
		heapifyDown(0);
		position.remove(min.getKey());
		return min;
	}


	private void heapifyDown(int i) {
		int current = i;
		while (getLeft(current) < size) {
			int left = getLeft(current);
			int right = getRight(current);

			if ( (heap[current].compareTo(heap[left]) > 0) 
					&& (heap[current].compareTo(heap[right]) > 0) ) {
				if (heap[left].compareTo(heap[right]) <= 0) {
					swap(current, left);
					current = left;
				} else {
					swap(current, right);
					current = right;
				}
			} else if (heap[current].compareTo(heap[left]) > 0) {
				swap(current, left);
				current = left;
			} else if (heap[current].compareTo(heap[right]) > 0) {
				swap(current, right);
				current = right;
			} else {
				break;
			}
		}
	}

	private int getLeft(int i) {
		return 2*i + 1;
	}

	private int getRight(int i) {
		return 2*i + 2;
	}
	
	public boolean decreaseKey(char key, int newValue) {
		if (position.containsKey(key)) {
			int index = position.get(key);
			
			if (heap[index].getValue() > newValue) {
				heap[index].setValue(newValue);
				heapifyUp(index);
				return true;
			}
		}
		return false;
	}
	
	public boolean containsKey(char key) {
		return position.containsKey(key);
	}

	public void print() {
		int i = 0;
		int pow2 = 1;
		int limit = 1;
		System.out.println("-----BEGIN-----");
		while (i < size) {
			if (i == limit) {
				System.out.println();
				pow2 *= 2;
				limit += pow2;
			}
			System.out.print(heap[i] + " ");
			i++;
		}
		if (i > 0 && i <= limit) {
			System.out.println();
		}
		System.out.println("------END------");
		System.out.println();
	}

	public static void main(String[] args) {
		int size = 10;
		MinBinaryHeap heap = new MinBinaryHeap(size);
		
		System.out.println("INSERT:");
		
		heap.insert(new Vertex('A',11));
		heap.insert(new Vertex('B',10));
		heap.insert(new Vertex('C',15));
		heap.insert(new Vertex('D',8));
		heap.insert(new Vertex('E',7));
		heap.insert(new Vertex('F',5));
		heap.print();

		heap.insert(new Vertex('G',3));
		heap.print();

		System.out.println("DECREASE:");
		
		heap.decreaseKey('B', 4);
		heap.print();
		
		heap.decreaseKey('C', 1);
		heap.print();
		
		System.out.println("EXTRACT:");
		Vertex[] sorted = new Vertex[size];
		int count = 0;
		while (heap.size() > 0) {
			sorted[count++] = heap.extractMin();
			heap.print();
		}
		
		System.out.println("SORTED:");
		for (int i = 0; i < count; i++) {
			System.out.print(sorted[i] + ((i!=count-1)? " < " : ""));
		}
		System.out.println();
	}

}
