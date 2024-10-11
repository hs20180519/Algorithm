import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static int[][] dist;
	
	static Queue<int[]> waterQueue = new LinkedList<>();
	static Queue<int[]> dochiQueue = new LinkedList<>();
	

	public static void main(String[] args) throws IOException {
		// S -> D
		// 물 : bfs ( * 통과 못함 )
		// 고슴도치 : 최소 시간(물이 찰 예정인 칸으로 이동 못함)

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken()); // 티떱숲의 세로길이
		C = Integer.parseInt(st.nextToken()); // 가로 길이

		map = new char[R][C];
		dist = new int[R][C];
		visited= new boolean[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int dochiX = 0;
		int dochiY = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'S') {
					dochiX = i;
					dochiY = j;
					dochiQueue.add(new int[] {dochiX, dochiY});
					visited[dochiX][dochiY] = true;
				}
				if (map[i][j] == '*') {
					waterQueue.add(new int[] {i, j});
				}
			}
		}
		
		int result = bfs(dochiX, dochiY);
		if(result == -1) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(result);
		}
	}

	public static int bfs(int x, int y) {
		while (!dochiQueue.isEmpty()) {
			// 물을 먼저 퍼뜨린다.
			int waterSize = waterQueue.size();
			for(int i=0; i<waterSize; i++) {
				int[] waterXY = waterQueue.poll();
				int wx = waterXY[0];
				int wy = waterXY[1];
				
				for (int d = 0; d < 4; d++) {
					int nx = wx + dx[d];
					int ny = wy + dy[d];
				
					if (0 <= nx && nx < R && 0 <= ny && ny < C && map[nx][ny] == '.') {
						map[nx][ny] = '*';
						waterQueue.add(new int[] {nx, ny});
					}
				}
			}
		
			int dochiSize = dochiQueue.size();
			for(int i=0; i<dochiSize; i++) {
				int[] dochiXY = dochiQueue.poll();
				int dochix = dochiXY[0];
				int dochiy = dochiXY[1];
				
				for(int d = 0; d < 4; d++) {
					int nx = dochix + dx[d];
					int ny = dochiy + dy[d];
					
					if (0 <= nx && nx < R && 0 <= ny && ny <C ) {
						 if (map[nx][ny] == 'D') { // 비버의 굴에 도착하면 이동 횟수를 반환
	                            return dist[dochix][dochiy] + 1;
	                     }
						 if(map[nx][ny] == '.' && !visited[nx][ny]) {
							 visited[nx][ny] = true;
							 dist[nx][ny] = dist[dochix][dochiy]+1;
							 dochiQueue.add(new int[] {nx, ny});
						 }
					}
				}
			}
		}
		return -1;
	}
}