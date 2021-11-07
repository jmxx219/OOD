import java.util.ArrayList;
import java.util.List;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * Customer 클래스: 고객 대여정보
 * 대여목록 출력 기능을 가지고 있음 
 * Replace Temp with Query를 적용한 버전 (효율성은 무시하나?) 
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
		int totalprice = 0;
		int frequentRentalPoints = 0;
		String result = String.format("고객 %s님의 대여목록:\n", name);
		for(Rental rental: rentals){
			//Replace Temp with Query
			//int thisprice = rental.getCharge();
			//Eclipse-shortcut: Command-Alt-I
			frequentRentalPoints += rental.getFrequentRentalPoints(); // 총 적립포인트
			result += String.format("\t%s\t%,d원\n", 
					rental.getMovie().getTitle(), rental.getCharge()); // 각 대여마다 출력정보 
			totalprice += rental.getCharge(); // 총 대여금액 
		}
		result += String.format("총금액: %,d원\n", totalprice);
		result += String.format("적립포인트: %d점\n", frequentRentalPoints);
		return result;
	}
	/*
	private int computeFrequentRentalPoints(Rental rental) {
		int frequentRentalPoint = 100;
		if((rental.getMovie().getPriceCode()==Movie.PriceCode.NEW_RELEASE) &&
			rental.getDaysRented()>1)
			frequentRentalPoint += 100;
		return frequentRentalPoint;
	}
	*/
}
