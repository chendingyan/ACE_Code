package graph;

import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Graph graph = new Graph(new Scanner(System.in));
		graph.printGraph();
		graph.BFS();
		graph.DFS();
	}
}
