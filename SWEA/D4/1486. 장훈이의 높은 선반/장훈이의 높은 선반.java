import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	/*
	 * [문제 풀이를 위한 아이디어]
	 * np를 이용한 조합으로 모든 경우의 수를 계산한 후, 
	 * 탑의 높이와 조합의 합을 비교하여 가장 작은 수(답)를 갱신
	 * 
	 * [난이도]
	 * 햄버거 다이어트랑 똑같이 풀어서 무난했다.
	 * D4에 겁먹지 않기 ..
	 * 조합으로 했으면 시간 초과났을 텐데, np 라는 좋은 풀이 방법을 알게 된 것 같다.
	 */

	static int[] heights;
	static int N;
	static int B;
	static int ans;

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("res/d0820/input_SWEA_1486"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 점원의 수
			B = Integer.parseInt(st.nextToken()); // 탑의 높이

			heights = new int[N]; // 점원의 키를 저장할 배열
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}
			ans = Integer.MAX_VALUE; // 키의 합 - 탑의 높이
			
			for (int i = 1; i <= N; i++) { // 1부터 N까지 조합
				combination(i);
			}
			System.out.printf("#%d %d\n", tc, ans);

		}

	}

	// N개중에서 num개를 뽑아 만든 조합
	public static void combination(int num) {
		int[] p = new int[N];
		// 뒤에서부터 num 개수만큼 1로 채우기
		// N이 5이고 num이 3이라면, 	0 0 1 1 1
		// 그 다음 np를 돌고 나면, 		0 1 0 1 1
		// 그 다음 np를 돌고 나면,		0 1 1 0 1
		// 그 다음 np를 돌고 나면,		0 1 1 1 0 ...
		// 이런식으로 num개 만큼 1이 됨
		
		for (int i = N - 1; i >= N - num; i--) {
			p[i] = 1;
		}

		// 남은 부분 0으로 채우기
		Arrays.fill(p, 0, N - num, 0);

		do {
			// 키의 합
			int sumHeight = 0;
			for (int i = 0; i < N; i++) {
				// 선택했다면
				if (p[i] == 1) {
					sumHeight += heights[i];
					// 이상이면 break
					if(sumHeight >= B) break;
				}
			}
			if (sumHeight >= B) {
				// 키의 합과 탑의 높이의 차이중 가장 작은 것
				ans = Math.min(ans, sumHeight - B);
			}
		} while (np(p));

	}

	// np : 다음 순열을 찾음
	public static boolean np(int[] p) {
		// step 1. 꼭대기 찾기
		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i])
			i--;
		// 꼭대기가 맨 처음일 경우 종료
		if (i == 0)
			return false;

		// step 2. 꼭대기 앞의 수보다 큰 수 찾기
		int j = N - 1;
		while (p[i - 1] >= p[j])
			j--;

		// step 3. 꼭대기 앞의 수와 꼭대기 앞의 수보다 큰 수 중 가장 작은 수 교환
		swap(p, i - 1, j);

		// step 4. 꼭대기부터 맨 끝까지 오름차순 정렬
		int k = N - 1;
		while (i < k) {
			swap(p, i++, k--);
		}
		return true;
	}

	// p배열의 i번째와 j번째를 바꿈
	public static void swap(int[] p, int i, int j) {
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}
}
