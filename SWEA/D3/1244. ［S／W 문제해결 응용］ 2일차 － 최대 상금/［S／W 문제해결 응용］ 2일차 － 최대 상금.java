import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static char[] numbers;
	static int N, answer;
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/d0903/input_SWEA_1244.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // tc
		
		for(int tc = 1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			numbers = st.nextToken().toCharArray();
			N = Integer.parseInt(st.nextToken());

			// N의 횟수가 주어진 숫자판의 개수보다 많으면 N개로 축소
			if(N > numbers.length){
				N = numbers.length;
			}

			answer = Integer.MIN_VALUE;
			makeMaxPrize(N, 0);
			System.out.println("#"+tc+" "+answer);
		}
	}
	public static void makeMaxPrize(int count, int startIndex){
		if(count == 0){
			int number = 0;
            for (char c : numbers) {
                number = number * 10 + (c - '0');
            }
			answer = Math.max(answer, number);
			return;
		}
		// 교환할 숫자 2개 찾기
		for (int i = startIndex; i < numbers.length -1 ; i++) {
			for (int j = i+1; j < numbers.length ; j++) {
				swap(i, j);
				makeMaxPrize(count-1, i);
				swap(i, j);
			}

		}
		
		
	}

	public static void swap(int a, int b) {
		char temp = numbers[a];
		numbers[a] = numbers[b];
		numbers[b] = temp;
	}
}
