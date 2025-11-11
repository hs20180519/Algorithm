import java.util.*;

class Solution {
    int count = 0;
    public int solution(int[] number) {
        dfs(0, 0, 0, number, new boolean[number.length]);
        return count;
    }
    
    public void dfs(int depth, int start, int sum, int[] number, boolean[] visited){
        if(depth==3){
            if(sum == 0) count++;
            return;
        }
        
        for(int i=start; i<number.length; i++){
            if(!visited[i]){
                visited[i] = true;
                dfs(depth+1, i+1, sum+number[i], number, visited);
                visited[i] = false;
            }
        }
    }
}