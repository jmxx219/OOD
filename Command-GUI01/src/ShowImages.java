import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
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
 * 명령패턴: GUI의 이벤트 처리
 * GUI의 이벤트 처리는 관찰자 패턴, 명령 패턴이 동시에 구현되어 있는 형태
 * 관찰자 패턴: 노드(또는 사건)가 Subject, Event 처리자가 Observer
 * 명령 패턴: 노드가 invoker가 되고, Event 처리자가 명령 객체
 * 그럼에도 불구하고 기존 구현 방식은 execute와 그것에 대응되는 undo가 하나의 객체로 모델링되지 않음
 * Undo 기능이 필요한 경우는 어떻게?
 * - 방법 1. undo 기능을 하는 메소드를 만들어 undo 사건에 처리자로 등록
 * - 방법 2. 명령 객체를 정의하여 사용함
 * 
 * 이 예제에서 receiver가 없고, 명령 객체에 수행해야 하는 일을 정의하고 있음   
 * 이 예제는 명령 객체를 스택에 유지하여 실행한 것을 계속 취소할 수 있도록 하고 있음
 * 하지만 같은 객체를 계속 저장하면 올바르게 취소되지 않기 때문에 매번 새로운 명령 객체를 생성하여 사용
 * 
 * 축구공: 명령 객체를 이용하지 않음
 * 야구공, 농구공: 명령 객체를 이용함
 */
public class ShowImages extends Application {
	private Button soccerDoButton = new Button("축구공");
	private Button soccerUndoButton = new Button("축구공 취소");
	private Button baseballDoButton = new Button("야구공");
	private Button basketballDoButton = new Button("농구공");
	private Button undoButton = new Button("취소");
	private Pane centerPane = new Pane();
	// 명령 객체를 유지하는 것과 최근에 그린 이미지 노드를 유지하는 것과의 차이는?
	private Stack<ImageView> soccerImages = new Stack<>();
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
	private void drawImage(Image image) {
		ImageView iView = createInstance(image);
		soccerImages.push(iView);
		centerPane.getChildren().add(iView);
	}
	private void removeImage() {
		if(!soccerImages.isEmpty())
			centerPane.getChildren().remove(soccerImages.pop());
	}
	private void setActions() {
		Image soccer = new Image("soccer.png");
		Image baseball = new Image("baseball.png");
		Image basketball = new Image("basketball.png");
		
		// 기존에 사용한 방법
		soccerDoButton.setOnAction(e->drawImage(soccer));
		soccerUndoButton.setOnAction(e->removeImage());
		
		// 명령 객체를 이용하고 있음
		// 매번 생성함
		baseballDoButton.setOnAction(e->{ 
			Command command = new ImageDrawCommand(baseball, centerPane, 50d);
			undoCommands.push(command);
			command.execute();
		}); // 야구공
		basketballDoButton.setOnAction(e->{
			Command command = new ImageDrawCommand(basketball, centerPane, 100d);
			undoCommands.push(command);
			command.execute();
		}); // 농구공
		undoButton.setOnAction(e->{
			if(!undoCommands.isEmpty()) {
				Command command = undoCommands.pop();
				command.undo();
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
