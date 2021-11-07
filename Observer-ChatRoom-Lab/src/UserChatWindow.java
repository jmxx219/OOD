import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2021년도 2학기 
 * @author 2019136072 손지민
 * @file UserChatWindow.java
 * 각 사용자의 채팅창 (View+Controller 역할, 모델은 User 클래스)
 * 관찰자 패턴: User subject에 대한 관찰자
 */
public class UserChatWindow extends Stage{
	private User user;
	private CheckBox isOnline = new CheckBox("온라인");
	private ComboBox<String> roomChoice = new ComboBox<>();
	private TextArea chatArea = new TextArea();
	private TextArea sendArea = new TextArea();
	private Button sendButton = new Button("전송");
	private MenuItem leaveRoomMenuItem = new MenuItem("채팅방 나가기");
	private MenuItem deleteRoomContentsMenuItem = new MenuItem("채팅방 지우기");
	
	public UserChatWindow(User user) {
		this.user = user;
		BorderPane mainPane = new BorderPane();
			
		chatArea.setPrefRowCount(8);
		chatArea.setEditable(false);
		
		mainPane.setTop(constructChoiceView());
		mainPane.setCenter(chatArea);
		mainPane.setBottom(constructSendView());
		
		VBox topPane = new VBox();
		topPane.getChildren().addAll(constructMenuBar(), mainPane);
		
		setTitle(user.getUserID());
		setScene(new Scene(topPane,300,300));
	}
	
	private HBox constructChoiceView() {
		HBox choiceView = new HBox();
		roomChoice.getItems().addAll(user.getRooms());
		roomChoice.getSelectionModel().select(0);
		roomChoice.getSelectionModel().selectedItemProperty().addListener((o,ov,nv)->roomChanged(o,ov,nv));
		roomChoice.setMinWidth(200); 
		isOnline.setSelected(true);
		isOnline.setOnAction(e->onlineStatusChanged());
		choiceView.setPadding(new Insets(10));
		choiceView.setSpacing(10);
		choiceView.getChildren().addAll(roomChoice, isOnline);
		
		return choiceView;
	}
	
	private HBox constructSendView() {
		HBox sendView = new HBox();
		sendView.setPadding(new Insets(10));
		sendView.setSpacing(10);
		sendView.getChildren().addAll(sendArea, sendButton);
		sendArea.setPrefRowCount(2);
		sendArea.setPrefColumnCount(40);
		sendButton.setMinWidth(60);
		sendButton.setMinHeight(60);
		sendButton.setOnAction(e->sendMessage());
		return sendView;
	}
	
	private MenuBar constructMenuBar() {
		leaveRoomMenuItem.setOnAction(e->leaveRoom());
		deleteRoomContentsMenuItem.setOnAction(e->clearRoom());
		Menu setupMenu = new Menu("설정");
		setupMenu.getItems().addAll(leaveRoomMenuItem, deleteRoomContentsMenuItem);
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(setupMenu);
		return menuBar;
	}
	
	public void onlineStatusChanged() {
		user.setOnline(isOnline.isSelected());
		if(user.isOnline()) {
			sendButton.setDisable(false);
			sendArea.setEditable(true);
		}
		else {
			sendButton.setDisable(true);
			sendArea.setEditable(false);
		}
	}
	
	public void roomChanged(
			ObservableValue<? extends String> observable, String oldValue, String roomName) {
		if(roomName!=null) chatArea.setText(prepareOutput(roomName));
	}
	
	public void sendMessage() {
		String roomName = roomChoice.getSelectionModel().getSelectedItem();
		String message = sendArea.getText();
		if(message.length()!=0) {
			ChatServer.getServer().sendMessage(roomName, new ChatMessage(user.getUserID(), message));
			sendArea.setText("");
		}
	}
	
	public void leaveRoom() {
		Platform.runLater(new Runnable() {
		    @Override public void run() {
		    	String roomName = roomChoice.getSelectionModel().getSelectedItem();
		    	if(chatConfirmDialog("코리아텍 ChatTalk", 
						roomName+"에서 나가시겠습니까???", 
		    			"나가기", "취소")){
					ChatServer.getServer().deleteUserFromRoom(user.getUserID(), roomName);
					if(user.getRooms().length>=1) {
						roomChoice.getItems().remove(roomName);
						roomChoice.getSelectionModel().select(0);
						roomName = roomChoice.getSelectionModel().getSelectedItem();
						update(roomName);
					}
					else close();
		    	}
		    }
		});
	}
	
	public void clearRoom() {
		String roomName = roomChoice.getSelectionModel().getSelectedItem();
		if(chatConfirmDialog("코리아텍 ChatTalk", 
				roomName+"의 내용을 모두 지우시겠습니까?", 
    			"지우기", "취소")){
			user.clearRoom(roomName);
			update(roomName);
    	}
	}
	
	// user에서 통보할 때 사용하는 메소드
	public void update(String roomName) {
		roomChoice.getSelectionModel().select(roomName);
		chatArea.setText(prepareOutput(roomName));
	}
	 
	// 여러 메시지를 동시에 출력하기 위한 보조 함수 (매번 모든 메시지를 다시 출력)
	private String prepareOutput(String roomName) {
		List<ChatMessage> chats = user.getRoomLog(roomName);
		return chats.stream().map(c->c.senderID()+": "+c.content()+"\n")
				.collect(Collectors.joining());
	}
	
	public static boolean chatConfirmDialog(String title, String content,
			String okButton, String cancelButton){
		Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.setContentText(content);
    	ButtonType buttonTypeOK = new ButtonType(okButton, ButtonData.OK_DONE);
    	ButtonType buttonTypeCancel = new ButtonType(cancelButton, ButtonData.CANCEL_CLOSE);
    	alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
    	ImageView icon = new ImageView(new Image("koreatech.jpg"));
		icon.setFitHeight(80);
		icon.setPreserveRatio(true);
		alert.setGraphic(icon);
    	Optional<ButtonType> result = alert.showAndWait();
    	return (result.isPresent()&&result.get() == buttonTypeOK);
	}
}
