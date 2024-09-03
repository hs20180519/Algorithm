import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int N;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] minTime;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = 1;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if (N==0) {
				break;
			}
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited = new boolean[N][N];
			bfsWithPQ(0, 0);
//			for(int[] m : minTime) {
//			System.out.println(Arrays.toString(m));
//			}
			System.out.println("Problem "+tc+": "+minTime[N-1][N-1]);
			tc++;
		}
	}
	public static void bfsWithPQ(int x, int y) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
		pq.add(new int[] {x, y, map[x][y]});
		minTime = new int [N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				minTime[i][j] = Integer.MAX_VALUE;
			}
		}
		minTime[x][y] = map[x][y]; // 첫 노드 초기화
		visited[x][y] = true;
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int cx = curr[0];
			int cy = curr[1];
			int cw = curr[2];
			
			for(int d=0; d<4; d++) {
				int nx = cx+dx[d];
				int ny = cy+dy[d];
				if(isInBound(nx, ny) && !visited[nx][ny] && minTime[nx][ny] > cw+map[nx][ny]) {
					minTime[nx][ny] = cw + map[nx][ny];
					pq.add(new int[] {nx, ny, minTime[nx][ny]});
					visited[nx][ny] = true;
				}
			}
		}
	}
	public static boolean isInBound(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
}
