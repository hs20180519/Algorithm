import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 입력
		for(int tc = 1; tc<= T; tc ++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 초기 n개의 집합
			int M = Integer.parseInt(st.nextToken()); // m개의 연산
			parents = new int[N+1]; // 부모 노드
			
			make(); // 부모 설정
			System.out.printf("#%d ", tc); // 테스트 케이스 출력
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int unionOrFind = Integer.parseInt(st.nextToken()); // 0 -> 합집합, 1 -> 같은 집합 확인
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				
				// 같은 집합 확인
				if(unionOrFind == 1) {
					int aRoot = findSet(a);
					int bRoot = findSet(b);
					
					if(aRoot == bRoot) { // 있으면 1
						System.out.print(1);
					}
					else { // 없으면 0
						System.out.print(0);
					}
				}
				
				// 합집합
				else if(unionOrFind == 0) {
					union(a, b);
				}
			
			}
			System.out.println();
		}
	}
	
	// 초기 부모 설정
	static void make() {
		for(int i=1; i<=N; i++) {
			// 자신의 부모를 자신으로 하여 대표자가 되도록 설정
			parents[i] = i;
		}
	}
	
	// a가 속해있는 집합 찾음
	static int findSet(int a) {
		// 자신이 자신의 부모라면 루트노드이고 집합의 대표자가 됨
		if(a==parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	// 합집합
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		// 두 집합의 대표자가 같으면 이미 같은 집합이므로 합집합 연산 불가
		if(aRoot==bRoot) return false;
		
		// aRoot에 bRoot를 흡수 : 두 집합 합치기
		parents[bRoot] = aRoot;
		return true;
	}
}
