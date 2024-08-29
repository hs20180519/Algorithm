import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 섬의 개수
			
			long[] Xs = new long[N];  // X 좌표 배열
			long[] Ys = new long[N];	// Y 좌표 배열
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				Xs[i] = Long.parseLong(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) { 
				Ys[i] = Long.parseLong(st.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine()); // 환경 부담 세율 실수
			
			// 간선 리스트
			List<Edge> edges = new ArrayList<>();
			
			
			int index = 0;
			for(int i=0; i<N; i++) { // 간선 생성
				for(int j=i+1; j<N; j++) {
					double dist = Math.pow(Xs[i] - Xs[j], 2) + Math.pow(Ys[i] - Ys[j], 2);
					double envFee = E * dist;
					edges.add(new Edge(i, j, envFee));
				}
			}
			
			Collections.sort(edges); // 환경 비용 오름차순 정렬
            
			// 크루스칼 알고리즘을 위한 유니온 파인드 초기화
            parents = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
            }
            
            double result = 0; // 총 비용
            int count = 0; // 선택된 간선의 수
            
            for (Edge edge : edges) {
                if (union(edge.from, edge.to)) {
                    result += edge.envfee;
                    count++;
                    if (count == N - 1) break; // 모든 섬이 연결되었을 때 종료
                }
            }
            System.out.printf("#%d %.0f%n", tc, result);
		
		}
	}
	
	// Union 연산
    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA != rootB) {
            parents[rootB] = rootA;
            return true;
        }
        
        return false;
    }
	// Find 연산
    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

	static class Edge implements Comparable<Edge>{
		int from, to;
		double envfee; // 반올림하여 정수 형태로 출력
		
		public Edge(int from, int to, double envfee) {
			super();
			this.from = from;
			this.to = to;
			this.envfee = envfee;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.envfee, o.envfee);
		}
	}
}
