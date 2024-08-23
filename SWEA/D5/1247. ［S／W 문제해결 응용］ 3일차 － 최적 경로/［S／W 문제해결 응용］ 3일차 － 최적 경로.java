import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	/*
	 * 
	 * [문제 풀이를 위한 아이디어]
	 * 1. 순열로 모든 경우의 수를 구한다.
	 * 2. 각각의 순열에서 회사의 좌표로 시작하고, 고객의 집을 전부 돈다면 집의 좌표로 끝낸다.
	 * 3. 이동거리는 (현재x좌표 - 과거x좌표)의 절대값 +  (현재y좌표 - 과거y좌표)로 구한다.
	 * 4. 모든 경우의 수를 구했다면, 집까지의 거리 또한 더해 이동 거리에 누적한다.
	 * 5. 이동거리와 최단거리를 비교하여 최단거리보다 이동거리가 작으면, 최단거리를 갱신한다.
	 * 6. 백트래킹 : 이동 거리가 현재의 최단거리보다 크다면 더 이상 계산하지 않는다.
	 * 
	 * 
	 * [난이도]
	 * 재귀는 참 어렵다.
	 * 
	 *  totalDistance += Math.abs(cx - x) + Math.abs(cy - y); 
	 *  visit(cx, cy, cnt + 1, totalDistance);              
	 *  totalDistance -= Math.abs(cx - x) + Math.abs(cy - y);
	 *  
	 * 처음에는 이런식으로 구현했는데, totalDistance의 값이 돌아와도, 마지막에 집과의 거리를 구하며
	 * totalDistance값 자체가 달라지므로 단순히 빼서 돌아오는 것으로 풀면 안된다.
	 * 
	 * 애초에 재귀함수에 visit(cx, cy, cnt + 1, totalDistance + Math.abs ...) 이런식으로 넣어줘야 한다.
	 * 
	 * 
	 */
	
	static int N; // 고객의 수
	static int minDistance; // 최단 거리
	static boolean[] visited; // 순열을 위한 방문 배열
	static int startX, startY, endX, endY; // 회사의 좌표, 집의 좌표
	static ArrayList<int[]>clientXY; // 고객들의 좌표
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/d0823/input_SWEA_1247"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 고객의 수

			StringTokenizer st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken()); // 회사의 좌표 (시작점)
			startY = Integer.parseInt(st.nextToken());

			endX = Integer.parseInt(st.nextToken()); // 집의 좌표 (끝점)
			endY = Integer.parseInt(st.nextToken());

			clientXY = new ArrayList<>(); // N명의 고객의 좌표
	
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				clientXY.add(new int[] { x, y });
			}
			
			minDistance = Integer.MAX_VALUE; // 최단 거리	
			
			visited = new boolean[N]; // 방문 여부
			visit(startX, startY, 0, 0);
			sb.append("#").append(tc).append(" ").append(minDistance).append("\n");
			
		}
		System.out.println(sb);
	}

	// 고객들의 집을 순서대로 방문하고, 전부 방문했으면 집까지의 거리를 더함
	public static void visit(int x, int y, int cnt, int totalDistance) {	
		if (cnt == N) { // 모든 고객의 집을 방문했으면 (cnt : 방문한 고객 집 수)
			totalDistance += Math.abs(x-endX) + Math.abs(y-endY); // 마지막 집까지의 거리를 더함
			minDistance = Math.min(minDistance, totalDistance);  // 최단 거리 갱신
			return;
		}
		
		if (totalDistance > minDistance) { // 백트래킹 : 총 거리가 현재의 최단거리보다 크다면 더 볼 필요 X
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i]) continue; // 방문했으면 X
			
			visited[i] = true; // 방문 처리
			int[] cur = clientXY.get(i); // 고객 집의 좌표 가져옴
			
			int cx = cur[0]; // 고객의 x 좌표
			int cy = cur[1]; // 고객의 y 좌표
			
			// cnt + 1 : 현재 방문한 집 수 +1
			// totalDistance + .. : 지금 위치와 고객의 집의 위치를 계산하여 totalDistance에 더함
			visit(cx, cy, cnt+1,totalDistance + Math.abs(cx-x) + Math.abs(cy-y));
			
			// 방문 취소
			visited[i] = false;
			
		}
		
	}

}
