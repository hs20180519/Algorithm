import java.util.*;
class Solution {
    int[] numbers;
    int target;
    int n;
    int answer;
    public int solution(int[] number, int targe) {
        answer = 0;
        numbers = number;
        target = targe;
        n = numbers.length;
        
        dfs(0, 0);
        
        return answer;
    }
    public void dfs(int depth, int sum){
   
        if(depth == n){
            if(sum == target){
                answer++;
            }
            return;
        }
        
      
        dfs(depth+1, sum + numbers[depth]);
        dfs(depth+1, sum - numbers[depth]);
    }
}