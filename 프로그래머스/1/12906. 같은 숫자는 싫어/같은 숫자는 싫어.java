import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Deque<Integer> s = new LinkedList<>();
        for(int a : arr){
            if(s.size() >= 1 && s.peekLast() == a){
                continue;
            }
            s.add(a);
        }
        
        int len = s.size();
        int[] answer = new int[len];

        for(int i=0; i<len; i++){
            answer[i] = s.pollFirst();
        }

        return answer;
    }
}