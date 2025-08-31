import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;

        // dp ? 10억이라 사용 X
//         int[] dp = new int[n+1];
//         dp[0] = 0;
//         for(int i=1; i<=n; i++){
//             if(i%2 == 0){ // 짝수면
//                 dp[i] = Math.min(dp[i-1] + 1, dp[i/2]);
//             }else{
//                 dp[i] = Math.min(dp[i-1] + 1, dp[i/2] + 1);
//             }
//         }
        
//         return dp[n];
        
        while(n > 0){
            if(n % 2 == 0){
                n /= 2;
            }else{
                n--;
                ans++;
            }
        }
        return ans;
    }
}