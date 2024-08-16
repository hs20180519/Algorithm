
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	private static int[][] arr;
	private static boolean[] isSelected;
	private static int N;
	private static int L;
	private static int ans;
	public static void main(String[] args) throws Exception {
		 //System.setIn(new FileInputStream("res/d0816/input_SWEA_5215"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
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
			
			//combination(0, 0, 0);
			powerSet(0,0, 0, new boolean[N]);
			System.out.printf("#%d %d",tc,ans);
			System.out.println("");
			
		}
	}
	
		public static void combination(int startIndex, int scores, int calories) { // 조합
			if(calories <= L) { // 칼로리의 합이 최대 칼로리보다 작으면
				ans=Math.max(scores, ans); // 최대값 갱신
			}
			for(int i=startIndex; i<N; i++) { // startIndex부터 차례대로 더하기						
				combination(i+1, scores+arr[i][0], calories+arr[i][1]);
			}
		}
		
		public static void powerSet(int cnt, int scores, int calories, boolean[] isSelected) { // 부분집합
			if (calories <= L) { //최대 칼로리보다 작을 때
				ans=Math.max(scores, ans);
				
			}
			if(cnt == N) { // 모든 재료의 수를 다 썼으면
				return;
			}
			else {
				//System.out.println(Arrays.toString(isSelected));
				isSelected[cnt] = true;
				powerSet(cnt+1,  scores+arr[cnt][0], calories+arr[cnt][1], isSelected);
				isSelected[cnt] = false;
				powerSet(cnt+1, scores, calories, isSelected);
				
				
			}
		}
}
