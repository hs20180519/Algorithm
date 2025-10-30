import java.util.*;

public class Solution {
    public int[] solution(int []arr) {

        Stack<Integer> st = new Stack<>();
        
        for(int a: arr){
            if(!st.isEmpty() && st.peek() == a){
                continue;
            }else{
                st.push(a);
            }
        }
 
        int size = st.size();
        int[] answer = new int[size];
        for(int i=size-1; i>=0; i--){
            answer[i] = st.pop();
        }

        return answer;
    }
}