/*
http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=310&sca=20
*/

import java.util.Scanner;

public class Bingo {
	public static void solve(int[] board, int[] list) {
		for(int i=0; i<12; i++) {
			for(int j=0; j<board.length; j++) {
				if(board[j] == list[i]) {
					board[j] = 0;
					break;
				}
			}
		}

		for(int i=0; i<board.length; i++) {
			System.out.print(board[i] + " ");
			if(i%5 == 4) {
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		int[] board = new int[25];
		Scanner sc = new Scanner(System.in);

		for(int i=0; i<25; i++) {
			board[i] = sc.nextInt();
		}

		int[] list = new int[25];

		for(int i=0; i<25; i++) {
			list[i] = sc.nextInt();
		}

		solve(board, list);
	}
}
