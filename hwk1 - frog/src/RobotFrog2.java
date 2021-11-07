/**
 * @copyright �ѱ�����������б� ��ǻ�Ͱ��к� ��ü���⼳��׽ǽ�
 * @version 2021�⵵ 2�б�
 * @author 2019136072 ������
 * 
 * ���԰��踦 �̿��Ͽ� ���� �� ����
 * 
 * 1. ����
 * - �ڵ� �ߺ��� �ٿ��ְ� ���� Ŭ������ Ȯ������ �ʾƵ� �ȴ�.
 * 
 * 2. ����
 * - ���� �������� �̿��� �� ����.
 */

public class RobotFrog2 {
	private Frog frog = new Frog();
	private int battery = 5;
	
	public void jump(){
		if(this.battery > 0) {
			frog.jump();
			this.battery -= 1;
		}
	}

	public void croak(){
		if(this.battery > 0) {
			frog.croak();
			this.battery -= 1;
		}
	}
	
	public void charge() {
		this.battery = 5;
	}
}
