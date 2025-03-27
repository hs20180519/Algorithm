import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answers = new int[commands.length];
        for(int i=0; i<commands.length; i++){
            // 2, 5, 3
            
            int start = commands[i][0] -1;
            int end = commands[i][1];
            int[] temp = new int[end-start];
            int k =0;
            for(int j=start; j<end; j++){
                 temp[k++] = array[j];
            }
            
            Arrays.sort(temp);
            // System.out.println(Arrays.toString(temp));
            answers[i] = (temp[commands[i][2]-1]);
            
        }
        return answers;
    }
}