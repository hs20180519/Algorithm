import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 과목의 수
		int M = Integer.parseInt(st.nextToken()); // 선수 조건의 수
		ArrayList<Integer>[] graph = new ArrayList[N+1];
		int[] inDegree = new int[N+1];
		int[] ans = new int[N+1];
		
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			inDegree[b]++;
		}
		
		Queue<int[]> q = new LinkedList<>();
		for(int i=1; i<=N; i++) {
			if(inDegree[i] == 0) {
				q.add(new int[] {i, 1});
			}
		}
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int c = curr[0];
			int d = curr[1];
			ans[c] = d;
			
			for(int next : graph[c]) {
				inDegree[next]--;
				if(inDegree[next]==0) {
					q.add(new int[] {next, d+1});
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			System.out.print(ans[i]+ " ");
		}
	}
	

}
