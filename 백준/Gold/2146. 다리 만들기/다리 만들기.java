
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static boolean[][] visited;
	static int ans;
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/d0819/input_Main_2146"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 지도의 크기
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N][N];
		int count = 1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j] && arr[i][j] != 0) {
					bfs(i, j, count++);
				}
			}
		}
		ans = Integer.MAX_VALUE;

		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(arr[i][j] != 0) {
					visited = new boolean[N][N]; // 방문 배열 초기화
					findDistance(i, j, 0);
				}
			}
		}
//		visited = new boolean[N][N];
//		findDistance(3, 4, 0);
		System.out.println(ans-1);
		
	}
	
	public static void bfs(int x, int y, int num) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.addFirst(new int[] {x, y, num});
		arr[x][y] = num;
		visited[x][y] = true;
		while(!q.isEmpty()) {
			int[] xy = q.pollLast();
			int cx = xy[0];
			int cy = xy[1];
			for(int i=0; i<4; i++) {
				int nx = cx+dx[i];
				int ny = cy+dy[i];
				
				if(0<= nx && nx < N && 0<= ny && ny < N && arr[nx][ny] != 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					q.addFirst(new int[] {nx, ny});
					arr[nx][ny] = num;
				}
			}
		}
	}

	public static void findDistance(int x, int y, int num){
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.addFirst(new int[]{x, y, num});
		visited[x][y] = true;
		int landNum = arr[x][y];
		while(!q.isEmpty()){
			int[] cur = q.pollLast();
			int cx = cur[0];
			int cy = cur[1];
			int d = cur[2];
			for(int i=0; i<4; i++){
				int nx = cx+dx[i];
				int ny = cy+dy[i];

				// 범위 안이고, 지나가지 않았고, 같은 대륙이 아니라면
				if(0<= nx && nx < N && 0<= ny && ny < N && !visited[nx][ny] && arr[nx][ny] != landNum){
					visited[nx][ny] = true;
					q.addFirst(new int[] {nx, ny, d+1});
					//System.out.printf("%d %d\n", nx, ny);
					if (arr[nx][ny] != 0){ // 다른 대륙이라면
						ans = Math.min(ans, d+1);
						return;
					}
				}

			}
		}
	}
	

}
