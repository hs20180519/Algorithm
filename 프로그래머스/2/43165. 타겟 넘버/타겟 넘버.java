import java.util.*;

class Solution {
    int count = 0;
    int target;
    int[] numbers;
    public int solution(int[] numbers, int target) {
        this.target = target;
        this.numbers = numbers;
        dfs(0, 0, numbers.length);
        return count;
    }
    
    public void dfs(int depth, int sum, int n){
        if(depth == n){
            if(sum == target){
                count++;
            }
            return;
        }
        
        dfs(depth+1, sum+numbers[depth], n);
        dfs(depth+1, sum-numbers[depth], n);
        
    }
}