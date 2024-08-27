import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	/*
	 * 
	 * [문제 풀이를 위한 아이디어]
	 * 사이클이 없고, 선행 작업이 먼저 필요한 경우 -> 위상 정렬
	 * 
	 * graph는 각각의 인덱스가 ArrayList로 이루어져있으며, 노드가 선행 관계일 경우 후행 노드들을 추가한다.
	 * (4 1 일 경우, graph[4]에 1을 추가한다. 즉 graph[4] = {1})
	 * indegree[to]++ 진입차수를 증가한다.
	 * (4 1 경우 graph[1]++; 즉 1번 노드에 들어오는 진입 차수를 하나 증가한다.)
	 * 
	 * 위상 정렬을 사용하여
	 * 큐를 사용하여 진입차수가 0인 정점을 큐에 삽입하고,
	 * 큐가 빌 때까지 큐에서 하나씩 꺼낸다. 
	 * ans에 꺼낸 수를 집어넣는다 -> 일을 끝낼 수 있는 작업 순서. 입력 차수가 0이면 바로 답에 추가 된다.
	 * 그 후 꺼낸 노드의 후행 관계에 있는 노드들의 진입 차수를 하나씩 줄이고(indegree[i]--)
	 * 0이면 큐에 추가한다.(q.add[i])
	 * 
	 * 
	 * [난이도 및 느낀점]
	 * 생소했지만 이해했다 !
	 * 앞으로도 선행 관계가 있는 경우의 문제를 풀 수 있게 되었다.
	 * 입력 받을 때 from 에 to를 추가하는 것과, 진입차수라는 새로운 배열 생성에 주의한다.
	 * 
	 */
	
	static int V, E;
	static ArrayList<Integer>[] graph;
	static int[] indegree;
	static ArrayList<Integer> ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken()); // 정점의 개수
			E = Integer.parseInt(st.nextToken()); // 간선의 개수

			indegree = new int[V + 1]; // 진입 차수 개수
			graph = new ArrayList[V + 1]; // 각 노드별 다음 진입 가능한 노드들
			ans = new ArrayList<>(); // 가능한 작업 순서

			for (int i = 1; i <= V; i++) { // 노드 초기화
				graph[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				graph[from].add(to); // graph에 추가
				indegree[to]++; // 진입 차수 추가
			}

			topologicalSort(); // 위상 정렬

			System.out.print("#" + tc + " ");
			for (int a : ans) {
				System.out.print(a+" ");
			}
			System.out.println();
		}
	}

	public static void topologicalSort() {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i < V; i++) {
			if (indegree[i] == 0) { // 진입차수가 0인 노드들을 큐에 삽입
				q.add(i);
			}
		}
		while (!q.isEmpty()) {
			int now = q.poll();
			ans.add(now); // 현재 뽑은 것을 답에 삽입
			for (int i : graph[now]) { // 꺼낸 노드의 후행 관계에 있는 노드들을 뽑음
				indegree[i]--; // 진입 차수를 줄이고
				if (indegree[i] == 0) { // 0일 경우 큐에 삽입
					q.add(i);
				}
			}
		}
	}

}
