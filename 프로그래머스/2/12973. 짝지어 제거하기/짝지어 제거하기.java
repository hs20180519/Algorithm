import java.util.*;
class Solution
{
    public int solution(String s)
    {
        int answer = -1;
        Stack<Character> st = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char next = s.charAt(i);
            if(st.size() > 0 && st.peek() == next){
                st.pop();
            }
            else{
                st.push(next);
            }
        }
        
        return st.size() > 0 ? 0 : 1;
    }
}