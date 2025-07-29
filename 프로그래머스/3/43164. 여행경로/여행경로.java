import java.util.*;

class Solution {
    String[] answer;
    boolean[] visited;
    boolean finished = false;
    List<String> path = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        int n =tickets.length;
        visited = new boolean[n];
        
          Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) return a[1].compareTo(b[1]);
            return a[0].compareTo(b[0]);
        });
        
        path.add("ICN");
        dfs("ICN", tickets, 0);
        return answer;
    }
    
    public void dfs(String cur, String[][] tickets, int depth){
        if(finished) return;
        if(depth == tickets.length){
            answer = path.toArray(new String[0]);
            finished = true;
            return;
        }
        
         for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(cur)) {
                visited[i] = true;
                path.add(tickets[i][1]);
                dfs(tickets[i][1], tickets, depth + 1);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
    }
       
}