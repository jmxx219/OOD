/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * 상태 패턴
 * 자동판매기: 한번에 투입한 돈을 나타내기 위한 자료구조
 * 같은 종류의 동전, 화폐는 한번에 여러 개 투입이 가능한 것으로 처리함 
 */
public record InsertedCash(Currency currency, int amount) {
	public InsertedCash{
		if(amount<0) throw new IllegalArgumentException();
	}
}