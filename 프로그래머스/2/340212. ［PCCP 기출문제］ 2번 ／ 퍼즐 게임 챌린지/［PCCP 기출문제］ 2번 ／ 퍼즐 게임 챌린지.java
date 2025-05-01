class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100_000;
        int answer = right;
        
        while (left <= right){
            int mid = (left + right)/2;
            
            if(isPossible(mid, diffs, times, limit)){
                answer = mid;
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return answer;
    }
    public boolean isPossible(int level, int[] diffs, int[] times, long limit){
        long totalTime = 0;
        
        for(int i=0; i < diffs.length; i++){
            int diff = diffs[i];
            int time_cur = times[i];
            int time_prev = (i>0) ? times[i-1] : 0;
    
            if(diff<=level) totalTime += time_cur;
            else{
                long retryCount = diff - level;
                totalTime += (long)(time_cur + time_prev) * retryCount + time_cur;
                if(totalTime > limit) return false;
            }
        }
        return totalTime <= limit;
    }
}