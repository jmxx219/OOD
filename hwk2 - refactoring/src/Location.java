/**
 * @copyright �ѱ�����������б� ��ǻ�Ͱ��к� ��ü���⼳��׽ǽ�
 * @version 2021�⵵ 2�б�
 * @author 2019136072 ������
 */
public class Location {
	private int row;
	private int col;
	private int length;

	public Location(int r, int c, int l) {
		this.row = r;
		this.col = c;
		this.length = l; // ������ġ���� ���� �ش���ġ���� �Ÿ� ����
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
