import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        long sum1 = 0;
        long sum2 = 0;
        
        for(int x : queue1){
            q1.add(x);
            sum1 += x;
        }
        
        for(int x : queue2){
            q2.add(x);
            sum2 += x;
        }
        
        long target = (sum1 + sum2) / 2;
        int limit = queue1.length*3;
        int count = 0;
        
        while(count <= limit){
            if(sum1 == target) return count;
            
            if(sum1 > target){
                int x = q1.poll();
                sum1 -= x;
                sum2 += x;
                q2.add(x);
            }else{
                int x = q2.poll();
                sum2 -= x;
                sum1 += x;
                q1.add(x);
            }
            
            count++;
        }
        
        return -1;
    }
}