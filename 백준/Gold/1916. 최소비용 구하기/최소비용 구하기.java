
import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
	int end, cost;
	
	public Node(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.cost, o.cost);
	}
}


public class Main {
	// 다익스트라 : 한 점에서 시작해 다른 정점까지의 최단 경로 찾는 알고리즘
	// 해당 가중치가 "양의 정수"일 때만 가능
	// 음의 가중치 존재시 벨만포드 알고리즘
	// 모든 정점이 시작점인 경우 플로이드-와샬 알고리즘
	
	static ArrayList<Node>[] list;
	static int[] dist;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		list = new ArrayList[N+1];
		dist = new int[N+1];
		visited = new boolean[N+1];

		for(int i=1; i<= N; i++) {
			list[i] = new ArrayList<>();
		}
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[s].add(new Node(e, cost));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start);
		System.out.println(dist[end]);
		
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int curr = node.end;
			
			if(visited[curr]) continue;
			visited[curr] = true;
			
			for(Node n: list[curr]) {
				if(!visited[n.end] && dist[n.end] > dist[curr] + n.cost) {
					dist[n.end] = dist[curr] + n.cost;
					pq.add(new Node(n.end, dist[n.end]));
				}
			}
			
		}
	}

}
