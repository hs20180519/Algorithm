import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	/*
	 * 서로 다른 두 퀸이 공격하지 못하게 놓는 경우의 수
	 */
	static int N;
	static int[] selected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine()); // 보드 크기
			selected = new int[N]; 
			//System.out.println(dfs(0));
			System.out.println("#"+tc+" "+dfs(0));
		}
	}
	public static int dfs(int index) {
		if (index == N) {
			return 1;
		}
		
		int ret = 0;
		for(int i=0; i<N; i++) {
			selected[index] = i;
			if(possible(index)) {
				ret += dfs(index+1);
			}
		}
		return ret;
	}
	public static boolean possible(int index) {
		for(int i=0; i<index; i++) {
			// 이미 다른 곳에서 선택된 행
			if(selected[i] == selected[index]) {
				return false;
			}
			// 대각선 상에 놓여있는 경우
			if(Math.abs(index-i) == Math.abs(selected[index]-selected[i])) {
				return false;
			}
			
		}
		return true;
	}
}
