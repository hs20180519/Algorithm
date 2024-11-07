import java.io.*;
import java.util.*;

public class Solution {
	static int[] dx = { 0, 1, 0, -1 }; // 오른쪽, 아래, 왼쪽, 위
	static int[] dy = { 1, 0, -1, 0 };
	static int[] directions = {0, 1, 2, 3};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			char[][] command = new char[R][C];

			for (int i = 0; i < R; i++) {
				command[i] = br.readLine().toCharArray();
			}

			sb.append("#").append(tc).append(" ");
			if(canStop(R, C, command)) {
				sb.append("YES").append("\n");
			}else{
				sb.append("NO").append("\n");
			};
		}
		System.out.println(sb);
	}

	// 방향 얻기
	public static int getDirection(char c, int d, int m) {
		switch (c) {
		case '<':
			return 2;
		case '>':
			return 0;
		case '^':
			return 3;
		case 'v':
			return 1;
		case '_':
			return (m == 0 ? 0 : 2);
		case '|':
			return (m == 0 ? 1 : 3);
		default: // 그 외의 것은 방향 전환 없음
			return d;
		}
	}
	
	// 메모리 값 갱신
	public static int changeM(char c, int m) {
		if(0<= c-'0' && c-'0' <= 9) {
			return c-'0';
		}else if(c=='+') {
			return (m == 15 ? 0 : m+1);
		}else if(c=='-') {
			return (m == 0 ? 15 : m-1);
		}
		return m;
	}
	
	
	public static boolean canStop(int R, int C, char[][] command) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][][] visited = new boolean[R][C][4][16];
		
		// 방향과 메모리의 값에 따라 방문 여부 따져야 함
		visited[0][0][0][0] = true;

		q.add(new int[] {0,0,0,0}); // x, y, 방향, m
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			int cd = curr[2];
			int cm = curr[3];
			
			char currentCommand = command[cx][cy];
			if(currentCommand == '@')
				return true;
			
			// 방향과 메모리 갱신
			cd = getDirection(currentCommand, cd, cm);
			cm = changeM(currentCommand, cm);
			
			// ?인 경우 네 방향 모두 큐에 추가
			if (currentCommand == '?') {
				for(int dir : directions) {
					// 크기 넘으면 다시 처음으로
					int nx = (cx + dx[dir] + R) % R;
					int ny = (cy + dy[dir] + C) % C;
					
					if(!visited[nx][ny][dir][cm]) {
						visited[nx][ny][dir][cm] = true;
						q.add(new int[] {nx, ny, dir, cm});
					}
				}
			}else {
				int nx = (cx + dx[cd] + R) % R;
				int ny = (cy + dy[cd] + C) % C;
				if(!visited[nx][ny][cd][cm]) {
					visited[nx][ny][cd][cm] = true;
					q.add(new int[] {nx, ny, cd, cm});
				}
			}
		}
		return false;
	
	}
}
