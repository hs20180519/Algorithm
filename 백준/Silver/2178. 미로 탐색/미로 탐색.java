import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


// 너비 우선 탐색(bfs) 사용
// 시작 좌표를 큐에 넣음
// 상 -> 하 -> 좌 -> 우 순으로 돌며, 새로운 좌표를 찾음
// 새로운 좌표가 범위 안(0<=row<n && 0<=col<m)이거나 방문한 적 없으면(arr[row][col]==1) 
// 기존 값(1)에 +1 해서 저장
// 값이 2가 되며 (방문 처리 및 지나야하는 칸 수가 됨)
// 새로운 좌표를 큐에 넣음
// 이 과정을 큐가 빌 때까지(더 갈 수 있는 좌표가 없을 때 까지) 반복

public class Main{
	private static int n;
	private static int m;
	private static int[] dx; // x 좌표
	private static int[] dy; // y 좌표
	private static int[][] arr; // 입력받은 칸
	
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // row
        m = Integer.parseInt(st.nextToken()); // col
        arr = new int [n][m]; // row * col의 배열 생성
        
        dx = new int[]{-1, 1, 0, 0}; // 상 하 좌 우
        dy = new int[] {0, 0, -1, 1}; // 상 하 좌 우
        
        for(int i=0; i<n; i++) { // arr에 입력받은 배열 int형으로 저장
        	String line = br.readLine();
        	for(int j =0; j<m; j++) {
        		arr[i][j] = line.charAt(j) -'0';
        	}
        }
        
        bfs(0,0); // (0,0)부터 너비 우선 탐색
        System.out.println(arr[n-1][m-1]); // 탐색 후 마지막 인덱스 출력
        
    }
    private static void bfs(int x, int y) {
    	Queue<int[]>queue = new LinkedList<>(); // 큐 선언
    	queue.add(new int[] {x,y}); // x, y 값 큐에 넣기
    	
    	while(!queue.isEmpty()) { // 큐가 비지 않을 때
    		int[] c = queue.poll();
    		int cx = c[0]; // 큐에서 꺼낸 첫번째 x
    		int cy = c[1]; // 큐에서 꺼낸 첫번째 y
    		
    		for(int i=0; i<4; i++) { // 4 방향 순회
    			int nx = cx + dx[i]; // 새로운 nx
    			int ny = cy + dy[i]; // 새로운 ny
    			
    			// 새로운 nx와 ny가 범위 안이거나 arr[nx][ny]를 방문하지 않았으면
    			if(nx>=0 && ny>=0 && nx < n && ny <m && arr[nx][ny]==1) {
    				arr[nx][ny] = arr[cx][cy] + 1; // 전의 값 + 1 (이동 칸 증가)
    				queue.add(new int[] {nx,ny}); // 새로운 값을 큐에 넣기 
    			}
    		}
    	}
    }
    
}

