import java.util.List;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 2019136072 손지민  
 * @file BlackJackPlayerHand.java 
 * 탬플릿 메소드 패턴
 * 블랙잭 게임에서 각 플레이어의 패 정보 유지
 */
public class BlackJackPlayerHand {
	private List<Card> cards;
	private int score = 0;
	private boolean isBlackJack = false;
	public BlackJackPlayerHand(List<Card> cards) {
		this.cards = cards;
		score = computeScore();
	}
	public void init() {
		cards.clear();
		score = 0;
		isBlackJack = false;
	}
	public List<Card> getCards(){
		return cards;
	}
	public void addCard(Card card) {
		cards.add(card);
		score = computeScore();
	}
	public int getScore() {
		return score;
	}
	public boolean isBlackJack() {
		return isBlackJack;
	}
	private boolean isBust() {
		return score > 21;
	}

	private int computeScore() { // 현재 패의 합계 계산
		// ace는 1 또는 11을 사용할 수 있고, 현재 점수는 21에 가깝도록 ace를 활용해야 함. 
		// 또 블랙잭인 경우에는 블랙잭 변수를 true로 설정
		int sum = 0;
		int cntAce = 0;
		int blockN = 0;
		
		for(Card c : cards) {
			if(c.number() > 1 && c.number() <= 10) {
				sum += c.number();
			}
			else {
				if(c.number() == 1) {
					cntAce += 1;
					sum += 1;
				}
				if(c.number() > 10) sum += 10;
				blockN += 1;
			}
		}

		while(cntAce > 0) {
			if(sum + cntAce * 10 <= 21) {
				sum = sum + cntAce * 10;
			}
			cntAce -= 1;
		}
		
		if(sum == 21 && blockN > 0) isBlackJack = true;
		else isBlackJack = false;
		
		return sum;
	}

	// 사용자와 딜러의 합계와 블랙잭 여부를 이용하여 게임 결과를 결정함
	public static BlackJackGameResult determineResult( 
		BlackJackPlayerHand userHand, BlackJackPlayerHand dealerHand) {		
		
		if(!userHand.isBust() && !dealerHand.isBust()) { // 둘 다 bust가 아니면
			if(userHand.getScore() > dealerHand.getScore()) return BlackJackGameResult.USERWIN;
			else if(userHand.getScore() < dealerHand.getScore()) return BlackJackGameResult.USERLOST;
			else {
				if(userHand.getScore() == 21) {
					if(userHand.isBlackJack && dealerHand.isBlackJack) return BlackJackGameResult.DRAW;
					else if(userHand.isBlackJack) return BlackJackGameResult.USERWIN;
					else return BlackJackGameResult.USERLOST;
				}
				return BlackJackGameResult.USERLOST;
			}
		}
		
		// 둘 중 한 사람만 bust이면 
		if(userHand.isBust() && !dealerHand.isBust()) return BlackJackGameResult.USERLOST;
		else if(!userHand.isBust() && dealerHand.isBust()) return BlackJackGameResult.USERWIN;
		
		// 둘 다 bust이면
		return BlackJackGameResult.DRAW;
	}
}
