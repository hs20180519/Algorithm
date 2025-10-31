import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 중복 없이 M개를 고른 순열
	 */
	static int N, M;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N+1];
		permute(0, new int[M]);
	}
	public static void permute(int cnt, int[] answers) {
		if(cnt == M) {
			for(int i=0; i<cnt; i++) {
				System.out.print(answers[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=1; i<=N; i++) {
			if(visited[i])continue;
			answers[cnt] = i;
			visited[i] = true;
			permute(cnt+1, answers);
			visited[i] = false;
		}
	}
}
