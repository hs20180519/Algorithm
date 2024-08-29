import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	/* 
	 * [문제 풀이를 위한 아이디어] 
	 * 입력을 받을 때, 간선으로 받는다
	 * Edge클래스를 만들어서 start, end, weight를 받는다.
	 * 클래스 내에서 compareTo를 재정의한여 weight를 오름차순 정렬한다.
	 * (최소 가중치를 만들어야 하기 때문에 가장 낮은 weight를 가진 간선부터 선택해야 하기 때문이다.)
	 * 
	 * a와 b를 union 연산하고, 연산이 되면 서로의 부모 노드가 다르다는 것이니
	 * 선택한 간선의 수 + 1, cost에 weight를 누적한다.
	 * 선택한 간선의 수가 노드개수 -1, 즉 V-1이 되면 멈춘다.
	 * 
	 */
	
	
	static int V;
	static int E;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 정점의 개수
			E = Integer.parseInt(st.nextToken()); // 간선의 개수

			Edge[] edges = new Edge[E]; // 간선들

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); // 노드 a
				int b = Integer.parseInt(st.nextToken()); // 노드 b
				int c = Integer.parseInt(st.nextToken()); // 가중치 c
				edges[i] = new Edge(a, b, c); // a, b 사이의 가중치 c를 갖는 간선
			}

			Arrays.sort(edges); // 최소 스패닝을 위해 weight 기준 오름차순 정렬
			make();
			
			long count = 0, cost = 0;
			for (Edge e : edges) { // 간선들 차례로 돌며 union
				if (union(e.a, e.b)) {
					count++; // 선택한 간선의 수
					cost += e.weight; // 간선의 가중치 합
					if (count == V - 1)
						break; // 간선의 수가 N-1 되면 종료
				}
			}
			System.out.printf("#%d %d\n", tc, cost);
		}

	}

	static void make() { // 자신을 부모 노드로 설정
		parents = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			parents[i] = -1;
		}
	}

	static int findSet(int a) { // 루트 노드 찾음
		if (parents[a] < 0)
			return a;
		return parents[a] = findSet(parents[a]);
	}

	static boolean union(int a, int b) { // 합집합
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		if (aRoot == bRoot)
			return false; // 부모 노드가 같으면 합칠 수 없음

		parents[aRoot] += parents[bRoot]; // aRoot를 부모 노드로 하여 합침
		parents[bRoot] = aRoot;
		return true;
	}

	static class Edge implements Comparable<Edge> { // 간선 클래스
		int a;
		int b;
		int weight;

		public Edge(int a, int b, int weight) {
			super();
			this.a = a;
			this.b = b;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) { // weight 오름차순
			return Integer.compare(this.weight, o.weight);
		}

	}
}
