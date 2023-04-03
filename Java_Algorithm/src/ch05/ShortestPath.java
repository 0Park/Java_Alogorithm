package ch05;

class MyGraph{
	private int count; // 노드 수
	private int[][] vertexMatrix;  // matrix로 그래프 표시
	private int[] distance;    // 특정 노드에 대한 각 노드의 최단거리
	private boolean[] visited;  // visited 된지에 대한 여부를 알려주는 matrix
	private static int UNLIMIT = 99999999;    // distance의 초기값
	
	public MyGraph(int count){
		this.count = count;
		vertexMatrix = new int[count][count];
		distance = new int[count];
		visited = new boolean[count];
	}
	
	public void addEdges(int from, int to, int weight) {
		vertexMatrix[from][to] = weight;
		vertexMatrix[to][from] = weight;
	}
	
	public void calcShortestPath(int from) {
//		distance 초기화
		for(int i=0;i<count;i++) {
			distance[i] = UNLIMIT;
		}
		
		visited[from] = true;
		distance[from] = 0;
		
//		from(출발점)과 연결된 노드의 distance 갱신
		for(int i=0; i < count; i++) {
//			from을 방문했고 from과 i node가 연결되어 있다면
			if(visited[from] && vertexMatrix[from][i] !=0) {
				distance[i] = vertexMatrix[from][i];
			}
		}
		
		for(int k=0; k<count-1; k++) {
			int min=UNLIMIT;
			int minIndex = -1;
//			선택된 노드에서 연결된 노드 중 distance의 길이가 가장 작은 것을 고르는 작업
			for(int i=0; i < count; i++) {
				if(!visited[i] && distance[i] != UNLIMIT) {
					if(distance[i]<min) {
						min = distance[i];
						minIndex=i;
					}
				}
			}
//			distance가 가장 작은 노드 방문(해당 노드를a라고 가정)
			visited[minIndex] = true;
			for(int i=0; i<count;i++) {
//				a 노드에서 연결된 노드들의(방문하지 않은 노드) 거리를 계산한다. 
				if(!visited[i] && vertexMatrix[minIndex][i]!=0){
					if(distance[i] > distance[minIndex]+vertexMatrix[minIndex][i]) {
						distance[i] = distance[minIndex] + vertexMatrix[minIndex][i];
					}
				}
			}
			
		}
		
	}
	
	public void showDistance(int from) {
		for(int i=0; i<count; i++) {
			System.out.println(from+" 노드로부터 "+i+" 노드의 최단 거리는 : "+ distance[i]);
		}
	}
}

public class ShortestPath {

	public static void main(String[] args) {
		MyGraph graph = new MyGraph(6);
		
		graph.addEdges(0, 1, 1);
		graph.addEdges(0, 2, 4);
		graph.addEdges(1, 2, 2);
		graph.addEdges(2, 3, 1);
		graph.addEdges(3, 4, 8);
		graph.addEdges(3, 5, 3);
		graph.addEdges(4, 5, 4);

		graph.calcShortestPath(3);
		graph.showDistance(3);

	}

}
