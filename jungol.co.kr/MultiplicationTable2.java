import java.util.Scanner;

public class multiplicationTable2 {
	public static void solve(final int s, final int e) {
		if(s < e) {
			printAsceTable(s, e);
		} else {	// s > e
			printDescTable(s, e);
		}
	}
	
	public static void printAsceTable(final int s, final int e) {
		for(int i=s; i<=e; i++) {
			for(int j=1; j<=9; j++) {
				System.out.printf("%d * %d = %2d   ", i, j, i*j);
				if(j%3 == 0) {
					System.out.println();
				}
			}
			System.out.println();
		}
	}

	public static void printDescTable(final int s, final int e) {
		for(int i=s; i>=e; i--) {
			for(int j=1; j<=9; j++) {
				System.out.printf("%d * %d = %2d   ", i, j, i*j);
				
				if(j%3 == 0) {
					System.out.println();
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		final Scanner sc = new Scanner(System.in);
		final int s = sc.nextInt();
		final int e = sc.nextInt();

		solve(s, e);
	}
}
