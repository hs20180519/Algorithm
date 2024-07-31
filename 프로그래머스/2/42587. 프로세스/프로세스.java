import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        // 맨 앞을 빼고 맨 뒤에 넣어야 함 
        // -> 이는 배열에서는 매우 비효율적, 큐 사용
        
        // 1. Deque에 추가
        Deque<Integer> c = new ArrayDeque<>(); 
        for(int prior : priorities){
            c.add(prior);
        } 
        
        // 2. 더 큰 수가 있는지 확인
        int b = 0; // 제일 큰 수 일 때 0, 다른 큰 수가 있을 때 1
        while(!c.isEmpty()){
            for(int num : c){
                if(num > c.getFirst()){ // 하나라도 큰 수가 있으면
                    b = 1;
                    break;
                }
            }
            if (b==1){ // 3-1. 하나라도 큰 수가 있으면
                int n = c.removeFirst(); // 첫번째 수 빼서
                c.add(n); // 뒤에 집어넣기
                location = (location + c.size()-1)%c.size(); // loc = loc-1 (0인경우 가장 끝으로)
                b = 0; // b 초기화
            }
            else{ // 3-2. 하나라도 큰 수가 없으면 (제일 큰 수라면)
                c.removeFirst(); // 첫번째 수 빼기
                if (location == 0){ // 찾는 위치가 처음 위치라면
                    break; // 종료
                }
                else{
                    location = (location + c.size()-1)%c.size(); // loc = loc-1 (0인경우 가장 끝으로)
                    answer++; // 다른 것의 호출 횟수
                }     
            }
        }
        return answer+1;
    }
}