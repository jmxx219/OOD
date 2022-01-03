import javafx.event.ActionEvent; 

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 김상진 
 * MVC 패턴
 * TTTController.java
 * TicTacToe 게임에서 컨트롤러 역할: 모델과 뷰 유지
 *  1) 뷰의 구성요소 이벤트 처리자 구현
 *  2) 사용자 조작 결과를 모델에 전달, 모델과 상호작용 결과를 뷰에 전달
 *  3) 게임 로직이 구현되어 있음???
 */
public class TTTController {
	private TTTModel theModel;
	private TTTView theView;
	public TTTController(TTTModel theModel, TTTView theView) {	
		this.theModel = theModel;
		this.theView = theView;
		theView.setButtonHandler(e->boardButtonClicked(e), e->newGameButtonClicked());
	}
	public void newGameButtonClicked() {
		theModel.clear();
		theView.clear();
	}
	public void boardButtonClicked(ActionEvent event) {
		TTTButton button = (TTTButton)event.getSource();
		int row = button.getRow();
		int col = button.getCol();
		if(theModel.hasGameFinished()) return;
		if(!theModel.isEmpty(row, col)) return;
		boolean userPlayer = theModel.setCell(row, col);
		theView.update(row,col,userPlayer);
		theModel.printboard();
		if(theModel.hasWinner()) {
			theView.setInformation(userPlayer? "사용자 승": "컴퓨터 승");
		}
		else if(theModel.hasGameFinished()) {
			theView.setInformation("무승부");
		}
	}
}
