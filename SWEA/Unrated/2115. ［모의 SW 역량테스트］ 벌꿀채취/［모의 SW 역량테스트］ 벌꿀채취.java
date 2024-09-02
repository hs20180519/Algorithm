

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, M, C;
	static int[][] honeys;
	static boolean[][] visited;
	static int firstMaxProfit = 0;
	static int secondMaxProfit = 0;

	// 선택한 벌통 위치 기억을 위한 변수들
	static int[] firstSelection = new int[4]; // {r, start, r, end}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 벌통들의 크기
			M = Integer.parseInt(st.nextToken()); // 선택할 수 있는 벌통의 개수
			C = Integer.parseInt(st.nextToken()); // 꿀을 채취할 수 있는 최대 양

			honeys = new int[N][N]; // 꿀의 정보
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					honeys[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			visited = new boolean[N][N];
			firstMaxProfit = 0;
		

			// 첫 번째 최대 꿀 채취
			findMaxHoney();
			int firstProfit = firstMaxProfit;

			// 첫 번째 선택한 벌통 위치에 대해 방문 체크
			markFirstSelectionVisited();

			// 두 번째 최대 꿀 채취 (첫 번째 채취에 사용된 벌통 제외)
			secondMaxProfit = 0;  // 두 번째 최대 이익을 다시 초기화
			findMaxHoney();  // 방문 상태를 유지한 채로 다시 호출
			int secondProfit = secondMaxProfit;

			System.out.println("#"+tc+" "+ (firstProfit + secondProfit));
		}
	}

	public static void findMaxHoney() {
		for (int r = 0; r < N; r++) {
			for (int i = 0; i <= N - M; i++) {
				if (canCollectHoney(r, i)) { // 벌통을 채취할 수 있는지 확인
					int profit = calculateMaxProfit(r, i, 0, 0, 0);

					if (profit > firstMaxProfit) {
						firstMaxProfit = profit;
						// 기존 선택 초기화 및 새로운 선택 설정
						firstSelection[0] = r;
						firstSelection[1] = i;
						firstSelection[2] = r;
						firstSelection[3] = i + M - 1;
					} else if (profit > secondMaxProfit && !isVisited(r, i)) {
						secondMaxProfit = profit; // 두 번째 채취 시 기존 방문 위치 제외
					}
				}
			}
		}
	}

	public static boolean canCollectHoney(int r, int start) {
		for (int j = 0; j < M; j++) {
			if (visited[r][start + j]) {
				return false;
			}
		}
		return true;
	}

	public static int calculateMaxProfit(int r, int start, int currentHoney, int currentProfit, int idx) {
		if (idx == M) {
			return currentProfit;
		}

		int nextHoney = currentHoney + honeys[r][start + idx];
		int nextProfit = currentProfit + honeys[r][start + idx] * honeys[r][start + idx];

		if (nextHoney <= C) {
			return Math.max(calculateMaxProfit(r, start, nextHoney, nextProfit, idx + 1),
					calculateMaxProfit(r, start, currentHoney, currentProfit, idx + 1));
		} else {
			return calculateMaxProfit(r, start, currentHoney, currentProfit, idx + 1);
		}
	}

	// 첫 번째 선택된 벌통의 위치를 visited 배열에 표시
	public static void markFirstSelectionVisited() {
		for (int i = firstSelection[1]; i <= firstSelection[3]; i++) {
			visited[firstSelection[0]][i] = true;
		}
	}

	// 범위 내에 선택한 벌통이 있는지 확인
	public static boolean isVisited(int r, int start) {
		for (int j = 0; j < M; j++) {
			if (visited[r][start + j]) {
				return true;
			}
		}
		return false;
	}
}
