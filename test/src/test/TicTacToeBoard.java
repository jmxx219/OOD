package test;

import java.util.Scanner;

public class TicTacToeBoard extends Board {
	public int x, y;
	public int Tie = 0;
	Scanner scan = new Scanner(System.in);

	public TicTacToeBoard() {
		super(3);
	}

	public void print() {
		System.out.println("Tic Tac Toe Game");
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				System.out.print(" " + map[row][col]);
			}
			System.out.println();
		}
	}

	public void placePlayer1() {
		while (true) {
			System.out.print("다음 수의 좌표를 입력하시오: ");
			x = scan.nextInt();
			y = scan.nextInt();
			if (x > 2 || y > 2 || map[x][y] != ".") {
				System.out.println("잘못된 위치입니다.");
				continue;
			} else {
				map[x][y] = "X";
				break;
			}
		}
	}

	public void placePlayer2() {
		while (true) {
			System.out.print("다음 수의 좌표를 입력하시오: ");
			x = scan.nextInt();
			y = scan.nextInt();
			if (x > 2 || y > 2 || map[x][y] != ".") {
				System.out.println("잘못된 위치입니다.");
				continue;
			} else {
				map[x][y] = "O";
				break;
			}
		}

	}

	public void checkWinner() {
		for (int i = 0; i < 3; i++) {
			int Xwin = 0;
			int Owin = 0;
			int Xwin0 = 0;
			int Owin0 = 0;
			for (int j = 0; j < 3; j++) {
				if (map[i][j] == "X") {
					Xwin++;
				}
			}
			if (Xwin == 3) {
				System.out.print("X is win");
				Tie = 1;
				break;
			}
			Xwin = 0;
			for (int j = 0; j < 3; j++) {
				if (map[j][i] == "X") {
					Xwin++;
				}
			}
			if (Xwin == 3) {
				System.out.print("X is win");
				Tie = 1;
				break;
			}
			Xwin = 0;
			for (int j = 0; j < 3; j++) {
				if (map[j][2 - j] == "X") {
					Xwin++;
				}
			}
			if (Xwin == 3) {
				System.out.print("X is win");
				Tie = 1;
				break;
			}
			for (int j = 0; j < 3; j++) {
				if (map[j][j] == "X") {
					Xwin0++;
				}
			}
			if (Xwin0 == 3) {
				System.out.print("X is win");
				Tie = 1;
				break;
			}
			for (int j = 0; j < 3; j++) {
				if (map[i][j] == "O") {
					Owin++;
				}
			}
			if (Owin == 3) {
				System.out.print("O is win");
				Tie = 1;
				break;
			}
			Owin = 0;
			for (int j = 0; j < 3; j++) {
				if (map[j][i] == "O") {
					Owin++;
				}
			}
			if (Owin == 3) {
				System.out.print("O is win");
				Tie = 1;
				break;
			}
			Owin = 0;
			for (int j = 0; j < 3; j++) {
				if (map[j][2 - j] == "O") {
					Owin++;
				}
			}
			if (Owin == 3) {
				System.out.print("O is win");
				Tie = 1;
				break;
			}
			for (int j = 0; j < 3; j++) {
				if (map[j][j] == "O") {
					Owin0++;
				}
			}
			if (Owin0 == 3) {
				System.out.print("O is win");
				Tie = 1;
				break;
			}
		}
	}

	public void run() {
		print();
		for (int i = 0; i < 4; i++) {
			placePlayer1();
			print();
			checkWinner();
			if (Tie == 1) {
				break;
			}
			placePlayer2();
			print();
			checkWinner();
			if (Tie == 1) {
				break;
			}

		}
		if (Tie == 0) {
			placePlayer1();
			print();
			checkWinner();
		}
		if (Tie == 0) {
			System.out.print("Tie");
		}
	}
}
