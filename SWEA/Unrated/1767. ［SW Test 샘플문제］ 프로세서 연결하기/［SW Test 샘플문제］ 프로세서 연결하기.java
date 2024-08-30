import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	/*
	 * [문제 풀이를 위한 아이디어] 
	 * 가장자리가 아닌 코어의 좌표를 cores에 저장
	 * dfs로 코어의 좌표를 하나씩 꺼낸다
	 * 		코어의 좌표에서 네 방향으로 전선을 설치
	 * 		dfs(코어의 좌표 + 1, 고른 코어 수 + 1, 기존 전선의 길이 + 현재 설치한 전선 수)
	 * 
	 * connect와 setWire함수를 만들어서 전선을 몇개 놓을 수 있는지 세고, 전선을 놓는다.
	 * 
	 */
	
	
	static int N;
	static int[][] grid;
	static List<int[]> cores;
	
	static int maxCountCores, minWireLength;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine()); // cell 크기
			cores = new ArrayList<>();
			grid = new int[N][N]; // 멕시노스 정보 저장 배열
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
					if(!isOnEdge(i, j) && grid[i][j] == 1) { // 가장자리가 아니고, 1이면 코어에 추가
						cores.add(new int[] {i, j});
					}
				}
			}
			
			maxCountCores = 0; // 최대 코어 개수
			minWireLength = Integer.MAX_VALUE; // 최소 전선 개수
			dfs(0, 0, 0);
			System.out.printf("#%d %d\n",tc, minWireLength);
			
		}
		
	}
	
	// 가장자리에 있으면 true
	// 가장자리에 없으면 false
	static boolean isOnEdge(int x, int y) {
		return (x==0 || x == N-1 || y == 0 || y == N-1);
	}
	
	static void dfs(int index, int connectedCores, int wireLength) {
		if(index == cores.size()) { // 다 돌았을 때
			if(connectedCores > maxCountCores) { // 코어 수가 클 때 (기존 최대 코어 수보다)
				maxCountCores = connectedCores;
				minWireLength = wireLength;
			}else if(connectedCores == maxCountCores) { // 코어 수가 같을 때는 전선 수가 더 적은 것을 고름
				minWireLength = Math.min(wireLength, minWireLength);
			}
			return;
		}
		
		int[] core = cores.get(index);
		int x = core[0];
		int y = core[1];
		
		// 네 방향으로 전선을 연결해보려는 시도
		for(int dir=0; dir<4; dir++) {
			int len = connect(x, y, dir);
			if(len != -1) {
				setWire(x, y, dir, 2); // 전선을 설치
				dfs(index+1, connectedCores+1, wireLength+len);
				setWire(x, y, dir, 0); // 전선을 설치 취소
			}
		}
        // 전선을 연결하지 않는 경우도 고려
        dfs(index + 1, connectedCores, wireLength);
        
	}
	
	// 연결 가능한 전선의 개수를 return
	// 연결 가능하지 않을 때 -1 return
	static int connect(int x, int y, int dir) {
		int length = 0;
		int nx = x+dx[dir];
		int ny = y+dy[dir];
		
		while(0<= nx && nx < N && 0 <= ny && ny < N) {
			if(grid[nx][ny] != 0) { // 다른 코어나 전선이 있는 경우
				return -1;
			}
			nx += dx[dir];
			ny += dy[dir];
			length++;
		}
		return length;
	}
	
	// 전선을 dir 방향대로 설치함
	// value 값으로 채움
	static void setWire(int x, int y, int dir, int value) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		while(0<= nx && nx < N && 0 <= ny && ny < N) {
			grid[nx][ny] = value;
			nx += dx[dir];
			ny += dy[dir];
		}
	}
}
