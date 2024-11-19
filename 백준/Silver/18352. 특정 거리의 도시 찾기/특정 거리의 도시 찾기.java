import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<int[]>[] edgeList;

	public static void main(String[] args) throws IOException {
		// start가 X니까 다익스트라
		// O(ElogV)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 도시의 개수
		int M = Integer.parseInt(st.nextToken()); // 도로의 개수
		int K = Integer.parseInt(st.nextToken()); // 최단 거리 정보
		int X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호
		edgeList = new ArrayList[N+1];
		
		for(int i=0; i<=N; i++) {
			edgeList[i] = new ArrayList<>();
		}
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// a부터 b도시로 이동하는 단방향 도로 존재
			edgeList[a].add(new int[] {b, 1});
			
		}
		dijkstra(X, N, K);
		
	}

	private static void dijkstra(int X, int N, int K) {
		int ans = 0;
		int[] dist = new int[N+1];
		for(int i=1; i<=N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[X] = 0; // 자기 자신 0
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
		// 거리 순으로 정렬
		pq.add(new int[] {X, 0});
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int currentNode = curr[0];
			for(int[] next : edgeList[currentNode]) {
				if(dist[next[0]] > dist[currentNode] + next[1]) {
					dist[next[0]] = dist[currentNode] + next[1];
					pq.add(new int[] {next[0], dist[next[0]]});
				}
			}
			
		}
		
		for(int i=1; i<=N; i++) {
			if(dist[i] == K) {
				System.out.println(i);
				ans++;
			}
		}
		if(ans == 0) {
			System.out.println(-1);
		}
	}
}
