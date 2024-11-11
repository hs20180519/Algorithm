import java.io.*;
import java.util.*;

public class Main {
	// 최소 비용 - 크루스칼
	// 모든 간선에 대해 오름차순 정렬 후, 사이클이 발생하지 않는 간선들 중 가장 거리가 짧은 간선 선택
	// 서로소 집합(Union, Find) 이용
	
	static int[] parents;
	static ArrayList<int[]> edgeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		int M = Integer.parseInt(br.readLine()); // 연결할 수 있는 선의 수
		StringTokenizer st = null;
		edgeList = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edgeList.add(new int[] {a, b, c});
		}
		
		edgeList.sort((o1, o2) -> o1[2] - o2[2]); // 1. 오름차순 정렬
		
		parents = new int[N+1]; // 2. 부모 배열 초기화
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		int ans = 0;
		for(int i=0; i < M; i++) {
			int[] curr = edgeList.get(i);
			int x = curr[0];
			int y = curr[1];
			int weight = curr[2];
			
			if(find(x) != find(y)) { // 3. 사이클이 아니라면 (부모가 같지 않다면)
				ans += weight; // 4. 최소 비용에 더하고
				union(x, y); // 5. 둘을 union
			}
		}
		System.out.println(ans);
		br.close();
		
	}
	
	public static int find(int x) {
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
	public static void union(int x, int y) {
		int xParent = find(x);
		int yParent = find(y);
		
		if(xParent != yParent) {
			parents[yParent] = xParent;
		}
	}
}
