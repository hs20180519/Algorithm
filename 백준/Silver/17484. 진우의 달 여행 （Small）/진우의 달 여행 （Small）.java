import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      int[][] space = new int[N][M];
      int[][][] dp = new int[N][M][3];// 0: 좌하단, 1: 하단, 2:우하단
      
      
      for(int i=0; i<N; i++){
        st = new StringTokenizer(br.readLine());
        for(int j=0; j<M; j++){
          space[i][j] = Integer.parseInt(st.nextToken());
          Arrays.fill(dp[i][j], Integer.MAX_VALUE);
        }
      }
      
      
      // 1. 첫줄은 그대로
      for(int i=0; i<M; i++){
        for(int j=0; j<3; j++){
          dp[0][i][j] = space[0][i];
        }
      }
      
      for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 3; k++) {
                    if (k == 0 && j + 1 < M) { // 왼쪽 아래
                        dp[i][j][k] = Math.min(dp[i][j][k],
                            Math.min(dp[i - 1][j + 1][1], dp[i - 1][j + 1][2]) + space[i][j]);
                    }
                    if (k == 1) { // 아래
                        int min = Integer.MAX_VALUE;
                        if (dp[i - 1][j][0] != Integer.MAX_VALUE)
                            min = Math.min(min, dp[i - 1][j][0]);
                        if (dp[i - 1][j][2] != Integer.MAX_VALUE)
                            min = Math.min(min, dp[i - 1][j][2]);
                        if (min != Integer.MAX_VALUE)
                            dp[i][j][k] = Math.min(dp[i][j][k], min + space[i][j]);
                    }
                    if (k == 2 && j - 1 >= 0) { // 오른쪽 아래
                        dp[i][j][k] = Math.min(dp[i][j][k],
                            Math.min(dp[i - 1][j - 1][0], dp[i - 1][j - 1][1]) + space[i][j]);
                    }
                }
            }
        }

      // 마지막 줄 최소값 찾기
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < M; j++) {
            for (int k = 0; k < 3; k++) {
                ans = Math.min(ans, dp[N - 1][j][k]);
            }
        }

        System.out.println(ans);
  }
}