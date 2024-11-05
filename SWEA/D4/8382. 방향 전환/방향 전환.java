
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] visitedX;
	static boolean[][] visitedY;
	public static void main(String[] args) throws IOException {
		// 가로와 세로를 번갈아서 이동해야 함
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			
			visitedX = new boolean[201][201];
			visitedY = new boolean[201][201];
			// bfs
			System.out.println("#"+tc+" " +bfs(startX, startY, endX, endY));
		}
	}
	
	public static int bfs(int startX, int startY, int endX, int endY) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {startX, startY, 0, 0}); // 가로 방향
		q.add(new int[] {startX, startY, 0, 1}); // 세로 방향
		visitedX[startX+100][startY+100] = true; // 가로 방향 방문
		visitedY[startX+100][startY+100] = true; // 세로 방향 방문
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			int distance = curr[2]; // 이동 거리
			int cd = curr[3]; // 현재 방향			
			
			if(cx == endX && cy == endY) {
				return distance;
			}
			
			if(cd==0) { // 전의 것이 가로 방향이라면
				for(int d=0; d<4;d+=2) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];
					
					if(isInBound(nx, ny) && !visitedX[nx+100][ny+100]) {
						q.add(new int[] {nx, ny, distance+1, 1});
						visitedX[nx+100][ny+100] = true;
					}
					
				}
			}else if(cd==1) {
				for(int d=1; d<4;d+=2) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];
					
					if(isInBound(nx, ny) && !visitedY[nx+100][ny+100]) {
						q.add(new int[] {nx, ny, distance+1, 0});
						visitedY[nx+100][ny+100] = true;
					}
				}
			}
		}
		return -1;
		
	}
	
	public static boolean isInBound(int x, int y) {
		return -100<= x && x <= 100 && -100 <= y && y <= 100;
	}
}
