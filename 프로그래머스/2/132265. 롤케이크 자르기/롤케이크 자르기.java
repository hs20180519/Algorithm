import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        // 슬라이딩 윈도우 느낌쓰
        int len = topping.length;
        
        int za = 0;
        
        int[] chulsu = new int[10001];
        int[] dongsang = new int[10001];
        int a = 0;
        int b = 0;
        
        chulsu[topping[0]] = 1;
        a++;
        for(int i=1; i<len; i++){
            if(dongsang[topping[i]]==0) b++;
            dongsang[topping[i]] += 1;
        }
        
        if(a == b) answer++;
        
        for(int gizun = 1; gizun<len; gizun++){
            if(chulsu[topping[gizun]]==0) a++; // 철수가 없던 토핑이면 종류 추가
            chulsu[topping[gizun]] += 1;
            
            dongsang[topping[gizun]] -= 1; // 동생이 있던 토핑 제거
            if(dongsang[topping[gizun]]==0) b--; // 아예 없어지면 종류 제거
            
            if(a == b) answer++;
        }
        
        
        return answer;
        
    }
}