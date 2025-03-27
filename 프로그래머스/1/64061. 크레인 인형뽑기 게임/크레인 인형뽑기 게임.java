import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> a = new Stack<Integer>();
        for(int m : moves){
            // m-1 번째 인덱스를 세로로 탐색
            for(int i=0; i<board[0].length; i++){
                // 0이 아니면 뺌
                
                if(board[i][m-1] != 0){
                    if(!a.isEmpty() && a.peek() == board[i][m-1]){
                        answer = answer+2;
                        a.pop();
                        board[i][m-1] = 0;
                
                        
                    }else{
                        a.push(board[i][m-1]);
                        board[i][m-1] = 0;
                    }
                    break;
                }
                
            }
        }
        
        return answer;
    }
}