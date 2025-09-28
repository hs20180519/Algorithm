import java.util.*;
import java.io.*;

public class Main {
    static int[] dx = {-1, 0, 1, 0}; // 위, 오른쪽, 아래, 왼쪽
    static int[] dy = {0, 1, 0, -1};
     public static void main(String[] args) throws IOException {
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(br.readLine());
      
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      
      int dir = Integer.parseInt(st.nextToken());
      
      int[][] room = new int[N][M];
      boolean[][] visited = new boolean[N][M];
      
      for(int i=0; i<N; i++){
        st = new StringTokenizer(br.readLine());
        for(int j=0; j<M; j++){
          room[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      
      
      while(true){
        // System.out.println(r+ " " + c + " 현재 방향 "+ dir);
        // 현재 칸이 아직 청소되지 않은 경우 현재 칸을 청소
        if(room[r][c] == 0){
          visited[r][c] = true;
          room[r][c] = 2;
        }
          
        // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
        if(!isAroundClean(r,c, room)){
          
          dir--; // 반시계 회전
          if(dir==-1) dir = 3;
            
          int nx = r+dx[dir];
          int ny = c+dy[dir];
            
          // 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진
          if(0<=nx && nx < N && 0<= ny && ny < M){
            if(room[nx][ny] == 0){
              r = nx;
              c = ny;
            }
          }
        }// 현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 없는 경우
        else{
          // 한칸 후진
          int nx = r-dx[dir];
          int ny = c-dy[dir];
            
          // 벽이라 후진할 수 없다면 작동을 멈춘다
          if(0<=nx && nx < N && 0<= ny && ny < M && room[nx][ny] != 1){
            r = nx;
            c = ny;
          }else{
            break;
          }
        }
      }
      
      int answer = 0;
      for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
          if(visited[i][j]) answer++;
        }
      }
      System.out.println(answer);
      
     }
     
     static public boolean isAroundClean(int x, int y, int[][] room){
       for(int d=0; d<4; d++){
         int nx = x+dx[d];
         int ny = y+dy[d];
         if(0<=nx && 0<= ny && nx<room.length && ny<room[0].length){ // 범위 안
            if(room[nx][ny] == 0) return false;
         }
       }
       return true;
    }
      
  
}