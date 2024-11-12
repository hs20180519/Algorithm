import java.util.*;
import java.io.*;

public class Main {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static char[][] map;
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][K + 1];

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		System.out.println(bfs(0, 0, N, M, K));
	}

	public static int bfs(int x, int y, int N, int M, int K) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y, 0, 0 }); // x, y, 벽 부순 개수, 이동 거리
		visited[x][y][0] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			int k = curr[2];
			int dist = curr[3];

			if (cx == N - 1 && cy == M - 1)
				return dist+1;

			for (int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];

				if (0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] - '0' == 0 && !visited[nx][ny][k]) {
					q.add(new int[] { nx, ny, k, dist + 1 });
					visited[nx][ny][k] = true;
				}

				if (k < K) {
					if (0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] - '0' != 0 && !visited[nx][ny][k + 1]) {
						q.add(new int[] { nx, ny, k + 1, dist + 1 });
						visited[nx][ny][k + 1] = true;
					}
				}
			}
		}
		return -1;
	}
}
