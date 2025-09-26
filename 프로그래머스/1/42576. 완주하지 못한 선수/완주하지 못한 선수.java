import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> hmap = new HashMap<>();
        for(String p : participant){
            hmap.put(p, hmap.getOrDefault(p, 0) + 1);
        }
        
        for(String c : completion){
            hmap.put(c, hmap.getOrDefault(c, 0) - 1);
        }
        
        for(String s : hmap.keySet()){
            if(hmap.get(s) != 0) return s;
        }
        
        return answer;
    }
}