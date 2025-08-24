import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        // 모든 사람이 심사를 마치는 최소 시간은 1 ~ times최댓값 * n
        // 시간에 따라 이분탐색하여 가능한 지 확인
        Arrays.sort(times);
        
        long start = 0;
        long end = times[times.length-1] * (long) n;
        
        while(start <= end){
            long mid = (start + end) /2;
            
            if(isPossible(n, mid, times)){ // 가능한 경우, 더 적은 시간도 가능한지
                answer = mid;
                end = mid-1;
            }else{
                start = mid+1;
            }           
        }
        return answer;
    }
    
    public boolean isPossible(int n, long mid, int[] times){
        long people = 0; // 심사 가능한 사람
        for(int time : times){
            people += mid / time; // 걸린 시간 / 심사시간
            if(people >= n) return true;
        }
        return false;
    }
}