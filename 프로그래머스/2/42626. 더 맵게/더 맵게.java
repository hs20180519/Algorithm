import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        Queue<Integer> q = new PriorityQueue<>();
        for(int s : scoville){
            q.add(s);
        }
        
        while(q.peek() < K){
            if(q.size() == 1){
                return -1;
            }
            
            int a = q.poll();
            int b = q.poll();
            q.add(a+2*b);
            answer++;
        }
        return answer;
    }
}