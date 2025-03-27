import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long left = 1;
        long right = (long) times[times.length - 1] * n; // 최악의 경우
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            long people = 0;
            for (int time : times) {
                people += mid / time;  // mid 시간 동안 몇 명 심사 가능?
            }

            if (people >= n) {
                answer = mid;        // 가능하니까 시간 줄여보기
                right = mid - 1;
            } else {
                left = mid + 1;      // 부족하니까 시간 늘리기
            }
        }

        return answer;
    }
}