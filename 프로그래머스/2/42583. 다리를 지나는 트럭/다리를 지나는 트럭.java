import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> q = new LinkedList<>();
        int time = 0;
        int currentWeight = 0;
        int idx = 0;
        
        for(int i=0; i<bridge_length; i++){ // 초기화
            q.add(0);
        }
        
        while(!q.isEmpty()){
            time++;
            currentWeight -= q.poll();
            
             if (idx < truck_weights.length) {
                if (currentWeight + truck_weights[idx] <= weight) {
                    // 트럭 올리기
                    q.add(truck_weights[idx]);
                    currentWeight += truck_weights[idx];
                    idx++;
                } else {
                    // 올릴 수 없으면 0 추가 (시간만 흐르게)
                    q.add(0);
                }
            }
        }
        
        
        return time;
    }
}