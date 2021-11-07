/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향설계및실습
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 */
public class Location {
	private int row;
	private int col;
	private int length;

	public Location(int r, int c, int l) {
		this.row = r;
		this.col = c;
		this.length = l; // 시작위치에서 부터 해당위치까지 거리 저장
	}

	public int getRow() {
		return this.row;
	}
	public int getCol() {
		return this.col;
	}
	public int getLength() {
		return this.length;
	}
}
