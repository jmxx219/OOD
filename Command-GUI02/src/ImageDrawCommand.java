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
 * 명령 객체이지만 Receiver가 없음
 * - 방법 1. clone을 이용함 ==> 와부에 명령 객체 스택이 필요함
 * - 방법 2. clone을 사용하지 않고 방법 1처럼 매번 새 객체를 생성하여 사용할 수 있음
 *   ==> 와부에 명령 객체 스택이 필요함
 * - 방법 3. 멤버 변수로 ImageView를 유지하는 스택 사용 가능 (undo 로직을 별도 구현) 
 *   ==> 객체를 하나만 생성 가능. 하지만 여러 개 명령 객체를 이용하면 이 방법을 사용하기 힘듦
 */
public class ImageDrawCommand implements Command{
	private final Image image;
	private final Pane pane;
	private final double size;
	// execute에서 그린 이미지를 undoImageView에 유지하여 나중에 undo할 때 활용 
	private Stack<ImageView> undoImageViews = new Stack<>();
	public ImageDrawCommand(Image image, Pane pane, double size) {
		this.image = image;
		this.pane = pane;
		this.size = size;
	}
	@Override
	public void execute() {
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(size);
		imageView.setPreserveRatio(true);
		double width = size;
		double height = imageView.boundsInParentProperty().get().getHeight();
		double x = ThreadLocalRandom.current().nextInt((int)(pane.getWidth()-width));
		double y = ThreadLocalRandom.current().nextInt((int)(pane.getHeight()-height));
		
		imageView.setX(x);
		imageView.setY(y);
		pane.getChildren().add(imageView);
		undoImageViews.push(imageView);
	}
	@Override
	public void undo() {
		if(!undoImageViews.isEmpty())
			pane.getChildren().remove(undoImageViews.pop());
	}
}