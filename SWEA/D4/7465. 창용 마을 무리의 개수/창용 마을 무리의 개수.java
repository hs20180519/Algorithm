import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	/*
	 * 
	 * [문제 풀이를 위한 아이디어] 
	 * 서로소 union
	 * -1로 초기화한다.
	 * 
	 * a b
	 * -1 -1
	 * 
	 * 합집합시
	 * a의 root에 b의 root값을 더한다 -> people[rootA] += people[rootB]
	 * 
	 * a b
	 * -2 -1
	 * 
	 * b의 값을 a의 인덱스로 변경한다 -> people[rootB] = rootA;
	 * a b
	 * -2 0
	 * 
	 * 
	 * [난이도]
	 * 서로소 집합을 -1로 초기화하고 더하는 값에 대해 배움
	 * 
	 */
	static int[] people;

	public static void make() { // 집합 초기화
		for (int i = 0; i < people.length; i++) {
			people[i] = -1;
		}
	}

	public static int find(int a) {
		if (people[a] < 0) // 루트 노드이면 자기 자신 반환
			return a;
		return people[a] = find(people[a]);
	}

	public static boolean union(int a, int b) { // 합집합
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB) {
			return false;
		}

		people[rootA] += people[rootB];
		people[rootB] = rootA;
		
		return true;
	}

	public static int checkMuri(int N) { 
		int count = 0;

		for (int i = 1; i <= N; i++) { //0보다 작으면 루트 노드 -> 무리가 하나
			if (people[i] < 0)
				count++;
		}
		return count;
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/d0828/input_SWEA_7465.txt")); // TODO : X
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 살고 있는 사람 수
			int M = Integer.parseInt(st.nextToken()); // 관계 수

			people = new int[N+1]; // 초기화
			make();

			for (int i = 0; i < M; i++) { // 관계 수만큼 받음
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				union(a, b); // a와 b 합집합
			}
			System.out.printf("#%d %d\n", tc, checkMuri(N));
		}
	}
}
