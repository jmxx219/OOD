/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * Composite Pattern
 * SubtractNode.java
 * 자식을 가지는 연산자 노드 중 뺄셈 노드
 */
public class SubtractNode extends OperatorNode {
	public SubtractNode(Node left, Node right) {
		super(left, right);
	}
	@Override
	public double evaluate() {
		return left.evaluate()-right.evaluate();
	}

}
