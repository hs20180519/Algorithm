import java.util.*;

class Solution {
    int[][] dices;
    int n;
    int[] answer;
    int maxWin = 0;
    public int[] solution(int[][] dice) {
        dices = dice;
        n = dices.length;
        
        answer = new int[n/2];
  
        
        
        dfs(0, 0, new int[n/2]);
        
        
        
        return answer;
        
    }
    // 주어진 dice 중 n/2개를 고른 뒤, 각각의 경우 순열로 합
    public void dfs(int depth, int start, int[] temp){
        if(depth == n/2){
            
            // A팀 조합, B팀 조합
            int[] tempB = new int[n/2];
            int idx = 0;
            for(int i=0; i<n; i++){
                if(!isInA(temp, i)){
                    tempB[idx++] = i;
                }
            }
            
            List<Integer> sumA = new ArrayList<>();
            List<Integer> sumB = new ArrayList<>();
            
            makeAllSum(0, 0, temp, sumA);
            makeAllSum(0, 0, tempB, sumB);
            
            int win = countAWin(sumA, sumB);
            if(win > maxWin){
                maxWin = win;
                
                for(int i=0; i<temp.length; i++){
                    answer[i] = temp[i]+1;
                }
            }
            
            return;
        }
        
        for(int i=start; i<n; i++){
            temp[depth] = i;
            dfs(depth+1, i+1, temp);
        }
    }
    
    public boolean isInA(int[] arrA, int num){
        for(int i=0; i<n/2; i++){
            if(arrA[i] == num) return true;
        }
        return false;
    }
    
    
    
    public void makeAllSum(int depth, int sum, int[] temp, List<Integer> result){
        if(depth == temp.length){
            result.add(sum);
            return;
        }
        
        int diceNo = temp[depth];
        for(int i=0; i<6; i++){
            makeAllSum(depth+1, sum+dices[diceNo][i], temp, result);
        }
    }
    
    // O(6^n) 이므로 이분탐색으로 줄이기
    public int countAWin(List<Integer> sumA, List<Integer> sumB){
        // 1. 정렬
        Collections.sort(sumA);
        Collections.sort(sumB);
        
        int win = 0;
        for(int a: sumA){
            int cnt = lowerBound(sumB, a); // b < a인 개수
            win += cnt;
        }
        return win;
    }
    
    public int lowerBound(List<Integer> sumB, int a){
        int start = 0;
        int end = sumB.size();
        
        while(start< end){
            int mid = (start + end) / 2;
            if(sumB.get(mid) < a){ start = mid + 1;}
            else end = mid;
        }
        
    return start;
    }
    
}