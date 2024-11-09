import java.io.*;
import java.util.*;

public class Solution {
	// 완전 탐색
    
	static int N, X, M;
	static ArrayList<int[]> hamsters;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 햄스터 우리의 개수
			X = Integer.parseInt(st.nextToken()); // 각 우리의 햄스터 마리 수 (0마리 이상 X마리 이하)
			M = Integer.parseInt(st.nextToken()); // 기록의 개수
			hamsters = new ArrayList<>();

			allHamsters(0, new int[N]);


			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				// l번 우리부터 r번 우리까지 햄스터 수가 s마리
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());

				filterHamsters(l, r, s);

			}

			sb.append("#").append(tc).append(" ");

			int size = hamsters.size();
			if(size == 0){
				sb.append("-1");
			}
			else {
				int[] largeHamster = getLargestHamsters();
				for (int j = 0; j < N; j++) {
					sb.append(largeHamster[j]).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}


	// 모든 햄스터 경우의 수
	public static void allHamsters(int cnt, int[] temp){
		if (cnt == N){
			// System.out.println(Arrays.toString(temp));
			hamsters.add(temp.clone());
			return;
		}
		for(int i=0; i<=X; i++){
			temp[cnt] = i;
			allHamsters(cnt+1, temp);
		}
	}

	// 경우의 수에서 조건에 맞는 경우만 필터링
	public static void filterHamsters(int l, int r, int s) {
		ArrayList<int[]> filteredHamsters = new ArrayList<>();

		for(int[] a : hamsters){
			int sum = 0;

			for(int i=l-1; i<r; i++){
				sum+= a[i];
			}

			if(sum == s){
				filteredHamsters.add(a);
			}
			hamsters = filteredHamsters;
		}

	}

	// 햄스터 수가 가장 많은 경우의 수 반환
	// 이때 가장 먼저 반환 된 수가 사전 순으로 앞선다
	public static int[] getLargestHamsters(){
		int maxSum = 0;
		int[] ans = new int[N];
		for(int[] a : hamsters){
			int sum = 0;
			for(int b : a){
				sum+= b;
			}
			if(sum > maxSum){
				maxSum = sum;
				ans = a.clone();
			}
		}
		return ans;
	}
}
