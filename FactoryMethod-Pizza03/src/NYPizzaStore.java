/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 김상진
 * 팩토리 메소드 패턴: Head First Pattern 예제
 * NYPizzaStore.java: 뉴욕 피자 팩토리
 * 팩토리 메소드 패턴: 구체적 팩토리
 */
public class NYPizzaStore extends PizzaStore {
	@Override
	// type-safe 하지 않음. 이것을 극복하는 방법은?
	protected Pizza createPizza(String type) {
		Pizza pizza = null;
		switch(type){
		case "치즈": pizza = new NYCheesePizza(); break;
		case "포테이토": pizza = new NYPotatoPizza(); break;
		}
		return pizza;
	}

}
