class Solution {
    // down, left, right, up
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    boolean isFound = false;
    StringBuilder sb = new StringBuilder();
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        dfs(0, x, y, r, c, k, n, m);
        
        if(isFound) System.out.println(sb.toString());
        else{
            System.out.println("IMPOSSIBLE");
        }
        return answer;
    }
    
    void dfs(int depth, int x, int y, int r, int c, int k, int n, int m){
        if(isFound) return;
        
        
        int distance = Math.abs(x - r) + Math.abs(y - c);
        int remain = k - depth;

        // 가지치기 (불가능한 경로 제거)
        if (distance > remain || (remain - distance) % 2 != 0) return;
        
        if(depth == k){
            if(x == r && y == c){
                // System.out.println(sb.toString());
                isFound = true;
                return;
            }
        }
        
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isIn(nx, ny, n, m)){
                sb.append(i);
                dfs(depth+1, nx, ny, r, c, k, n, m); 
                sb.deleteCharAt(sb.length() - 1);
                if(isFound) return;
            }
        }
    }
    boolean isIn(int x, int y, int n, int m){
        return 0<=x && x < n && 0<= y && y < m;
    }
}