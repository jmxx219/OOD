import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 2019136072 손지민
 * 팩토리 메소드 패턴: Asteroid
 * UserShip.java: 사용자 우주선
 */
public class UserShip extends Group {
	private int x;
	private int y;
	private Line line1;
	private Line line2;
	private Line line3;
	public UserShip(int x, int y) {
		this.x = x;
		this.y = y;
		line1 = new Line(x+10,y,x,y+20);
		line2 = new Line(x+10,y,x+20,y+20);
		line3 = new Line(x+5,y+14,x+15,y+14);
		line1.setStroke(Color.WHITE);
		line2.setStroke(Color.WHITE);
		line3.setStroke(Color.WHITE);
		getChildren().addAll(line1, line2, line3);
	}
}
