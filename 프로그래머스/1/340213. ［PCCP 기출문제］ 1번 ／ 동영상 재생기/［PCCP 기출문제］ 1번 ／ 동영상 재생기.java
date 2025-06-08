import java.util.*;
import java.io.*;
class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        
        int ctime = calTimeStrToInt(pos); // 현재 시간
        int etime = calTimeStrToInt(video_len); // 종료 시간
        int ostime = calTimeStrToInt(op_start); // 오프닝 시작 시간
        int oetime = calTimeStrToInt(op_end); // 오프닝 종료 시간
        
        for(String c : commands){
            if(ostime<= ctime && ctime < oetime){ // 오프닝 사이면 오프닝 끝 시간으로
                ctime = oetime; 
            }
            
            if(c.equals("next")){
                ctime += 10;
                ctime = ctime > etime ? etime : ctime;
            }else{
                ctime -= 10;
                ctime = ctime < 0 ? 0 : ctime;
            }
            
        }
        if(ostime<= ctime && ctime < oetime){ // 오프닝 사이면 오프닝 끝 시간으로
            ctime = oetime; 
        }
        
        return calTimeIntToStr(ctime);
    }
    
    public int calTimeStrToInt(String t){ // String -> Int
        char[] time = t.toCharArray();
        int a = (time[0]-'0') * 10 + (time[1]-'0');
        int b = (time[3]-'0') * 10 + (time[4]-'0');
        return a*60 + b;
    }
    
    public String calTimeIntToStr(int time){
        int t = time/60;
        int m = time%60;
        StringBuilder sb = new StringBuilder();
        
        // 시간
        if (t == 0){
            sb.append("00");
        }else if(1<= t && t< 10){
            sb.append('0').append(t);
        }else{
            sb.append(t);
        }
        
        sb.append(":");
        
        // 분
        if (m == 0){
            sb.append("00");
        }else if(1<= m && m< 10){
            sb.append('0').append(m);
        }else{
            sb.append(m);
        }
        
        return sb.toString();
        // return String.format("%02d:%02d", player.time / 60, player.time % 60);
    }
    
}