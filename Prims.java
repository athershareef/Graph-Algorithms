package mst;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Prims {
	static int[][] adj;
	static int distance[];
	static boolean visited[];
	static int source = 0;

	public static void main(String args[]) {
		adj = new int[][] { { 0, 6, 10, 0, 0, 0, 0, 0, 0, 0, }, { 6, 0, 12, 11, 14, 0, 0, 0, 0, 0, },
				{ 10, 12, 0, 12, 0, 0, 8, 16, 0, 0, }, { 0, 11, 12, 0, 0, 6, 3, 0, 0, 0, },
				{ 0, 14, 0, 0, 0, 4, 0, 0, 6, 0, }, { 0, 0, 0, 6, 4, 0, 0, 0, 12, 0, },
				{ 0, 0, 8, 3, 0, 0, 0, 0, 16, 6, }, { 0, 0, 16, 0, 0, 0, 0, 0, 0, 8, },
				{ 0, 0, 0, 0, 6, 12, 16, 0, 0, 13, }, { 0, 0, 0, 0, 0, 0, 6, 8, 13, 0 } };
		distance = new int[adj.length];
		visited = new boolean[adj.length];
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<Node> vertices = new PriorityQueue<>(adj.length, new Node());
		distance[source] = 0;
		vertices.add(new Node(source, 0));
		while (!vertices.isEmpty()) {
			Node start = vertices.remove();
			visited[start.getNode()] = true;
			for (int i = 0; i < adj.length; i++) {
				if (!visited[i]) {
					if (adj[start.getNode()][i] != 0) {
						int startdist = adj[start.getNode()][i];
						int newdist = startdist;
						if (newdist < distance[i]) {
							distance[i] = newdist;
						}
						vertices.add(new Node(i, distance[i]));
					}
				}
			}
		}
		System.out.println(Arrays.toString(distance));
		int mst_weight = 0;
		for (int each : distance) {
			mst_weight += each;
		}
		System.out.println(mst_weight);
	}

}

class Node implements Comparator<Node> {

	public int node;
	public int d;

	@Override
	public int compare(Node n1, Node n2) {
		if (n1.d < n2.d) {
			return -1;
		}
		if (n1.d > n2.d) {
			return 1;
		}
		return 0;
	}

	public Node() {
		super();
	}

	public Node(int node, int d) {
		super();
		this.setNode(node);
		this.d = d;
	}

	public int getNode() {
		return node;
	}

	public void setNode(int node) {
		this.node = node;
	}

}