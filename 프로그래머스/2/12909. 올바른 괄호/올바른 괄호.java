import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack <Character> stack = new Stack<>();
        
        // 배열을 돌며 "(" 면 stack에 추가
        // ")" 면 "(" pop. -> pop할 것이 없거나(비어있는 스택) (이 없으면 false return
      
        if (s.length() % 2 == 1){
            return false;
        }
        
        for(char c : s.toCharArray()){
            if ( c == '('){
                stack.push(c);
            }else if(c==')'){
                // 스택이 비어 있으면 올바른 괄호 쌍이 없음
                if (stack.isEmpty()){
                    return false;
                }
                stack.pop(); // 괄호 쌍을 맞췄으므로 스택에서 제거
            }
        }
         
        return stack.isEmpty();
    }
}