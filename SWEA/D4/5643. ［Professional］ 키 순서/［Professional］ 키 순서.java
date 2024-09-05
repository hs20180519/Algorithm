
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    /*
1
6
6
1 5
3 4
5 4
4 2
4 6
5 2
     */
    static int N;
    static int M;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] reverseGraph;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            graph = new ArrayList[N + 1];
            reverseGraph = new ArrayList[N + 1];


            for (int i = 0; i < N + 1; i++) {
                graph[i] = new ArrayList<>();
                reverseGraph[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[to].add(from);
                reverseGraph[from].add(to);
            }

            int ans = 0;
            for (int i = 0; i < N+1; i++) {
                visited = new boolean[N+1];
                int parent = dfs(i, graph);
                int child = dfs(i, reverseGraph);
                if (parent + child == N-1){
                    ans ++;
                }
            }
            System.out.println("#"+tc+" "+ans);
        }
    }

    // dfs로 그래프 탐색하여 노드 개수 세기
    public static int dfs(int node, ArrayList<Integer>[] g){
        visited[node] = true;
        int count = 0;

        for(int next : g[node]){
            if (!visited[next]){
                count += 1 + dfs(next, g);
            }
        }
        return count;
    }
}
