import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        // Arrays.sort(scoville);
        int len = scoville.length;
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> a-b);
        for(int i=0; i<len; i++){
            q.add(scoville[i]);
        }
        
        
        
        for(int i=0; i<len; i++){
            while(q.size() >= 2){
                int a = q.poll();
                int b = q.poll();
                if(a >= K && b >= K){
                    break;
                }
                
                if (a < K || b < K){
                    q.add(a + (2*b));
                    answer++;
                     // System.out.println(q);
                }
            }
        }
        
        while(!q.isEmpty()){
            if(q.poll() < K) return -1;
        }
        
        return answer;
    }
}