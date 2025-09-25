import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String[] answer = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            answer[i] = Integer.toString(numbers[i]);
        }
        
        Arrays.sort(answer, ((a, b) -> (b+a).compareTo(a+b)));
        
        StringBuilder sb = new StringBuilder();
        for(String s : answer){
            sb.append(s);
        }
        
        if (sb.charAt(0) == '0') return "0";
        
        return sb.toString();
    }
}