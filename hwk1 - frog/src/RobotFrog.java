/**
 * @copyright �ѱ�����������б� ��ǻ�Ͱ��к� ��ü���⼳��׽ǽ�
 * @version 2021�⵵ 2�б�
 * @author 2019136072 ������
 * 
 * ����� �̿��Ͽ� ���� �� ����
 * 
 * 1. ����
 * - �ڵ� �ߺ��� �ٿ��ְ� �ڵ带 ������ �� �ְ� ���ش�.
 * - �θ� Ÿ���� �̿��Ͽ� �پ��� ������ �ڽ��� ���� ���������� ó���� �� �ִ�.
 * 
 * 2. ����
 * - �ڽ� Ŭ������ �ڽ��� �ʿ�ʹ� ������� �θ� Ŭ������ ��� �޼ҵ带 ��ӹ޴´�.
 * - subclassing�� �ǰ� subtyping�� ������� ���� �� �ִ�.
 */

public class RobotFrog extends Frog {
	private int battery = 5;
	
	public void jump(){
		if(this.battery > 0) {
			super.jump();
			this.battery -= 1;
		}
	}

	public void croak(){
		if(this.battery > 0) {
			super.croak();
			this.battery -= 1;
		}
	}
	
	public void charge() {
		this.battery = 5;
	}
}