/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 * @file CondimentDecorator.java
 * 장식패턴에서 장식자 추상 클래스
 * 장식 제거
 */
public abstract class CondimentDecorator extends Beverage {
	protected Beverage beverage;
	protected CondimentDecorator(Beverage beverage){
		this.beverage = beverage;
	}
	@Override
	public abstract String getDescription();
}
