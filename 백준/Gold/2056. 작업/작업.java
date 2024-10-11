import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	// 위상정렬
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 작업의 수
		
		List<Integer>[] list = new ArrayList[N+1]; // 인접 리스트 배열
		int[] times = new int[N+1]; // 각 작업의 시간
		int[] inDegree = new int[N+1]; // 진입 차수
		int[] dp = new int[N+1]; // 최소 시간
		
		for(int i=0; i<=N; i++) {
			list[i] = new ArrayList<>(); 
		}
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken()); // 선행 작업의 수
			for(int j=0; j<count; j++) {
				int task = Integer.parseInt(st.nextToken());
				list[task].add(i);
				inDegree[i]++;
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(inDegree[i] == 0) { // 진입차수가 0이면 큐에 작업
				queue.add(i);
				dp[i] = times[i]; // 선행 작업이 없으면 작업의 시간이 곧 최소
			}
		}
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for(int next : list[current]) {
				inDegree[next]--;
				dp[next] = Math.max(dp[next], dp[current]+times[next]); // 최소 시간 갱신
				if(inDegree[next] == 0) { // 진입차수가 0이면 큐에 추가
					queue.add(next);
				}
			}
		}
		int result = 0;
		for(int d : dp) {
			result = Math.max(result, d);
		}
		System.out.println(result);
	}

}
