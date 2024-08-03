import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0; // 걸린 시간
        int currentWeight = 0; // 현재 무게
        
        Queue <Integer> bridges = new LinkedList<>(); // 다리(를 지나고 있는 트럭)
        Queue <Integer> trucks = new LinkedList<>(); // 트럭
        
        for(int truck : truck_weights){ // truck_weights -> trucks(queue)
            trucks.add(truck);
        }
        
        for(int i=0; i<bridge_length; i++){ // bridges를 bridge_length만큼 0으로 채움
            bridges.add(0);
        }
        
        while(!bridges.isEmpty()){
            answer ++;
            currentWeight -= bridges.poll(); // 트럭이 건너감
            if (!trucks.isEmpty()) {
                if (currentWeight + trucks.peek() <= weight) { // 다음 트럭이 다리에 올라올 수 있는지 확인
                    int truck = trucks.poll();
                    bridges.add(truck);
                    currentWeight += truck;
                } else {
                    bridges.add(0); // 트럭이 올라올 수 없으면 0을 추가하여 시간이 지나도록 함
                }
            }
        }
        
        return answer;
    }
}