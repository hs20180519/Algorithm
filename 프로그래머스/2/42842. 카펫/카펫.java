class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int x = 0;
        int y = 1; // 노란색이 세로 몇 줄인지 탐색
   
        while(true){
            int temp = yellow/y; // 가로 개수
           
            if(yellow%y == 0){
                if(temp*2 + y*2 + 4 == brown){
                    x = temp;
                    break;
                }}
            y++;
        }   
        answer[0] = x+2;
        answer[1] = y+2;
        return answer;
    }
}