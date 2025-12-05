import java.util.*;
class Solution {
    int score = -1; // 최대 점수 차이
    int[] answer;
    int n;
    int[] info;
    public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;
        
        answer = new int[11];
        int[] temp = new int[11];

        dfs(0, n, temp);
        if(score <= 0) return new int[]{-1};
        
        return answer;
    }
    
    public void dfs(int depth, int count, int[] temp){
        if(depth == 10){
            
            temp[10] = count;
            
            int lionSc = lionScore(info, temp);
            
            if(lionSc > 0){
                if(lionSc > score){
                    answer = Arrays.copyOf(temp, 11);
                    score = lionSc;
                }else if(lionSc == score){
                    // 가장 낮은 점수를 더 많이 맞힌 경우
                    answer = moreLowScore(answer, temp);
                }
            
            }
            
           
            temp[10] = 0;
            return;
        }
        
        int need = info[depth] + 1;
        
        // 이기기
        if(count >= need){
            temp[depth] = need;
            dfs(depth+1, count-need, temp);
            temp[depth] = 0;
        }
        
        // 포기
        temp[depth] = 0;
        dfs(depth+1, count, temp);
    }
    
    public int lionScore(int[] info, int[] lionInfo){
        int apichSum = 0;
        int lionSum = 0;
        
        for(int i=0; i<11; i++){
            if(info[i] == 0 && lionInfo[i] == 0) {
                continue;
            }
            
            if(info[i] < lionInfo[i]){
                lionSum += (10-i);
            }else{
                apichSum += (10-i);
            }
            
        }
        
        return lionSum - apichSum;
    }
    
    // 낮은 점수를 더 많이 맞는 배열 return
    public int[] moreLowScore(int[] a, int [] b){
        for(int i=10; i>=0; i--){
            if(b[i] > a[i]) return Arrays.copyOf(b, 11);
            else if(b[i] < a[i]) return Arrays.copyOf(a, 11); 
        }
        return a;
    }
                      
}