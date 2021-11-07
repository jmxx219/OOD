import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향설계및실습
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 */
public class DeleteCommand implements Command, Cloneable{
	private final Pane pane;
	private Shape shape;
	
	public DeleteCommand(Pane pane) {
		this.pane = pane;
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	@Override
	public void execute() {
		pane.getChildren().remove(shape);
//		System.out.println("삭제:" + shape.toString());
	}

	@Override
	public void undo() {
		pane.getChildren().add(shape);
	}
	
	@Override
	public void redo() {
		pane.getChildren().remove(shape);
	}

	@Override
	public Shape getShape() {
		return shape;
	}
	
	@Override
	protected DeleteCommand clone() {
		try {
			return (DeleteCommand) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
