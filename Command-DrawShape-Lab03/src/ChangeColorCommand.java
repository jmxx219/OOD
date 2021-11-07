import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향설계및실습
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 */
public class ChangeColorCommand implements Command, Cloneable{
	private final Pane pane;
	private Shape shape;
	private Paint undoPaint;
	
	public ChangeColorCommand(Pane pane) {
		this.pane = pane;
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	@Override
	public void execute() {
		undoPaint = shape.getFill();
		ColorPicker picker = new ColorPicker();
		picker.setLayoutX(shape.getBoundsInLocal().getCenterX());
		picker.setLayoutY(shape.getBoundsInLocal().getCenterY());
		pane.getChildren().add(picker);
		picker.setOnAction(e->{
			shape.setFill(picker.getValue());
			pane.getChildren().remove(picker);
		});
		
		
	}

	@Override
	public void undo() {
		Paint tmp = shape.getFill();
		shape.setFill(undoPaint);
		undoPaint = tmp;
	}
	
	@Override
	public void redo() {
		undo();
	}

	@Override
	public Shape getShape() {
		return shape;
	}
	
	@Override
	protected ChangeColorCommand clone() {
		try {
			return (ChangeColorCommand) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
