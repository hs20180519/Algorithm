import java.io.*;
import java.util.*;

public class Solution {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// 모든 물인 칸들(W)에서 땅(L)으로 된 것으로 bfs
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			char[][] map = new char[N][M];

			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}

			boolean[][] visited = new boolean[N][M];
			Queue<int[]> q = new LinkedList<>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 'W') {
						q.add(new int[] { i, j, 0 });
						visited[i][j] = true;
					}
				}
			}

			int totalDistance = 0;

			while (!q.isEmpty()) {
				int[] curr = q.poll();
				int cx = curr[0];
				int cy = curr[1];
				int cd = curr[2];

				if (map[cx][cy] == 'L') {
					totalDistance += cd;
				}

				for (int d = 0; d < 4; d++) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];

					if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
						q.add(new int[] { nx, ny, cd + 1 });
						visited[nx][ny] = true;
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(totalDistance).append("\n");
		}
		System.out.println(sb);
	}
}
