import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/d0904/input_SWEA_1952.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			int[] months = new int[12]; // 이용 계획

			StringTokenizer st = new StringTokenizer(br.readLine());
			// 이용권의 요금 입력
			int dayCost = Integer.parseInt(st.nextToken());
			int monthCost = Integer.parseInt(st.nextToken());
			int threeMonthCost = Integer.parseInt(st.nextToken());
			int yearCost = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) { // 1월부터 12월까지의 이용계획 입력
				months[i] = Integer.parseInt(st.nextToken());
			}

			// step 1. dp 배열 초기화
			int[] dp = new int[13];
			Arrays.fill(dp, 1, 13, Integer.MAX_VALUE);

			// step 2. 매달 비용 계산
			for (int i = 1; i <= 12; i++) {
				// 1일권
				dp[i] = dp[i - 1] + months[i - 1] * dayCost;

				// 1개월권
				dp[i] = Math.min(dp[i], (i>=1 ? dp[i-1] + monthCost : monthCost));
			
				// 3개월권
                if (i >= 3) {
                    dp[i] = Math.min(dp[i], dp[i - 3] + threeMonthCost);
                } else {
                    dp[i] = Math.min(dp[i], threeMonthCost);
                }
                
                // 1년권
                dp[i] = Math.min(dp[i], yearCost);
			}
            System.out.println("#" + tc + " " + dp[12]);
		}

	}

}
