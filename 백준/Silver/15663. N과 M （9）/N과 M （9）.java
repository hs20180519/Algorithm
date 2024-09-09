

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{

	/*
	 * 배열의 주소가 복사되니 ArrayList에 추가할 때 temp배열 만들어서 추가
	 */

	static int N, M, S;
	static int[] arr;
	static StringBuilder sb;
	static boolean[] visited;
	static ArrayList<int[]> answers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	
		Arrays.sort(arr); // 오름차순 정렬
		answers = new ArrayList<>();
		
		permute(0, new int[M]);
		
		for(int[] a : answers) {
			for(int i=0; i<M; i++) {
				sb.append(a[i]).append(" ");
			}
			sb.append("\n");
		}
	

		
		System.out.println(sb);

	}

	public static void permute(int cnt, int[] answer) {
		// 다 뽑았으면 sb에 추가
		if (cnt == M) {
			int[] temp = new int[cnt];
			for(int i=0; i<cnt; i++) {
				temp[i] = answer[i];
			}
			if(!isInAnswer(temp)) {
				answers.add(temp);
				
			}
			
			return;
		}

		for (int i = 0; i <N ; i++) {
			if (!visited[i]) {
				answer[cnt] = arr[i];
				visited[i] = true;
				permute(cnt + 1, answer);
				visited[i] = false;
			}
		}
	}

	
	public static boolean isInAnswer(int[] a ) {
		for(int[] b : answers) {
			if(Arrays.equals(a,  b)) return true;
		}
		return false;
	}
}
