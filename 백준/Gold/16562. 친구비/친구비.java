import java.io.*;
import java.util.*;

public class Main {
    
    public static int[] parent;
    public static int[] cost;
    
    public static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    public static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);
        
        if(pa == pb) return;
        
        // 더 작은 비용을 root
        if(cost[pa] < cost[pb]) parent[pb] = pa;
        else parent[pa] = pb;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int M = Integer.parseInt(st.nextToken()); // 친구 관계 수
        int k = Integer.parseInt(st.nextToken()); // 가지고 있는 돈
        
        parent = new int[N+1];
        cost = new int[N+1];
        
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            parent[i] = i;
            cost[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            union(v, w);
        }
        
        boolean[] visited = new boolean[N+1];
        int money = 0;
        
        for(int i=1; i<=N; i++){
            int p = find(i);
            if(!visited[p]){
                visited[p] = true;
                money += cost[p];
            }
        }
        
        if(money <= k) System.out.println(money);
        else System.out.println("Oh no");
        
        // 코드를 작성해주세요
    }
}
