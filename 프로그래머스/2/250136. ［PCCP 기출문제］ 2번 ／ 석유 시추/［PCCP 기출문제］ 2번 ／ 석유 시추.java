import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static int[] landSize;

    public static int solution(int[][] land) {
        int answer = 0;
        int n = land[0].length; // 가로
        int m = land.length; // 세로

        // Initialize the visited array and landSize array
        visited = new boolean[m][n];
        int landNumber = 1;
        landSize = new int[(n*m)/2 + 1];
        
        // Find all land clusters and their sizes using BFS
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    landSize[landNumber] = bfs(i, j, land, landNumber, n, m);
                    landNumber++;
                }
            }
        }

        // Calculate the maximum size of land clusters connected vertically
        for (int i = 0; i < n; i++) {
            int temp = 0;
            TreeSet<Integer> s = new TreeSet<>(); // TreeSet to store unique landNumbers
            for (int j = 0; j < m; j++) {
                if (land[j][i] != 0 && !s.contains(land[j][i])) {
                    s.add(land[j][i]);
                    temp += landSize[land[j][i]];
                }
            }
            answer = Math.max(temp, answer);
        }

        return answer;
    }

    public static int bfs(int x, int y, int[][] land, int landNumber, int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y});
        visited[x][y] = true;
        land[x][y] = landNumber;
        int count = 1; // Size of the land cluster

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];
            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                if (0 <= nx && nx < m && 0 <= ny && ny < n && !visited[nx][ny] && land[nx][ny] == 1) {
                    q.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                    land[nx][ny] = landNumber;
                    count++;
                }
            }
        }
        return count;
    }
}
