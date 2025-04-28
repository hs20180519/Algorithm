import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        // string으로 받아서 맨 앞으로 숫자 정렬 ?
        String[] c = new String[numbers.length];
        for(int i =0; i<numbers.length; i++){
            c[i] = Integer.toString(numbers[i]);
        }
        
        Arrays.sort(c, (a,b) -> (b+a).compareTo(a+b));
        
        StringBuilder sb = new StringBuilder();
        for(String s : c){
            sb.append(s);
        }
        
        if(sb.charAt(0) == '0'){
            return "0";
        }
        
        return sb.toString();
    }
}