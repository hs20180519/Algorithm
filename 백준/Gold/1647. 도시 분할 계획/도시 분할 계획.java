import java.util.*;
import java.io.*;

public class Main {
	static ArrayList<int[]> edgeList;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 집의 개수
		int M = Integer.parseInt(st.nextToken()); // 길의 개수
		
		// 크루스칼로 최소 경로 비용을 구한 후, 가장 많은 비용 간선 끊기
		edgeList = new ArrayList<>();
		for(int i = 0; i< M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edgeList.add(new int[] {a, b, c});
		}
		
		edgeList.sort((o1, o2)-> o1[2]-o2[2]); // 반드시 오름차순 정렬
		
		parents =  new int[N+1];
		for(int i=1; i<=N; i++) {
			parents[i] = i;
		}
		
		int ans = 0;
		int maxWeight = 0;
		for(int i=0; i<M; i++) {
			int[] curr = edgeList.get(i);
			int a = curr[0];
			int b = curr[1];
			int weight = curr[2];
			
			if(find(a) != find(b)) {
				ans += weight;
				maxWeight = Math.max(weight, maxWeight);
				union(a, b);
			}
		}
		System.out.println(ans - maxWeight);
	}
	
	public static int find(int x) {
		if(parents[x] == x) return x;
		return parents[x] = find(parents[x]);
	}
	
	public static void union(int x, int y) {
		int xParent = find(x);
		int yParent = find(y);
		
		if(xParent != yParent) {
			parents[xParent] = yParent;
		}
	}

}
