import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	/*
	 * [문제 풀이를 위한 아이디어]
	 * 주변에 자기보다 1이 작은 좌표가 없다면 dfs로 탐색한다.
	 * 탐색중에 visited와 maxLength를 갱신한다.
	 * 
	 * 
	 * [난이도]
	 * bfs는 엄청나게 익숙한데 dfs로 풀려니까 어색하다
	 * dfs로도 많이 풀어봐야지 !
	 * 
	 * 
	 */
	static int N;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] rooms;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 방의 크기
			rooms = new int[N][N]; // 방

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			visited = new boolean[N][N];
			int maxRoom = 0; // 최대 이동할 수 있는 방
			int startRoom = Integer.MAX_VALUE; // 시작 방 번호
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!hasSmallerNeighbor(i, j) && !visited[i][j]) { // 주변에 더 작은 게 없고, 방문한적 없다면
						int pathLength = dfs(i, j);

						if (pathLength > maxRoom) { // 최대 이동할 수 있는 방보다 dfs값이 더 크다면
							maxRoom = pathLength; // 최대 이동할 수 있는 방 갱신
							startRoom = rooms[i][j]; // 시작 방 번호 갱신
						} else if (pathLength == maxRoom && startRoom > rooms[i][j]) { // 최대 이동할 수 있는 방과 dfs값이 같은데, 방 번호가 더 작으면
							startRoom = rooms[i][j]; // 시작 방 번호 갱신
						}
					}
					continue;

				}
			}
			sb.append("#").append(tc).append(" ").append(startRoom).append(" ").append(maxRoom).append("\n");
		}
		System.out.println(sb);
	}

	public static int dfs(int x, int y) {
		visited[x][y] = true; // 방문
		int maxLength = 1;

		for (int d = 0; d < 4; d++) { // 사방탐색
			int nx = x + dx[d];
			int ny = y + dy[d];

			// 범위 내이고, 방문한 적 없고, 기존 방 번호보다 하나 더 크다면
			// 현재까지 계산된 경로의 최대 길이와 새로 계산된 경로 길이 중 더 큰 값을 갱신한다.
			if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
				if (rooms[nx][ny] == rooms[x][y] + 1) {
					maxLength = Math.max(maxLength, 1 + dfs(nx, ny));
				}
			}
		}
		return maxLength;
	}

	// 주변에 자신보다 1 더 작은 방이 있는 지 확인
	// 있으면 true
	// 없으면 false를 반환한다.
	public static boolean hasSmallerNeighbor(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (0 <= nx && nx < N && 0 <= ny && ny < N) {
				if (rooms[nx][ny] == rooms[x][y] - 1) {
					return true;
				}
			}
		}
		return false;
	}
}
