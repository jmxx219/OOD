import java.util.Stack;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2021년도 2학기
 * @author 2019136072 손지민
 * 1. 명령 패턴 없이 구현(취소, 재실행 구현)
 * 취소를 하기 전에는 어떤 행동을 바로 전에 했는지, 그 행동이 어떤 도형과 연결되어 있는지 정보를 유지 -> 스택에
 */
public class DrawShapeApp extends Application {
	enum ShapeType {SQUARE, CIRCLE, TRIANGLE};
	enum ShapePlay {DRAW, CHANGE, DELETE}; // 그리기, 색 변경, 지우기
	private static final int HEIGHT = 500;
	private static final int WIDTH = 500;
	private static final int RADIUS = 40;
	
	private RadioButton drawButton = new RadioButton("추가");
	private RadioButton selectButton = new RadioButton("선택");
	private Button undoButton = new Button("취소");
	private Button redoButton = new Button("재실행");
	
	private Stack<Shape> undoShape = new Stack<>(); // 이전 실행 도형
	private Stack<ShapePlay> undoPlay = new Stack<>(); // 이전 실행 행동
	private Stack<Paint> undoColor = new Stack<>(); // 이전 색깔
	
	private Stack<Shape> redoShape = new Stack<>(); // 취소 도형
	private Stack<ShapePlay> redoPlay = new Stack<>(); // 취소 행동
	private Stack<Paint> redoColor = new Stack<>(); // 취소 색깔
	
	private ComboBox<String> shapeChoice = new ComboBox<>();
	private ShapeType currentShape = ShapeType.SQUARE;
	private Shape selectedShape = null;
	
	private ContextMenu popupMenu = new ContextMenu();
	
	private Pane centerPane = new Pane();
	
	private void drawShape(double x, double y) {
		Shape shape = null;
		switch(currentShape) {
		case SQUARE:
			shape = new Rectangle(x-RADIUS, y-RADIUS, RADIUS*2, RADIUS*2);
			break;
		case CIRCLE:
			shape = new Circle(x,y, RADIUS);
			break;
		case TRIANGLE:
			Polygon triangle = new Polygon();
			final double radian = Math.PI / 180F;
			triangle.getPoints().addAll(new Double[] {
				x+RADIUS*Math.cos(30*radian),
				y+RADIUS*Math.sin(30*radian),
				x+RADIUS*Math.cos(150*radian),
				y+RADIUS*Math.sin(150*radian),
				x+RADIUS*Math.cos(270*radian),
				y+RADIUS*Math.sin(270*radian)
			});
			shape = triangle;
		}
		shape.setStroke(Color.BLACK);
		shape.setFill(null);
		shape.setStrokeWidth(5d);
		centerPane.getChildren().add(shape);
		undoShape.add(shape);
		undoPlay.add(ShapePlay.DRAW);
		redoPlay.clear();
		redoShape.clear();
		redoColor.clear();
	}
	
	private void changeColor() {
		undoShape.add(selectedShape);
		undoPlay.add(ShapePlay.CHANGE);
		undoColor.add(selectedShape.getFill());
		redoPlay.clear();
		redoShape.clear();
		redoColor.clear();
		ColorPicker picker = new ColorPicker();
		picker.setLayoutX(selectedShape.getBoundsInLocal().getCenterX());
		picker.setLayoutY(selectedShape.getBoundsInLocal().getCenterY());
		centerPane.getChildren().add(picker);
		picker.setOnAction(e->{
			selectedShape.setFill(picker.getValue());
			centerPane.getChildren().remove(picker);
		});
	}
	
	private void deleteShape() {
		undoShape.add(selectedShape);
		undoPlay.add(ShapePlay.DELETE);
		redoPlay.clear();
		redoShape.clear();
		redoColor.clear();
		centerPane.getChildren().remove(selectedShape);
	}
	
	private void selectShape(double x, double y, double screenX, double screenY) {
		for(int i=centerPane.getChildren().size()-1; i>=0; i--) {
			Shape shape = (Shape) centerPane.getChildren().get(i);
			if(shape.getBoundsInLocal().contains(x, y)) {
				selectedShape = shape;
				popupMenu.show(centerPane, screenX, screenY);
				break;
			}
		}
	}
	
	private void mouseHandle(MouseEvent mouseEvent) {
		double x = mouseEvent.getX();
		double y = mouseEvent.getY();
		if(x<RADIUS) x = RADIUS;
		else if(x+RADIUS>WIDTH) x = WIDTH-RADIUS;
		if(y<RADIUS) y = RADIUS;
		else if(y+RADIUS>HEIGHT) y = HEIGHT-RADIUS;
		if(drawButton.isSelected()) drawShape(x, y);
		else selectShape(x, y, mouseEvent.getScreenX(), mouseEvent.getScreenY());
	}
	
	private void undoHandle() {
		if(undoShape.isEmpty()) return;
		Shape sh = undoShape.pop();
		ShapePlay play = undoPlay.pop();
		redoShape.add(sh);
		redoPlay.add(play);
		
		switch(play) {
		case DRAW:
			centerPane.getChildren().remove(sh);
			break;
		case CHANGE:
			redoColor.add(sh.getFill());
			Paint prevPicker = undoColor.pop();
			sh.setFill(prevPicker);
			break;
		case DELETE:
			centerPane.getChildren().add(sh);
			break;
		}
		
	}
	
	private void redoHandle() {
		if(redoShape.isEmpty()) return;
		
		Shape sh = redoShape.pop();
		ShapePlay play = redoPlay.pop();
		undoShape.add(sh);
		undoPlay.add(play);
		
		switch(play) {
		case DRAW:
			centerPane.getChildren().add(sh);
			break;
		case CHANGE:
			undoColor.add(sh.getFill());
			Paint prevPicker = redoColor.pop();
			sh.setFill(prevPicker);
			break;
		case DELETE:
			centerPane.getChildren().remove(sh);
			break;
		}
	}
	
	private HBox constructControlPane() {
		String[] shapeList = {"Square", "Circle", "Triangle"};
		shapeChoice.getItems().addAll(shapeList);
		shapeChoice.getSelectionModel().selectFirst();
		shapeChoice.setOnAction(e->
			currentShape = ShapeType.valueOf(shapeChoice.getSelectionModel().getSelectedItem().toUpperCase())
		);
	
		ToggleGroup actionGroup = new ToggleGroup();
		actionGroup.getToggles().addAll(drawButton, selectButton);
		drawButton.setSelected(true);
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		
		HBox controlPane = new HBox();
		controlPane.setPadding(new Insets(10));
		controlPane.setSpacing(10);
		controlPane.getChildren().addAll(shapeChoice, drawButton, selectButton, spacer, undoButton, redoButton);
		return controlPane;
	}
	
	private void constructPopupMenu() {
		MenuItem fillColorItem = new MenuItem("채우기 색 변경");
		fillColorItem.setOnAction(e->changeColor());
		MenuItem deleteItem = new MenuItem("삭제");
		deleteItem.setOnAction(e->deleteShape());
		popupMenu.getItems().addAll(fillColorItem, deleteItem);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		
		centerPane.setBackground(
			new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		centerPane.setMinWidth(500d);
		centerPane.setMinHeight(500d);
		centerPane.setOnMouseClicked(e->mouseHandle(e));
		
		undoButton.setOnAction(e->undoHandle());
		redoButton.setOnAction(e->redoHandle());
		
		mainPane.setCenter(centerPane);
		mainPane.setTop(constructControlPane());
		primaryStage.setTitle("도형 그리기");
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.setResizable(false);
		primaryStage.show();
		
		constructPopupMenu();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
