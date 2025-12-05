import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        // 1 1 4 7 9 1 1 4 7 9 두배로 늘린 뒤, 5까지만 보기 슬라이딩 윈도우 크기 1, 2, 
        int n = elements.length;
        int[] newElements = new int[2*n];
        for(int i=0; i<n; i++){
            newElements[i] = elements[i];
            newElements[i+n] = elements[i];
        }
        
        HashSet<Integer> hset = new HashSet<>();
        for(int i=1; i<=n; i++){ // 슬라이딩 윈도우 크기
            int sum = 0;
            for(int k=0; k<i; k++){
                sum += newElements[k];
            }
            hset.add(sum);
            
            for(int j=0; j<n; j++){
                sum += newElements[i+j] - newElements[j];  
                hset.add(sum);
            }

        }

        return hset.size();
    }
}