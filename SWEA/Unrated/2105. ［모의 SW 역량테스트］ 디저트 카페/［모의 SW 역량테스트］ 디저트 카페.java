import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {
	
	/*
	 * 
	 * [문제 풀이를 위한 아이디어]
	 * 대각선의 방향은 순서대로 설정한다.
	 * 먹었던 디저트인지 판별하기 위해 hashset에 디저트를 저장한다.
	 * 
	 * dfs
	 * 만약, 범위 밖이면 return 한다.
	 * 만약, depth가 0보다 크고 시작점에 도착하면 최대 디저트 수를 갱신한다.
	 * 만약 이미 먹은 디저트인 경우 return 한다.
	 * 
	 * 모든 조건을 통과한 후 디저트를 먹어 디저트 배열에 add한다.
	 * 진행 방향과 같은 방향으로 계속 진행한다.(dfs)
	 * 그 다음 바로 옆 방향으로 다시 진행한다.(dfs+1)
	 * 
	 * [느낀점]
	 * 알다가도 모르겠는 dfs
	 * 
	 */
	
	
	static int[][] cafe;
	static int N;
	static final int[] dx = { 1, 1, -1, -1 }; // 대각선 방향
	static final int[] dy = { 1, -1, -1, 1 };
	static HashSet<Integer> eatenDesserts; // 먹은 디저트를 저장하는 집합
	static int maxDessert;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/d0827/input_SWEA_2105"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 디저트 카페 크기
			cafe = new int[N][N]; // 디저트 카페가 모여있는 곳

			for (int i = 0; i < N; i++) { // 디저트 카페 입력
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					cafe[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			maxDessert = -1; // 최대 디저트 수 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					eatenDesserts = new HashSet<>(); // 먹은 디저트 초기화
					dfs(0, 0, i, j, i, j); // DFS 탐색 시작
				}
			}
			System.out.printf("#%d %d\n", tc, maxDessert);

		}

	}

	public static void dfs(int depth, int d, int x, int y, int startX, int startY) {// int d) {
		// 범위 밖이거나
		if (!isInBounds(x, y))
			return;

		// 시작점이면서 길이 2 이상일 때
		if (depth > 0 && x == startX && y == startY) {
			maxDessert = Math.max(maxDessert, depth);
			return;
		}

		// 이미 먹은 디저트인 경우
		if (eatenDesserts.contains(cafe[x][y])) {
			return;
		}

		// 먹기
		eatenDesserts.add(cafe[x][y]);

		// 진행 방향과 같은 방향으로 계속 이동
		dfs(depth + 1, d, x + dx[d], y + dy[d], startX, startY);

		// 다음 방향으로 전환
		if (d + 1 < 4)
			dfs(depth + 1, d + 1, x + dx[d + 1], y + dy[d + 1], startX, startY);

		// 먹기 취소
		eatenDesserts.remove(cafe[x][y]);
	}

	// 범위 check
	public static boolean isInBounds(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

}