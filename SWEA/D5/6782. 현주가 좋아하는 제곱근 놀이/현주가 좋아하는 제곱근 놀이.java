
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	/*
	 * [문제 풀이를 위한 아이디어] 
	 * 가장 먼저 제곱근 N을 만들고, 만들지 못하면 +1 2가 될 때 까지 반복한다.
	 * 이때 +1을 계속하면 시간 초과가 날 것이니, 미리 다음 제곱근까지의 수를 계산하여 더한다.
	 * 
	 * [난이도]
	 * 최적화가 중요하다..! 무작정 +1을 했는데 N이 10^12라서 시간 초과가 났다.
	 * 미리 다음 제곱근을 구해서 계산하면 계산량이 N(1)로 줄어든다.
	 * 최적화만 신경쓰면 평이한 문제였던 것 같다.
	 * 
	 * int : 10^9
	 * long : 10^18
	 * double : 10^308 (소숫점)
	 * 범위에 주의해서 풀자 !!
	 * 
	 */
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/d0821/input_SWEA_6782"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		StringBuilder st = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			// N의 범위는 10^12이므로 long
			long num = Long.parseLong(br.readLine()); 
			
			// 조작 횟수
			int count = 0; 
			
			while (num != 2) {
				long sqrt = (long) Math.sqrt(num); // 제곱근
				
				// (1) 제곱근으로 바꿀 수 있는 경우
				// 5의 제곱근은 2.236 ... 	2.236 * 2.236 != 5이므로 false
				// 4의 제곱근은 2			2 * 2 = 4 이므로 true
				if (sqrt * sqrt == num) { 
					num = sqrt; // 숫자 갱신
					count++; // 조작 횟수 + 1
				} 
				
				// (2) 제곱근으로 바꿀 수 없는 경우
				else {
					// 다음 제곱근까지의 수를 세서 count를 늘리고, 수를 갱신함
					// 5의 경우 3 * 3 = 9가 nextSquare이 됨
					// count는 9 - 5, 즉 4가 늘어남
					// num은 9가 됨
					long nextSquare = (sqrt + 1) * (sqrt + 1);
					count += (nextSquare - num);
					num = nextSquare;
				}
			}
			st.append("#").append(tc).append(" ").append(count).append("\n");
		}
		System.out.println(st);
	}

}
