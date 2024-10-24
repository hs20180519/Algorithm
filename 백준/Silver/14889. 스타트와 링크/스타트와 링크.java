import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int map[][];
    static int N;
    static int linkTotalSum = 0;
    static int startTotalSum = 0;
    static int smallNum = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        // 조합 + calculate
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }

        }
        pickTeams(0, 0, new int[N/2]);

        // N/2개만큼 스타트 팀 고르는 조합 (나머지 링크 팀)
        // 조합을 골랐으면 map을 통해 능력치의 합을 더해 스타트 팀, 링크 팀 차이 구함
        // 최솟값 갱신
        System.out.println(smallNum);


    }

    static void pickTeams(int cnt, int startIndex, int[] startTeams){
        if(cnt == N/2){ // 스타트 팀 다 골랐으면
            int[] linkTeams = new int[N/2]; // 링크 팀 구하기
            int index = 0;
            for(int i=0; i<N; i++){
                if(!isInStartTeam(i, startTeams)){
                    linkTeams[index++] = i;
                }
            }

            // 팀끼리 조합을 구해 능력치의 합 구함
            abilitySumLinkTeam(0, 0, linkTeams, new int[N/2]); // linkTotalSum
            abilitySumStartTeam(0,0, startTeams, new int[N/2]); // startTotalSum

            // 능력치 차이의 최솟값 갱신
            smallNum = Math.min(smallNum, Math.abs(linkTotalSum-startTotalSum));   
            
            // 초기화
            linkTotalSum = 0;
            startTotalSum = 0;
            return;
        }

        // N/2개를 뽑는 조합
        for(int i=startIndex; i<N; i++){
            startTeams[cnt] = i;
            pickTeams(cnt+1, i+1, startTeams);
        }
    }

    // 스타트 팀에 있으면  true
    static boolean isInStartTeam(int n, int[] startTeam){
        for(int i=0; i<N/2; i++){
            if(startTeam[i] == n) return true;
        }
        return false;
    }

    
    // startTeam에서 능력치의 합(조합)구하기
    static void abilitySumStartTeam(int cnt, int startIndex, int[] m, int[] answers){
        if(cnt == 2){
            int x = answers[0];
            int y = answers[1];

            startTotalSum += map[x][y] + map[y][x];
            // 총합에 더하기
            return;
        }

        for(int i=startIndex; i<m.length; i++){
            answers[cnt] = m[i];
            abilitySumStartTeam(cnt+1, i+1, m, answers);
        }
    }

    // linkTeam에서 능력치의 합(조합)구하기
    static void abilitySumLinkTeam(int cnt, int startIndex, int[] m, int[] answers){
        if(cnt == 2){
            int x = answers[0];
            int y = answers[1];

            linkTotalSum += map[x][y] + map[y][x];
            // 총합에 더하기
            return;
        }

        for(int i=startIndex; i<m.length; i++){
            answers[cnt] = m[i];
            abilitySumLinkTeam(cnt+1, i+1, m, answers);
        }
    }
}
