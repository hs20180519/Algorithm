import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> m = new HashMap<>();
        for(int i=0; i<participant.length; i++){
            m.put(participant[i], m.getOrDefault(participant[i], 0) + 1);
        }
        
        for(int i=0; i<completion.length; i++){
            m.put(completion[i], m.get(completion[i])-1);
        }
        
        for(String name : m.keySet()){
            if(m.get(name) == 1){
                return name;
            }
        }

        return "";
    }
}