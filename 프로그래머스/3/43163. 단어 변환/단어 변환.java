import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {

        int n = words.length; // 6
        int m = begin.length(); // 단어 길이 3
        boolean[] visited = new boolean[n];

        Queue<char[]> q = new LinkedList<>();
      
        StringBuilder sb = new StringBuilder();
        sb.append(begin).append("0");
        q.add(sb.toString().toCharArray());

        while(!q.isEmpty()){
            char[] curr = q.poll();
            
            if(isEqual(curr, target.toCharArray(), m)){
                // 찾았을 때
                return curr[m]-'0';
            }
            
            for(int i=0; i<n; i++){
                if(!visited[i] && onlyOneDiff(curr, words[i].toCharArray(), m)){ // 하나만 다르면
                    StringBuilder s = new StringBuilder();
                    s.append(words[i]).append((curr[m]-'0'+1));
                    q.add(s.toString().toCharArray());
                    visited[i] = true;
                }
            }
        }
        
        
        return 0;
    }
    
    public boolean onlyOneDiff(char[] a, char[] b, int m){
        int count = 0;
        for(int i=0; i<m; i++){
            if(a[i] != b[i]) count++;
        }
        return count == 1;
    }
        
    public boolean isEqual(char[] a, char[] b, int m){
        for(int i=0; i<m; i++){
            if(a[i] != b[i]) return false;
        }
        return true;
    }
}