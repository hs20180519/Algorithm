import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> hmap = new HashMap<>();
        for(String p : participant){
            hmap.put(p, hmap.getOrDefault(p, 0) + 1);
        }
        
        for(String c : completion){
            hmap.put(c, hmap.getOrDefault(c, 0) -1);
        }
        
        for(String i : hmap.keySet()){
            if(hmap.get(i) == 1){
                return i;
            }
        }
        return "";
    }
}