import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        for(int i =0; i<k; i++){
            int num = Integer.parseInt(br.readLine());
            if (num == 0){
                stack.pop();
            }
            else {
                stack.push(num);
            }
        }
        for(int s : stack){
            sum+=s;
        }
        System.out.println(sum);
    }
}
