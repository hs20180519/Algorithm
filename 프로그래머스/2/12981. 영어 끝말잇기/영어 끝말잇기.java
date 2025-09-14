import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {};
        HashSet<String> hset = new HashSet<>();
        int len = words.length;
        
        String[][] endGame = new String[n][len/n];
        
        int idx = 0;
        for(int j=0; j<len/n; j++){ // 3
            for(int i=0; i<n; i++){ // 5
                // 이미 있는 단어거나, 전에 한 문자인 경우
                
                if( hset.contains(words[idx]) || (idx > 0) &&
                  !(words[idx].startsWith(Character.toString(words[idx-1].charAt(words[idx-1].length()-1))))){
                    // i번째 사람의 j번째
                    return new int[]{i+1, j+1};
                
                }else{
                    hset.add(words[idx]);
                    endGame[i][j] = words[idx++];
                }
            }
        }
        
        
        System.out.println(Arrays.deepToString(endGame));

        return new int[]{0, 0};
    }
}