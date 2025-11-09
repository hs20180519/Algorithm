import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int T = Integer.parseInt(br.readLine());
        for(int a = 0; a<T; a++){
            // 팀의 개수, 문제의 개수, 내 팀 아이디, 로그 엔트리의 개수
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            
            List<int[]>[] score = new ArrayList[n+1]; // 팀의 점수
            int[] lastSubmitTime = new int[n+1]; // 마지막으로 제출한 시간
            int[] submitNumber = new int[n+1];
            
            // score[1] = {1, 30} - 1번팀이 30점 획득
            for(int b = 1; b <= n; b++){
                score[b] = new ArrayList<>();
            }
            
            for(int M=0; M<m; M++){
                // 팀 아이디, 문제 번호, 획득한 점수
                st = new StringTokenizer(br.readLine()); 
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                
                lastSubmitTime[i] = M;
                submitNumber[i]++;
                boolean found = false;
                for (int cu = 0; cu < score[i].size(); cu++) {
                    int[] curr = score[i].get(cu);
                    if (curr[0] == j) { // 같은 문제 발견
                        if (curr[1] < s) curr[1] = s; // 점수 갱신
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    score[i].add(new int[]{j, s}); // 새 문제 등록
                }  
            }
            
            // 점수 계산
            int[] team = new int[n+1];
            for(int i=1; i<=n; i++){
                int sum = 0;
                for(int[] cu : score[i]){
                    sum += cu[1];
                }
                team[i] = sum;
            }
            // System.out.println(Arrays.toString(team));
            
            // 내 팀과 비교하여 순위 계산
            int rank = 0;
            for(int i=1; i<=n; i++){
                if(i!= t){
                    if(team[i] > team[t]){ // 최종 점수
                        rank++;
                    }else if(team[i] == team[t]){
                        if (submitNumber[i] < submitNumber[t]) rank++;
                        else if (submitNumber[i] == submitNumber[t] && lastSubmitTime[i] < lastSubmitTime[t]) rank++;
                    }
                }
            }
            System.out.println(rank+1);
            
        }
        // 코드를 작성해주세요
    }
    
}
