import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 조합으로 칼로리 수가 제한 칼로리보다 작은 모든 경우의 수를 구하고,
// 점수의 최대값을 갱신한다.

public class Solution{
	private static int[][] arr;
	private static int N;
	private static int L;
	private static int ans;
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("./res/d0814/input_SWEA_5215"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine()); // Test Case
		
		for(int tc=1; tc<=T; tc++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 재료의 수
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리
			
			arr = new int[N][2];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int calorie = Integer.parseInt(st.nextToken());
				arr[i][0] = score; // 점수
				arr[i][1] = calorie; // 칼로리
			}
			
			combination(0, 0, 0);
			System.out.printf("#%d %d",tc,ans);
			System.out.println("");
			
		}
	}
		public static void combination(int startIndex, int scores, int calories) {
			if(calories <= L) { // 칼로리의 합이 최대 칼로리보다 작으면
				ans=Math.max(scores, ans); // 최대값 갱신
			}
			for(int i=startIndex; i<N; i++) { // startIndex부터 차례대로 더하기
				int calorie = calories+ arr[i][1];
				int score = scores+ arr[i][0];						
				combination(i+1, score, calorie);
			}
	}
}
