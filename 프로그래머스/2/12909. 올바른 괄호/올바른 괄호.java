import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray()){
            if(!stack.isEmpty() && c == ')' && stack.peek() == '('){
                
               stack.pop();
            }else{
                stack.push(c);
            }
        
          
             // System.out.println(stack);
        }
        

        return stack.isEmpty()? true: false;
    }
}