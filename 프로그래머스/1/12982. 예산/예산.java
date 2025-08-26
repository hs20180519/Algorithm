import java.util.*;
class Solution {
    public int solution(int[] d, int budget) {
        int answer = 0;
        int start = 0;
        int end = d.length;
        
        Arrays.sort(d);
        while(start<=end){
            int mid = (start + end) / 2; // 예산
            
            int sum = 0; // 부서별 신청 금액 합
            for(int i=0; i<mid; i++){
                sum += d[i];
            }
            
            if(sum <= budget){ // 가능하다면
                answer = Math.max(answer, mid); // 더 늘려보자
                start = mid + 1;
            }else{
                end = mid - 1;
            }
            
        }
        return answer;
    }
}