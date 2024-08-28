
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	/*
	 * 
	 * [문제 풀이를 위한 아이디어]
	 * 조합 및 판별
	 * 1. 입력받고 정렬
	 * 2. 조합 중 최소 한개의 모음, 최소 두개의 자음이면 출력
	 * 
	 * [난이도]
	 * 순조부
	 * 
	 */
	
	static int L, C;
	static char[] arr;
	static char[] ans;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken()); // 몇 개 뽑는지
		C = Integer.parseInt(st.nextToken()); // 문자의 종류
		
		arr = new char[C];
		visited = new boolean[C];
		ans = new char[L];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(arr);
		//System.out.println(Arrays.toString(arr));
		
		combination(0, 0);
	
	}
	public static void combination(int depth, int startIndex) {
		if(depth==L && !check(ans)) {
			return;
		}
		
		if(depth==L && check(ans)) { // + 최소 한개의 모음과 두개의 자음이라면
			for(char a: ans) {
				System.out.print(a);
			}
			System.out.println();
			return;
		}
		
		for(int i=startIndex; i<C; i++) {
			ans[depth] = arr[i];
			combination(depth+1, i+1);
		}
	}
	
	public static boolean check(char[] arr) {
		int mCount = 0; // 모음 개수
		int jCount = 0; // 자음 개수
		for(char a : arr) {
			if (a=='a' || a=='e' || a =='i' || a=='o' || a=='u') {
				mCount++;
			}else {
				jCount++;
			}
		}
		return (mCount>=1 && jCount >= 2);
		
	}
	

}
