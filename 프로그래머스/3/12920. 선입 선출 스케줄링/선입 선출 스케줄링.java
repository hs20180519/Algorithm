import java.util.*;
class Solution {
    public int solution(int n, int[] cores) {
        int answer = Integer.MAX_VALUE;
        
        // 작업 시간에 대한 이분탐색 시행
        int start = 0;
        int end = 10_000*10_000; // 최대 코어의 수 * 최대 작업 처리 시간
        
        while(start <= end){
            int mid = (start+end) / 2;
            
            int sum = calcTime(mid, cores);
            
            if(sum >= n){ // 작업의 수가 더 크다면 줄여야 됨
                answer = Math.min(mid, answer);
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
    
        // time-1까지 끝난 작업 개수
        int untilTimeMinusOne = calcTime(answer-1, cores);
        int remain = n - untilTimeMinusOne;
            
        
        // time 시점에 끝나는 작업들 확인
        for (int i = 0; i < cores.length; i++) {
            if (answer % cores[i] == 0) { // 이 시점에 끝난 작업
                remain--;
                if (remain == 0) return i + 1;
            }
        }
        return -1;
        }
    
    public int calcTime(int mid, int[] cores){
        if (mid < 0) return 0;
        int sum = cores.length;
        for(int i=0; i<cores.length; i++){
                sum += mid / cores[i];
        }
        return sum;
    }
}