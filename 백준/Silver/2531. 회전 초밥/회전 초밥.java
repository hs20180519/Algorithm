
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥 가지 수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수 (슬라이딩 윈도우 크기)
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		int[] chobab = new int[N];
		int[] eaten = new int[d+1];
		eaten[c] = 3001; // 최대값
		int count = 1;

		for(int i=0; i<N; i++){
			chobab[i] = Integer.parseInt(br.readLine());
		}

		for(int i=0; i<k; i++){
			int e = chobab[i];
			if(eaten[e] == 0) count++;
			eaten[e]++;
		}

		int max = count;
		for(int i=0; i<N-1; i++){
			int start = chobab[i];
			int end = chobab[i+k<N ? i+k : (i+k) % N];

			if(--eaten[start] == 0) count--;
			if(++eaten[end] == 1) count++;
			max = Math.max(max, count);
		}

		System.out.println(max);

	}
}
