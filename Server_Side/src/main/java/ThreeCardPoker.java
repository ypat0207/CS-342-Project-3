import java.util.HashMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class ThreeCardPoker extends Application {
	
	private Button serverOnButton, serverOffButton;
	private TextField portBox;
	private ListView<String> serverLog1, serverLog2, serverLog3, serverLog4;
	private ObservableList<String> serverData1, serverData2, serverData3, serverData4;
	private HashMap<String, Scene> sceneMap;
	private Server server;

	public static void main(String[] args) {
		launch(args);
	}
	
	private Scene createIntroScene() {
		Label title = new Label("3 Card Poker");
		title.setStyle("-fx-font-weight: 500; -fx-font-size: 50px;");
		
		Label portNumberLabel = new Label("Port Number:   ");
		HBox portNumberBox = new HBox(portNumberLabel,portBox);
		portNumberBox.setPadding(new Insets(0, 0, 10, 220));
		Label label = new Label();
		HBox empty = new HBox(label);
		empty.setPadding(new Insets(30, 0, 10, 220));
		
		serverOnButton.setPrefSize(150, 30);
		serverOnButton.setPadding(new Insets(0, 0, 0, 0));
		HBox serverOnButtonBox = new HBox(serverOnButton);
		serverOnButtonBox.setPadding(new Insets(0, 0, 10, 275));

		VBox vb = new VBox(10, title, empty, portNumberBox, serverOnButtonBox);
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(90, 10, 300, 0));
		BorderPane root = new BorderPane();
		root.setCenter(vb);
	     	return new Scene(root, 700, 700);
	}
	
	private Scene createServerScene() {
		serverLog1.setPrefWidth(400);
		serverLog2.setPrefWidth(400);
		serverLog3.setPrefWidth(400);
		serverLog4.setPrefWidth(400);
		HBox hb1 = new HBox(10, serverLog1, serverLog2);
		HBox hb2 = new HBox(10, serverLog3, serverLog4);
		VBox vb = new VBox(10,serverOffButton, hb1, hb2);
		vb.setAlignment(Pos.CENTER);
		vb.setPadding(new Insets(10, 10, 10, 10));
		BorderPane root = new BorderPane();
		root.setCenter(vb);
	     	return new Scene(root, 700, 700);
	}
	
	private ObservableList<String> getListDataFromClientID(String id) {
		if (id.equals("1")) {return serverData1;}
		else if (id.equals("2")) {return serverData2;}
		else if (id.equals("3")) {return serverData3;}
		else if (id.equals("4")) {return serverData4;}
		else {return null;}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("3-card Poker");
		serverOnButton = new Button("Turn on server");
		serverOffButton = new Button("Turn off server");
		portBox = new TextField();
		portBox.setPromptText("Enter port number");
		serverLog1 = new ListView();
		serverData1 = FXCollections.observableArrayList();
		serverLog1.setItems(serverData1);
		serverLog2 = new ListView();
		serverData2 = FXCollections.observableArrayList();
		serverLog2.setItems(serverData2);
		serverLog3 = new ListView();
		serverData3 = FXCollections.observableArrayList();
		serverLog3.setItems(serverData3);
		serverLog4 = new ListView();
		serverData4 = FXCollections.observableArrayList();
		serverLog4.setItems(serverData4);
		
		serverOnButton.setOnAction(e -> {
			serverData1.clear();
			serverData2.clear();
			serverData3.clear();
			serverData4.clear();

			serverData1.add("Server is waiting for client #1 to join.");
			serverData2.add("Server is waiting for client #2 to join.");
			serverData3.add("Server is waiting for client #3 to join.");
			serverData4.add("Server is waiting for client #4 to join.");
			
			server = new Server(Integer.valueOf(portBox.getText()),
					data -> {
				Platform.runLater(() -> {
					if (data.get(1).equals("0")) {
						serverData1.add(data.get(0));
						serverData2.add(data.get(0));
						serverData3.add(data.get(0));
						serverData4.add(data.get(0));
					} else {
						ObservableList<String> listData = getListDataFromClientID(data.get(1));
						if (listData != null) {listData.add(data.get(0));}
					}
				});
			});
			primaryStage.setScene(sceneMap.get("server"));
		});

		serverOffButton.setOnAction(e -> {
			server.close();
			primaryStage.setScene(sceneMap.get("intro"));
		});
		
		sceneMap = new HashMap();
		sceneMap.put("intro", createIntroScene());
		sceneMap.put("server", createServerScene());

		primaryStage.setScene(sceneMap.get("intro"));
		primaryStage.show();

		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
			System.exit(0);
		});
	}
}
