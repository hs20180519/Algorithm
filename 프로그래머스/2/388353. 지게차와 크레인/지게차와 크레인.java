import java.util.*;

class Solution {
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    char[][] sto;
        
    public int solution(String[] storage, String[] requests) {
        
        // 알파벳이 하나인 경우에는 지게차 (접근 가능한 현재 위치 필요)
        // 두개인 경우는 모든 것 꺼내옴
        int n = storage.length;
        int m = storage[0].length();
        
        sto = new char[n][m];
        for(int i=0; i<n; i++){
            sto[i] = storage[i].toCharArray(); 
        }
       
        

        for(String r : requests){
            if(r.length() == 2){ // 크레인
                for(int i=0; i<n; i++){
                    for(int j=0; j<m; j++){
                        if(sto[i][j] == r.charAt(0)){
                            sto[i][j] = '0';
                        }
                    }
                }
            }else{
                for(int i=0; i<n; i++){
                    for(int j=0; j<m; j++){
                        if(sto[i][j] == r.charAt(0) && IsConnectOutside(i, j, n, m)){
                            sto[i][j] = '1';
                        }
                    }
                }
                for(int i=0; i<n; i++){
                    for(int j=0; j<m; j++){
                        if(sto[i][j] == '1'){
                            sto[i][j] = '0';
                        }
                    }
                }
                
            }
        }
        int answer =0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(sto[i][j] != '0'){
                    answer++;
                }
            }
        }
        // System.out.println(Arrays.deepToString(sto));
        return answer;
    }
    public boolean IsConnectOutside(int a, int b, int n, int m){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        q.add(new int[]{a, b});
        visited[a][b] = true;
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];
            
            if(cx == 0 || cy == 0 || cx == n-1 || cy == m-1){
                return true;
            }
            for(int d=0; d<4; d++){
                int nx = cx+dx[d];
                int ny = cy+dy[d];
                if(0<=nx && nx<n && 0<= ny && ny < m && !visited[nx][ny] && sto[nx][ny]== '0'){
                    q.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        return false;
    }
}