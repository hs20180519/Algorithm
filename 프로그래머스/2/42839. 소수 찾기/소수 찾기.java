import java.util.*;
class Solution {
    int len;
    int answer;
    int[] numbers;
    HashSet<Integer> hset;
    public int solution(String number) {
        answer = 0;
        len = number.length();
        
        numbers = new int[len];
        char[] arr = number.toCharArray();
        hset = new HashSet<>();
        
        for(int i=0; i<len; i++){
            numbers[i] = arr[i]-'0';   
        }
        
        // 1개만 뽑
        
        for(int i=0; i<len+1; i++){
            dfs(0, i, new int[i], new boolean[len]);
        }

        System.out.println(hset);
        return hset.size();
    }
    
    // 1 7 -> 1, 7, 17, 71 만들어야 함
    public void dfs(int depth, int k, int[] temp, boolean[] visited){
    
        if(depth == k){
      
            int number = 0;
            for(int i=0; i<temp.length; i++){
                number += temp[i] * (int) Math.pow(10, temp.length - i - 1);
            }
    
            // System.out.println(number);
            if(isPrime(number)) hset.add(number);
            return;
            
        }
        
        for(int i=0; i<len; i++){
            if(!visited[i]){
                visited[i] = true;
                temp[depth] = numbers[i];
                dfs(depth+1, k, temp, visited);
                visited[i] = false;
            }
          
        }
    }
    
    public boolean isPrime(int number){
        if(number == 0 || number == 1) return false;
        for(int i=2; i<number; i++){
            if(number % i == 0) return false;
        }
        return true;
    }
}