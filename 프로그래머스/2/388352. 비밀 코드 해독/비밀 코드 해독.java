import java.util.*;

class Solution {
    int[][] q;
    int[] ans;
    int n;
    int m;
    int answer;
    public int solution(int nn, int[][] qq, int[] aa) {
        answer = 0;
        q = qq;
        ans = aa;
        n = nn;
        m = q.length;
        
        // combi + dfs
        
        dfs(0, new int[5], 1);
        
        return answer;
    }
    
    public void dfs(int depth, int[] temp, int start){
        if(depth == 5){
            if(isValid(temp)) answer++;
            return;
        }
        for(int i = start; i<=n; i++){
            temp[depth] = i;
            dfs(depth+1, temp, i+1);
        }
    }
    
    public boolean isValid(int[] temp){
        Set<Integer> set = new HashSet<>();
        for(int x: temp) set.add(x);
        
        for(int i=0; i<m; i++){
            int match = 0;
            for(int x : q[i]){
                if(set.contains(x)) match++;
            }
            if(match != ans[i]) return false;
            
        }
        return true;
    }
}