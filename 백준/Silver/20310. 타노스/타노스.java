import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 절반의 0과 절반의 1 제거
        // 사전순으로 가장 빠르게 하려면 ..
        // 1010 -> 0은 뒤에서 빼고 1은 앞에서 빼기
        // 000011 -> 001
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        int zero = 0;
        int one = 0;
        for(int i=0; i<arr.length; i++){
            if(arr[i] == '0') zero++;
            else one++;
            dq.add(arr[i]- '0');
        }

        int zeroCount = zero/2;
        int oneCount = one/2;
       
        // 뒤에서부터 0 검사해서 0 제거
        while(zeroCount != 0){
            dq.removeLastOccurrence(0);
            zeroCount--;
        }
        // 앞에서부터 1 검사해서 1 제거
        while(oneCount != 0){
            dq.removeFirstOccurrence(1);
            oneCount--;
        }

        while(!dq.isEmpty()){
            System.out.print(dq.pollFirst());
        }
        
    }
}
