package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Graph {
	private final int V;
	private int E;
	private ArrayList<Integer>[] adj;
	
	public Graph(int V){
		this.V = V;
		adj = new ArrayList[V+1];
		for(int v = 0; v <= V; v++){
			adj[v] = new ArrayList<Integer>();
		}
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	public Graph(Scanner sc){
		System.out.println("Input the number of vertex:");
		this.V = sc.nextInt();
		adj = new ArrayList[V+1];
		for(int v = 0; v <= V; v++){
			adj[v] = new ArrayList<Integer>();
		}
		System.out.println("Input the number of edges:");
		int E = sc.nextInt();
		for(int i = 0; i < E; i++){
			int v = sc.nextInt();
			int w = sc.nextInt();
			addEdge(v, w);
			System.out.println("Insert finish");
		}
	}
	
	public void printGraph(){
		System.out.println("The number of vertex: " + V());
		System.out.println("The number of edges: "+ E());
		for(int i = 0; i <= V; i++){
			System.out.print(i+": ");
			ArrayList<Integer> arr = adj[i];
			for(int j: arr){
					System.out.print(j+" ");
			}
			System.out.println();
		}
	}
	
	public void BFS(){
		Queue<Integer> queue = new LinkedList<Integer>();
		ArrayList<Integer> array = new ArrayList<Integer>();
		int firstNode = 1;
		queue.offer(firstNode);
		while(!queue.isEmpty()){
			int dequeue = queue.poll();
			array.add(dequeue);
			for(int i: adj[dequeue]){
				if(!array.contains(i) && !queue.contains(i)){
					queue.offer(i);
				}
			}
		}
		for(int i: array){
			System.out.print(i+" ");
		}
	}
	
	public void DFS(){
		Stack<Integer> stack = new Stack<Integer>();
		ArrayList<Integer> array = new ArrayList<Integer>();
		boolean flag = false;
		int firstNode = 1;
		stack.push(firstNode);
		while(!stack.isEmpty()){
			int peek = stack.peek();
			if(!array.contains(peek)){
				array.add(peek);
			}
			for(int i: adj[peek]){
				if(!array.contains(i)){
					stack.push(i);
					flag = true;
				}
				if(flag == true){
					break;
				}
			}
			if(flag == false){
				stack.pop();
			}
			flag = false;
		}
		for(int i: array){
			System.out.print(i+" ");
		}
	}
}
