import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 중복 없이 M개를 고른 조합
	 */
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		combination(0,1, new int[M]);
	}
	public static void combination(int cnt, int startIndex, int[] answers) {
		if(cnt == M) {
			for(int i=0; i<cnt; i++) {
				System.out.print(answers[i]+" ");
			}
			System.out.println();
			return;
		}
		for(int i=startIndex; i<=N; i++) {
			answers[cnt] = i;
			combination(cnt+1, i+1, answers);
		}
	}
}
