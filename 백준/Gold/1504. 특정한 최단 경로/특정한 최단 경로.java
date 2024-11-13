import java.util.*;
import java.io.*;

public class Main {
	static List<int[]>[] edgeList;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		edgeList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			edgeList[i] = new ArrayList<>();
		}
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edgeList[a].add(new int[] { b, c });
			edgeList[b].add(new int[] { a, c });
		}

		st = new StringTokenizer(br.readLine());
		int u = Integer.parseInt(st.nextToken()); // 반드시 지나야하는 정점
		int v = Integer.parseInt(st.nextToken());

		// 1부터 u까지 최단 거리 + u부터 v까지 최단 거리 + u부터 n까지 최단 거리
		// 1부터 v까지 최단 거리 + v부터 u까지 최단 거리 + v부터 n까지 최단 거리
		// 둘 중 최솟 값

		int a = dijkstra(1, u, N);
		int b = dijkstra(u, v, N);
		int c = dijkstra(v, N, N);

		int aCase = a + b + c;
		if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE || c == Integer.MAX_VALUE) {
			aCase = -1;
		}

		a = dijkstra(1, v, N);
		b = dijkstra(v, u, N);
		c = dijkstra(u, N, N);
		int bCase = a + b + c;
		if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE || c == Integer.MAX_VALUE) {
			bCase = -1;
		}

		if (aCase == -1) {
			if (bCase == -1) {
				System.out.println(-1);
			} else {
				System.out.println(bCase);
			}
		} else {
			if (bCase == -1) {
				System.out.println(aCase);
			} else {
				System.out.println(Math.min(aCase, bCase));
			}
		}
	}

	static public int dijkstra(int start, int end, int N) {

		dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));
		q.add(new int[] { start, 0 });
		dist[start] = 0;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int c = curr[0];
			int cost = curr[1];

			if (dist[c] < cost)
				continue;

			for (int[] next : edgeList[c]) {
				if (dist[next[0]] > dist[c] + next[1]) {
					dist[next[0]] = dist[c] + next[1];
					q.add(new int[] { next[0], dist[next[0]] });
				}
			}
		}
		return dist[end];
	}
}
