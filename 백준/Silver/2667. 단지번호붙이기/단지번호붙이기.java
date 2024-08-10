
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[][] arr;
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int n;
    private static int m; // 단지 수
    private static int count; // 각 단지 내 집의 수
    private static ArrayList<Integer> ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n][n];
        ans = new ArrayList<>(n);
        visited = new boolean[n][n];
        for(int i =0; i<n; i++){
            String string = br.readLine();
            for(int j =0; j<n; j++){
                arr[i][j] = string.charAt(j) -'0';
            }
        }

        for (int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                if(arr[i][j] ==1 && !visited[i][j]) {
                    m++;
                    count = 0;
                    bfs(i,j);
                    ans.add(count);
                }
            }
        }
        Collections.sort(ans);
        System.out.println(m); // 단지 수
        for (Integer an : ans) {
            System.out.println(an);
        }


    }

    private static void bfs(int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;
        count++;
        while(!queue.isEmpty()){
            int[] current = queue.poll();

            int cx = current[0];
            int cy = current[1];
            for(int i = 0; i <4; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (nx >= 0 && ny >=0 && ny < n && nx < n && arr[nx][ny] == 1 && !visited[nx][ny]){

                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    count++;
                }
            }
        }
    }
}
