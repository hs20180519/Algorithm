import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	/*
	 * [문제 풀이 방법]
	 * LIS[i] : i원소를 증가부분수열의 마지막으로 하는 증가부분수열의 최장 길이 값
	 * 현재의 수가 이전의 수보다 크고, 
	 * 그 전의 최장 길이+1보다 현재의 최장길이가 작으면
	 * LIS[i]값을 갱신한다.
	 * 
	 * LIS의 최대값이 답이 된다.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); 
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for(int tc = 1; tc<=T; tc++) {
			
			int N = Integer.parseInt(br.readLine()); // 수열의 길이
			int[] arr = new int[N];
			int[] LIS = new int[N]; // 최장증가부분수열 배열
			
			st = new StringTokenizer(br.readLine()); // 수열 입력
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int maxNum = 0;
			for(int i=0; i<N; i++) { // 현재 수			
				LIS[i] = 1;
				for(int j=0; j<i; j++) { // 이전의 수
					// 현재의 수가 이전의 수보다 크고, 
					// 그 전의 최장 길이+1보다 현재의 최장길이가 작으면
					// 갱신한다.
					if(arr[j] < arr[i] && LIS[i] < LIS[j]+1) {
						LIS[i] = LIS[j]+1;
					}
					// 최대 값
					maxNum = Math.max(LIS[i], maxNum);
				}
			}
			
			sb.append("#").append(tc).append(" ");
			sb.append(maxNum);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
