import java.util.Optional;

/*
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * CalculatorController.java
 * 컨트롤러: 
 * 1) 뷰와 모델 유지
 * 2) 뷰의 구성요소에 대한 처리자 구현
 * 3) 정규표현식 등을 이용하여 사용자가 입력한 표현식의 유효성 검사
 */
public class CalculatorController {
	private CalculatorView theView;
	private CalculatorModel theModel;
	private InfixExpressionValidator validator = new InfixExpressionValidator();
	public CalculatorController(CalculatorView theView, CalculatorModel theModel) {
		this.theView = theView;
		this.theModel = theModel;
		this.theView.setCalculatorListener(e->handleCompute());
	}

	public void handleCompute() {
		String infix = theView.getExpression();
		Optional<String> message = validator.test(infix);
		
		if(message.isPresent()){
			theView.setException(message.get());
		}
		else {
			infix = InfixExpressionValidator.replaceUnaryMinus(infix);
			theView.setCalcSolution(theModel.compute(infix));
			theView.setException(
				"주의. 단항 연산자(-), 이항 연산자(+,-,*,/), 괄호만 사용할 수 있음"
			);
		}
	}
	
}
