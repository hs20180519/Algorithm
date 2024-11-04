
import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static ArrayList<Integer>[] graph;
	static int minDistance = Integer.MAX_VALUE;
	static int x = Integer.MAX_VALUE;
	static int y = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 건물의 개수 (정점)
		int M = Integer.parseInt(st.nextToken()); // 도로의 개수 (간선)
		graph = new ArrayList[N + 1];

		for (int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		 // 두 개 조합 선택
		 chooseTwo(0, 1, new int[2]);
		
		 System.out.println(x+" " + y + " " +minDistance);
//		int[] ans = {1, 2};
//		
//		for(int i=1; i<N+1; i++) {
//			boolean[] visited = new boolean[N+1];
//			System.out.println(bfs(i,visited, 1, 2));
//		}

	}

	public static void chooseTwo(int cnt, int startIndex, int[] answers) {
		if (cnt == 2) {
			// 모든 도시에서의 왕복 시간의 합 구하기
			int currentDistance = caculateAllDistance(answers);
		
			if(minDistance > currentDistance) {
				minDistance = currentDistance;
				x = answers[0];
				y = answers[1];
			}
			else if(minDistance == currentDistance) {
				if(x > answers[0]) {
					x = answers[0];
					y = answers[1];
				}else if(x == answers[0]) {
					y = Integer.min(answers[1], y);
				}
			}
			// System.out.println(x + " " + y);
			return;
		}

		for (int i = startIndex; i <= N; i++) {
			answers[cnt] = i;
			chooseTwo(cnt + 1, i + 1, answers);
		}
	}

	public static int caculateAllDistance(int[] answers) {
		int a = answers[0]; // 치킨집 위치
		int b = answers[1];
		int dist = 0;
	
		for (int i = 1; i < N + 1; i++) { // 1번부터 치킨집 위치까지의 최단 거리 찾음
			boolean[] visited = new boolean[N+1];
			// System.out.println(bfs(i, visited, a, b));
			dist += bfs(i,visited,a, b);
		}
		// System.out.println("토탈거리" + dist);
		return dist;
	}
	public static int bfs(int v, boolean[] visited, int a, int b) {
		Queue<int[]> q = new LinkedList<>();
		visited[v] = true; // 방문
		
		q.add(new int[] {v, 0});
		if(v == a || v == b) {
			return 0;
		}
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int c = curr[0];
			int dist = curr[1]+1;
			
			for(int k : graph[c]) {
				if(k == a || k == b) {
					// 종료
					return dist*2;
				}
				
				if(!visited[k]) {
					visited[k] = true;
					q.add(new int[] {k, dist+1});
				}
			}
		}
		return -1;
	}
	

}
