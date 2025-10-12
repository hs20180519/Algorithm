import java.util.*;

class Solution {
    String moeum = "AEIOU";
    List<String> arr;
    public int solution(String word) {
        arr = new ArrayList<>();
        dfs(0, new char[5]);
        
        Collections.sort(arr);
        
        if(word.equals("A")) return 1;
 
        while(word.length() < 5){
            word += '0';
        }
        
        // System.out.println(arr);
        for(int i=0; i<arr.size(); i++){
            if(arr.get(i).equals(word)){
                return i;
            }
        }
        int answer = 0;
        return answer;
    }
    
    public void dfs(int depth, char[] temp){
        arr.add(String.valueOf(temp));
        
        if(depth == 5){
            return;
        }
        
        for(int i=0; i<5; i++){
            temp[depth] = moeum.charAt(i);
            dfs(depth+1, temp);
            temp[depth] = '0';
            
        }
    }
}