import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

    static int N, W, H, minBricks;
    static int[][] map;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt(); // 구슬을 떨어뜨릴 수 있는 횟수
            W = sc.nextInt(); // 맵의 너비
            H = sc.nextInt(); // 맵의 높이
            map = new int[H][W];

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            minBricks = Integer.MAX_VALUE;
            dfs(0, map);
            if(minBricks == Integer.MAX_VALUE) minBricks = 0;
            System.out.println("#" + tc + " " + minBricks);
        }
        sc.close();
    }

    // 구슬을 떨어뜨리는 모든 경우의 수를 탐색
    static void dfs(int count, int[][] currentMap) {
        if (count == N) { // N번 떨어뜨렸다면 남은 벽돌을 세고 최소값 갱신
            minBricks = Math.min(minBricks, countBricks(currentMap));
            return;
        }

        for (int i = 0; i < W; i++) {
            int[][] newMap = copyMap(currentMap);
            if (breakBricks(i, newMap)) {
                applyGravity(newMap);
                dfs(count + 1, newMap);
            }
        }
    }

    // 벽돌을 터뜨리는 함수 (BFS)
    static boolean breakBricks(int col, int[][] map) {
        for (int row = 0; row < H; row++) {
            if (map[row][col] > 0) { // 벽돌이 있는 경우만
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{row, col, map[row][col]});
                map[row][col] = 0;

                while (!queue.isEmpty()) {
                    int[] current = queue.poll();
                    int x = current[0];
                    int y = current[1];
                    int range = current[2];

                    for (int d = 0; d < 4; d++) {
                        for (int r = 1; r < range; r++) {
                            int nx = x + dx[d] * r;
                            int ny = y + dy[d] * r;
                            if (nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] > 0) {
                                queue.add(new int[]{nx, ny, map[nx][ny]});
                                map[nx][ny] = 0;
                            }
                        }
                    }
                }
                return true; // 벽돌을 깨트린 후 반환
            }
        }
        return false; // 깨트릴 벽돌이 없을 때
    }

    // 벽돌이 깨진 후 중력을 적용
    static void applyGravity(int[][] map) {
        for (int col = 0; col < W; col++) {
            int emptyRow = H - 1;
            for (int row = H - 1; row >= 0; row--) {
                if (map[row][col] > 0) {
                    int temp = map[row][col];
                    map[row][col] = 0;
                    map[emptyRow--][col] = temp;
                }
            }
        }
    }

    // 맵 복사
    static int[][] copyMap(int[][] original) {
        int[][] newMap = new int[H][W];
        for (int i = 0; i < H; i++) {
            System.arraycopy(original[i], 0, newMap[i], 0, W);
        }
        return newMap;
    }

    // 남은 벽돌의 수를 세는 함수
    static int countBricks(int[][] map) {
        int count = 0;
        for (int[] rows : map) {
            for (int cell : rows) {
                if (cell > 0) count++;
            }
        }
        return count;
    }
}
