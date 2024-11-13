import java.util.*;
import java.io.*;

public class Main {
	static int[] dx = { -1, 0, 1, 0, 0, 0 };
	static int[] dy = { 0, 1, 0, -1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, -1, 1 };
	static int M, N, H;
	static int[][][] tomatos;
	static boolean[][][] visited;
	static Queue<int[]> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken()); // 상자 가로 칸
		N = Integer.parseInt(st.nextToken()); // 상자 세로 칸
		H = Integer.parseInt(st.nextToken());

		tomatos = new int[N][M][H];
		visited = new boolean[N][M][H];

		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < M; m++) {
					tomatos[n][m][h] = Integer.parseInt(st.nextToken());
					if (tomatos[n][m][h] == 1) {
						q.add(new int[] { n, m, h, 0 });
						visited[n][m][h] = true;
					}
				}
			}
		}

		int ans = bfs();
//		for (int h = 0; h < H; h++) {
//			for (int n = 0; n < N; n++) {
//				for (int m = 0; m < M; m++) {
//					System.out.print(tomatos[n][m][h]);
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		
		boolean isYetTomato = false;

		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if (tomatos[n][m][h] == 0) {
						isYetTomato = true;
					}
				}
			}
		}
		
		if(isYetTomato) System.out.println(-1);
		else System.out.println(ans);
	}

	public static int bfs() {
		int longestDay = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			int cz = curr[2];
			int days = curr[3];
			longestDay = Math.max(days, longestDay);

			for (int d = 0; d < 6; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				int nz = cz + dz[d];

				// 익지 않은 토마토 익히기
				if (isInBound(nx, ny, nz) && !visited[nx][ny][nz] && tomatos[nx][ny][nz] == 0) {
					q.add(new int[] { nx, ny, nz, days + 1 });
					tomatos[nx][ny][nz] = 1;
					visited[nx][ny][nz] = true;
				}
			}
		}
		return longestDay;
	}

	public static boolean isInBound(int x, int y, int z) {
		return 0 <= x && x < N && 0 <= y && y < M && 0 <= z && z < H;
	}
}
