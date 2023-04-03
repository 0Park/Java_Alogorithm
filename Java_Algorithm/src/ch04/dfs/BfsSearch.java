package ch04.dfs;

import java.util.ArrayList;

import ch04.graph.UndirectedGraph;

public class BfsSearch {
	int count;
	boolean[] visited;
	ArrayList<Integer> queue;
	int[][] matrix;
	
	public BfsSearch(int count) {
		this.count=count;
		visited = new boolean[count];
		queue = new ArrayList<Integer>();
	}
	
	public void bfsTraversal() {
		queue.add(0);
		visited[0] =true;
		while(queue.size() != 0 ) {
			int node = queue.remove(0);
			System.out.print(node + " ");
			
			for(int i=0; i < count;i++) {
				if(matrix[node][i] !=0 && visited[i]==false) {
					queue.add(i);
					visited[i]=true;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int count = 8;
		UndirectedGraph graph = new UndirectedGraph(count);
		
		graph.addEdges(0, 1, 1);
		graph.addEdges(0, 2, 1);
		graph.addEdges(1, 3, 1);
		graph.addEdges(1, 4, 1);
		graph.addEdges(2, 5, 1);
		graph.addEdges(2, 6, 1);
		graph.addEdges(3, 7, 1);
		
		BfsSearch bfs = new BfsSearch(count);
		bfs.matrix = graph.getMatrix();
		bfs.bfsTraversal();
	}
}
