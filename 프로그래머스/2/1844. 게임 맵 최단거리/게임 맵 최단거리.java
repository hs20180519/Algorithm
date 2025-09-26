import java.util.*;
class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    int[][] map;
    public int solution(int[][] maps) {
        int answer = 0;
        map = maps;
        // bfs(0,0);
        return bfs(0,0);
    }
    
    public int bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        q.add(new int[]{x, y, 1});
        visited[x][y] = true;
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];
            int dist = curr[2];
            
            if(cx == map.length-1 && cy == map[0].length-1){
                return dist;
            }
            
            for(int d=0; d<4; d++){
                int nx = cx+dx[d];
                int ny = cy+dy[d];
                if(0<=nx && nx < map.length && 0<=ny && ny<map[0].length && map[nx][ny] != 0 && !visited[nx][ny]){
                    q.add(new int[]{nx, ny, dist+1});
                    visited[nx][ny] = true;
                }
            }
        }
        return -1;
        
    }
}