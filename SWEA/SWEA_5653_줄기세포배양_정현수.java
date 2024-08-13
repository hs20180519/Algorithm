package ssafy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

// 예외 케이스가 있음 -> 보완 필요
public class SWEA_5653_줄기세포배양_정현수 {
	
    /*
     * 1. 입력받을 그리드의 크기를 N, M에서 양쪽에 K를 더한 값으로 설정
     *    (그리드의 중앙에 입력된 데이터를 배치하기 위해)
     * 2. 입력받은 x, y 좌표를 +K하여 그리드의 중앙에 위치시키고, 
     *    세포의 생명력 정보를 큐에 저장
     * 3. 큐는 생명력 수치가 높은 세포가 우선적으로 처리되도록 
     *    우선순위 큐를 사용하여 내림차순으로 정렬
     * 4. K시간 동안 시뮬레이션을 진행하며, 세포의 활성화 및 번식을 처리
     *    - 세포가 활성화되면 사방으로 번식 시도 (BFS 방식)
     *    - 번식할 위치는 방문하지 않은 좌표만 고려하여 처리
     * 5. 각 시간마다 번식된 세포를 큐에 추가하고, 최종적으로 살아남은 세포의 수를 계산
     * 6. 결과를 출력
     */
	
	private static int [][] arr;
	private static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌
	private static int[] dy = {0, 1, 0, -1}; // 상 우 하 좌
	private static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./res/input_5653_줄기세포배양"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트 개수 입력
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 세로 크기
			int M = Integer.parseInt(st.nextToken()); // 가로 크기
			int K = Integer.parseInt(st.nextToken()); // 배양 시간
			
			// 그리드의 크기를 설정 (배양 시간이 K이므로 양쪽에 K를 더한 크기로 설정)
			arr = new int[K+N+K][K+M+K];
			// 방문 여부를 추적할 배열 (그리드 크기와 동일)
			visited = new boolean[K+N+K][K+M+K];
			
			// 생명력이 큰 순서대로 정렬(내림차순)
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[2]-o2[2]));
               
			// 그리드 초기화 및 세포 정보를 우선순위 큐에 삽입
			for(int i=K; i<N+K; i++) { // index K부터 입력 받기
				st = new StringTokenizer(br.readLine());
				for(int j=K; j<M+K; j++) {
					int life = Integer.parseInt(st.nextToken()); // 생명력 수치
					arr[i][j] = life;
					if (life > 0) { // 생명력이 0보다 클 경우에만 큐에 삽입
						pq.offer(new int[] {i, j, life, life+life}); // {x좌표, y좌표, 생명력, 활성화 시간}
					}
				}			
			}
	
			// K시간 동안 시뮬레이션
			for(int k =1; k<=K; k++) {
				// 번식된 세포들을 담기 위한 새로운 큐 선언
				ArrayDeque<int[]> q = new ArrayDeque<>();
				
				// 기존 우선순위 큐에서 세포를 하나씩 꺼내어 처리
				while(!pq.isEmpty()) {
					int[] xy = pq.poll();
					xy[3]--; // 세포의 활성화 시간을 줄임 (시간 경과)
					
					// 세포가 활성화 상태로 진입할 때 (활성화 시간의 절반이 지나면 번식 시작)
					if(xy[2] > xy[3]) {
						for(int d=0; d<4; d++) { // 4방향으로 번식 시도
							int nx = xy[0]+dx[d];
							int ny = xy[1]+dy[d];
						
							// 새로운 좌표가 아직 방문되지 않았으면 번식
							if(!visited[nx][ny]) {
								visited[nx][ny] = true;
								q.offer(new int[] {nx, ny, xy[2], xy[2]+xy[2]}); // 번식된 세포를 큐에 삽입
							}
						}
					}
					// 세포가 아직 활성화 상태를 유지하고 있으면 다시 큐에 삽입    
					if (xy[3] > 0) { 
						q.offer(xy);
					}
				}
				// 현재 시간에 번식된 세포들을 우선순위 큐에 다시 삽입
				pq.addAll(q);
			}
			// 최종적으로 살아남은 (활성 상태 및 비활성 상태인) 세포의 수를 기록
			sb.append("#").append(tc).append(" ").append(pq.size()).append("\n");
		}
		System.out.println(sb.toString());
	}
}