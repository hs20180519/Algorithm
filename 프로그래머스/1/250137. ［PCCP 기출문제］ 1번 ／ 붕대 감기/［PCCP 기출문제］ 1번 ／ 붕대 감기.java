class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        // 시전 시간, 1초당 회복량, 추가 회복량
        // 3 2 7
        // 
        int time = 1;
        int continues = 0;
        int currHealth = health;
        
        int at = 0;
        while(at != attacks.length){ // 0
            if(time == attacks[at][0]){ // 
                currHealth -= attacks[at][1];
                continues = 0;
                time++;
                if(currHealth <= 0) return -1; // 플레이어 죽음
                    at++;
            }else{
                continues++;
                if(continues >= bandage[0]){
                    currHealth += bandage[2]; // 추가 회복량
                    continues = 0;
                }
                currHealth += bandage[1]; // 1초당 회복량
                if(currHealth > health) currHealth = health; // 최대 체력 이상 회복 X
                // System.out.println("현재 시간: " + time + "체력: " + currHealth);
                time++;
            }
        }
        
        return currHealth;
    }
}