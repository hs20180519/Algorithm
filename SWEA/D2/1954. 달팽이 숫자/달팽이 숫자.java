//package ssafy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
	
	// 원래의 방향 d를 유지하되, 범위를 벗어나거나 이미 채워져 있는 경우 우회전

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("./res/input_1954_달팽이숫자"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        // 방향 벡터: 우, 하, 좌, 상
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine()); // 배열의 크기
            int[][] arr = new int[N][N];
            
            int x = 0, y = 0; // 시작 좌표
            int count = 1; // 현재 숫자
            int d = 0; // 현재 방향 (우)

            while (count <= N * N) {
                // 현재 위치에 숫자 저장
                arr[x][y] = count++;
                
                // 다음 위치 계산
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                // 다음 위치가 범위를 벗어나거나 이미 채워져 있는 경우 방향 전환
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || arr[nx][ny] != 0) {
                    d = (d + 1) % 4; // 방향 전환
                    nx = x + dx[d];
                    ny = y + dy[d];
                }
                
                // 다음 위치로 이동
                x = nx;
                y = ny;
            }
            
            // 결과 출력
            System.out.printf("#%d\n", tc);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(String.format("%d ", arr[i][j])); // 숫자를 3자리로 맞추어 출력
                }
                System.out.println();
            }
        }
        br.close(); // BufferedReader 닫기
    }
}
