import java.io.*;
import java.util.*;

// 가장 긴 증가하는 부분 수열
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        int[] dp = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int LIS = 0; // 처음에 저장된 원소는 없으므로 0
        for(int i=1; i<=N; i++){ 
            // 이분 탐색으로 dp 테이블에 저장된 원소를 탐색하며
            // 현재 선택된 숫자가 dp 테이블의 어떤 위치에 포함될지 파악
            int idx = BinarySearch(arr[i], 0, LIS, LIS+1, dp);
            
            // 찾지 못한 경우
            if(idx == -1){
                // 가장 마지막 위치에 원소를 삽입하고 LIS 길이 증가
                dp[LIS++] = arr[i];
            }
            
            // 찾은 경우
            else{
                // 해당 위치에 현재 값을 삽입하여 갱신
                dp[idx] = arr[i];
            }
        }
        System.out.println(LIS);
    }

    public static int BinarySearch(int num, int start, int end, int size, int[] dp){
        int loc = 0; // 위치
        while(start<=end){
            // 중앙 값
            int mid = (start + end)/2;

            // 만일 현재 원소가 해당 원소보다 작거나 같다면, 앞 부분 탐색
            if(num <= dp[mid]){
                loc = mid;
                end = mid-1;
            }

            // 만일 현재 원소가 해당 원소보다 크다면, 뒷 부분 탐색
            else{
                start = mid+1;
            }
        }

        // 모든 수들보다 큰 경우
        if(start == size){
            return -1;
        }
        // 찾은 경우
        else{
            return loc;
        }
    }

}
