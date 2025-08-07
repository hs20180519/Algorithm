import java.util.*;

class Solution {
    int ans = 0;
    public int solution(int k, int[][] dungeons) {
        // 순열
        // System.out.println(Arrays.toString(dungeons));
        int n = dungeons.length;
        perm(0, new int[n], k, dungeons, new boolean[n]);
        return ans;
    }
    
    public void perm(int depth, int[] temp, int k, int[][] dungeons, boolean[] visited){
        if(depth==dungeons.length){ // 다 골랐으면
            // System.out.println(Arrays.toString(temp));
            ans = Math.max(ans, calc(k, dungeons, temp));
            return;
        }
        for(int i=0; i<dungeons.length; i++){
            if(!visited[i]){
                visited[i] = true;
                temp[depth] = i;
                perm(depth+1, temp, k, dungeons, visited);
                visited[i] = false;
            }
        }
    }
    
    public int calc(int k, int[][] dungeons, int[] temp){
        int answer = 0;
        for(int i=0; i<dungeons.length; i++){
            if(k >= dungeons[temp[i]][0]){
                k -= dungeons[temp[i]][1];
                answer++;
            }
        }
        return answer;
    }
}