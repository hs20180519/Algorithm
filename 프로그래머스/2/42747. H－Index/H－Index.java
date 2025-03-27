import java.util.*;
class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
    
        for(int i=0; i<n; i++){
            // 현재 i번 이상 인용된 논문 세야 함
            // 뒤에서부터 계산
            int h = n-i;
            if(citations[i] >= h){
                return h;
            }
        }
        
        return 0;
    }
}