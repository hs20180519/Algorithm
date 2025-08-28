import java.util.*;

class Solution {
    boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        // 연결한 뒤에 bfs 돌리기
        int gatsu = computers.length;
        
        visited = new boolean[gatsu];
        List<Integer>[] arr = new ArrayList[gatsu];
        
        for(int i=0; i<gatsu; i++){
            arr[i] = new ArrayList<>();
        }
        for(int i=0; i<computers.length; i++){
            for(int j=0; j<computers.length; j++){
                if(computers[i][j] == 1){
                    arr[i].add(j);
            
                    // System.out.println(i+"랑"+j+"연결");
                }
            }
        }
        
       
        // System.out.println(Arrays.toString(arr));
        
        
        for(int i=0; i<gatsu; i++){
            if(!visited[i]){
                bfs(i, arr);
                
                // for(int j=0; j<gatsu; j++){
                //     System.out.print(visited[j]+ " ");
                // }
                // System.out.println("");
                answer++;
            }
        }
        return answer;
    }
    public void bfs(int num, List<Integer>[] arr){
        Queue<Integer> q = new LinkedList<>();
        q.add(num);
        visited[num] = true;
        while(!q.isEmpty()){
            int curr = q.poll();
    
            for(int c : arr[curr]){
                if(!visited[c]){
                    
                    visited[c] = true;   
                    q.add(c);
                }
            }
        }
    }
}