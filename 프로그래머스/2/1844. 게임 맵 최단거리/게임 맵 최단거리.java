import java.util.*;
import java.io.*;
class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    
    public int solution(int[][] maps) {
   
        int n = maps.length;
        int m = maps[0].length;
        
        int[][] visited = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
    
        int answer = bfs(maps, visited, n, m);
        return answer;
    }
    
    int bfs(int[][] maps, int[][] visited, int n, int m){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        visited[0][0] = 1;
        while(!q.isEmpty()){
            int[] curr= q.poll();
            int x = curr[0];
            int y = curr[1];
            
            
            if(visited[n-1][m-1]!=Integer.MAX_VALUE){
                return visited[n-1][m-1];
            }
            
            for(int d=0; d<4; d++){
                int nx = x+dx[d];
                int ny = y+dy[d];
                
                if(0<=nx && nx < n && 0<=ny && ny<m && maps[nx][ny] == 1 && visited[x][y]+1 < visited[nx][ny]){
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = visited[x][y]+1;
                }
            }
        }
        return -1;
    }
}