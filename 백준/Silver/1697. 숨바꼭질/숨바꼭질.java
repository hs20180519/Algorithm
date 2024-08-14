

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int K;
	private static int ans;
	private static boolean[] visited;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("./res/d0814/input_BOJ_1697"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		 N = Integer.parseInt(st.nextToken());
		 K = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];
		bfs(N);
	}
	private static void bfs(int x) {
		// {위치, 이동시간}
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x, 0});
		visited[x] = true;
		while(!q.isEmpty()) {
			int []current = q.poll();
			int distance = current[0];
			int time = current[1];
			
			
			if (distance == K) {
				System.out.println(time);
				break;
			}
			
			int[] nextPositions = {distance + 1, distance - 1, distance * 2};
			
			for (int next : nextPositions) {
                if (next >= 0 && next <= 100000 && !visited[next]) {
                    q.add(new int[] {next, time + 1});
                    visited[next] = true;
                }
            }
		}
	}
}

