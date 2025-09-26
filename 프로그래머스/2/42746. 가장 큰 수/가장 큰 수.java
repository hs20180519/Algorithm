import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] c = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            c[i] = Integer.toString(numbers[i]);
        }
        
        Arrays.sort(c, (a,b) -> (b+a).compareTo(a+b));
        if(c[0].equals("0")) return "0";
        
        StringBuilder sb = new StringBuilder();
        for(String s : c){
            sb.append(s);
        }
        
        return sb.toString();
    }
}