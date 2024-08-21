import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * 
	 * [문제 풀이를 위한 아이디어]
	 * 큐를 이용해서 풀자
	 * 큐의 마지막과 들어오려는 값이 같으면,
	 * 값을 빼고 두배 한 값을 다시 넣어준다. 
	 * 이미 변경했다는 의미로 change를 true로 변경한다.
	 * 
	 * 큐의 마지막과 들어오려는 값이 같지 않고,
	 * 0이 아니라면 큐에 들어오려는 값을 추가한다. 
	 * 변경되지 않았다는 의미로 change를 false로 변경한다.
	 * 
	 * 연산이 끝난 후 큐에서 차례대로 값을 꺼내서 map에 채운다. 남은 값들은 0으로 채운다.
	 * 
	 * 이를 네 방향에서 진행하자.
	 * 
	 * 
	 * [난이도] 코드가 효율이 조금 안좋은 것 같지만 풀었으니 오케이입니다..
	 * startIndex와 endIndex를 받으면 고칠 수 있을 것 같다.
	 * 
	 */

	static int N;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 격자의 크기
			String command = st.nextToken(); // 명령어

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// command가 up일 때
			switch (command) {
			case "up":
				up();
				break;
			case "down":
				down();
				break;
			case "left":
				left();
				break;
			case "right":
				right();
				break;
			}
			sb.append("#").append(tc).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);

	}

	// 1. up
	public static void up() {
		for (int y = 0; y < N; y++) {
			ArrayDeque<Integer> q = new ArrayDeque<>();
			boolean change = false; // 두 번 연속 변하는 것 방지용 변수
			for (int i = 0; i < N; i++) {

				// 큐의 마지막과 들어오려는 값이 같으면,
				// 값을 빼고 두배 한 값을 다시 넣어준다.
				// 이미 변경했다는 의미로 change를 true로 변경한다.
				if (!q.isEmpty() && q.peekLast() == map[i][y] && !change) {
					q.pollLast();
					q.addLast(map[i][y] * 2);
					change = true;
				}

				// 큐의 마지막과 들어오려는 값이 같지 않고, 0이 아니라면
				// 큐에 들어오려는 값을 추가한다.
				// 변경되지 않았다는 의미로 change를 false로 변경한다.
				else if (map[i][y] > 0) {
					q.addLast(map[i][y]);
					change = false;
				}
			}

			// 큐에서 차례대로 값을 꺼내서 map에 채운다.
			// 남은 값들은 0으로 채운다.

			for (int i = 0; i < N; i++) {
				if (!q.isEmpty()) {
					map[i][y] = q.pollFirst();
				} else {
					map[i][y] = 0;
				}
			}
		}
	}

	// 2. down
	public static void down() {
		for (int y = 0; y < N; y++) {
			ArrayDeque<Integer> q = new ArrayDeque<>();
			boolean change = false;
			for (int i = N - 1; i >= 0; i--) {
				if (!q.isEmpty() && q.peekLast() == map[i][y] && !change) {
					q.pollLast();
					q.addLast(map[i][y] * 2);
					change = true;
				}

				else if (map[i][y] > 0) {
					q.addLast(map[i][y]);
					change = false;
				}
			}

			for (int i = N - 1; i >= 0; i--) {
				if (!q.isEmpty()) {
					map[i][y] = q.pollFirst();
				} else {
					map[i][y] = 0;
				}
			}
		}

	}

	public static void left() {
		for (int x = 0; x < N; x++) {
			ArrayDeque<Integer> q = new ArrayDeque<>();
			boolean change = false;
			for (int i = 0; i < N; i++) {
				if (!q.isEmpty() && q.peekLast() == map[x][i] && !change) {
					q.pollLast();
					q.addLast(map[x][i] * 2);
					change = true;
				}

				else if (map[x][i] > 0) {
					q.addLast(map[x][i]);
					change = false;
				}
			}

			for (int i = 0; i < N; i++) {
				if (!q.isEmpty()) {
					map[x][i] = q.pollFirst();
				} else {
					map[x][i] = 0;
				}
			}
		}

	}

	public static void right() {
		for (int x = 0; x < N; x++) {
			ArrayDeque<Integer> q = new ArrayDeque<>();
			boolean change = false;
			for (int i = N - 1; i >= 0; i--) {
				if (!q.isEmpty() && q.peekLast() == map[x][i] && !change) {
					q.pollLast();
					q.addLast(map[x][i] * 2);
					change = true;
				}

				else if (map[x][i] > 0) {
					q.addLast(map[x][i]);
					change = false;
				}
			}

			for (int i = N - 1; i >= 0; i--) {
				if (!q.isEmpty()) {
					map[x][i] = q.pollFirst();
				} else {
					map[x][i] = 0;
				}
			}
		}
	}
}
