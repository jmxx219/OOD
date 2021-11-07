import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 김상진 
 * 리펙토링
 * Rental 클래스: 대여정보 
 */
public class Rental {
	private Movie movie;		// 영화
	private int daysRented;		// 대여기간 
	public Rental(Movie movie, int daysRented) {
		this.movie = Objects.requireNonNull(movie);
		this.daysRented = daysRented;
	}
	public Movie getMovie() {
		return movie;
	}
	public int getDaysRented() {
		return daysRented;
	}
	// MOVED HERE
	public int getCharge(){
		int price = 0;
		switch(getMovie().getPriceCode()){
		case REGULAR:
			price += 2000;
			if(daysRented>2) price += (daysRented-2)*1500;
			break;
		case NEW_RELEASE:
			price += daysRented*2000;
			break;
		case CHILDRENS:
			price += 1500;
			if(daysRented>3) price += (daysRented-3)*1500;
			break;
		}
		return price;
	}
}
