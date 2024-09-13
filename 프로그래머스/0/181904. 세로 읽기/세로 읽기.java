class Solution {
    public String solution(String my_string, int m, int c) {
        String answer = "";
		StringBuilder sb = new StringBuilder();
		int n = my_string.length();
		String[] arr = new String[n / m]; // n/m개의 행, m개의 열

		
		int index = 0;
		for (int i = 0; i < n/m; i++) {
			arr[i] = my_string.substring(index, index+m);
			index += m;
		}
		
		sb.append(answer);
		for(int i=0; i<n/m; i++) {
			sb.append(arr[i].substring(c-1, c));
		}
		
		answer = sb.toString();
		return answer;
    }
}