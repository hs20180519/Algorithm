import java.io.*;
import java.util.*;

public class Solution {
	static int[] dx = { 0, 1, 0, -1 }; // 오른쪽, 아래, 왼쪽, 위
	static int[] dy = { 1, 0, -1, 0 };
	static int maxDistance, x, y, d;
	static char[] command;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			command = br.readLine().toCharArray();
			maxDistance = 0;

			solve();
			sb.append("#").append(tc).append(" ");
			if (maxDistance == -1) {
				sb.append("oo").append("\n");
			} else {
				sb.append(maxDistance).append("\n");
			}

		}
		
		
		
		System.out.println(sb);
	}

	private static void solve() {
	
		x = 0;
		y = 0;
		d = 0;
		
		for(int i=0; i<4; i++) {
			int startD = d;
			command();
			
			if(x == 0 && y == 0) {
				return;
			}
			
			// 마지막 방향이 시작 방향과 같다면 무한
			if(d == startD) {
				maxDistance = -1;
				return;
			}
		}
	}
	
	private static void command() {
		for(char c : command) {
			if (c == 'S') {
				x += dx[d];
				y += dy[d];
				maxDistance = Math.max(maxDistance, x*x+y*y);
			}else if(c == 'L') {
				d = (d+1) % 4;
			}else if(c == 'R') {
				d = (d+3) % 4;
			}
		}
	}
}
