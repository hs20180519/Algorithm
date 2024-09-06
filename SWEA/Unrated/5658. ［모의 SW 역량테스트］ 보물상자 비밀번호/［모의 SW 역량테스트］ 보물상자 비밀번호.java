import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	
	/*
	 * 1. 숫자를 String으로 입력받는다.
	 * 2. numLen(N/4)만큼 나눠 HashSet에 저장한다.
	 * 3. 내림차순 정렬을 위해 PQ를 선언하여 HashSet안의 수들을 집어넣는다.
	 * 4. K번째까지 poll하여 답을 구한다.
	 * 
	 */
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/d0906/input_SWEA_5658.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 숫자의 개수
			int K = Integer.parseInt(st.nextToken()); // 크기 순서

			String input = br.readLine();
			int numLen = N/4; // 수의 길이 및 돌아야하는 회전 수
			Set<String> numSet = new HashSet<>();
			
			for(int i=0; i<numLen;i++) {
				for(int j=0; j<N; j+= numLen) {
					// 생성 가능한 수를 hashSet에 넣음(중복X)
					String s = input.substring(j, j+numLen);
					numSet.add(s);
				}
				// 숫자가 앞으로 한칸씩 당겨짐
				input = input.charAt(N-1) + input.substring(0, N-1);
			}
			
			// 오름차순 정렬위해 pq 사용
			PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2)-> o2-o1);
			for(String num : numSet) {
				pq.add(Integer.parseInt(num, 16)); // 16진수로 변경
			}
			
			// K번째까지 뽑음
			int ans = 0;
			while(K>0) {
				ans = pq.poll();
				K--;
			}
			System.out.println("#"+tc+" "+ans);
		}
	}
}
