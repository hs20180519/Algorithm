import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        char[] arr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean isJadenCase = false;

        // 첫 문자가 알파벳이라면
        
        arr[0] = Character.toUpperCase(arr[0]);
        sb.append(arr[0]);
           
        for(int i=1; i<arr.length; i++){
            if(arr[i-1] == ' '){
                arr[i] = Character.toUpperCase(arr[i]);
            }else{
                arr[i] = Character.toLowerCase(arr[i]);
            }
            sb.append(arr[i]);
        }
        
        return sb.toString();
    }
}