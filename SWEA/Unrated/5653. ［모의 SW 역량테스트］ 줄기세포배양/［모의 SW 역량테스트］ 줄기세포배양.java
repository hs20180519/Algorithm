
import java.io.*;
import java.util.*;

class Cell implements Comparable<Cell> {
    int x, y, life, time;

    public Cell(int x, int y, int life, int time) {
        this.x = x;
        this.y = y;
        this.life = life;
        this.time = time;
    }

    @Override
    public int compareTo(Cell o) {
        return o.life - this.life; // 생명력 기준 내림차순 정렬 (생명력이 높은 세포 우선)
    }
}

public class Solution {
    static int N, M, K;
    static int[][] grid;
    static PriorityQueue<Cell> cells;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        // BufferedReader를 사용하여 입력을 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 수 입력
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            // 각 테스트 케이스에 대한 초기 설정 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // 세포가 확장할 수 있는 범위를 고려해 그리드 크기 설정
            grid = new int[N + 2 * K][M + 2 * K];
            cells = new PriorityQueue<>();

            // 초기 세포 상태 입력 및 PriorityQueue에 추가
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    if (life > 0) {
                        // 세포의 초기 위치를 고려해 추가 (중앙을 기준으로 배치)
                        cells.offer(new Cell(i + K, j + K, life, life));
                        grid[i + K][j + K] = life;
                    }
                }
            }

            // K 시간 동안 세포의 번식 및 생명 주기 시뮬레이션
            for (int time = 1; time <= K; time++) {
                PriorityQueue<Cell> newCells = new PriorityQueue<>();

                // 현재 큐에 있는 모든 세포 처리
                while (!cells.isEmpty()) {
                    Cell cell = cells.poll();

                    // 활성화된 세포가 번식할 때
                    if (cell.time == 0) {
                        for (int d = 0; d < 4; d++) {
                            int nx = cell.x + dx[d];
                            int ny = cell.y + dy[d];

                            // 빈 공간에 새로운 세포 번식
                            if (grid[nx][ny] == 0) {
                                grid[nx][ny] = cell.life;
                                newCells.offer(new Cell(nx, ny, cell.life, cell.life));
                            }
                        }
                    }

                    cell.time--; // 시간이 지나면서 세포의 상태 변화

                    // 아직 죽지 않은 세포는 큐에 다시 추가
                    if (cell.time + cell.life > 0) {
                        newCells.offer(cell);
                    }
                }

                cells = newCells; // 새로운 세대의 세포들로 갱신
            }

            // 결과를 StringBuilder에 저장
            sb.append("#").append(t).append(" ").append(cells.size()).append("\n");
        }

        // 최종 결과 출력
        System.out.print(sb.toString());
    }
}
