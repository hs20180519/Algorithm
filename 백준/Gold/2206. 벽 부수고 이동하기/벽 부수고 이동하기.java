import java.util.*;
import java.io.*;
public class Main {
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		boolean[][][] visited = new boolean[N][M][2];
		
		// 시작과 끝 칸 포함 계산
		// 벽을 부수고 이동하는 것이 경로가 짧아진다면 벽을 한 개까지 부수고 이동 가능
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		System.out.println(bfs(0,0,visited, map, N, M));
		
	}
	public static int bfs(int x, int y, boolean[][][]visited, char[][] map, int N, int M) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y, 0, 0});
		visited[x][y][0] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			int block = curr[2];
			int dist = curr[3];
			
			if(cx == N-1 && cy == M-1) return dist+1;
			
			for(int d=0; d<4; d++) {
				int nx = cx+dx[d];
				int ny = cy+dy[d];
				
				if(0<=nx && nx <N && 0<=ny && ny<M && map[nx][ny]-'0'!=1 && !visited[nx][ny][block]) {
					visited[nx][ny][block] = true;
					q.add(new int[] {nx, ny, block, dist+1});
				}
				
				if(block < 1) {
					if(0<=nx && nx <N && 0<=ny && ny<M && map[nx][ny]-'0'==1 && !visited[nx][ny][block+1]) {
						visited[nx][ny][block+1] = true;
						q.add(new int[] {nx, ny, block+1, dist+1});
					}
				}
			}
		}
		return -1;
	}
}
