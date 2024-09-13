import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class Solution {
//	public static void main(String[] args) {
//		int[][] land = new int[][] { { 0, 0, 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 0, 0 }, { 1, 1, 0, 0, 0, 1, 1, 0 },
//				{ 1, 1, 1, 0, 0, 0, 0, 0 }, { 1, 1, 1, 0, 0, 0, 1, 1 } };
//		System.out.println(solution(land));
//	}

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visited;
	static int[] landSize;
	
	public static int solution(int[][] land) {
		int answer = 0;
		int n = land[0].length; // 가로 8
		int m = land.length; // 세로 5
		
		// bfs를 돌아 땅 번호 저장
		// 개수는 따로 기록해두기
	
		visited = new boolean[m][n];
		int landNumber = 1;
		landSize = new int[(n*m)/2+1];
		
		// 땅 번호 기록
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(land[i][j] == 1 && !visited[i][j]) {
					// 땅 번호 변경 및 땅 크기 저장
					landSize[landNumber] = bfs(i, j, land, landNumber,n, m);
					landNumber++;
				}
			}
		}
		

		// 0열 ~ n열까지 비교
		// 같은 landNumber면 더하지 않는다. (set 사용)
		for(int i=0; i<n; i++) {
			int temp = 0;
			TreeSet<Integer> s = new TreeSet<>(); // 땅 번호를 저장할 트리셋
			for(int j=0; j<m; j++) {
				if(land[j][i] !=0 && !s.contains(land[j][i])) { // 0이 아니고 이미 셋에 없다면
					s.add(land[j][i]);
					temp += landSize[land[j][i]];
				}
			}
			answer = Math.max(temp, answer);
		}
		
		return answer;
	}
	
	public static int bfs(int x, int y, int[][] land, int landNumber, int n, int m) {
		Queue <int[]> q = new LinkedList<>();
		q.add(new int[] {x,y});
		visited[x][y] = true;
		land[x][y] = landNumber;
		int count = 1; // 석유 덩어리 크기
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			for(int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				if(0<=nx && nx<m && 0<=ny && ny<n && !visited[nx][ny] && land[nx][ny]== 1) {
					q.add(new int[] {nx, ny});
					visited[nx][ny] = true;
					land[nx][ny] = landNumber;
					count++;
				}
			}
		}
		return count;
	}

}