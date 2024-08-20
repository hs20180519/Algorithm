import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
 * [문제 풀이를 위한 아이디어]
 * N을 입력받아 0 ~ N/2 줄까지 (가운데 줄 포함) 계산 - 윗줄
 * N~2 +1 ~ N 줄까지 계산 - 아랫줄
 *  
 *  - 윗줄
 * i = 0 ~ N/2
 * j = N/2 -i ~ N/2 + i -> j는 가운데에서 i만큼 왼쪽, 오른쪽으로 퍼진 형태
 * 
 *  - 아랫줄
 * i = N/2 + 1 ~ N
 * index = = (N - 2) / 2 : 맨 처음 아랫줄의 시작점
 * j = N/2 - index ~ N/2 + index
 * index -= 1
*/

public class Solution {
	static int N; // 농장의 크기
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/d0820/input_SWEA_2805"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					arr[i][j] = s.charAt(j) - '0';
				}
			}

			int sum = 0;
			for (int i = 0; i <= N / 2; i++) { // 0 1 2
				for (int j = N / 2 - i; j <= N / 2 + i; j++) {
					sum += arr[i][j];
				}
			}
			int index = (N - 2) / 2; // 1
			for (int i = N / 2 + 1; i < N; i++) { // 3 4
				for (int j = N / 2 - index; j <= N / 2 + index; j++) {
					sum += arr[i][j];
				}
				index -= 1;
			}

			System.out.printf("#%d %d\n", tc, sum);

		}

	}

}
