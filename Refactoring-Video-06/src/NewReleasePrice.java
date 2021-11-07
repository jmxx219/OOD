/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2020년도 2학기 
 * 리펙토링
 * NewReleasePrice: 최신 영화 대여금과 적립금 
 * @author 김상진 
 */
public class NewReleasePrice implements Price {
	@Override
	public int getCharge(int daysRented) {
		return daysRented*2000;
	}
	@Override
	public int getFrequentRentalPoints(int daysRented){
		return daysRented>1 ? 200: Price.super.getFrequentRentalPoints(daysRented);
	}
}
