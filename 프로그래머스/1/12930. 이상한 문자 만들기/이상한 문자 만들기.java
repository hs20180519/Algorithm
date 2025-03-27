import java.util.*;
import java.io.*;

class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        int count = 0;
        for(int i=0; i<arr.length; i++){
            if(arr[i] == ' '){ // 공백일 경우
                count = 0; // 카운트 초기화
                sb.append(" ");
                continue;
            }
            if(count % 2 == 0){ // 짝수 인덱스
                sb.append(Character.toUpperCase(arr[i]));
                count++;
            } else{
                sb.append(Character.toLowerCase(arr[i]));
                count++;
            }
            
        }
        String answer = sb.toString();
        return answer;
    }
}