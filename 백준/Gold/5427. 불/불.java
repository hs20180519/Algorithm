
import java.io.*;
import java.util.*;

public class Main {

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static char[][] building;
	static boolean[][] visited;
	static boolean[][] bvisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		for(int t=1; t<=T; t++){
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			building = new char[h][w];
			visited = new boolean[h][w];
			bvisited = new boolean[h][w];

			for(int i=0; i<h; i++){
				building[i] = br.readLine().toCharArray();
			}

			Queue<int[]> fireQ = new LinkedList<>();
			Queue<int[]> sangeunQ = new LinkedList<>();

			for(int i=0; i<h; i++){
				for(int j=0; j<w; j++){
					if(building[i][j] == '@') { // 상근 위치
						visited[i][j] = true;
						sangeunQ.add(new int[] {i, j, 1});
					}else if(building[i][j] == '*') { // 불
						fireQ.add(new int[] {i, j});
						bvisited[i][j] = true;
					}
				}
			}

			int answer = -1;
			while(!sangeunQ.isEmpty()) {
				// 1. 불
				int fireLen = fireQ.size();
				for (int f = 0; f < fireLen; f++) {
					int[] c = fireQ.poll();
					int cx = c[0];
					int cy = c[1];


					for (int d = 0; d < 4; d++) {
						int nx = cx + dx[d];
						int ny = cy + dy[d];
						if (isInBound(nx, ny, w, h) && building[nx][ny] != '#' && !bvisited[nx][ny]) {
							building[nx][ny] = '*';
							fireQ.add(new int[] {nx, ny});
							bvisited[nx][ny] = true;


						}
					}
				}
				// 2. 상근 이동
				int sangeunLen = sangeunQ.size();
				for (int s = 0; s < sangeunLen; s++) {

					int[] c = sangeunQ.poll();
					int cx = c[0];
					int cy = c[1];
					int time = c[2];


					if (isEntrance(cx, cy, w, h)) {
						answer = time;
                        sangeunQ.clear();
						break;
					}

					for (int d = 0; d < 4; d++) {
						int nx = cx + dx[d];
						int ny = cy + dy[d];

						if (isInBound(nx, ny, w, h) && !visited[nx][ny] && building[nx][ny] == '.') {
							sangeunQ.add(new int[] {nx, ny, time + 1});
							visited[nx][ny] = true;
						}
					}
				}

			}

			if (answer == -1){
				sb.append("IMPOSSIBLE");
			}
			else{
				sb.append(answer);
			}

			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static boolean isEntrance(int x, int y, int w, int h){ // 둘 중 하나라도 0이라면 출구
		return x == 0 || y == 0 || x == h-1 || y == w-1;
	}

	public static boolean isInBound(int x, int y, int w, int h){
		return 0<=x && x<h && 0<=y && y<w;
	}

}
