import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> q = new LinkedList<>();
        
        if(cacheSize ==0){
            return 5*cities.length;
        }
        
        for(String c : cities){
            c = c.toUpperCase();
            if(q.size() < cacheSize && !q.contains(c)){
                q.add(c);
                answer += 5;
                
                // System.out.println("cache miss, 큐가 차지 않았어용 "+ q);
            }
            else if(!q.contains(c)){ // 포함되어 있지 않으면 가장 오래 사용하지 않은 것과 교체
                q.poll();
                q.add(c);
                answer += 5;
                // System.out.println("cache miss, 큐가 차있으니깡 가장 오래 사용하지 않은 것 빼고, 새로운거 넣을거예용" + q);
                
            }else{ // 포함되어 있으면 맨 끝에 배치
                q.remove(c);
                q.add(c);
                answer += 1;
                // System.out.println("cache hit, 이미 있는거니깡 맨 뒤로 보낼거예용" + q);
            }
        }
        
        return answer;
    }
}