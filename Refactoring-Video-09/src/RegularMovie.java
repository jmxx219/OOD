/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * RegularMovie 클래스: 일반 영화 
 */
public class RegularMovie extends Movie {
	public RegularMovie(String title) {
		super(title);
	}
	@Override public int getCharge(int daysRented){
		return (daysRented>2)? 2000+(daysRented-2)*1500: 2000;
	}
}
