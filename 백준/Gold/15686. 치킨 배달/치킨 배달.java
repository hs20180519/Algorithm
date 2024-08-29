import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 
	 * [문제 풀이를 위한 아이디어] 
	 * 1이면 집에 추가, 2면 치킨집에 추가한다.
	 * 조합으로 치킨집이 위치 가능한 모든 경우를 고려한다.
	 * 이때 각각의 치킨집에 따른 집들과의 거리를 구한다.
	 * 가장 최소인 거리를 출력한다.
	 * 
	 * [난이도]
	 * 최단 경로를 구할 필요 없이, 좌표의 차이를 구해야 한다.
	 * bfs 쓸 필요 없이 고정되어있는 집의 좌표를 하나씩 꺼내서 써야한다.
	 * 조합도 인덱스 조합을 만들어
	 * (5개 중 3개를 뽑는 경우 0 1 2 / 0 1 3 ..) 
	 * 치킨집의 좌표를 꺼내와야한다.
	 * 
	 */
	static int[][] map;
	static int N, M;
	static int totalDistance = Integer.MAX_VALUE;
	static List<int[]> chickens = new ArrayList<>();
	static List<int[]> houses = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N]; // 도시		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) { // 집
					houses.add(new int[] {i, j});
				}else if(map[i][j] == 2) { // 치킨집
					chickens.add(new int[] {i, j});
				}
			}
		}
		
		combination(new int[M], 0, 0);
		System.out.println(totalDistance);
	}
	
	// 인덱스 조합
	public static void combination(int[] selected, int startIndex, int cnt) {
		if(cnt == M) { // M개를 뽑았으면
			totalDistance = Math.min(caculateDistance(selected), totalDistance);
			return;
		}
		for(int i=startIndex; i<chickens.size(); i++) {
			selected[cnt] = i;
			combination(selected, i+1, cnt+1);
		}
	}

	// 선택한 치킨집의 위치에 대한 모든 집과의 거리 계산
	public static int caculateDistance(int[] selected) {
		int tot = 0;
		
		for(int[] house: houses) { // 집의 좌표 (2, 1)
			int distance = Integer.MAX_VALUE;
			for(int select : selected) { // 선택한 조합 인덱스 -> 0 1 2, 0 1 3, 0 1 4 ...
				int[] chicken = chickens.get(select); // 치킨집의 위치
				int d = Math.abs(house[0]-chicken[0]) + Math.abs(house[1]-chicken[1]); // 치킨집과 집의 좌표 차이
				distance = Math.min(distance, d); // 최단거리
			}
			tot+= distance;
		}
		return tot;
	}
	

}
