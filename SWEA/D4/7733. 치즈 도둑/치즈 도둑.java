import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		// 날마다 브루트포스로 탐색하여
		// 해당 날의 맛있는 정도인 치즈를 0으로 만들고,
		// bfs로 덩어리 개수 센다  x 100
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for(int tc = 1; tc<= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 치즈 한 변의 길이
			int[][] cheezes = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++){
					cheezes[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ans = Integer.MIN_VALUE;
			
			// 1부터 100일
			for(int day = 0; day<= 100; day++) {
				eatCheezes(day, cheezes, N);
			
				boolean[][] visited = new boolean[N][N];
				int lump = 0; // 덩어리 개수
				
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						if(cheezes[i][j]!=0 && !visited[i][j]) {
							bfs(i, j, cheezes, N, visited);
							
							lump++;
						}
					}
				}
				
//				for(int[] c : cheezes) {
//					System.out.println(Arrays.toString(c));
//				};
//				System.out.println();
//				System.out.println(lump); // 덩어리
				
				ans = Math.max(ans, lump);
			}
			System.out.println("#" + tc + " " +ans);
		}
	}
	
	// 해당 일의 치즈 먹어 치워
	public static void eatCheezes(int day, int[][] cheezes, int N) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(cheezes[i][j] == day) {
					cheezes[i][j] = 0;
				}
			}
		}
	}
	
	public static void bfs(int x, int y, int[][] cheezes, int N, boolean[][] visited) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			
			for(int d=0; d<4; d++) {
				int nx = cx+dx[d];
				int ny = cy+dy[d];
				
				if(isInBound(nx, ny, N) && !visited[nx][ny] && cheezes[nx][ny] != 0) {
					q.add(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
	}
	
	public static boolean isInBound(int x, int y, int N) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}
