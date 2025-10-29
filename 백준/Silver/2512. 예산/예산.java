import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int N = Integer.parseInt(br.readLine());
      int[] arr = new int[N];
      StringTokenizer st = new StringTokenizer(br.readLine());
      int sum = 0;
      int maxNum = 0;
      
      // 일단 다 더한 다음에 M보다 작은지 확인
      // 작으면 arr에서 가장 큰 값 찾아서 배출.
      // 그렇지 않으면, 기준보다 작은 값들은 다 빼고, 나머지 큰 값 개수로 나눔
      
      for(int i=0; i<N; i++){
        arr[i] = Integer.parseInt(st.nextToken());
        sum+= arr[i];
        maxNum = Math.max(arr[i], maxNum);
      }
      
      
      long M = Long.parseLong(br.readLine());
      

        if (sum <= M) {
            System.out.println(maxNum);
            return;
        }

        int left = 0;
        int right = maxNum;
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2; // 상한
            long total = 0;

            for (int x : arr) {
                total += Math.min(x, mid);
            }

            if (total <= M) {
                result = mid;
                left = mid + 1;  // 더 높일 수 있는지 확인
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
      
  
}