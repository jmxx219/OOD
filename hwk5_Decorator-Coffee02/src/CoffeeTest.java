/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 * @file CoffeeTest.java
 * 생성 메소드 추가 버전
 * 인자로 클래스 이름과 같은 문자열을 제공해야 함
 * 한글을 사용하고 싶으면 별도 매핑 테이블이 필요함
 */

class Dog{
}

public class CoffeeTest {
	public static void main(String[] args) {
		BeverageFactory.addAdditionRestriction("Whip" , 1);
		BeverageFactory.addAdditionRestriction("Milk" , 0); // 0은 갯수 제한이 없다.
		BeverageFactory.addAdditionRestriction("Mocha", 2);
		BeverageFactory.addCoffeeRestriction("Mocha", "DarkRoast");
//		BeverageFactory.print();
		try {
			Beverage beverage 
				= BeverageFactory.createCoffee("HouseBlend", "Mocha", "Whip", "Mocha", "Mocha", "Whip");
			System.out.printf("%s: %,d원%n", 
				beverage.getDescription(), beverage.cost());
			beverage 
				= BeverageFactory.createCoffee("HouseBlend", "Milk", "Mocha", "Milk", "Milk");
			System.out.printf("%s: %,d원%n", 
					beverage.getDescription(), beverage.cost());
			
			beverage 
			= BeverageFactory.createCoffee("HouseBlend", "Whip", "Mocha");
		System.out.printf("%s: %,d원%n", 
				beverage.getDescription(), beverage.cost());
//			beverage 
//				= BeverageFactory.createCoffee("Dog", "Milk", "Mocha");
			//	= Beverage.createCoffee("Mocha", "Milk", "Mocha");
			//	= Beverage.createCoffee("DarkRoast", "DarkRoast", "Mocha");
//			System.out.printf("%s: %,d원%n", 
//					beverage.getDescription(), beverage.cost());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
