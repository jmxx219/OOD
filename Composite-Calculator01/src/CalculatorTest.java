import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * 2021년도 2학기
 * Composite Pattern
 * CalculatorTest.java
 * 테스트 프로그램
 * @author 김상진
 */
class CalculatorTest {

	@Test
	void simpleTest() {
		Node value1 = new ValueNode(1);
		Node value2 = new ValueNode(2);
		Node value3 = new ValueNode(3);
		Node multNode = new OperatorNode(Operator.MULTIPLY,value2,value3);
		Node addNode = new OperatorNode(Operator.ADD,value1,multNode);
		assertEquals(addNode.evaluate(),2*3+1.0);
		
		Node divNode = new OperatorNode(Operator.DIVIDE,value2,value3);
		addNode = new OperatorNode(Operator.ADD,value1,divNode);
		assertEquals(addNode.evaluate(),2.0/3+1);
		
		Node subNode = new OperatorNode(Operator.SUBTRACT,value3,value2);
		addNode = new OperatorNode(Operator.ADD,value1,subNode);
		assertEquals(addNode.evaluate(),3-2+1.0);
	}

}
