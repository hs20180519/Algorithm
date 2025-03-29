import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> m = new HashMap<String, Integer>();
        for(String[] c : clothes){
            String name = c[0];
            String s = c[1];
            m.put(s, m.getOrDefault(s, 0) + 1);
        }
        
        // 종류 더하고, 
        int answer = 1;
        for(int count : m.values()){
            answer *= (count + 1);
        }
  
        return answer -1;
    }
}