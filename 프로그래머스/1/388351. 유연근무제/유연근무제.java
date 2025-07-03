import java.util.*;
class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int goodPerson = 0;
        
        // day가 6, 7인 경우에는 세지 않음
        // schedules에서 각각 10분을 더한 값을 다시 저장
        // 8:55 -> 9:05가 되려면
        // 8*60 + 55 (535) + 10 (545)
        // /60 몫 9 나머지 5 -> 몫 * 10 + 나머지
        
        for(int i=0; i<schedules.length; i++){
            int sch = schedules[i];
            int a = (sch/100)*60 + (sch%100) + 10;
            schedules[i] = (a/60)*100 + (a%60);
        }
        
        for(int i=0; i<schedules.length; i++){ // i번째 직원
            int day = startday; // 현재 날짜
            int count = 0; // 지킨 날짜 수
            for(int j=0; j<7; j++){
                if(day == 6 || day == 7){
                    day++;
                    day = day > 7 ? 1 : day;
                    continue;
                }
                if(timelogs[i][j] <= schedules[i]){
                    count++;
                }
                day++;
            }
            if(count == 5){
                goodPerson++;
            }
        }
      
        return goodPerson;
    }
}