
import java.util.*;
import java.io.*;
public class Main {
	// 슬라이딩 윈도우
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken()); // 슬라이딩 윈도우의 크기
		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		long sum =0;
		for(int i=0; i<m; i++){ // 맨 처 음
			sum += arr[i];
		}

		long ans = sum;
		for(int end = m; end < n; end++){ // 3, 4
			int start = end - m;

			sum -= arr[start];
			sum += arr[end];
			ans = Math.max(ans, sum);
		}

		System.out.println(ans);

 	}
}
