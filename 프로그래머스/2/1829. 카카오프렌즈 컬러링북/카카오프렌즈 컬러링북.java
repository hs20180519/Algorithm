import java.util.*;
class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int maxSizeOfOneArea;
    boolean[][] visited;
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        maxSizeOfOneArea = 0;

        visited = new boolean[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(picture[i][j] !=0 && !visited[i][j]){
                    numberOfArea++;
                    bfs(i, j, picture[i][j], picture, n, m);
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public void bfs(int x, int y, int num, int[][] picture, int n, int m){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        int count = 0;
        
        while(!q.isEmpty()){
            int[] c = q.poll();
            int cx = c[0];
            int cy = c[1];
            count++;
            // System.out.println(cx + " " + cy + " " + count);
            maxSizeOfOneArea = Math.max(count, maxSizeOfOneArea);
            
            for(int d=0; d<4; d++){
                int nx = cx+dx[d];
                int ny = cy+dy[d];
                
                if(0<=nx && nx < m && 0<=ny && ny<n && !visited[nx][ny] && picture[nx][ny] == num){
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}