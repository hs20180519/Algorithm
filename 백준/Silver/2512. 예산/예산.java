import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 코드를 작성해주세요
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int sum = 0;
        int max = 0;
        int answer = 0;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            max = Math.max(arr[i], max);
        }
        int M = Integer.parseInt(br.readLine());
        
        // 다 가능하다면 최댓값
        if(sum <= M){
            System.out.println(max);
        }else{
            int start = 0;
            int end = max;
            while(start <= end){
                int mid = (start + end) / 2;
                int mmsum = 0;
                
                for(int i=0; i<N; i++){
                    if(arr[i] >= mid){
                        mmsum += mid;
                    }else{
                        mmsum += arr[i];
                    }
                }
                
                if(mmsum <= M){
                    answer = Math.max(mid, answer);
                    start = mid+1;
                }else{
                    end = mid-1;
                }
            }
            System.out.println(answer);
            
            
        }
        
        
    }
}
