import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        // map 만들어서 각 이름마다 count +1
        // completion 돌면서 count-1
        // 마지막에 남아있는 이름이 답
        HashMap<String, Integer> map = new HashMap<>();
        for(String p : participant){
            map.put(p, map.getOrDefault(p, 0)+1);
        }
    
        for(String c : completion){
            map.put(c, map.getOrDefault(c, 0)-1);
        }
        for(String s : map.keySet()){
            if(map.get(s) == 1){
                return s;
            }
        }
        
        return answer;
    }
}