import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2021년도 2학기
 * @author 김상진
 * GUI01과 축구공 차이점.
 * 1. 이벤트 처리자에 undo 메소드 추가
 * 2. Undo를 처리하기 위한 스택을 처리자 내에 정의함
 * GUI01과 야구공, 농구공 차이점.
 * 1. 매번 새로운 명령 객체를 생성하지 않음
 * 2. clone 대신에 내부적으로 스택 유지함
 * 3. 여러 명령 객체를 이용하기 때문에 외부 스택이 추가로 필요함 
 *    ==> static 스택을 사용하면???
 *    ==> 여러 개의 명령 객체를 이용할 경우에는 Command Manager 개념의 도입도 고려할 수 있음
 */

interface HandlerWithUndo extends EventHandler<ActionEvent>{ 
	void undo();
}

public class ShowImages extends Application {
	private Button soccerDoButton = new Button("축구공");
	private Button soccerUndoButton = new Button("축구공 취소");
	private Button baseballDoButton = new Button("야구공");
	private Button basketballDoButton = new Button("농구공");
	private Button undoButton = new Button("취소");
	private Pane centerPane = new Pane();
	// 명령 객체를 유지하는 것과 최근에 그린 이미지 노드를 유지하는 것과의 차이는?
	private Stack<Command> undoCommands = new Stack<>();
	
	private ImageView createInstance(Image image) {
		ImageView iView = new ImageView(image);
		iView.setFitWidth(100d);
		iView.setPreserveRatio(true);
		
		double width = 100d;
		double height = iView.boundsInParentProperty().get().getHeight();
		double x = ThreadLocalRandom.current().nextInt((int)(centerPane.getWidth()-width));
		double y = ThreadLocalRandom.current().nextInt((int)(centerPane.getHeight()-height));
		
		iView.setX(x);
		iView.setY(y);
		return iView;
	}
	
	private class DrawHandler implements HandlerWithUndo{
		private Image image;
		private Stack<ImageView> soccerImages = new Stack<>();
		public DrawHandler(Image image) {
			this.image = image;
		}
		@Override public void handle(ActionEvent event) {
			ImageView iView = createInstance(image);
			soccerImages.push(iView);
			centerPane.getChildren().add(iView);
		}
		@Override public void undo() {
			if(!soccerImages.isEmpty())
				centerPane.getChildren().remove(soccerImages.pop());
		}
	}
	
	private void setActions() {
		Image soccer = new Image("soccer.png");
		Image baseball = new Image("baseball.png");
		Image basketball = new Image("basketball.png");
		
		DrawHandler handler = new DrawHandler(soccer);
		
		ImageDrawCommand baseballCommand = new ImageDrawCommand(baseball, centerPane, 50d);
		ImageDrawCommand basketballCommand = new ImageDrawCommand(basketball, centerPane, 100d);
		
		// 기존에 사용한 방식
		soccerDoButton.setOnAction(handler);
		soccerUndoButton.setOnAction(e->handler.undo());
		
		// 명령 객체를 이용하고 있음
		baseballDoButton.setOnAction(e->{
			baseballCommand.execute();
			undoCommands.push(baseballCommand);
		}); // 야구공
		basketballDoButton.setOnAction(e->{
			basketballCommand.execute();
			undoCommands.push(basketballCommand);
		}); // 농구공
		undoButton.setOnAction(e->{
			if(!undoCommands.isEmpty()) {
				undoCommands.pop().undo();
			}
		});		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		HBox buttonPane = new HBox();
		buttonPane.setPadding(new Insets(10d));
		buttonPane.setSpacing(10d);
		buttonPane.getChildren().addAll(soccerDoButton, soccerUndoButton,
				baseballDoButton, basketballDoButton, undoButton);
		setActions();
		
		centerPane.setBackground(
			new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		centerPane.setMinWidth(500d);
		centerPane.setMinHeight(500d);
		
		mainPane.setCenter(centerPane);
		mainPane.setBottom(buttonPane);
		primaryStage.setTitle("Command Pattern Example");
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
