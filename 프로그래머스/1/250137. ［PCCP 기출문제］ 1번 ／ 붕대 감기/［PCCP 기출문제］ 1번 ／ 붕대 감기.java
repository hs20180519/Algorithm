class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int h = health; // 현재 체력
        int ctime = 0; // 현재 시간
        int contin = 0; // 연속횟수

        int index = 0;
        
        while(index < attacks.length){
            if(attacks[index][0] == ctime){ // 곻격하면
                h-=attacks[index][1];
                contin = 0;
                index++;
            }else{
                contin += 1;
                h = h+bandage[1] < health ? h+bandage[1] : health; // 체력 회복 (<최대 체력)
            }
            
            if (h<=0){ // 죽음
                h = -1;
                break;
            }
            if (contin == bandage[0]){ // t초 연속으로 붕대 감기 성공하면
                h = h+bandage[2] < health ? h+bandage[2] : health; // 추가 체력 회복 (<최대 체력)
                contin = 0;
            }
            ctime++; // 시간 증가
           
        }
            
        return h;
    }
}