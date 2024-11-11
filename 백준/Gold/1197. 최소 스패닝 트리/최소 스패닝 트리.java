import java.util.*;
import java.io.*;

public class Main {
    // 프림 : 정점 위주의 알고리즘, 간선이 많은 경우 유리
    // 크루스칼 : 간선 위주의 알고리즘, 정점이 많은 경우 유리

    // 정점의개수가 10,000이며 간선의 개수가 100,000이 될 수 있으므로 프림사용
    public static boolean[] visited;
    public static List<int[]> [] list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); // 정점
        int E = Integer.parseInt(st.nextToken()); // 간선

        list = new ArrayList[V+1];
        visited = new boolean[V+1];
        for(int i=1; i<= V; i++){
            list[i] = new ArrayList<>();
        }

        for(int e = 0; e<E; e++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new int[]{b, c});
            list[b].add(new int[]{a, c});
        }

        System.out.println(prim(1));
    }
    public static int prim(int start){
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
        pq.add(new int[]{start, 0});

        int total = 0;
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int s = curr[0];
            int weight = curr[1];

            if(visited[s]) continue;

            visited[s] = true;
            total += weight;

            for(int[] n : list[s]){
                if(!visited[n[0]]){
                    pq.add(n);
                }
            }
        }
        return total;
    }
}
