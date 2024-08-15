//package d0815;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("res/d0815/input_SWEA_2001"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 배열 크기
            int M = Integer.parseInt(st.nextToken()); // 파리채 크기
            int[][] arr = new int[N+1][N+1]; // 입력 배열

            // 누적 합 배열 계산 위해, 첫번째 행과 열은 빼고 입력 받음
            // 첫번째 행과 열은 0
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    arr[i + 1][j + 1] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] sum_arr = new int[N+1][N+1]; // 누적합 배열

            // 누적합 : 원래 있는 수 + 누적합 왼쪽수 + 누적합 위쪽수 - 누적합 왼쪽&누적합 위쪽 겹친 수(대각선 수)
            // => sum_arr[i][j] = arr[i][j] + sum_arr[i-1][j] + sum_arr[i][j-1] - sum_arr[i-1][j-1]
            for (int i=1; i<N+1; i++){
                for(int j=1; j<N+1; j++){
                    sum_arr[i][j] = arr[i][j] + sum_arr[i-1][j] + sum_arr[i][j-1] - sum_arr[i-1][j-1];
                }
            }
//            for(int i=0; i<N+1; i++) {
//                System.out.println(Arrays.toString(sum_arr[i]));
//            }
            // M 범위의 누적합중 최대 값
            int max_kill_fly = 0;
            for(int i=0; i<=N-M; i++){
                for(int j=0; j<=N-M; j++){
                    max_kill_fly = Math.max(max_kill_fly, sum_arr[i+M][j+M] - sum_arr[i][j+M] - sum_arr[i+M][j] + sum_arr[i][j]);
                }
            }
            sb.append("#").append(tc).append(" ").append(max_kill_fly).append("\n");
        }
        System.out.println(sb);
    }

}
