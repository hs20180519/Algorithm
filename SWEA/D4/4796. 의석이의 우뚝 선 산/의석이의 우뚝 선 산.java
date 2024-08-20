
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {


	static int[] m;
	static int N;
	static ArrayList<Integer> topIndex;

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테스트 케이스 수
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 산의 길이
			m = new int[N];

			for (int i = 0; i < N; i++) {
				m[i] = sc.nextInt(); // 산의 높이
			}
			//System.out.println(Arrays.toString(m));
			topIndex = new ArrayList<>();

			for (int i = 1; i < N - 1; i++) { // 꼭대기의 인덱스를 topIndex에 추가
				if (m[i - 1] < m[i] && m[i] > m[i + 1]) { // 올라갈 때
					topIndex.add(i);
				}
			}

			int ans = 0;
			for(int index : topIndex){
				int left = countLeft(index);
				int right = countRight(index);
				ans += left*right;
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
		public static int countLeft(int index){
			int count = 0;
			for(int i=index; i>0; i--){
				if(m[i] > m[i-1]){
					count++;
				}
				else{
					break;
				}
			}
			return count;
		}
	public static int countRight(int index){
		int count = 0;
		for(int i=index; i<N-1; i++){
			if(m[i] > m[i+1]){
				count++;
			}
			else{
				break;
			}
		}
		return count;
	}

}

