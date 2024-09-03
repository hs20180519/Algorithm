import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	/*
	 * 가중치 양수, 최단 거리, 다익스트라 알고리즘
	 * 
	 */
	static int N;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0,0, -1, 1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				char[] ch = br.readLine().toCharArray();
				for(int j=0; j<N; j++) {
					map[i][j] = ch[j] - '0';
				}
			}
			System.out.println("#"+tc+" "+getMinTime(0,0,N-1, N-1));
		}
	}
	
	static int getMinTime(int sr, int sc, int er, int ec) {
		final int INF = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[N][N];
		int[][] minTime = new int[N][N];
		PriorityQueue<int[]> pQueue = new PriorityQueue<>((o1, o2)-> o1[2]-o2[2]);
		// int[] = {r, c, time}, time기준으로 최소 힙 관리
		
		// minTime 초기화
		for(int i=0; i<N; i++) {
			for(int j =0; j<N; j++) {
				minTime[i][j] = INF;
			}
		}
		
		// 첫 정점 0
		minTime[sr][sc] = 0;
		pQueue.offer(new int[] {sr, sc, minTime[sr][sc]});
		
		while(!pQueue.isEmpty()) {
			int[] stopOver = pQueue.poll();
			int r = stopOver[0];
			int c = stopOver[1];
			int time = stopOver[2];
			
			// 이미 방문했던 노드 X - 이미 최소값임
			if(visited[r][c]) continue;
			visited[r][c] = true;
			
			if(r==er && c == ec) return time;
			
			// 현재 정점 기준으로 사방 탐색
			for(int d = 0; d< 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(isInBound(nr, nc)&& !visited[nr][nc] && minTime[nr][nc] > time+map[nr][nc]) {
					minTime[nr][nc] = time + map[nr][nc];
					pQueue.offer(new int[] {nr, nc, minTime[nr][nc]});
				}
			}
		}
		return -1;
	}
	public static boolean isInBound(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
}
