import java.util.Scanner;

public class strConvert {
	public static void solve(final char[][] list, char[] arr) {		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<list.length; j++) {
				if(arr[i] == list[j][0]) {
					arr[i] = list[j][1];
					break;
				}
			}
		}

		System.out.println(new String(arr));
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	
		int n;
	
		n = sc.nextInt(); sc.nextLine();

		char[][] list = new char[n][2];
		
		for(int i=0; i<n; i++) {
			String str = sc.nextLine();
				
			list[i][0] = str.charAt(0);
			list[i][1] = str.charAt(2);
		}

		n = sc.nextInt(); sc.nextLine();

		char[] input = new char[n];

		for(int i=0; i<n; i++) {
			String str = sc.nextLine();
			
			input[i] = str.charAt(0);
		}

		solve(list, input);	
	}
}
