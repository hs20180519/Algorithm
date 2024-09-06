import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * bfs로 큐에 좌표와 이동시간을 넣고, 이동 시간이 경과 시간과 동일하면 count를 증가한다. 
	 * 방문시 1: 상하좌우, 2: 상하, 3: 좌우 .. 탐색에 유의한다. 다음 좌표도 그와 연결 가능해야 한다.
	 * 방문배열을 돌아 방문 횟수를 센다.
	 */
	static int N, M, L;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
	static int[] dy = {0, 0, -1, 1}; 

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/d0906/input_SWEA_1953.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 지도 세로
			M = Integer.parseInt(st.nextToken()); // 지도 가로
			int R = Integer.parseInt(st.nextToken()); // 멘홀 세로 위치
			int C = Integer.parseInt(st.nextToken()); // 멘홀 가로 위치
			L = Integer.parseInt(st.nextToken()); // 탈출 후 소요 시간
	
			map = new int[N][M]; // 지도
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited = new boolean[N][M]; // 방문 배열
			
			bfs(R, C);
			
			int count = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(visited[i][j]) count++;
				}
			}
			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		System.out.println(sb);
	}
	
	// time넘으면 q에 넣지 않기
	public static void bfs(int x, int y) { // 시작점
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y, 0}); // 현재 걸린 시간 : 0
		visited[x][y] = true;
		
		while(!q.isEmpty()) {	
			int[] curr =q.poll();
			int cx = curr[0];
			int cy = curr[1];
			int cTime = curr[2]; // 현재 경과한 시간
	
			int currentNumber = map[cx][cy];
			switch(currentNumber){
			case 1: // 1: 상하좌우 탐색
				for(int d=0; d<4; d++) {
					int nx = cx+dx[d];
					int ny = cy+dy[d];
					if(check(nx, ny)&& checkD(nx, ny,d)) {
						if(cTime+1 < L) {
							q.add(new int[] {nx, ny, cTime+1});
							visited[nx][ny] = true;
						}
					}
				}
				break;
			case 2: // 2: 상하 탐색 d = 0, 1
				for(int d=0; d<2; d++) {
					int nx = cx+dx[d];
					int ny = cy+dy[d];
					if(check(nx, ny) && checkD(nx, ny,d)) {
						if(cTime+1 < L) {
							// 이동하려는 곳이 상하나 
							q.add(new int[] {nx, ny, cTime+1});
							visited[nx][ny] = true;
						}
					}
				}
				break;
			case 3: // 3: 좌우 탐색 d = 2, 3
				for(int d=2; d<4; d++) {
					int nx = cx+dx[d];
					int ny = cy+dy[d];
					if(check(nx, ny) && checkD(nx, ny,d)) {
						if(cTime+1 < L) {
							q.add(new int[] {nx, ny, cTime+1});
							//System.out.println(nx +" " + ny+ " " + cTime + "를 큐에 추가");
							visited[nx][ny] = true;
						}
					}
				}
				break;
			case 4: // 4: 상우 탐색 d = 0, 3
				for(int d=0; d<4; d=d+3) {
					int nx = cx+dx[d];
					int ny = cy+dy[d];
					if(check(nx, ny) && checkD(nx, ny,d)) {
						if(cTime+1 < L) {
							q.add(new int[] {nx, ny, cTime+1});
							visited[nx][ny] = true;
						}
					}
				}
				break;
			case 5: // 5: 하우 탐색 d = 1, 3
				for(int d=1; d<4; d=d+2) {
					int nx = cx+dx[d];
					int ny = cy+dy[d];
					
					if(check(nx, ny) && checkD(nx, ny,d)) {
						if(cTime+1 < L) {
							q.add(new int[] {nx, ny, cTime+1});
							visited[nx][ny] = true;
						}
					}
				}
				break;
			case 6: // 6: 하좌 탐색 d = 1, 2
				for(int d=1; d<3; d++) {
					int nx = cx+dx[d];
					int ny = cy+dy[d];
					if(check(nx, ny) && checkD(nx, ny,d)) {
						if(cTime+1 < L) {
							q.add(new int[] {nx, ny, cTime+1});
							visited[nx][ny] = true;
						}
					}
				}
				break;
			case 7: // 7: 상좌 탐색 d = 0, 2
				for(int d=0; d<4; d=d+2) {
					int nx = cx+dx[d];
					int ny = cy+dy[d];
					if(check(nx, ny) && checkD(nx, ny,d)) {
						if(cTime+1 < L) {
							q.add(new int[] {nx, ny, cTime+1});
							visited[nx][ny] = true;
						}
					}
				}
				break;
			}
	
		}
		
	}
	
	// 범위 안에 있고, 터널이 없고, 방문한 적 없다면
	public static boolean check(int x, int y) {
		return 0 <= x && x<N && 0 <= y && y <M &&map[x][y] != 0&& !visited[x][y];
	}
	
	// 그 다음 좌표와 연결할 수 있다면
	public static boolean checkD(int nx, int ny, int d) {
		int m = map[nx][ny];
		switch(d) {
		case 0: // 상
			return m == 1 || m == 2 || m == 5 || m == 6;	
		case 1: // 하
			return m == 1 || m == 2 || m == 4 || m == 7;
		case 2: // 좌
			return m == 1 || m == 3 || m == 4 || m == 5;
		case 3: // 우
			return m == 1 || m == 3 || m == 6 || m == 7;
		}
		return false;
	}
}
