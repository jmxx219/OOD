/**
 * 
 */
package test;

/**
 * @copyright �ѱ�����������б� ��ǻ�Ͱ��к� ��ü���⼳��׽ǽ�
 * @version 2021�⵵ 2�б�
 * @author 2019136072 ������
 */
public class Shark extends Fish {
//	private int age;
//	private String name;
	
	public Shark(int age, String name) {
		super(age, name);
	}
	
	public void greeting(){
		super.greeting();
	}
	
	public void bite() {
		System.out.println("Bite!");
	}
}
