import java.util.*;
import java.io.*;
class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[][] visited;
    public int solution(int[][] maps) {
    
        int a = maps.length;
        int b = maps[0].length;
        visited = new boolean[a][b];
        
        int answer = bfs(0, 0, a, b, maps);
        return answer;
    }
    
    public int bfs(int startX, int startY, int a, int b, int[][] maps){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY, 0});
        visited[startX][startY] = true;
        
        while(!q.isEmpty()){
            int[] c = q.poll();
            int cx = c[0];
            int cy = c[1];
            int dist = c[2];
            
            if(cx == a-1 && cy == b-1)
                return dist+1;
            
            for(int d=0; d<4; d++){
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                if(inValid(nx, ny, a, b)){
                    if(maps[nx][ny] == 1){
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny, dist+1});
                    }
                }
            }
        }
        return -1;
    }
    
    public boolean inValid(int x, int y, int a, int b){
        return 0 <= x && x < a && 0 <= y && y < b && (!visited[x][y]);
    }
}