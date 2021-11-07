import java.util.Objects;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2021년도 2학기 
 * 리펙토링
 * Rental 클래스: 대여정보 
 * @author 김상진 
 */
public class Rental {
	private Movie movie;	// 대여영화
	private int daysRented;	// 대여기간 
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
}
