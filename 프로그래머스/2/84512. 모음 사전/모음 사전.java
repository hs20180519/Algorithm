import java.util.*;
class Solution {
    int answer;
    String words;
    int idx = 0;
    public int solution(String word) {
        String alpabet = "AEIOU";
        words = word;
        dfs(0, alpabet, "");
        return answer;
    }
    
    public void dfs(int depth, String alpabet, String w){
        if(w.equals(words)){
            answer = idx;
        }
        
        if(depth == 5){
            return;
        }
        
        for(int i=0; i<5; i++){
            idx++;
            dfs(depth+1, alpabet, w+alpabet.charAt(i));
        }
    }
}