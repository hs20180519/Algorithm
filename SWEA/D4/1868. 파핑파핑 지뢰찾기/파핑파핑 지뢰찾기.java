

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	/*
	 * 
	 * [문제 풀이를 위한 아이디어]
	 * 입력받을 때 *은 -1로, 나머지는 주변 별의 수로 변경한다.
	 * 0인 것은 bfs로 돌고, 나머지는 팔방탐색하여 count를 증가시킨다.
	 * 
	 * [느낀점]
	 * 앞으로는 범위 확인 함수를 밖으로 빼야겠다.
	 * 
	 */
	
	
    // 방향 배열 (8방향 탐색)
    private static final int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    private static final int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
    	//System.setIn(new FileInputStream("res/d0826/input_SWEA_1868"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine()); // 표의 크기
            char[][] grid = new char[N][N];
            boolean[][] visited = new boolean[N][N];

            // 지뢰 찾기 표 입력 받기
            for (int i = 0; i < N; i++) {
                grid[i] = br.readLine().toCharArray();
            }

            // 지뢰 주변의 숫자 표시를 위한 2차원 배열 (지뢰는 -1로 표시)
            int[][] mineCount = new int[N][N];

            // 지뢰 주변의 숫자 계산
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] == '*') {
                        mineCount[i][j] = -1; // 지뢰 있는 칸은 -1로 표시
                        for (int d = 0; d < 8; d++) {
                            int ni = i + dx[d];
                            int nj = j + dy[d];
                            if (isInBounds(ni, nj, N) && grid[ni][nj] == '.') { // 범위 안이고, 비어있다면
                                mineCount[ni][nj]++;
                            }
                        }
                    }
                }
            }

            int clickCount = 0;

            // 먼저 0인 칸들에 대해 BFS 실행
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (mineCount[i][j] == 0 && !visited[i][j]) {
                        bfs(i, j, N, mineCount, visited);
                        clickCount++;
                    }
                }
            }

            // 남아있는 모든 안전한 칸을 클릭
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (mineCount[i][j] > 0 && !visited[i][j]) {
                        clickCount++;
                    }
                }
            }

            System.out.println("#" + tc + " " + clickCount);
        }
    }

    // BFS를 사용하여 0인 칸과 연결된 모든 칸을 탐색
    private static void bfs(int x, int y, int N, int[][] mineCount, boolean[][] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int dir = 0; dir < 8; dir++) {
                int nx = cx + dx[dir];
                int ny = cy + dy[dir];

                if (isInBounds(nx, ny, N) && !visited[nx][ny] && mineCount[nx][ny] >= 0) {
                    visited[nx][ny] = true;
                    if (mineCount[nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    // 범위 확인 함수
    private static boolean isInBounds(int x, int y, int N) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
