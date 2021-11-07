/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * TimeTest.java
 * 불변 클래스 Time, 객체 풀, 테스트 프로그램
 */
public class TimeTest {

	public static void main(String[] args) {
		Time t1 = Time.of(10,30,20);
		Time t2 = Time.of(10,30,20);
		Time t3 = Time.of(20,30,10);
		
		Time t4 = Time.of(1,5,10);
		Time t5 = Time.of(10,5,1);
		Time t6 = Time.of(5,1,10);
		
		System.out.println(t1==t2);
		System.out.println(t3==t2);
		System.out.println(t1==t3);
		System.out.println(t4==t5);
		System.out.println(t4==t6);
		System.out.println(t5==t6);
		
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);
		System.out.println(t4);
		System.out.println(t5);
		System.out.println(t6);
	}
}
