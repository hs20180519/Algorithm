import java.util.*;

class Solution {
    public int solution(int n, int w, int num) {
        
        int size = n/w; 
        int add = n%w > 0 ? 1 : 0; // 나머지가 0보다 크면 1
        
        int[][] map = new int[size + add][w];
        
        int curr = 1;
        int d = 0; // 처음에는 오른쪽
        int jul = 0;
        while(curr <= n){
            if(d==0){
                for(int i=0; i<w; i++){
                    map[jul][i] = curr++;
                }
                d++;
            }else{
                for(int i=w-1; i>=0; i--){
                    map[jul][i] = curr++;
                }
                d--;
            }
            jul++;
            
        }
        
        int x = 0;
        int y = 0;
        // 위에 몇개 쌓여있는지 찾고, 맨 위의 것이 n보다 크면 -1;
        for(int i= 0; i<(size+add); i++){
            for(int j=0; j<w; j++){
                if(map[i][j] == num){
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        
        int answer = size+add - x;
        if(map[size+add-1][y] > n){ // 하나 더 빼야됨
            answer -= 1;
        }
        
        System.out.println(answer);
        return answer;
    }
}