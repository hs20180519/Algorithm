import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/*
	 * dfs로 마지막까지 도착할 때 count ++ -> N은 32까지 가능하므로 시간초과 
	 * 3차원의 dp를 만들어 방향마다 다르게 계산 후
	 * 합한다.
	 * 
	 */
	static int N;
	static int[][] map;
	static long[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 맵 크기
		map = new int[N][N];
		dp = new long[N][N][3]; // 0 : 가로, 1 : 세로, 2 : 대각선

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// (0,1) 위치에 가로로 놓여있음
		dp[0][1][0] = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if (map[i][j] == 1)
					continue; // 벽은 진행 불가

				// 가로 -> 가로
				if (j - 1 >= 0) {
					dp[i][j][0] += dp[i][j - 1][0];
					// 대각선 -> 가로
					dp[i][j][0] += dp[i][j - 1][2];
				}

				// 세로 -> 세로
				if (i - 1 >= 0) {
					dp[i][j][1] += dp[i - 1][j][1];
					// 대각선 -> 세로
					dp[i][j][1] += dp[i - 1][j][2];
				}

				// 가로 -> 대각선, 세로 -> 대각선, 대각선 -> 대각선
				if (i - 1 >= 0 && j - 1 >= 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) {
					dp[i][j][2] += dp[i - 1][j - 1][0];
					dp[i][j][2] += dp[i - 1][j - 1][1];
					dp[i][j][2] += dp[i - 1][j - 1][2];
				}

			}
			
		}
		long result = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
		System.out.println(result);
	}

}
