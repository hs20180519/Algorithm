
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 최단 거리 -> bfs

    // bfs : 큐에 넣을 때, {x좌표, y좌표, move} 이때 move는 이동거리
    // 사방팔방으로 이동한 좌표들이 범위 안에 있고, 지나갈 수 있고, 방문하지 않았으면 큐에 추가(nx, ny, move+1)
    // 동시에 물고기가 있는 칸이며, 상어 크기보다 작은 물고기의 크기라면 fishes 배열 에 추가
    // bfs의 리턴값은 ArrayList<int[]>fishes

    // while (true)
    // bfs(x, y)로 현재 갈 수 있는 좌표들을 구함(fishes 반환, x, y는 상어의 현재 위치)
    // if (!fishes.isEmpty()) { fishes 가 비어있지 않을 때
    // 그 ArrayList(fishes)를 거리, x좌표, y좌표 순으로 정렬함
    // 맨 첫번째 요소, fishes.get(0)가 갈 수 있는 최단거리이자 가장 작은 x좌표, y좌표가 담긴 배열이 됨
    // ex) fishes.get(0) : { 3, 0, 1 }
    // 이동거리가 1이고 해당 x 좌표는 3, y 좌표는 0

    // 이동했으므로 해당 좌표로 다시 bfs를 돎
    // int temp[] = fishes.get(0);
    // x = temp[0]; y = temp[1]; distance += temp[2]; }
    // 처음으로 돌아가서 bfs(x, y) ..
    // 이때 거리는 누적하여 더함

    // else{ break; }
    // 더 이상 반환받은 fishes 가 없을 때 종료

    private static boolean[][] visited; // 방문 여부
    private static int[] dx = {-1, 0, 0, 1}; // 상 좌 우 하 (순서 상관X)
    private static int[] dy = {0, -1, 1, 0};
    private static int n;
    private static int shark_size; // 상어 크기
    private static int distance; // 상어의 이동거리
    private static int fish_eaten; // 상어가 먹은 물고기
    private static int shark_x_position; // 상어의 x 위치
    private static int shark_y_position; // 상어의 y 위치


    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 공간의 크기


        arr = new int[n][n];
        shark_size = 2; // 아기 상어의 처음 크기 2
        distance = 0; // 아기 상어의 이동거리 0

        for (int i = 0; i < n; i++) { // 상어 위치 및 물고기 위치 배열 받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) { // 상어 자리 받고, 0으로 변경
                    shark_x_position = i;
                    shark_y_position = j;
                    arr[i][j] = 0;
                }
            }
        }

        distance = 0; // 누적 거리 0으로 초기화
        while (true) {
            visited = new boolean[n][n];
            ArrayList<int[]> fishes = bfs(shark_x_position, shark_y_position);

            // 거리가 작은 순, x좌표가 작은 순, x좌표가 같으면 y좌표가 작은 순으로 정렬
            if (!fishes.isEmpty()) { // 이동 가능한 좌표가 있을 때
                fishes.sort((a, b) -> {
                    // 첫 번째 기준: 거리
                    if (a[2] != b[2]) {
                        return Integer.compare(a[2], b[2]);
                    }
                    // 두 번째 기준 : x좌표
                    else if (a[0] != b[0]) {
                        return Integer.compare(a[0], b[0]);
                    }
                    // 세 번째 기준: y좌표
                    return Integer.compare(a[1], b[1]);
                });
                int[] temp = fishes.get(0); // 첫번째 원소가 최단거리이자 x, y 제일 작음

                shark_x_position = temp[0]; // x좌표
                shark_y_position = temp[1]; // y좌표
                distance += temp[2]; // 이동거리

                arr[shark_x_position][shark_y_position] = 0; // 먹어치웠으므로 0으로 변경
                fish_eaten++; // 먹은 물고기 수 증가

                if (fish_eaten == shark_size) { // 먹은 물고기 수가 상어의 크기가 되면
                    shark_size++; // 상어의 크기를 증가하고
                    fish_eaten = 0; // 먹은 물고기 수 초기화
                }
            } else { // 이동 가능한 좌표가 없을 때
                break;
            }
        }
        System.out.println(distance); // 누적 이동 거리

    }

    private static ArrayList<int[]> bfs(int x, int y) {
        ArrayList<int[]> fishes = new ArrayList<>(); // 이동 가능한 좌표들을 저장할 arrayList
        Queue<int[]> queue = new LinkedList<>(); // 이동 좌표들을 담을 queue
        visited[x][y] = true; // 방문 처리
        queue.add(new int[]{x, y, 0}); // 상어의 위치와 이동거리를 queue 에 추가

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0]; // 현재 x좌표
            int cy = current[1]; // 현재 y좌표
            int current_move = current[2]; // 현재 이동거리
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                // 범위 안에 있고, 상어 크기보다 작거나 같고(지나갈 수 있음), 방문하지 않았더라면
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && arr[nx][ny] <= shark_size && !visited[nx][ny]) {
                    visited[nx][ny] = true; // 방문
                    queue.add(new int[]{nx, ny, current_move + 1}); // 이동거리 추가하여 큐에 추가

                    // 물고기가 있고, 상어 크기보다 작은 물고기 크기일 때
                    if (arr[nx][ny] != 0 && arr[nx][ny] < shark_size) {
                        fishes.add(new int[]{nx, ny, current_move + 1});
                    }
                }
            }

        }
        return fishes;
    }
}
