import java.io.*;
import java.util.*;
// 가장 긴 증가하는 부분 수열
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        int[] dp = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1; // dp 배열 초기화
        }

        for(int i=1; i<=N; i++){ // i : 현재
            for(int j=1; j<i; j++){ // j : 그 전
                if(arr[j] < arr[i])
                dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }
       
        int ans = 0;
        for(int i=1; i<=N; i++){
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
