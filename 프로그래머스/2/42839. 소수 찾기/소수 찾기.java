import java.util.*;
import java.io.*;
class Solution {
    
    Set<Integer> hset = new HashSet<Integer>();
    boolean[] visited;
    public int solution(String numbers) {
        visited = new boolean[numbers.length()];
        dfs("", numbers);
        
        int answer = 0;
        for(int num : hset){
            if(isPrime(num)) answer++;
        }
        
        return answer;
    }
    public void dfs(String current, String numbers){
        if(!current.equals("")){
            hset.add(Integer.parseInt(current));
        }
        
        for(int i=0; i<numbers.length(); i++){
            if(!visited[i]){
                visited[i]= true;
                dfs(current+numbers.charAt(i), numbers);
                visited[i] = false;
            }
        }
    }
    
    boolean isPrime(int n){
        if(n<=1) return false;
        for(int i=2; i<=Math.sqrt(n); i++){
            if(n%i == 0) return false;
        }
        return true;
    }
}