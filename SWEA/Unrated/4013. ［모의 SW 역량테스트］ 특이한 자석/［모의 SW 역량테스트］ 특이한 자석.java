import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	static int[][] magnet;
	static int[] directions;

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("res/d1002/input_SWEA_4013.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine()); // 회전 횟수
			magnet = new int[4][8];

			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int magnetNum = Integer.parseInt(st.nextToken()); // 자석의 번호
				int magnetDir = Integer.parseInt(st.nextToken()); // 회전 방향

				magnetNum -= 1;
				// 자석 번호에 회전하는 연산
				magnetic(magnetNum, magnetDir);
			}

			int result = 0;
			// 점수의 총 합 계산
			for (int i = 0; i < 4; i++) {
				result += magnet[i][0] * (Math.pow(2, i));
			}

			sb.append("#").append(tc).append(" ");
			sb.append(result).append("\n");
		}

		System.out.println(sb);

	}

	public static void rotate(int[] a, int dir) { // 회전
		Deque<Integer> q = new LinkedList<>();
		for (int b : a) {
			q.add(b);
		}

		if (dir == -1) { // 반시계 방향 회전
			int first = q.pollFirst();
			q.addLast(first);
		} else { // 시계 방향 회전
			int last = q.pollLast();
			q.addFirst(last);

		}

		for (int i = 0; i < a.length; i++) {
			a[i] = q.pollFirst();
		}
	}

	public static void magnetic(int magnetIdx, int magnetDir) {
		directions = new int[4]; // 각 자석의 회전 방향
		directions[magnetIdx] = magnetDir; // 선택된 자석의 회전 방향 설정
		
		// 왼쪽 자석에 대한 회전 결정
        for (int left = magnetIdx - 1; left >= 0; left--) {
            if (magnet[left][2] != magnet[left + 1][6]) {
                directions[left] = -directions[left + 1];
            } else {
                break;
            }
        }

        // 오른쪽 자석에 대한 회전 결정
        for (int right = magnetIdx + 1; right < 4; right++) {
            if (magnet[right][6] != magnet[right - 1][2]) {
                directions[right] = -directions[right - 1];
            } else {
                break;
            }
        }

       for(int i=0; i<4; i++) {
    	   if(directions[i] !=0) {
    		   rotate(magnet[i], directions[i]);
    	   }
       }

	}
}
