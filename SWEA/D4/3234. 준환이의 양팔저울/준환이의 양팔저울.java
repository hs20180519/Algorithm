import java.io.*;
import java.util.*;

public class Solution {
	static int ans;
	public static void main(String[] args) throws Exception{
		// System.setIn(new FileInputStream("res/d1125/input_SWEA_3234"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] weights = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}
			ans = 0;
			
			dfs(0, 0, 0, new boolean[N], weights,N);
			sb.append("#").append(tc).append(" ");
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int cnt, int left, int right, boolean[] visited, int[] weights, int N) {
		if (left < right) {
			return;
		}
		if(cnt == N) {
			// 계산
			ans++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(cnt+1, left+weights[i], right, visited, weights, N);
				dfs(cnt+1, left, right+weights[i], visited, weights, N);
				visited[i] = false;
			
			}
			
		}
	}
}
