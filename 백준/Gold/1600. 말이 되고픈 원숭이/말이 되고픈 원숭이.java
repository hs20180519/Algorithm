import java.util.*;
import java.io.*;

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int[] hx = {-2, -1, -2, -1,  2,  1, 2, 1};
	static int[] hy = {-1, -2,  1,  2, -1, -2, 1, 2};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine()); // 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken()); // y
		int H = Integer.parseInt(st.nextToken()); // x
		int[][] map = new int[H][W];
		int[][] visited = new int[H][W];
		
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		System.out.println(bfs(0,0, K, W, H, visited, map));
	}
	
	public static int bfs(int x, int y, int K, int W, int H, int[][] visited, int[][] map) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0, 0, 0}); // x, y, 말 동작, 총 동작 수
		visited[0][0] = 0;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			int k = curr[2];
			int move = curr[3];
			
			if(cx == H-1 && cy == W-1) {
				return move;
			}
			
			for(int d=0; d<4; d++) {
				int nx = cx+dx[d];
				int ny = cy+dy[d];
				
				if(isInBound(nx, ny, W, H) && map[nx][ny] != 1 && visited[nx][ny] > k) {
					q.add(new int[] {nx, ny, k, move+1});
					visited[nx][ny] = k;
				}
			}
			
			if(k < K) {
				for(int d=0; d<8; d++) {
					int nx = cx+hx[d];
					int ny = cy+hy[d];
					
					if(isInBound(nx, ny, W, H) && map[nx][ny] != 1 && visited[nx][ny] > k+1) {
						q.add(new int[] {nx, ny, k+1, move+1});
						visited[nx][ny] = k+1;
					}
				}
			}
		}
		return -1;
	}
	public static boolean isInBound(int x, int y, int W, int H) {
		return 0<=x && x<H && 0<= y && y<W;
	}
}
