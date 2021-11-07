/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * NewReleaseMovie 클래스: 최신 영화 
 */
public class NewReleaseMovie extends Movie {
	public NewReleaseMovie(String title) {
		super(title);
	}
	@Override public int getCharge(int daysRented){
		return daysRented*2000;
	}
	@Override public int getFrequentRentalPoints(int daysRented){
		return (daysRented>1)? 200: 100;
	}
}

