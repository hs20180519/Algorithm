
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    // 가로 길이(M) * 세로 길이(N)의 arr를 선언하고 0으로 채우기
    // 배추가 심어진 길이만큼 for문을 돌려 해당 위치에 1로 채우기
    // bfs 돌며 count ++

    private static int[][] arr;
    private static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    private static int[] dy = {0, 0, -1, 1};
    private static boolean[][] visited;
    private static int m;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스
        for(int test_case =0; test_case<t; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken()); // 가로
            n = Integer.parseInt(st.nextToken()); // 세로
            int k = Integer.parseInt(st.nextToken()); // 배추 개수

            arr = new int[m][n];
            for (int i = 0; i < m; i++) { // 배추 배열을 0으로 초기화
                for (int j = 0; j < n; j++) {
                    arr[i][j] = 0;
                }
            }

            for (int i = 0; i < k; i++) { // 배추 위치 받아 1로 변경
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                arr[x][y] = 1;
            }

            int count = 0;
            visited = new boolean[m][n]; // 방문 배열
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && arr[i][j] == 1) {
                        bfs(i, j);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    private static void bfs(int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        visited[x][y]= true; // 방문 처리
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int nx = current[0];
            int ny = current[1];

            for(int i=0; i<4; i++){
                int cx = nx+dx[i];
                int cy = ny+dy[i];

                if (cx>=0 && cx<m && cy>=0 && cy<n && arr[cx][cy] == 1 && !visited[cx][cy]){
                    queue.add(new int[]{cx, cy});
                    visited[cx][cy] = true;
                }
            }
        }
    }


}
