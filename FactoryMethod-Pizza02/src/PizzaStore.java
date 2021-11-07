/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 김상진
 * 팩토리 메소드 패턴: Head First Pattern 예제
 * PizzaStore.java: 피자 가게
 * 피자를 생성하는 객체
 * 팩토리 패턴(단순 생성 메소드) + 전략 패턴 
 */
public class PizzaStore {
	private PizzaFactory pizzaFactory;
	public void setPizzaFactory(PizzaFactory pizzaFactory) {
		this.pizzaFactory = pizzaFactory;
	}
	public Pizza orderPizza(String type){
		Pizza pizza = pizzaFactory.createPizza(type);
		// 이 부분은 변하지 않을 부분
		pizza.prepare(); 
		pizza.bake(); 
		pizza.cut();
		pizza.box();
		return pizza; 
	}
}
