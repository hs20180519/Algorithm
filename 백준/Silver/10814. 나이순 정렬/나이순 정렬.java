import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 백준 - Main
public class Main {
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("./res/d0814/input_BOJ_10814"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 회원의 수
		StringTokenizer st = null;

		Student[] arr = new Student[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			arr[i] = new Student(age, name, i);
		}

		Arrays.sort(arr, (o1, o2) -> {
			if (o1.getAge() == o2.getAge()) { // 나이가 같으면 가입한 순으로
				return o1.getIndex() - o2.getIndex();
			}
			return o1.getAge() - o2.getAge();
		});

		for (Student s : arr) {
			System.out.println(s.getAge() + " " + s.getName());
		}

	}
}

class Student {

	private int age;
	private String name;
	private int index;

	public Student(int age, String name, int index) {
		this.age = age;
		this.name = name;
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}