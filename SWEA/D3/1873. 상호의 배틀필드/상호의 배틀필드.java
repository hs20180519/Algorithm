import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	/*
	 * [문제 풀이를 위한 아이디어] 
	 * 현재 전차의 방향을 저장하고, 주어진 명령에 따라 변경한다. 
	 * 전차의 방향대로 앞으로 나아가거나, shoot한다. 
	 * 이동시에 전의 위치는 평지로 변경하고 후의 위치는 주어진 방향표의 모양대로 변경한다.
	 * 
	 * [난이도] 
     * 이동시에 전의 위치, 후의 위치를 주의하기
	 * 
	 */

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static char[][] map;
	static int H; // 맵의 높이
	static int W; // 맵의 너비

	static int d; // 전차의 방향
	static int carX;
	static int carY;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken()); // 높이 - x
			W = Integer.parseInt(st.nextToken()); // 너비 - y

			map = new char[H][W]; // 게임 맵

			carX = -1; // 전차의 위치
			carY = -1;

			char[] car = { '^', '>', 'v', '<' }; // 전차의 방향

			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = str.charAt(j);

					if (carX == -1) { // 전차의 위치가 아직 안 정해졌으면
						for (int k = 0; k < 4; k++) {
							if (map[i][j] == car[k]) {
								carX = i;
								carY = j;
								d = k;
								break;
							}
						}
					}
				}
			}

			int commandNum = Integer.parseInt(br.readLine()); // 사용자가 넣을 입력의 개수

			String commandString = br.readLine();
			for (int i = 0; i < commandNum; i++) {
				char command = commandString.charAt(i); // 주어진 명령어

				switch (command) {

				case 'U':
					up();
					break;
				case 'D':
					down();
					break;
				case 'R':
					right();
					break;
				case 'L':
					left();
					break;
				case 'S':
					shoot();
					break;
				}

			}
			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < H; i++) {
				sb.append(map[i]);
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	// d = 상, 우, 하, 좌
	public static void up() {
		d = 0; // 전차가 바라보는 방향을 위쪽으로 바꾼다
		map[carX][carY] = '^';
		if (0 <= carX - 1 && map[carX - 1][carY] == '.') { // 한 칸 위의 칸이 존재하고, 평지이면 이동
			map[carX][carY] = '.'; // 기존 것 평지로 변경
			carX = carX - 1; // 이동
			map[carX][carY] = '^'; // 이동한 곳 변경

		}
	}

	public static void down() {
		d = 2; // 전차가 바라보는 방향을 아래쪽으로 바꾼다
		map[carX][carY] = 'v';
		if (carX + 1 < H && map[carX + 1][carY] == '.') { // 한 칸 아래의 칸이 존재하고, 평지이면 이동
			map[carX][carY] = '.';
			carX = carX + 1;
			map[carX][carY] = 'v';
		}
	}

	public static void right() {
		d = 1; // 전차가 바라보는 방향을 오른쪽으로 바꾼다
		map[carX][carY] = '>';
		if (carY + 1 < W && map[carX][carY + 1] == '.') { // 한 칸 오른쪽의 칸이 존재하고, 평지이면 이동
			map[carX][carY] = '.';
			carY = carY + 1;
			map[carX][carY] = '>';
		}
	}

	public static void left() {
		d = 3; // 전차가 바라보는 방향을 왼쪽으로 바꾼다
		map[carX][carY] = '<';
		if (carY - 1 >= 0 && map[carX][carY - 1] == '.') { // 한 칸 왼쪽의 칸이 존재하고, 평지이면 이동
			map[carX][carY] = '.';
			carY = carY - 1;
			map[carX][carY] = '<';
		}
	}

	public static void shoot() {
		// 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.

		if (d == 0) { // 위쪽을 바라보고 있다면,
			for (int i = 1; i <= carX; i++) { // 벽까지
				if (map[carX - i][carY] == '*') { // 벽돌이라면 평지가 되고 소멸
					map[carX - i][carY] = '.';
					break;
				} else if (map[carX - i][carY] == '#') { // 강철이라면 포탄 소멸
					break;
				}
			}
		}

		else if (d == 1) { // 오른쪽을 바라보고 있다면,
			for (int i = 1; i < W - carY; i++) { // 벽까지
				if (map[carX][carY + i] == '*') { // 벽돌이라면 평지가 되고 소멸
					map[carX][carY + i] = '.';
					break;
				} else if (map[carX][carY + i] == '#') { // 강철이라면 포탄 소멸
					break;
				}
			}
		} else if (d == 2) {// 아래쪽을 바라보고 있다면,
			for (int i = 1; i < H - carX; i++) { // 벽까지
				if (map[carX + i][carY] == '*') { // 벽돌이라면 평지가 되고 소멸
					map[carX + i][carY] = '.';
					break;
				} else if (map[carX + i][carY] == '#') { // 강철이라면 포탄 소멸
					break;
				}
			}
		} else if (d == 3) { // 왼쪽을 바라보고 있다면,
			for (int i = 1; i <= carY; i++) { // 벽까지
				if (map[carX][carY - i] == '*') { // 벽돌이라면 평지가 되고 소멸
					map[carX][carY - i] = '.';
					break;
				} else if (map[carX][carY - i] == '#') { // 강철이라면 포탄 소멸
					break;
				}
			}
		}
	}

}
