/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * Composite Pattern
 * Test.java
 * 테스트 프로그램
 */
public class Test {
	public static void main(String[] args) {
		Node value1 = new ValueNode(1);
		Node value2 = new ValueNode(2);
		Node value3 = new ValueNode(3);
		Node multNode = new OperatorNode(Operator.MULTIPLY,value2,value3);
		Node addNode = new OperatorNode(Operator.ADD,value1,multNode);
		System.out.println(addNode.evaluate());
	}

}
