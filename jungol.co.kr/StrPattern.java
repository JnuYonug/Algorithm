/*
http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1775&sca=20
*/

import java.util.Scanner;

public class StrPattern {
	public static void solve(final String str) {
		String[] pattern = {"KOI", "IOI"};
		int cnt[] = new int[2];

		for(int i=0; i<pattern.length; i++) {
			int idx = 0;
			while(true) {
				idx = str.indexOf(pattern[i], idx);
				if(idx == -1) {
					break;
				}

				cnt[i]++;
				idx++;
			}
		}	

		for(int i=0; i<pattern.length; i++) {
			System.out.println(cnt[i]);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		solve(sc.nextLine());
	}
}
