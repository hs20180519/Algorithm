import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()); // 슬라이딩 윈도우의 크기
        
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int sum = 0;
        for(int i=0; i<X; i++){
            sum += arr[i];
        }

        int answer = sum;
        int count = 1;
        for(int i=X; i<N; i++){
            sum = sum - arr[i-X] + arr[i];
            
            if(sum > answer){
                count = 1;
                answer = sum;
            }else if(sum == answer){
                count++;
            }
        }
        
        if(answer ==0){
            System.out.println("SAD");
        }else{
            System.out.println(answer);
            System.out.println(count);
        }
    }   
}
