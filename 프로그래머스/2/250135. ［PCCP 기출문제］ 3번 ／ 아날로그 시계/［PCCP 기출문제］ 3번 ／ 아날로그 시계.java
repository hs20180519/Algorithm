class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
    
        int start = h1*3600 + m1*60 + s1;
        int end = h2*3600 + m2*60 + s2;
  
        for(int i= start; i<end; i++){
            double[] now = getDegrees(i); // 현재 시간의 시침/분침/초침 각도
            double[] next = getDegrees(i+1); // 다음 시간의 시침/분침/초침 각도
            
            boolean hMatch = hourMatch(now, next); // 시침 초침 겹치는지
            boolean mMatch = minuteMatch(now, next); // 분침 초침 겹치는지
            
            // 둘다 겹칠 때
            if(hMatch && mMatch){
                // 겹치는 각도가 정확히 같으면 둘이 동시에 한 번 겹침
                if(Double.compare(next[0], next[1]) == 0) answer++;
                // 아니면 따로 겹친 것
                else answer += 2;
            }
            else if(hMatch || mMatch){
                answer++;
            }
        }
        
        // 시작 시간이 정확히 0시나 12시인 경우
        // 시침/분침/초침이 모두 겹치므로
        if(start == 0 || start == 43200) answer++;
        
        return answer;
        
    }

    private double[] getDegrees(int seconds){
        int h = (seconds / 3600) % 12;
        int m = (seconds % 3600) / 60;
        int s = seconds % 60;
            
        // 시침 각도
        // 360도 / 12 => 1시간 : 30도
        // 30도 / 60 => 1분 : 0.5도
        // 0.5도 / 60 => 1초 : 1/120도
        double hDegree = h*30.0 + m*0.5 + s/120.0;
            
        // 분침 각도
        // 360도 / 60 => 1분 : 6.0
        // 6.0도 / 60 => 1초 : 0.1
        double mDegree = m*6.0 + s*0.1;
        
        // 초침 각도
        // 360도 / 60 => 1초 : 6.0
        double sDegree = s*6.0;
            
        return new double[]{hDegree, mDegree, sDegree};
    }
        
    // 시침과 초침
    private boolean hourMatch(double[] now, double[] next) {
        // 초침이 시침보다 작거나 같아졌을 때 (겹침 발생)
        if (Double.compare(now[0], now[2]) > 0 &&
            Double.compare(next[0], next[2]) <= 0) {
            return true;
        }
        // 초침이 354도 → 0도로 회전할 때, 시침이 여전히 354도 이상일 경우
        if (Double.compare(now[2], 354d) == 0 &&
            Double.compare(now[0], 354d) > 0) {
            return true;
        }
        return false;
    }
        
    // 분침과 초침
    private boolean minuteMatch(double[] now, double[] next) {
        // 초침이 분침보다 작거나 같아졌을 때 (겹침 발생)
       if (Double.compare(now[1], now[2]) > 0 &&
            Double.compare(next[1], next[2]) <= 0) {
            return true;
        }
       // 초침이 354도 → 0도로 회전할 때, 분침이 여전히 354도 이상일 경우
        if (Double.compare(now[2], 354d) == 0 &&
            Double.compare(now[1], 354d) > 0) {
            return true;
        }
       return false;
    }    
}