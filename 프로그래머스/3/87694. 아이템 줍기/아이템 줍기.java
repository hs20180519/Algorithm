import java.util.*;
class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    boolean[][] board;
    boolean[][] visited;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        board = new boolean[102][102];
        visited = new boolean[102][102];
        int len = rectangle.length;
        
        for(int m=0;m<len; m++){
            int[] c = rectangle[m];
            int lx = c[0]*2;
            int ly = c[1]*2;
            int rx = c[2]*2;
            int ry = c[3]*2;

            // 다 채우기
            for(int i=lx; i<=rx; i++){
                for(int j=ly; j<=ry; j++){
                    board[i][j] = true;
                }
            }
        }
        
        // 내부 false
        for(int[] c: rectangle){
            int lx = c[0]*2;
            int ly = c[1]*2;
            int rx = c[2]*2;
            int ry = c[3]*2;
            
            for (int i = lx+1; i < rx; i++) {
                for (int j = ly+1; j < ry; j++) {
                    board[i][j] = false;
                }
            }
        }
        
  
        
        return bfs(characterX*2, characterY*2, itemX, itemY, 0);
   

//         return answer;
    }
    
    public int bfs(int x, int y, int itemX, int itemY, int p){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, 0});
        visited[x][y] = true;
        
        while(!q.isEmpty()){
            int[] c = q.poll();
            int cx =c[0];
            int cy =c[1];
            int dist = c[2];
            
            if(cx == itemX*2 && cy == itemY*2){
                return dist/2;
            }
            
             for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx >= 0 && ny >= 0 && nx < 102 && ny < 102) {
                    if (board[nx][ny] && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny, dist + 1});
                    }
                }
            }
        }
        return -1;
    }
        
}