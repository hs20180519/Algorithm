import java.util.*;

class Solution {
    
    ArrayList<Integer>[] tree;
    int[] info;
    int max = 0;
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        int n = info.length;
        
        tree = new ArrayList[n];
        for(int i=0; i<n; i++){
            tree[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges){
            tree[edge[0]].add(edge[1]);
        }
        
        ArrayList<Integer> next = new ArrayList<>();
        next.add(0); // 0부터 시작
        
        dfs(0, 0, next);
        return max;
    }
    
    public void dfs(int sheep, int wolf, ArrayList<Integer> next){
        max = Math.max(max, sheep);
        
        for(int node: next){
            int ns = sheep;
            int nw = wolf;
            
            if(info[node] == 0) ns++;
            else nw++;
            
            if(ns <= nw) continue;
            
            ArrayList<Integer> newNext = new ArrayList<>(next);
            newNext.remove(Integer.valueOf(node)); // 방문 노드 제거
            newNext.addAll(tree[node]); // 자식들 추가
            
            dfs(ns, nw, newNext);
        }
        
    }
    
}