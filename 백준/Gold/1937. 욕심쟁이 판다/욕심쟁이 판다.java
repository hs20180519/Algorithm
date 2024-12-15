
import java.io.*;
import java.util.*;

// 욕심쟁이 판다
public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        
        int[][] arr = new int[N][N];
        int[][] dp = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxMove = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxMove = Math.max(maxMove, dfs(i, j, arr, dp, N));
            }
        }
        System.out.println(maxMove);
    }

    public static int dfs(int x, int y, int[][] arr, int[][] dp, int N){
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        dp[x][y] = 1;

        for(int d=0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(isInBound(nx, ny, N) && arr[nx][ny] > arr[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(nx, ny, arr, dp, N) + 1);
            }
        }

        return dp[x][y];
    }

    public static boolean isInBound(int x, int y, int N){
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
