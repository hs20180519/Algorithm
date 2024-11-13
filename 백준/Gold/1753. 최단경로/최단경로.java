import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int vertex, weight;

		public Node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) { // weight 기준으로 정렬
			return this.weight - o.weight;
		}
	
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken()); // 정점
		int E = Integer.parseInt(st.nextToken()); // 간선
		int K = Integer.parseInt(br.readLine()); // 시작 정점
		
		// 그래프를 인접 리스트로 표현하기 위해 리스트 배열 생성
		List<List<Node>> graph = new ArrayList<>();
		for(int i=0; i<=V; i++) {
			graph.add(new ArrayList<>()); // 각 정점마다 인접 리스트를 초기화
		}
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, weight)); // 정점 u에서 v로 가는 가중치 weight의 간선을 그래프에 추가 
			// graph[1] = [(2,2), (3,3)] 
			// graph[2] = [(3,4), (4,5)] ...
		}
		
		// 다익스트라 알고리즘
		int[] dist = new int[V+1]; // 시작 정점으로부터 최단 거리를 저장하는 배열
		Arrays.fill(dist, Integer.MAX_VALUE); // 초기값을 무한대로 설정
		dist[K] = 0; // 시작 정점 K의 거리는 0으로 설정
		// dist = [INF, 0, INF, INF, INF, INF]
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(K, 0)); // 자기 자신까지의 거리는 0. 시작 정점을 큐에 추가
		
		while(!pq.isEmpty()) {
			Node current = pq.poll(); // 우선순위 큐에서 가장 짧은 거리를 가진 정점을 꺼냄
			int currentVertex = current.vertex; // 현재 정점의 번호
			int currentWeight = current.weight; // 현재 정점까지의 누적 가중치
			
			// 만약 현재 꺼낸 정점의 가중치가 저장된 최단 거리보다 크다면, 이미 처리된 것이므로 무시
			if(currentWeight > dist[currentVertex]) continue;
			
			// 현재 정점에 인접한 모든 정점을 탐색
			for(Node n : graph.get(currentVertex)) {
				int newDist = dist[currentVertex] + n.weight; // 새로운 경로를 통한 거리 계산
				// newDist = dist[0] + 2 => 2				
				// newDist = dist[0] + 3 => 3
				
				// 만약 새로운 경로가 기존 경로보다 짧다면, 최단 거리 갱신
				if(newDist < dist[n.vertex]) {
					dist[n.vertex] = newDist; // 최단 거리 배열 갱신
					pq.offer(new Node(n.vertex, newDist)); // 갱신된 정점을 우선순위 큐에 추가
				}
				// dist[2] = 2;
				// pqp에 (2, 2) 추가
				// dist[3] = 3;
				// pq에 (3, 3) 추가
				// dist = [INF, 0, 2, 3, INF, INF]

			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=V; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			}else {
				sb.append(dist[i]).append("\n");
			}
		}
		System.out.println(sb);
	}
}
