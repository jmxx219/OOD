/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 김상진
 * 리펙토링
 * ChildrensPrice: 아동 영화 대여금과 적립금
 */
public class ChildrensPrice implements Price {
	@Override
	public int getCharge(int daysRented) {
		return (daysRented>3)? 1500+(daysRented-3)*1500: 1500;
	}
}
