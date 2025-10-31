import java.util.*;

class Solution {
    int len;
    int count;
    public int solution(int[] numbers, int target) {

        len = numbers.length;
        count = 0;
        
        dfs(0, 0, target, numbers);
        return count;
    }
    
    public void dfs(int depth, int sum, int target, int[] numbers){
        if(depth == len){
            if(sum == target){
                count++;
            }
            return;
        }
        
        dfs(depth+1, sum + numbers[depth], target, numbers);
        dfs(depth+1, sum - numbers[depth], target, numbers);
    }
}