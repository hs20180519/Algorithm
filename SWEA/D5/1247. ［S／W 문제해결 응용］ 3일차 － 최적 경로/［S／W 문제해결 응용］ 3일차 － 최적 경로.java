import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	/*
	 * [문제 풀이를 위한 아이디어]
	 *
	 * 
	 * [난이도]
	 * 
	 * 
	 */
	
	static int N; // 고객의 수
	static int minDistance; // 최단 거리
	static int startX, startY, endX, endY;
	static boolean[] visited;
	static ArrayList<int[]>clientXY;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 고객의 수

			StringTokenizer st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken()); // 회사의 좌표
			startY = Integer.parseInt(st.nextToken());

			endX = Integer.parseInt(st.nextToken()); // 집의 좌표
			endY = Integer.parseInt(st.nextToken());

			clientXY = new ArrayList<>(); // N명의 고객의 좌표
	
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				clientXY.add(new int[] { x, y });
			}
			
			minDistance = Integer.MAX_VALUE;		
			
			visited = new boolean[N]; // 방문 여부
			visit(startX, startY, 0, 0);
			sb.append("#").append(tc).append(" ").append(minDistance).append("\n");
			
		}
		System.out.println(sb);
	}

	public static void visit(int x, int y, int cnt, int totalDistance) {		
		if (cnt == N) { // 종료 조건
			totalDistance += Math.abs(x-endX) + Math.abs(y-endY);
			minDistance = Math.min(minDistance, totalDistance); 
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			int[] cur = clientXY.get(i);
			
			int cx = cur[0];
			int cy = cur[1];
			
			visit(cx, cy, cnt+1,totalDistance + Math.abs(cx-x) + Math.abs(cy-y));
			
			visited[i] = false;
			
		}
		
	}

}
