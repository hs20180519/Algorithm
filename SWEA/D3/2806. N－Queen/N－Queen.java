import java.util.*;
import java.io.*;

public class Solution {
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			ans = 0;
			sb.append("#").append(tc).append(" ");
			dfs(0, new int[N], N);
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int cnt, int[] selected, int N) {
		if(cnt == N) {
			ans++;
			return;
		}
		
		for(int i=0; i<N; i++) {
			selected[cnt] = i;
			if(possible(cnt, selected)) {
				dfs(cnt+1, selected, N);
			}
		}
	}
	
	public static boolean possible(int cnt, int[] selected) {
		for(int i=0; i<cnt; i++) {
			if(selected[cnt] == selected[i]) return false;
			
			if(Math.abs(cnt-i) == Math.abs(selected[cnt]-selected[i])) return false;
		}
		return true;
	}

}
