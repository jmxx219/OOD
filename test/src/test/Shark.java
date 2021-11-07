/**
 * 
 */
package test;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향설계및실습
 * @version 2021년도 2학기
 * @author 2019136072 손지민
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
