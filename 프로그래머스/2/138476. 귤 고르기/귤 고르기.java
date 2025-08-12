import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        HashMap<Integer, Integer> box = new HashMap<>();
        for(int t: tangerine){
            box.put(t, box.getOrDefault(t,0)+1);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
        for(int i:box.values()){
            pq.add(i);
        }
        int sell = 0;
        int size = pq.size();
        
        for(int i=0; i<size; i++){
            int s = pq.poll();
            answer += s;
            sell++;
            if(answer >= k){
                break;
            }
        }

        return sell;
    }
}