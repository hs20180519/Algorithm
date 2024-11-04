
import java.io.*;
import java.util.*;


// bfs 시간초과
// 플로이드-워샬 (N^3)

public class Main {
	static int N;
	static int[][] distances;
	static int minDistance = Integer.MAX_VALUE;
	static int x = -1;
	static int y = -1;
	static int INF = 1000000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 건물의 개수 (정점)
		int M = Integer.parseInt(st.nextToken()); // 도로의 개수 (간선)

		// 거리 배열 초기화
		distances = new int[N+1][N+1];
		for (int i = 1; i < N + 1; i++) {
			Arrays.fill(distances[i], INF);
			distances[i][i] = 0; // 자기 자신 0
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			distances[a][b] = 1;
			distances[b][a] = 1;
		}

		// 최단 거리 계산
		flodyWarshall();
		
		 // 두 개 조합 선택
		 chooseTwo(0, 1, new int[2]);
		
		 System.out.println(x+" " + y + " " +minDistance);

	}

	private static void flodyWarshall() {
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N;i++) {
				for(int j=1; j<=N; j++) {
					distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]); 
				}
			}
		}	
	}

	public static void chooseTwo(int cnt, int startIndex, int[] answers) {
		if (cnt == 2) {
			// 모든 도시에서의 왕복 시간의 합 구하기
			int currentDistance = caculateAllDistance(answers);
		
			// 최단거리가 같다면
			// 건물 번호 중 작은게 더 작을 수록, 작은 번호가 같다면 더 큰 번호가 작을 수록
			if(currentDistance < minDistance || 
					(currentDistance == minDistance && (answers[0] < x || (answers[0] == x && answers[1] < y)))) {
				minDistance = currentDistance;
				x = answers[0];
				y = answers[1];
			}
			return;
		}

		for (int i = startIndex; i <= N; i++) {
			answers[cnt] = i;
			chooseTwo(cnt + 1, i + 1, answers);
		}
	}

	public static int caculateAllDistance(int[] answers) {
		int a = answers[0]; // 치킨집 위치
		int b = answers[1];
		int dist = 0;
	
		for (int i = 1; i < N + 1; i++) { // 1번부터 치킨집 위치까지의 최단 거리 찾음
			if (i != a && i != b) {
				dist += Math.min(distances[i][a], distances[i][b]) * 2;
			}
		}
		return dist;
	}

}
