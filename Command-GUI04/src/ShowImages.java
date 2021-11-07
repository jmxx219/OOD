import java.util.Stack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
 * Command-GUI03과 차이점
 * 차이점 1. clone를 이용하지 않는 버전
 * 차이점 2. 명령 객체가 static 히스토리 스택을 유지함 (Command-GUI02와 유사)
 * - 개별 스택을 유지하여도 올바르게 동작함
 */
public class ShowImages extends Application {
	private CommandHolderButton soccerDoButton = new CommandHolderButton("축구공");
	private CommandHolderButton baseballDoButton = new CommandHolderButton("야구공");
	private CommandHolderButton basketballDoButton = new CommandHolderButton("농구공");
	private Button undoButton = new Button("취소");
	private Pane centerPane = new Pane();
	private Stack<Command> undoCommands = new Stack<>();
	
	// 하나의 처리자에서 모든 노드의 처리할 이벤트를 하나의 코드로 모두 처리할 수 있음
	void doAction(ActionEvent event) {
		CommandHolderButton holderButton = (CommandHolderButton)event.getSource();
		Command command = holderButton.getCommand();
		command.execute();
		undoCommands.push(command);
	}
	
	void setActions() {
		soccerDoButton.setCommand(
				new ImageDrawCommand(new Image("soccer.png"), centerPane, 100d));
		baseballDoButton.setCommand(
				new ImageDrawCommand(new Image("baseball.png"), centerPane, 50d));
		basketballDoButton.setCommand(
				new ImageDrawCommand(new Image("basketball.png"), centerPane, 100d));		

		soccerDoButton.setOnAction(e->doAction(e));
		baseballDoButton.setOnAction(e->doAction(e));
		basketballDoButton.setOnAction(e->doAction(e));
		undoButton.setOnAction(e->{
			if(!undoCommands.isEmpty()) undoCommands.pop().undo();
		});			
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		HBox buttonPane = new HBox();
		buttonPane.setPadding(new Insets(10d));
		buttonPane.setSpacing(10d);
		buttonPane.getChildren().addAll(
			soccerDoButton, baseballDoButton, basketballDoButton, undoButton);
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
