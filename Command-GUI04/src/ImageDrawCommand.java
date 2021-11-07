import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 김상진
 * @file ImageDrawCommand.java: 화면에 랜덤 위치에 그림을 추가
 */
public final class ImageDrawCommand implements Command, Cloneable{
	private final Image image;
	private final Pane pane;
	private final double size;
	// execute에서 그린 이미지를 undoImageView에 유지하여 나중에 undo할 때 활용 
	private ImageView undoImageView = null;
	private static final Stack<ImageView> history = new Stack<>();
	public ImageDrawCommand(Image image, Pane pane, double size) {
		this.image = image;
		this.pane = pane;
		this.size = size;
	}
	@Override
	public void execute() {
		undoImageView = new ImageView(image);
		undoImageView.setFitWidth(size);
		undoImageView.setPreserveRatio(true);
		double width = size;
		double height = undoImageView.boundsInParentProperty().get().getHeight();
		double x = ThreadLocalRandom.current().nextInt((int)(pane.getWidth()-width));
		double y = ThreadLocalRandom.current().nextInt((int)(pane.getHeight()-height));
		
		undoImageView.setX(x);
		undoImageView.setY(y);
		pane.getChildren().add(undoImageView);
		history.push(undoImageView);
	}
	@Override
	public void undo() {
		if(!history.isEmpty())
			pane.getChildren().remove(history.pop());
	}
}