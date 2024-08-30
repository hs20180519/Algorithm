import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * [문제 풀이를 위한 아이디어] 
	 * 1. MST -> 프림
	 * 2. 인접 행렬을 우선순위 큐가 담긴 리스트로 초기화 
	 * 3. 우선순위 큐는 cost가 작은 순서대로 정렬되어 있음
	 * 4. 임의의 점부터 시작하여 해당 정점의 간선들을 모두 pq에 넣음
	 * 5. pq에서 차례대로 꺼내서 방문하지 않았다면 방문처리, 사용한 간선 수 증가
	 * -> 해당 노드와 연결된 최소 비용 간선을 선택한 것임
	 * 6. 이를 사용한 간선이 V-1개가 될 때까지 반복함
	 * 
	 */

	static int V;
	static int E;
	static boolean[] visited;
	static PriorityQueue<Edge> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 정점의 개수
			E = Integer.parseInt(st.nextToken()); // 간선의 개수

			PriorityQueue<Edge>[] adjList = new PriorityQueue[V + 1];

			// 인접 행렬 초기화
			for (int i = 1; i <= V; i++) {
				adjList[i] = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost,  o2.cost));
			}

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				adjList[a].offer(new Edge(b, c));
				adjList[b].offer(new Edge(a, c));
			}

			// [ 프림 알고리즘 ]
			// 초기화
			visited = new boolean[V + 1];
			pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost,  o2.cost));

			// 임의의 점부터 시작, 1부터
			visited[1] = true;
			pq.addAll(adjList[1]);

			long ans = 0;
			int edgesUsed = 0;

			while (!pq.isEmpty() && edgesUsed < V - 1) {
				Edge current = pq.poll();

				if (visited[current.to])
					continue; // 방문했던 정점 무시

				// 최소 스패닝 트리에 정점 추가
				visited[current.to] = true;
				ans += current.cost;
				edgesUsed++;

				// pq에 이 정점과 인접한 모든 간선들을 추가
				pq.addAll(adjList[current.to]);

			}

			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static class Edge{
		int to, cost;

		public Edge(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}
	}

}
