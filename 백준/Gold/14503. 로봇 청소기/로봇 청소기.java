
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int d;
	static int dx[] = { -1, 0, 1, 0 };// 북 동 남 서
	static int dy[] = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] cleaned;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cleaned = new boolean[N][M];

		st = new StringTokenizer(br.readLine());
		int startX = Integer.parseInt(st.nextToken()); // 현재 위치
		int startY = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken()); // 현재 방향

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		clean(startX, startY);
		
		int ans =0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(cleaned[i][j] == true) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
	
	public static void clean(int x, int y) {
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] curr =q.poll();
			int cx = curr[0];
			int cy = curr[1];
			
			if(map[cx][cy] == 0) {
				cleaned[cx][cy] = true;
			}
			
			// 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
			if(hasCleanSpaceAround(cx, cy)) {
				for (int i = 0; i < 4; i++) {
					turnLeft();
					int nx = cx + dx[d];
					int ny = cy + dy[d];

					if (isInBound(nx, ny) && !(map[nx][ny] == 1) && !(cleaned[nx][ny])) {
						q.add(new int[] {nx, ny});
						break;
					}
				}
			}
			
			// 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
			// 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진
			else{
				int nx = cx - dx[d];
				int ny = cy - dy[d];
				if(!isInBound(nx, ny) || map[nx][ny] == 1) {
					return;
				}else {
					q.add(new int[] {nx, ny});
				}
			}
		}
	}

	public static boolean isInBound(int nx, int ny) {
		return 0 <= nx && nx < N && 0 <= ny && ny < M;
	}
	
	public static void turnLeft() {
		d = (d+3)%4;
	}
	
	public static boolean hasCleanSpaceAround(int x, int y) {
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(isInBound(nx, ny) && map[nx][ny]==0 && cleaned[nx][ny]==false) return true;
		}
		return false;
	}
}
