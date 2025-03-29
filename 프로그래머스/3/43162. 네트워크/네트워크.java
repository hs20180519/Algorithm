class Solution {
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        for(int i=0; i<computers.length; i++){
            if(!visited[i]){
                dfs(i, computers);
                answer++;
            }
        }
        
        return answer;
    }
    void dfs(int node, int[][] computers){
        visited[node] = true;
        for(int i=0; i<computers.length; i++){
            if(!visited[i] && computers[node][i] != 0){
                dfs(i, computers);
            }
        }
    }
}