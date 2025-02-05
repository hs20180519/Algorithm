
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 0, 1, 0, 1, -1, 1, -1};
    static int[] dy = {0, 1, 0, -1, 1, 1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0)
                break;

            int[][] map = new int[h][w];


            boolean[][] visited = new boolean[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int land = 0;
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    if(map[i][j] == 1 && !visited[i][j]){
                        bfs(w, h, i, j, map, visited);
                        land++;
                    }
                }
            }
            System.out.println(land);
        }
    }

    public static void bfs(int w, int h, int x, int y, int[][] map, boolean[][] visited){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while(!q.isEmpty()){
            int[] c = q.poll();
            int cx = c[0];
            int cy = c[1];

            for(int i=0; i<8; i++){
                int nx = cx+dx[i];
                int ny = cy+dy[i];

                if(0<=nx && nx<h && 0<=ny && ny<w && map[nx][ny]== 1 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                };
            }
        }
    }
}
