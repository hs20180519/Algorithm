

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    // 총 세개의 벽을 세우고(조합), 세웠을 때 바이러스를 퍼뜨린다.
    // 이때 배열의 안전 영역 크기를 구하고, 최댓값을 갱신한다.
    static int N, M;
    static int[][] map;
    static Point[] points;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int maxValue;
    public static void main(String[] args) throws IOException {
        // 0인 곳을 모두 ArrayList[]에 담고, index 별로 세개 씩 고른다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        points = new Point[N*M];
        int index = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0){
                    points[index++] = new Point(i, j);
                }
            }
        }
        maxValue = 0;
        combination(0,0,new int[3], index);
        System.out.println(maxValue);


    }
    public static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

    }
    public static void combination(int cnt, int startIndex, int[] answers, int index){
        if(cnt == 3){ // 안전 벽을 세 가지 모두 세웠을 때

            // 0. 임시로 쓸 맵 초기화
            int[][] newMap = new int[N][M];
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    newMap[i][j] = map[i][j];
                }
            }

            // 1. 뽑은 인덱스에 벽을 세운다.
            for(int i=0; i<3; i++){
                Point xy = points[answers[i]];
                newMap[xy.x][xy.y] = 1;
            }

            // 2. 바이러스를 퍼뜨린다.
            visited = new boolean[N][M];
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(newMap[i][j] == 2 && !visited[i][j]){
                        spreadingVirus(i, j, newMap);
                    }
                }
            }

            // 3. 안전 구역의 크기를 구한다.
            maxValue = Math.max(calculateSafeArea(newMap), maxValue);
            return;
        }
        for(int i=startIndex; i<index; i++){
            answers[cnt] = i;
            combination(cnt+1, i+1, answers, index);
        }
    }

    // 바이러스 퍼뜨리기
    public static void spreadingVirus(int x, int y, int[][] newMap){
        visited[x][y] = true;
        for(int d=0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(isInBound(nx, ny) && newMap[nx][ny]==0){ // 범위 안이고,  0일때만 퍼질 수 있다.
                newMap[nx][ny] = 2;
                spreadingVirus(nx, ny, newMap);
            }
        }

    }

    public static boolean isInBound(int x, int y){
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    // 안전 구역 계산
    public static int calculateSafeArea(int[][] newMap){
        int safe = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(newMap[i][j] == 0){
                    safe++;
                }
            }
        }
        return safe;
    }

}
