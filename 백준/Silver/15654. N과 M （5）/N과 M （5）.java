import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 순열
	
	static int N, M;
	static int[] arr;
	static StringBuilder sb;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		permute(0, new int [M]);
		System.out.println(sb);
	}
	public static void permute(int cnt, int[] answers) {
		if(cnt == M) {
			for(int i=0; i<cnt; i++) {
				sb.append(answers[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=0; i<N; i++) {
			if(!visited[i]) {
				answers[cnt] = arr[i];
				visited[i] = true;
				permute(cnt+1, answers);
				visited[i] = false;
			}	
		}
	}
}
