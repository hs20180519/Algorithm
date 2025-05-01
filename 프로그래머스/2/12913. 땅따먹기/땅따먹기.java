import java.util.*;
class Solution {
    int solution(int[][] land) {
        
        // dp[i][j] - i번째 행에서 j번째 숫자를 밟았을 때 최댓값
        int[][] dp = new int[land.length][4];
        for(int i=0; i<land.length; i++){
            for(int j=0; j<4; j++){
                dp[i][j] = land[i][j];
            }
        }
       
        for(int i=1; i<land.length; i++){ 
            for(int j = 0; j<4; j++){ 
                for(int k =0; k<4; k++){
                    if(k!=j) {
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][k] + land[i][j]);
                    }
                }
            }
        }

        int answer = 0;
        
        for(int k=0; k<4; k++){
            answer = Math.max(answer, dp[land.length-1][k]);
        }
        return answer;
    }
}