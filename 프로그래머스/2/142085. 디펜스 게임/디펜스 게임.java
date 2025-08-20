import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        // 무적권은 병사가 최대일 때 쓰면 효율이 좋음
        // 그러나 라운드 수가 중요하기 때문에 Arrays.sort X
        int len = enemy.length;
        int chance = k;
        
        if(chance >= len){
            return len;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>((a,b) -> b-a);
        for(int i=0; i<len; i++){
            q.add(enemy[i]);
            n -= enemy[i];
            
            if(n<0){ // 남은 병사가 0보다 작고,
                if(chance > 0){ // 찬스가 있다면
                    n += q.poll(); // 제일 큰 수에 찬스 쓰기
                    chance--;
                }else{
                    return i;
                }
            }
        }
        
        return len;
    }
}