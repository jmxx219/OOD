/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * SerialNumberGenerator.java
 * 내부 클래스를 이용하여 싱글톤을 만들어 계좌번호 생성 
 */
public class SerialNumberGenerator {
	private static class Holder{
		private static final SerialNumberGenerator unique = new SerialNumberGenerator();
	}
	private int count = 0;
	private SerialNumberGenerator(){}
	public static SerialNumberGenerator getInstance(){
		return Holder.unique;
	}
	public int getNext(){
		++count;
		return count;
	}
	
}
