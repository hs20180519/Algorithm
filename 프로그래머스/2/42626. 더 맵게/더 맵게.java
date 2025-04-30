import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int s : scoville){
            pq.add(s);
        }
        while(pq.peek() < K){
            // 1. poll 두번하고 pq에 삽입
            if(pq.size() < 2){
                return -1;
            }
            int first = pq.poll();
            int second = pq.poll();
            
            int newNum = first + second*2;
            answer++;
            pq.add(newNum);
        }
        
        System.out.println(pq);
        return answer;
    }
}