import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int n = discount.length;
        HashMap<String, Integer> hma = new HashMap<>();
        for(int i=0; i<want.length; i++){
            hma.put(want[i], number[i]);
        }
        

        
        for(int i=0; i<n; i++){ // 할인 시작 날
            HashMap<String, Integer> temp = new HashMap<>();
            for(int j=0; j<10; j++){ // 10일 동안
                if(i+j >= n){
                    break;
                };
                
                temp.put(discount[i+j], temp.getOrDefault(discount[i+j], 0) + 1);
                
                if(j==9){ // 마지막 날
                    
                    if(isAllDiscount(temp, hma)){
                        answer++;
                    }
                }
            }
            
        }
        return answer;
    }
    public boolean isAllDiscount(HashMap<String, Integer> temp, HashMap<String, Integer> hma){
          for(String k : temp.keySet()){
              if(temp.get(k) != hma.get(k)){
                    return false;
              }
          }
        return true;
    }
}