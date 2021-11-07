/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 김상진
 * 팩토리 메소드 패턴: Head First Pattern 예제
 * CheesePizza.java: 치즈피자
 * 단순 함수로 생성 부분을 추상화
 */
public class PizzaFactory {
	// type-safe 하지 않음. 이것을 극복하는 방법은?
	public Pizza createPizza(String type) {
		switch(type){
		case "치즈": return new CheesePizza();
		case "포테이토": return new PotatoPizza();
		} // 마르게리타, 하와이안, 고르곤졸라, 페퍼로니, 불고기
		return null;
	}
}
