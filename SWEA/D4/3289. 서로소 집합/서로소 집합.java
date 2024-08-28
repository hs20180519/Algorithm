import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] parents;

	// 각 원소가 자기 자신을 부모로 가리킴
	static void make(int N) {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	// 경로 압축 기법 사용하여 부모 노드 반환
	static int findSet(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = findSet(parents[a]);
	}

	// 합집합
	static boolean union(int a, int b) {
		int aRoot = findSet(a); // a의 부모
		int bRoot = findSet(b); // b의 부모

		// 두 집합의 대표자가 같으면 이미 같은 집합이므로 합집합 연산 불가
		if (aRoot == bRoot)
			return false;

		// 두 집합 합치기 : aRoot에 bRoot를 흡수
		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/d0828/input_SWEA_3289")); // TODO : X

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 집합 개수
			int M = Integer.parseInt(st.nextToken()); // 연산 횟수

			parents = new int[N + 1]; // 초기화

			make(N);

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int unionOrFind = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				// unionOr Find 0 : 합집합
				if (unionOrFind == 0) {
					union(a, b);
				}

				// unionOr Find 1 : 같은 집합 확인
				else if (unionOrFind == 1) {
					int aRoot = findSet(a);
					int bRoot = findSet(b);

					if (aRoot == bRoot) { // 같은 집합 O
						sb.append(1);
					} else { // 같은 집합 X
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
