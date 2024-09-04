import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, K;
	static int[][] map;
	static ArrayList<int[]> highest;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int mountainPath;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/d0904/input_SWEA_1929.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도 한 변의 길이
			K = Integer.parseInt(st.nextToken()); // 최대 공사 가능 깊이
			map = new int[N][N];

			highest = new ArrayList<>();

			int highestHeight = 0; // 최대 봉우리 높이
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					highestHeight = Math.max(highestHeight, map[i][j]);
				}
			}

			for (int i = 0; i < N; i++) { // 최대 봉우리들 highest에 추가
				for (int j = 0; j < N; j++) {
					if (map[i][j] == highestHeight) {
						highest.add(new int[] { i, j });
					}
				}
			}
			
			mountainPath = 0;
			for (int[] point : highest) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						for (int k = 0; k <= K; k++) { 
							trimMountain(point[0], point[1], i, j, k);
							makeRoad(point[0], point[1], 1);	
							trimMountain(point[0], point[1], i, j, -1 *k);
						}
					}
				}
			}
			System.out.println("#"+tc+" "+mountainPath);
			}
	}

	// 봉우리가 startX, startY일때, 다른 좌표 하나를 k깊이 만큼 깎음
	public static void trimMountain(int startX, int startY, int goX, int goY, int k) {
		if (startX != goX || startY != goY) {
			map[goX][goY] = map[goX][goY] - k;
		}

	}

	// dfs
	public static void makeRoad(int x, int y, int count) {
		mountainPath = Math.max(mountainPath, count);

		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			// 범위 안에 있고, 방문하지 않았으며, 더 낮은 지형일 때
			if (isInBound(nx, ny) && map[x][y] > map[nx][ny]) {
				makeRoad(nx, ny, count + 1);
			}
		}
	}

	public static boolean isInBound(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}
