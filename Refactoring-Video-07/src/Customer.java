import java.util.ArrayList;
import java.util.List;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * Customer 클래스: 고객 대여정보
 * 대여목록 출력 기능을 가지고 있음  
 */
public class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();
	public Customer(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void addRental(Rental rental){
		rentals.add(rental);
	}
	public String statement(){
		String result = String.format("고객 %s님의 대여목록:\n", name);
		for(Rental rental: rentals)
			result += String.format("\t%s\t%,d원\n", 
				rental.getMovieTitle(), rental.getCharge());
		result += String.format("총금액: %,d원\n", getTotalCharge());
		result += String.format("적립포인트: %d점\n", getTotalFrequencyRenterPoints());
		return result;
	}
	private int getTotalCharge() {
		int totalprice = 0;
		for(Rental rental: rentals)
			totalprice += rental.getCharge();
		return totalprice;
	}
	private int getTotalFrequencyRenterPoints() {
		int frequentRenterPoints = 0;
		for(Rental rental: rentals)
			frequentRenterPoints += rental.getFrequentRentalPoints();
		return frequentRenterPoints;
	}
}
