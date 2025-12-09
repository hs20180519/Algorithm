import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        List<Integer> line = new ArrayList<>();
        
        for(int i=N; i>= 1; i--){
            int cnt = arr[i-1];
            line.add(cnt,i);
        }
        
        for(int i : line){
            System.out.print(i + " ");
        }
        // 코드를 작성해주세요
    }
}
