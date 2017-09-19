package mst;

import java.util.ArrayList;

public class GraphMain {
	private int[][] adjM;
	private int[] weights;
	private boolean[] visited;
	private int[] parent;

	public static void main(String[] args) {
		
		ArrayList<ArrayList<String>> mywholeList=new ArrayList<ArrayList<String>>();

		int[][] adjM = { { 0, 6, 10, 0, 0, 0, 0, 0, 0, 0 }, { 6, 0, 12, 11, 14, 0, 0, 0, 0, 0 },
				{ 10, 12, 0, 12, 0, 0, 8, 16, 0, 0 }, { 0, 11, 12, 0, 0, 6, 3, 0, 0, 0 },
				{ 0, 14, 0, 0, 0, 4, 0, 0, 6, 0 }, { 0, 0, 0, 6, 4, 0, 0, 0, 12, 0 }, { 0, 0, 8, 3, 0, 0, 0, 0, 16, 6 },
				{ 0, 0, 16, 0, 0, 0, 0, 0, 0, 8 }, { 0, 0, 0, 0, 6, 12, 16, 0, 0, 13 },
				{ 0, 0, 0, 0, 0, 0, 6, 8, 13, 0 } };

		int[][] adjMDir = { { 0, 6, 10, 0, 0, 0, 0, 0, 0, 0 }, { -6, 0, 12, 11, 14, 0, 0, 0, 0, 0 },
				{ -10, -12, 0, 12, 0, 0, 8, 16, 0, 0 }, { 0, -11, -12, 0, 0, 6, 3, 0, 0, 0 },
				{ 0, -14, 0, 0, 0, 4, 0, 0, 6, 0 }, { 0, 0, 0, -6, -4, 0, 0, 0, 12, 0 },
				{ 0, 0, -8, -3, 0, 0, 0, 0, 16, 6 }, { 0, 0, -16, 0, 0, 0, 0, 0, 0, 8 },
				{ 0, 0, 0, 0, -6, -12, -16, 0, 0, 13 }, { 0, 0, 0, 0, 0, 0, -6, -8, -13, 0 } };

		GraphMain g = new GraphMain(adjM);
		System.out.println("The Undirected Graph given is");
		g.printGraph();
		System.out.println("\nPerforming Prim Algo on undirected graph\n");
		g.priMST(adjM);
		
		GraphMain d = new GraphMain(adjMDir);
		d.setAdjM(adjMDir);
		System.out.println("\nThe Directed Graph given is");
		d.printGraph();
		System.out.println("\nPerforming Prim Algo on Directed graph\n");
		d.priMST(adjMDir);

	}

	private void printGraph() {
		for (int i = 0; i < adjM[0].length; i++) {
			for (int j = i; j < adjM.length; j++) {
				if (adjM[i][j] > 0) {
					System.out.println("Edge " + i + " - " + j + " \t" + adjM[i][j]);
				}
				if (adjM[i][j] < 0) {
					System.out.println("Edge " + j + " - " + i + " \t" + adjM[j][i]);
				}
			}
		}
	}

	private void priMST(int[][] adjM) {

		for (int i = 0; i < adjM.length; i++) {
			int min = minVertex(weights, visited);
			visited[min] = true;

			for (int j = 0; j < adjM.length; j++) {

				if (adjM[min][j] > 0 && adjM[min][j] < weights[j] && visited[j] == false) {
					parent[j] = min;
					weights[j] = adjM[min][j];
				}

			}
		}
		printMST();

	}

	private void printMST() {
		for (int i = 1; i < adjM[0].length; i++) {
			System.out.println("Edge " + parent[i] + " - " + i + " \t" + Math.abs(adjM[i][parent[i]]));
		}
	}

	private int minVertex(int[] weights, boolean[] visited) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for (int i = 0; i < adjM.length; i++) {
			if (visited[i] == false) {
				if (weights[i] < min) {
					min = weights[i];
					minIndex = i;
				}
			}
		}
		return minIndex;
	}

	public int[][] getAdjM() {
		return adjM;
	}

	public void setAdjM(int[][] adjM) {
		this.adjM = adjM;
	}

	public int[] getWeights() {
		return weights;
	}

	public void setWeights(int[] weights) {
		this.weights = weights;
	}

	public boolean[] getVisited() {
		return visited;
	}

	public void setVisited(boolean[] visited) {
		this.visited = visited;
	}

	public int[] getParent() {
		return parent;
	}

	public void setParent(int[] parent) {
		this.parent = parent;
	}

	public GraphMain(int[][] adjM) {
		super();
		this.adjM = adjM;
		// Initialization of values
		this.weights = new int[adjM.length];
		this.visited = new boolean[adjM.length];

		this.parent = new int[adjM.length];

		for (int i = 0; i < adjM.length; i++) {
			weights[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}
		weights[0] = 0;
		parent[0] = -1;
	}

}