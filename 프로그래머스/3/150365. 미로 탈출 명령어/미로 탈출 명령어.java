class Solution {
    // down, left, right, up
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    char[] dir = {'d', 'l', 'r', 'u'};
    boolean isFound = false;
    StringBuilder sb = new StringBuilder();
    String answer = "impossible";
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        dfs(0, x, y, r, c, k, n, m);
        
        return answer;
    }
    
    void dfs(int depth, int x, int y, int r, int c, int k, int n, int m){
        if(isFound) return;
        
        int distance = Math.abs(x - r) + Math.abs(y - c);
        int remain = k - depth;
        
        if (distance > remain) return;

        // 가지치기 (불가능한 경로 제거)
        if (distance > remain || (remain - distance) % 2 != 0) return;
        
        if(depth == k){
            if(x == r && y == c){
                isFound = true;
                answer = sb.toString();
            }
            return;
        }
        
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isIn(nx, ny, n, m)){
                sb.append(dir[i]);
                dfs(depth+1, nx, ny, r, c, k, n, m); 
                sb.deleteCharAt(sb.length() - 1);
                if(isFound) return;
            }
        }
    }
    boolean isIn(int x, int y, int n, int m){
        return 1<=x && x <= n && 1<= y && y <= m;
    }
}