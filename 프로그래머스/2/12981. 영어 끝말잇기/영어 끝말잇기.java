import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answers = new int[2];
        HashSet<String> hset = new HashSet<String>();
        // 가장 먼저 탈락하는 사람의 번호, 몇 번째 차례에 탈락하는 지
        int number = 0;
        int count = 0;
        for(int i=0; i<words.length; i++){
           
            if(i!=0 && words[i-1].charAt(words[i-1].length()-1) != words[i].charAt(0)){
                count = i%n + 1;
                number = i/n + 1;
                break;
            }
            
            if(hset.contains(words[i])){
                System.out.println(i+ " " + words[i]);
                count = i%n + 1;
                number = i/n + 1;
                break;
            }else{
                hset.add(words[i]);
            }
        }
     
        answers[0] = count;
        answers[1] = number;
        return answers;
    }
}