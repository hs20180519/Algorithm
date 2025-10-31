import java.util.*;

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, -1, 0, 1};
    int[][] map;
    boolean[][] visited;
    int answer;
    public int solution(int[][] maps) {
        answer = 0;
        map = maps;
        visited = new boolean[map.length][map[0].length];
        // System.out.println(map.length + " " + map[0].length);
        
        bfs(0, 0, map.length, map[0].length);
        
        return answer == 0 ? -1 : answer;
    }
    
    public void bfs(int x, int y, int n, int m){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, 1});
        visited[x][y] = true;
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];
            int dist = curr[2];
            
            if(cx == n-1 && cy == m-1){
                answer = dist;
                break;
            }
            
            for(int d=0; d<4; d++){
                int nx = cx+dx[d];
                int ny = cy+dy[d];
                
                if(isOk(nx, ny) && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, dist+1});
                }
            }
        }
    }
    
    // 범위 안이고, 벽이 없을 때
    public boolean isOk(int x, int y){
        int n = map.length;
        int m = map[0].length;
        return 0 <= x && x< n && 0<= y && y < m && map[x][y] == 1;
    }
}