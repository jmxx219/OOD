/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 * @file CoffeeTest.java
 * 장식 제거. 가장 바깥쪽 장식만 제거 가능
 */
public class CoffeeTest {
	public static void main(String[] args) {
		Beverage beverage = new HouseBlend();
		beverage = new Milk(beverage);
//		beverage = new Mocha(beverage);
//		beverage = new Whip(beverage);
//		beverage = new Whip(beverage);
		System.out.printf("%s: %,d원%n", 
				beverage.getDescription(), beverage.cost());
		
		Beverage beverage2 = new HouseBlend();
//		beverage2 = new Mocha(beverage2);
		beverage2 = new Whip(beverage2);
//		beverage2 = new Milk(beverage2);
		System.out.printf("%s: %,d원%n", 
				beverage2.getDescription(), beverage2.cost());
		
		System.out.println(beverage.equals(beverage2));
	}
}
