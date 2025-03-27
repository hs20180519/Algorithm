import java.util.*;
import java.io.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        char[][] ans = new char[n][n];
        for(int i =0; i<n; i++){
            String binary = Integer.toBinaryString(arr1[i] | arr2[i]);
            
             // 길이 n에 맞게 앞에 0 채우기
            while (binary.length() < n) {
                binary = "0" + binary;
            }
            // if(answer[i].equals("1")) answer[i] = "#";
            // else{ answer[i] = " ";}
            ans[i] = binary.toCharArray();
        }
        
        for(int i=0; i<n; i++){
            StringBuilder sb = new StringBuilder();
            for(int j=0; j<n; j++){
                if(ans[i][j] == '1'){
                    sb.append('#');
                }else{
                    sb.append(' ');
                }
            }
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}