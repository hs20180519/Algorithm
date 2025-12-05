import java.util.*;
class Solution {
    int n;
    boolean[] visited; 
    int answer = -1;
    public int solution(int k, int[][] dungeons) {
        n = dungeons.length;
        visited = new boolean[n];
        dfs(0, k, dungeons);
        return answer;
    }
    
    public void dfs(int depth, int piludo, int[][] dungeons){
        // System.out.println(piludo);
        answer = Math.max(depth, answer);
        
        for(int i=0; i<n; i++){
            if(!visited[i] && dungeons[i][0] <= piludo){
                visited[i] = true;
                dfs(depth+1, piludo-dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }
    }
}