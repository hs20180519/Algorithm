
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

//	3 3
//	1
//	1 0
//	1 1
//	1 2 3 4

//	3 3
//	3
//	0 1
//	1 0
//	2 2
//	1 1
//	1 3 2 4

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken()); // 방의 크기
		int c = Integer.parseInt(st.nextToken());

		int[][] map = new int[r][c];
		boolean[][] visited = new boolean[r][c];

		// 장애물 개수
		int k = Integer.parseInt(br.readLine());
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());

			// 장애물 위치
			int brr = Integer.parseInt(st.nextToken());
			int bcc = Integer.parseInt(st.nextToken());

			map[brr][bcc] = 1;
		}

//		for(int[] m : map) {
//			System.out.println(Arrays.toString(m));
//		}

		// 시작 위치
		st = new StringTokenizer(br.readLine());
		int sr = Integer.parseInt(st.nextToken());
		int sc = Integer.parseInt(st.nextToken());

		int[] dx = new int[4]; // 1:위, 2:아래, 3:왼, 4:오
		int[] dy = new int[4];
		// 이동 방향의 순서
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {

			int d = Integer.parseInt(st.nextToken());

			switch (d) {
			case 1: // 위
				dx[i] = -1;
				dy[i] = 0;
				break;
			case 2: // 아래
				dx[i] = 1;
				dy[i] = 0;
				break;
			case 3: // 왼
				dx[i] = 0;
				dy[i] = -1;
				break;
			case 4: // 오
				dx[i] = 0;
				dy[i] = 1;
				break;
			}

		}

		int lastX = sr;
		int lastY = sc;

		// 로봇 이동

		// 현재 방향 유지

		int currentD = 0; // 현재 방향
		visited[sr][sc] = true;

		while (true) {

			boolean canMove = false;

			// 현재 방향으로 이동 시도
			for (int i = 0; i < 4; i++) {
				int nx = sr + dx[currentD];
				int ny = sc + dy[currentD];

				// 이동 가능하면
				if (0 <= nx && nx < r && 0 <= ny && ny < c && !visited[nx][ny] && map[nx][ny] != 1) {
					sr = nx;
					sc = ny;
					visited[sr][sc] = true;
					canMove = true;
					break;
				} else { // 이동 불가시
					// 방향 변경
					currentD = (currentD + 1) % 4;
				}

				// 모든 방향 시도했으나 이동할 수 없음
				if (i == 3) {
					lastX = sr;
					lastY = sc;
					canMove = false;
				}
			}
			if (!canMove) {
				break;
			}
		}
		System.out.println(lastX + " " + lastY);
	}
}
