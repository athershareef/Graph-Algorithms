package djk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
	static int[][] adj;
	static int distance[];
	static boolean visited[];
	static int source = 0;
	static int path[];
	static int V;

	public static void main(String args[]) {
		adj = new int[][] { { 0, 0, 1, 11 }, { 0, 0, 1, 1 }, { 1, 1, 0, 5 }, { 11, 1, 5, 0 } };
		V = adj.length;
		path = new int[adj.length];
		distance = new int[adj.length];
		visited = new boolean[adj.length];
		Arrays.fill(distance, Integer.MAX_VALUE);
		Arrays.fill(path, -1);
		PriorityQueue<Node> vertices = new PriorityQueue<>();
		distance[source] = 0;
		vertices.add(new Node(source, 0));
		ArrayList<ArrayList<Integer>> print = new ArrayList<>();
		int parent[] = new int[adj.length];
		for (int i = 0; i < adj.length; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(source);
			print.add(temp);
		}
		Arrays.fill(parent, -1);
		while (!vertices.isEmpty()) {
			Node start = vertices.remove();
			visited[start.getNode()] = true;
			for (int i = 0; i < adj.length; i++) {
				if (!visited[i]) {
					if (adj[start.getNode()][i] != 0) {
						int startdist = adj[start.getNode()][i];
						int newdist = distance[start.getNode()] + startdist;
						if (newdist < distance[i]) {
							path[i] = newdist;
							distance[i] = newdist;
							parent[i] = start.getNode();
						}
						vertices.add(new Node(i, distance[i]));
					}
				}

			}
		}
		printSolution(distance, V, parent);

	}

	static void printPath(int parent[], int j) {
		// Base Case : If j is source
		if (parent[j] == -1)
			return;

		printPath(parent, parent[j]);

		System.out.printf("%d ", j);

	}

	static // A utility function to print the constructed distance
	void printSolution(int dist[], int n, int parent[]) {
		int src = 0;
		System.out.printf("Vertex\t  Distance\tPath");
		for (int i = 0; i < V; i++) {
			System.out.printf("\n%d -> %d \t %d\t\t%d ", src, i, dist[i], src);
			printPath(parent, i);
		}
	}

}

class Node implements Comparable<Node> {

	public int node;
	public int d;

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

	@Override
	public int compareTo(Node n2) {
		if (this.d < n2.d) {
			return -1;
		}
		if (this.d > n2.d) {
			return 1;
		}
		return 0;
	}

}