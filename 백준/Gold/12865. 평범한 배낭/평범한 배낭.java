
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

    /*
    조합 + 백트래킹 => 시간 초과 예상 (N은 100까지 가능)

    dp => 사용
     */
    public static class Things implements Comparator<Things>{
        int weight;
        int value;

        Things(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compare(Things o1, Things o2) { // 무게 순 정렬
            return o1.weight - o2.weight;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물품의 수
        int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
        Things[] things = new Things[N]; // (무게, 가치)를 저장

        // 배낭 물품 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken()); // 무게
            int value = Integer.parseInt(st.nextToken()); // 가치
            things[i] = new Things(weight, value);
        }

        int [][] dp = new int[N+1][K+1]; // 행 : 물건을 몇 개 담을지, 열 : 최대 가치
        // dp[0][0] : 0개의 물건으로 0kg를 만들 때 최대 가치
        // dp[3][7] : 3개의 물건으로 7kg를 만들 때 최대 가치


        for(int i=0; i<N; i++){
            int currentWeight = things[i].weight;
            int currentValue = things[i].value;

            // 물건을 골랐으니, 무게를 하나씩 늘림. 1부터 K까지
            for(int j=1; j<K+1; j++){
                // 현재 물건의 무게가 가방의 무게보다 무거우면 현재 물건을 가방에 넣지 않음
                // 현재 가방 무게에 대해 이전 물건까지 계산한 값을 가져옴
                if(currentWeight > j){
                    dp[i+1][j] = dp[i][j];
                }

                // 현재 물건을 가방에 넣을 수 있으면
                // 현재 물건을 가방에 넣었을 때의 가치와 넣지 않았을 때의 가치 중 더 큰 값을 가져옴
                else{
                    dp[i+1][j] = Math.max(dp[i][j-currentWeight] + currentValue, dp[i][j]);
                }
            }
        }

        System.out.println(dp[N][K]);


    }
}