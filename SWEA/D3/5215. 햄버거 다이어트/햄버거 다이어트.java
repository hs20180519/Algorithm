
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
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
			if(calories <= L) {
				ans=Math.max(scores, ans);
			}
			for(int i=startIndex; i<N; i++) {
				int calorie = calories+ arr[i][1];
				int score = scores+ arr[i][0];		
				//System.out.println(i+1 + ", "+score + ", " + calorie);				
				combination(i+1, score, calorie);

			}
	}
}
