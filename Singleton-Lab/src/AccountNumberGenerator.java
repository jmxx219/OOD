/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * AccountNumberGenerator.java
 * 열거형으로 싱글톤을 만들어 계좌번호 생성 
 */
public enum AccountNumberGenerator {
	UNIQUE;
	private int count = 0;
	public int getNext() {
		return ++count;
	}
}
