import java.util.*;
class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;
        
        Arrays.sort(data, (a, b)->{
            if(a[0]==b[0]){
                return a[1]-b[1];
            }
            else{
                return a[0]-b[0];
            }});
        // System.out.println(Arrays.deepToString(data));

        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(isOk(i, j, data)) answer++;
            }
        }
        return answer;
    }
    public boolean isOk(int i, int j, int[][] data){
        if(data[i][0] == data[j][0] || data[i][1] == data[j][1]){
            return false;
        }
        
        for(int m = i+1; m < j; m++){
            if(data[i][0] < data[m][0] &&
               data[j][0] > data[m][0] &&
               Math.min(data[i][1], data[j][1]) < data[m][1] && 
               Math.max(data[i][1], data[j][1]) > data[m][1]) return false;
        }
        return true;
    }
}