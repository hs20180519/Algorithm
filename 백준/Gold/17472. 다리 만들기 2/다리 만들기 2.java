import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;

	static int N, M;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static ArrayList<int[]> edgeList;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1. BFS로 섬 번호 매기기
		// 2. [크루스칼 알고리즘]
		// - 모든 간선 구하기
		// 정렬, pq, parents

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		map = new int[N][M];
		boolean[][] visited = new boolean[N][M];

		for(int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int landNum = 1;
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(map[i][j] == 1 && !visited[i][j]){ // 땅이고, 방문한적 없으면
					bfs(i, j, landNum++, visited);
				}
			}
		}
		landNum--;
		edgeList = new ArrayList<>();

		makeEdges();
		System.out.println(findMST(landNum));

	}

	public static void bfs(int x, int y, int landNum, boolean[][] visited){
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{x, y});
		visited[x][y] = true;
		map[x][y] = landNum;

		while(!q.isEmpty()){
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];

			for(int d=0; d<4; d++){
				int nx = cx+dx[d];
				int ny = cy+dy[d];
				if(0<=nx && nx < N && 0<=ny && ny < M && map[nx][ny] == 1 && !visited[nx][ny]){
					q.add(new int[]{nx, ny});
					map[nx][ny] = landNum;
					visited[nx][ny] = true;
				}
			}
		}

	}

	private static void makeEdges() {
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(map[i][j] > 0){ // 섬
					int landNum = map[i][j];

					// 네 방향 탐색
					for(int d=0; d<4; d++){
						int len = 0;
						int nx = i;
						int ny = j;

						while(true){ // 한 방향씩 확인
							nx += dx[d];
							ny += dy[d];

							if(nx < 0 || nx >= N || ny <0 || ny >= M || map[nx][ny] == landNum) break;

							if(map[nx][ny] > 0){ // 섬에 도달한 경우
								if(len >= 2){
									edgeList.add(new int[]{landNum, map[nx][ny], len}); // 현재 섬, 다음 섬, 사이 거리
								}
								break;
							}else{
								len ++;
							}
						}
					}
				}
			}
		}
	}

	private static int findMST(int landNum){
		// 오름차순 정렬
		edgeList.sort((o1, o2) -> o1[2] - o2[2]);

		// 부모 배열
		int[] parents = new int[landNum+1];
		for(int i=1; i<= landNum; i++){
			parents[i] = i;
		}

		int total = 0;
		int usedEdges = 0;

		for(int [] edge : edgeList){
			int start = edge[0];
			int end = edge[1];
			int len = edge[2];
			// 합칠 수 있으면
			if(union(start, end, parents)){
				total += len;
				usedEdges++;
				if(usedEdges == landNum-1) return total;
			}
		}
		return -1;
	}

	private static int find(int x, int[] parents){
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x], parents);
	}

	private static boolean union(int x, int y, int[] parents){
		int pX = find(x, parents);
		int pY = find(y, parents);

		if(pX != pY){
			parents[pX] = pY;
			return true;
		}
		return false;
	}






}
