import java.util.*;
class Point{
    int landNumber;
    int landSize;
    
    public Point(int landNumber, int landSize){
        this.landNumber = landNumber;
        this.landSize = landSize;
    }
}

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[][] visited;
    Point[][] newLand;
    
    public int solution(int[][] land) {
        int r = land.length;
        int c = land[0].length;
        visited = new boolean[r][c];
        this.newLand = new Point[r][c];
        // 1. 맵에 bfs를 돌려서 각각의 (땅 번호와 석유덩어리 크기)를 저장함
    
        int number = 1;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(land[i][j] == 1 && !visited[i][j]){
                    bfs(land, i, j, number++);
                }
            }
        }
        int answer = 0;
        for(int i=0; i<c; i++){
            Set<Integer> s = new HashSet<>();
            int tempSum = 0;
            for(int j=0; j<r; j++){
                Point p = newLand[j][i];
                if (p != null && !s.contains(p.landNumber)) {
                    s.add(p.landNumber);
                    tempSum += p.landSize;
                }
            }
            answer = Math.max(tempSum, answer);
          
        }
  
        return answer;
    }
    
    public void bfs(int[][] land, int x, int y, int number){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        int count = 1;
        
        List<int[]> path = new ArrayList<>();
        path.add(new int[]{x,y});
        
        while(!q.isEmpty()){
            int[] c = q.poll();
            int cx = c[0];
            int cy = c[1];
            for(int d =0; d<4; d++){
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                
                if(0<=nx && nx<land.length && 0<=ny && ny <land[0].length && !visited[nx][ny] && land[nx][ny] == 1){
                    count++;
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    path.add(new int[]{nx,ny});
                }
            }
        }
        
        for(int[] p : path){
            newLand[p[0]][p[1]] = new Point(number, count);
        }
    }
}
