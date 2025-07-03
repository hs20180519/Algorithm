class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        // 최소 당첨 개수를 세고, 0의 개수만큼 더한 것이 최대
        int count = 0;
        int zero = 0;
        for(int num : win_nums){
            for(int lotto : lottos){
                if(lotto == num){
                    count++;
                    break;
                }
            }
        }
        for(int lotto : lottos){
            if(lotto == 0){
                zero++;
            }
        }
        
        // System.out.println(count +"  " + zero);
        int lowRank = makeRank(count);
        int highRank = makeRank(count + zero);
        answer[0] = highRank;
        answer[1] = lowRank;
        
        return answer;
    
    }
    
    public int makeRank(int count){
        switch (count){
                case 1: return 6;
                case 2: return 5;
                case 3: return 4;
                case 4: return 3;
                case 5: return 2;
                case 6: return 1;
        }
        return 6;
    }
}