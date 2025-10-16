import java.util.*;

class Solution {
    public int solution(int[] order) {
        Stack<Integer> stack = new Stack<>();
        int idx = 0;         // order 배열 인덱스
        int count = 0;       // 실은 상자 개수

        // 메인 벨트: 1번부터 n번까지
        for (int box = 1; box <= order.length; box++) {
            // 일단 메인 벨트 상자를 보조 컨테이너에 올려둔다.
            stack.push(box);

            // 스택의 top이 현재 필요한 order[idx]와 같을 경우, 계속 실을 수 있다.
            while (!stack.isEmpty() && stack.peek() == order[idx]) {
                stack.pop();
                idx++;
                count++;
            }
        }

        return count;
    }
}
