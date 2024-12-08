
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수열의 길이
		int M = Integer.parseInt(st.nextToken()); // 합

		int[] arr = new int[N]; // 수열
		st = new StringTokenizer(br.readLine());
		for(int n=0;n<N; n++){
			arr[n] = Integer.parseInt(st.nextToken());
		}

		int start = 0;
		int end = 0;
		int sum = 0;
		int answer = 0;

		while(start<N){
			if(sum >= M) sum -= arr[start++]; // sum이 M보다 크면, 새로운 현재 값을 빼주고 start 포인터를 한 칸 이동
			else if(end == N) break;
			else sum += arr[end++]; // sum이 M보다 작으면 sum에 현재 값을 더해준 후 end 포인터를 한 칸 이동

			if(sum==M) answer++;
		}

		System.out.println(answer);
	}

}
