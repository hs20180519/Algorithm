import java.util.*;

class Solution {
    static int ans = 0;
    public int solution(int k, int[][] dungeons) {
        int len = dungeons.length;
        cases(dungeons, k, 0, len, new int[len], new boolean[len]);
        return ans;
    }
    
    public void cases(int[][] dungeons, int k, int cnt, int len, int[] temp, boolean[] visited){
        if(cnt == len){
            // 탐험
            int tamhum = 0;
            int currentK = k;
            for(int i=0; i<len; i++){
                int index = temp[i];
                
                if(dungeons[index][0] <= currentK){ // 최소 필요 피로도가 현재 피로도보다 작으면
                    tamhum++;
                    currentK -= dungeons[index][1];
                } else{
                    break;
                }
            }
            
            ans = Integer.max(tamhum, ans);
            return;
        }
        
        // 순열(중복 X)
        for(int i=0; i<len; i++){
            temp[cnt] = i;
            if(!visited[i]){
                visited[i] = true;
                cases(dungeons, k, cnt+1, len, temp, visited);
                visited[i] = false;
            }
        }
        
    }
      
}