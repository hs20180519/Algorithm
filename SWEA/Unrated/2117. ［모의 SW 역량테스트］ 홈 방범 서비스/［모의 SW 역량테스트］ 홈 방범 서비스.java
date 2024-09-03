import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 도시의 크기
			M = Integer.parseInt(st.nextToken()); // 하나의 집이 지불할 수 있는 비용

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 손해를 보지 않고 서비스 가능한 최대 집의 수

			int maxHouseCount = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					for(int k=1; k<=N+1; k++) {
						visited = new boolean[N][N];
						int houseCount = calculateHouse2(i, j, k);
						if(houseCount == 0) {
							continue;
						}
						int costs = calculateCost(k, houseCount);
						if (costs >= 0){
							maxHouseCount = Math.max(houseCount, maxHouseCount);
						}
					}
				}
			}
			System.out.println("#"+tc+" "+maxHouseCount);		
		}
	}

//	// (x, y)에서 bfs를 통해 k개 만큼 탐색하여 집의 개수를 센다.
//	public static int calculateHouse(int x, int y, int k) {
//		int houseCount = map[x][y] == 1 ? 1 : 0;
//		Queue<int[]> queue = new LinkedList<>();
//		queue.add(new int[] { x, y, 1 });
//		visited[x][y] = true;
//
//		while (!queue.isEmpty()) {
//			int[] curr = queue.poll();
//			int cx = curr[0];
//			int cy = curr[1];
//			int ck = curr[2];
//
//			if (ck >= k) {
//				break;
//			}
//
//			for (int d = 0; d < 4; d++) {
//				int nx = cx + dx[d];
//				int ny = cy + dy[d];
//
//				// 범위 안에 있고, 방문안했으며, 집이면
//				if (isInBound(nx, ny) && !visited[nx][ny]) {
//					visited[nx][ny] = true;
//					queue.add(new int[] { nx, ny, ck + 1 });
//					if (map[nx][ny] == 1) {
//						houseCount++;
//					}
//				}
//			}
//		}
//
//		return houseCount;
//	}
	public static int calculateHouse2(int x, int y, int k) {
		int houseCount = 0;
		
		int startX = x - k > 0? x-k : 0; 
		int endX = x + k < N ? x+k : N-1;
		int startY = y -k > 0? y-k : 0;
		int endY = y + k < N ? y+k : N-1;
		for(int i=startX; i<=endX; i++) {
			for(int j=startY; j<=endY; j++) {
				// 중심과의 거리가 k이하이고, 1이면 count++
				if(Math.abs(x-i)+Math.abs(y-j) < k) {
					visited[i][j] = true; // TODO : X
					if(map[i][j] == 1) {
						houseCount++;
					}
				}
			}
		}
		return houseCount;
	}

	public static boolean isInBound(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

	public static int calculateCost(int K, int houses) {
		int profit = houses * M; // 수익
		int ManageCost = K * K + (K - 1) * (K - 1); // 운영 비용
		return profit - ManageCost; // 수익 - 운영 비용 => 최종 수익
	}
}
