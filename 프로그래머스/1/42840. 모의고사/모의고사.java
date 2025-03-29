import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = new int[3];
        
        int[] a = {1, 2, 3, 4, 5}; // 5
        int[] b = {2, 1, 2, 3, 2, 4, 2, 5}; // 8
        int[] c = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}; // 10
        
        for(int i=0; i<answers.length; i++){
            if(answers[i] == a[i%5]){
                answer[0]++;
            }
            if(answers[i] == b[i%8]){
                answer[1]++;
            }
            if(answers[i] == c[i%10]){
                answer[2]++;
            }
            
        }
        int maxNum = Math.max(answer[0], Math.max(answer[1], answer[2]));
            System.out.println(maxNum);
        
        int[] newAnswer = new int[3];
        int count = 0;
        for(int i=0; i<3; i++){
            if(answer[i] == maxNum){
                newAnswer[count++] = i+1; 
            }
        }
        
        int lenNum = 0;
        for(int i=0; i<3; i++){
            if(newAnswer[i] != 0){
                lenNum++;
            }
        }
        int[] aa = new int[lenNum];
        
        count = 0;
        for(int i=0; i<3; i++){
            if(newAnswer[i] != 0){
                aa[count++] = newAnswer[i];
            }
        }
        return aa;
    }
}