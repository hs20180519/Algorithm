
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int i=0; i<t; i++){ // t 개수만큼 입력받기
            char[] s = br.readLine().toCharArray();
            Stack <Character> stack = new Stack<>(); // stack 선언
            for(char a : s){ // stack에 입력받은 괄호 값 집어넣기
                if(a=='('){
                    stack.push(a);
                }
                else{ // (면,
                    if(!stack.isEmpty() && stack.peek()=='('){
                        stack.pop();
                    }
                    else{
                        stack.push(a);
                    }
                }
            }
            System.out.println(stack.isEmpty()? "YES" : "NO");
        }
    }
}
