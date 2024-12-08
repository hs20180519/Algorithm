import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws  IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()); // DNA 문자열 길이
		int P = Integer.parseInt(st.nextToken()); // 비밀번호 문자열 길이

		char[] dna = new char[S];
		dna = br.readLine().toCharArray();

		int[] check = new int[4];

		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++){
			check[i] = Integer.parseInt(st.nextToken());
		}

		int[] arr = new int[4]; // arr[0] = 3 : 0번째 문자(A)가 나온 횟수

		for(int i=0; i<P; i++){ // 첫 부분 문자열 세팅
			switch (dna[i]){
				case 'A': arr[0]++; break;
				case 'C': arr[1]++; break;
				case 'G': arr[2]++; break;
				case 'T': arr[3]++; break;
			}
		}

		int answer = 0;
		if(isValid(arr, check)) answer++;

		// 부분 문자열 만들기 => 이전 부분문자열의 첫 문자는 제외하고, 끝에서 한 문자를 추가
		for(int end=P; end<S; end++){ // 부분 문자열의 끝 위치
			int start = end-P; // 이전 부분문자열의 시작

			// 이전 부분문자열의 시작 문자 제외
			switch (dna[start]){
				case 'A': arr[0]--; break;
				case 'C': arr[1]--; break;
				case 'G': arr[2]--; break;
				case 'T': arr[3]--; break;
			}

			// 이전 부분문자열의 끝에서 한 문자 추가
			switch (dna[end]){
				case 'A': arr[0]++; break;
				case 'C': arr[1]++; break;
				case 'G': arr[2]++; break;
				case 'T': arr[3]++; break;
			}

			if(isValid(arr, check)) answer++;
		}
		System.out.println(answer);
	}

	static boolean isValid(int[] arr, int[] check){
		for(int i=0; i<4; i++){
			if(arr[i] < check[i]) return false;
		}
		return true;
	}
}
