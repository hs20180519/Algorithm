class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        // 현재 위치
        int current = calculateTime(pos);
        
        // 오프닝 시간
        int opStart = calculateTime(op_start);
        int opEnd = calculateTime(op_end);
        
        for(String command : commands){   
            // 만약 현재 위치가 오프닝 구간이라면, 오프닝 건너뛰기가 시행됨
            if(opStart <= current && current <= opEnd){
                current = opEnd;
            }
 
            if(command.equals("prev")){
                current -= 10;
                if(current < 0){
                    current = 0;
                }
            }else if(command.equals("next")){
                current += 10;
                int endTime = calculateTime(video_len);
                if(current > endTime){
                    current = endTime;
                }
            }
            
            if(opStart <= current && current <= opEnd){
                current = opEnd;
            }
            
        }
        
        String answer = returnToString(current);
       
        return answer;
    }
    
    public int calculateTime(String time){
        return Integer.parseInt(String.valueOf(time.charAt(0)) +String.valueOf(time.charAt(1))) * 60 + Integer.parseInt(String.valueOf(time.charAt(3)) +String.valueOf(time.charAt(4)));
    }
    
    public String returnToString(int time){
        String a = String.valueOf(time/60);
        String b = String.valueOf(time%60);
        if(a.length()==1) a='0'+a;
        if(Integer.parseInt(b)<10 && b.length()==1) b='0'+b;
        if(Integer.parseInt(b)>=10 && b.length()==1) b=b+'0';
        if(a.length()==0) a="00";
        if(b.length()==0) b="00";
        return a+":"+b;
    }
}