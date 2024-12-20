import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 중복있이 순열
	 * 비내림차순 조건 추가
	 */
	static int N, M;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		permute(0, new int[M]);
		System.out.println(sb);
	}
	public static void permute(int cnt, int[] answers) {
		// 증가하는 수열이 아니면 return
		if(!isIncreasing(cnt, answers)) {
			return;
		}
		
		if(cnt == M) {
			for(int i=0; i<cnt; i++) {
				sb.append(answers[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=1; i<=N; i++) {
			answers[cnt] = i;
			permute(cnt+1, answers);
		}
		
	}
	
	// 증가하는 수열이면 true
	public static boolean isIncreasing(int cnt, int[] answers) {
		for(int i =1; i<cnt; i++) {
			if (answers[i-1] > answers[i]) return false;
		}
		return true;
	}
}
