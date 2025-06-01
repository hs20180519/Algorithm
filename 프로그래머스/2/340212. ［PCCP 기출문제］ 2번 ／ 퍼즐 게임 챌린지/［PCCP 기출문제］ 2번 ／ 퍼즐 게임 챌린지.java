class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        
        // 숙련도의 최솟값
        // 이진 탐색
        
        int start = 0;
        int end = 100000;
        while(start <= end){
            int mid = (start + end)/2; // 레벨
            if(calc(diffs, times, limit, mid)){ // 가능하면 더 줄이기
                end = mid-1;
                answer = mid;
            }else{
                start = mid+1;
            }
        }
        
        return answer;
    }
    
    // 현재 레벨에 따라 제한 시간 내에 퍼즐을 해결할 수 있는지
    public boolean calc(int[] diffs, int[] times, long limit, long level){
        long t = 0;
        for(int i=0; i<diffs.length; i++){
            if(diffs[i] <= level){
                t += times[i];
            }else{
                if (i<1) return false;
                t += (diffs[i] - level) * (times[i]+times[i-1]) + times[i];
            }
        }
        return t <= limit;
    }
}