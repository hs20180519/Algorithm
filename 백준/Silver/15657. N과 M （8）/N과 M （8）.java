import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 같은 수를 여러 번 골라도 된다 -> 중복 순열 -> visited 사용 X
	 * 비내림차순
	 */

	static int N, M;
	static int[] arr;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr); // 오름차순 정렬
		permute(0, new int[M]);
		System.out.println(sb);

	}

	public static void permute(int cnt, int[] answers) {
		// 비내림차순이 아니면
		if(!isSorted(cnt, answers)) return;
		
		// 다 뽑았으면 sb에 추가
		if (cnt == M) {
			for (int i = 0; i < cnt; i++) {
				sb.append(answers[i]).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			answers[cnt] = arr[i];
			permute(cnt + 1, answers);
		}
	}
	
	public static boolean isSorted(int cnt, int[] answers) {
		for(int i=1; i<cnt; i++) {
			if(answers[i-1] > answers[i]) return false;
		}
		return true;
	}
}
