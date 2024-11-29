import java.util.*;
import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			ArrayList<Integer>[] graph = new ArrayList[V + 1];
			int[] inDegree = new int[V + 1];

			for (int i = 1; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a].add(b);
				inDegree[b]++;
			}
			sb.append("#").append(tc).append(" ");

			Queue<Integer> q = new LinkedList<>();
			for (int i = 1; i <= V; i++) {
				if (inDegree[i] == 0) {
					q.add(i);
//					sb.append(i).append(" ");
				}
			}

			while (!q.isEmpty()) {
				int c = q.poll();
				sb.append(c).append(" ");

				for (int next : graph[c]) {
					inDegree[next]--;
					if (inDegree[next] == 0) {
						q.add(next);
					}
				}

			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
