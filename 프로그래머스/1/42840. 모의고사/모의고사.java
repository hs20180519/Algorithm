import java.util.*;

class Solution {
    public int[] solution(int[] answers) {

        int[] a = {1,2,3,4,5}; // 5
        int[] b = {2,1,2,3,2,4,2,5}; // 8
        int[] c = {3,3,1,1,2,2,4,4,5,5}; // 10
        
        int cntA = 0;
        int cntB = 0;
        int cntC = 0;
        
        for(int i=0; i<answers.length; i++){
            if(answers[i] == a[i%5]) cntA++;
            if(answers[i] == b[i%8]) cntB++;
            if(answers[i] == c[i%10]) cntC++;
        }
        
        int max = 0;
        int count = 0;
        if(max < cntA){
            max = cntA;
            count++;
        }
        
        if(max < cntB){
            max = cntB;
            count = 1;
        }else if(max == cntB){
            count++;
        }
        
        if(max < cntC){
            max = cntC;
            count = 1;
        }else if(max == cntC){
            count++;
        }
        
        
        int[] answer = new int[count];
        int idx = 0;
        
        if(max == cntA){
            answer[idx++] = 1;
        }
        if(max == cntB){
            answer[idx++] = 2;
        }
        if(max == cntC){
            answer[idx++] = 3;
        }
        
        
        return answer;
    }
}