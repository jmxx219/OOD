/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기
 * @author 김상진
 * Composite Pattern
 * OperatorNode.java
 * 자식을 가지는 연산자 노드, 추상 타입
 */
public abstract class OperatorNode extends Node {
	protected Node left;
	protected Node right;
	public OperatorNode(Node left, Node right) {
		this.left = left;
		this.right = right;
	}
}
