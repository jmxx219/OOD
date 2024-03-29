import java.util.ArrayList;
import java.util.List;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * Customer 클래스: 고객 대여정보
 * 대여목록 출력 기능을 가지고 있음 
 * statement 메소드에서 각 대여마다 대여료를 계산하는 부분을 extract method한 버전 
 * @author 김상진 
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
	// 고객님 대여목록을 출력하고
	// 대여금액과 적립금액을 계산함
	public String statement(){
		int totalprice = 0;
		int frequentRentalPoints = 0;
		String result = String.format("고객 %s님의 대여목록:\n", name);
		for(Rental rental: rentals){
			int thisprice = computeRentalPrice(rental);
			frequentRentalPoints += 100;
			if((rental.getMovie().getPriceCode()==Movie.PriceCode.NEW_RELEASE) &&
				rental.getDaysRented()>1)
				frequentRentalPoints += 100;
			result += String.format("\t%s\t%,d원\n", rental.getMovie().getTitle(), thisprice);
			totalprice += thisprice;
		}
		result += String.format("총금액: %,d원\n", totalprice);
		result += String.format("적립포인트: %d점\n", frequentRentalPoints);
		return result;
	}
	// Extract Method //
		// 사용되는 변수: 
		// 1) thisAmount: 이 과정에서 계산되는 값 
		// 2) rental: 이 과정에서 참조되는 값
		/*
			int thisAmount = 0;
			switch(rental.getMovie().getPriceCode()){
			case REGULAR:
				thisAmount += 2000;
				if(rental.getDaysRented()>2)
					thisAmount += (rental.getDaysRented()-2)*1500;
				break;
			case NEW_RELEASE:
				thisAmount += rental.getDaysRented()*2000;
				break;
			case CHILDRENS:
				thisAmount += 1500;
				if(rental.getDaysRented()>3)
					thisAmount += (rental.getDaysRented()-3)*1500;
				break;
			}
		*/
		// ************** //
		private int computeRentalPrice(Rental rental){
			int price = 0;
			switch(rental.getMovie().getPriceCode()){
			case REGULAR:
				price += 2000;
				if(rental.getDaysRented()>2)
					price += (rental.getDaysRented()-2)*1500;
				break;
			case NEW_RELEASE:
				price += rental.getDaysRented()*2000;
				break;
			case CHILDRENS:
				price += 1500;
				if(rental.getDaysRented()>3)
					price += (rental.getDaysRented()-3)*1500;
				break;
			}
			return price;
		}
}
