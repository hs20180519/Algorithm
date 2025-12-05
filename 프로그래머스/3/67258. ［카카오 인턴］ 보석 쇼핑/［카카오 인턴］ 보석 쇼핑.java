import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int n = gems.length;

        HashSet<String> hset = new HashSet<>();
        
        for(int i=0; i<n; i++){
            hset.add(gems[i]);
        }
        int s = hset.size();
        
        HashMap<String, Integer> hmap = new HashMap<>();
        int left = 0;

        int answer = Integer.MAX_VALUE;
        int leftAnswer = 0;
        int rightAnswer = 0;
       
        for(int right = 0; right<n; right++){
            
            // 1. 오른쪽 보석 포함
            hmap.put(gems[right], hmap.getOrDefault(gems[right], 0) + 1);
            
            // 2. 모든 종류 포함시 -> left 줄이기
            while(hmap.size() == s){
                if(right - left + 1 < answer){
                    answer = right - left + 1;
                    leftAnswer = left;
                    rightAnswer = right;
                }
                
                // left 보석 제거
                hmap.put(gems[left], hmap.get(gems[left]) -1);
                if(hmap.get(gems[left]) == 0) hmap.remove(gems[left]);
                left++;
            }
        }
    return new int[]{leftAnswer+1, rightAnswer+1};
    }

}