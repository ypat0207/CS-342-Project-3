import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class ThreeCardPoker extends Application {

	private String ipInput;
	Client clientConnection;
	private ListView listItems;
	private ObservableList<String> listData;
	private BorderPane root;
	private Button connectButton;
	private Button foldButton;
	private	Button playWagerButton, endButton;
	private Button playAgain, Exit;
	private ImageView playerCardImageView1;
	private ImageView playerCardImageView2;
	private ImageView playerCardImageView3;
	private ImageView dealerCardImageView1;
	private ImageView dealerCardImageView2;
	private ImageView dealerCardImageView3;
	private TextField ipAddressField = new TextField();
	private TextField portNumberField = new TextField();
	Double totalPrize = 0.0;

	Color c = Color.GREEN;

	String path = System.getProperty("user.dir") + "/src/main/resources/images/";

	public static void main(String[] args) {
		launch(args);
	}

	public void changeLook() {
	    Random rand = new Random();
	    float red = rand.nextFloat();
	    float green = rand.nextFloat();
	    float blue = rand.nextFloat();
	    int alpha = rand.nextInt(3);
	    Color randomColor = new Color(red, green, blue, 0.5);
	    BackgroundFill backgroundFill = new BackgroundFill(randomColor, null, null);
            Background background = new Background(backgroundFill);
            root.setBackground(background);
	}
	
	public Image getImageFromCard(Card card) {
		String sym = card.getSymbol();
		String shp = card.getShape();
		String dir = path + shp + sym + ".png";
		
		try {
			FileInputStream file = new FileInputStream(dir);
			Image cardImage = new Image(file);
			return cardImage;
		} catch (Exception e) {
			System.out.println("Encountered error " + e);
			return null;
		}
	}
	
	public ImageView getImageViewForCard() {
		String dir = path + "Card-Back.png";
		try {
			FileInputStream file = new FileInputStream(dir);
		
			Image cardImage = new Image(file);
			ImageView cardImageView = new ImageView();
			cardImageView.setFitWidth(75);
			cardImageView.setFitHeight(75);
			cardImageView.setImage(cardImage);
			return cardImageView;

		} catch (Exception e) {
			System.out.println("Encountered error " + e);
			return null;
		}
	}	
	
	public void hideCards() {
		String dir = path + "Card-Back.png";
		try {
			FileInputStream file = new FileInputStream(dir);
			Image backImage = new Image(file);
			playerCardImageView1.setImage(backImage);
			playerCardImageView2.setImage(backImage);
			playerCardImageView3.setImage(backImage);
			dealerCardImageView1.setImage(backImage);
			dealerCardImageView2.setImage(backImage);
			dealerCardImageView3.setImage(backImage);
		} catch (Exception e) {
			System.out.println("Encountered error " + e);
		}
	}
	
	public void showClientCards(ArrayList<Card> clientHand) {	
		playerCardImageView1.setImage(getImageFromCard(clientHand.get(0)));
		playerCardImageView2.setImage(getImageFromCard(clientHand.get(1)));
		playerCardImageView3.setImage(getImageFromCard(clientHand.get(2)));
		foldButton.setDisable(false);
	    	playWagerButton.setDisable(false);

	}

	public void showDealerCards(ArrayList<Card> dealerHand) {
		dealerCardImageView1.setImage(getImageFromCard(dealerHand.get(0)));
		dealerCardImageView2.setImage(getImageFromCard(dealerHand.get(1)));
		dealerCardImageView3.setImage(getImageFromCard(dealerHand.get(2)));
	}
	
	public Scene createIntroScene(Stage primaryStage) {
		Label title = new Label("3 Card Poker");
		title.setStyle("-fx-font-family: Calligraffitti; -fx-font-weight: 500;"
				+ "-fx-font-size: 70px;-fx-text-fill: #FFFFFF;\n"
				+ "-fx-effect: dropshadow(gaussian, #000000		\n"
				+ ", 2, 5, 2, 1);");
		
		Label ipAddressLabel = new Label("IP Address:   ");
		ipAddressLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 15px; -fx-font-weight: 200;");	
		HBox ipAddressBox = new HBox(ipAddressLabel,ipAddressField);
		ipAddressBox.setPadding(new Insets(0, 0, 10, 231));
		Label portNumberLabel = new Label("Port Number:   ");
		portNumberLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 15px; -fx-font-weight: 200;");	
		HBox portNumberBox = new HBox(portNumberLabel,portNumberField);
		portNumberBox.setPadding(new Insets(0, 0, 10, 220));
		Label label = new Label();
		HBox empty = new HBox(label);
		empty.setPadding(new Insets(30, 0, 10, 220));
		HBox empty2 = new HBox(label);
		empty2.setPadding(new Insets(0, 0, 10, 220));
		
		connectButton = new Button("Connect");
		connectButton.setPrefSize(150, 70);
		connectButton.setPadding(new Insets(0, 0, 0, 0));
		HBox connectButtonBox = new HBox(connectButton);
		connectButtonBox.setPadding(new Insets(0, 0, 10, 275));
		
		connectButton.setStyle("-fx-background-color: \n"
					+ "        #ecebe9,\n"
					+ "        rgba(0,0,0,0.05),\n"
					+ "        linear-gradient(#cca30e, #cca30e),\n"
					+ "        linear-gradient(#CD853F 100%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
					+ "        linear-gradient(#e0bb34, #e6c34d);\n"
					+ "    -fx-background-insets: 0,9 9 8 9,9,0,6;\n"
					+ "    -fx-background-radius: 50;\n"
					+ "    -fx-padding: 15 30 15 30;\n"
					+ "    -fx-font-family: \"Helvetica\";\n"
					+ "    -fx-font-size: 18px;\n"
					+ "    -fx-text-fill: #311c09;\n"
					+ "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);");

		connectButton.setOnAction( e -> {
			String getIPAddress = ipAddressField.getText();
			int getPortNumber = Integer.parseInt(portNumberField.getText());
			clientConnection = new Client(getPortNumber, getIPAddress,
					data -> {
						Platform.runLater(() -> {
							listData.add(data.toString());
							if (data.toString().equals("Cards dealt to client")) {
								showClientCards(clientConnection.clientHand);	
							} else if (data.toString().equals("Dealer doesn't have Queen High or higher")) {
								hideCards();
							} else if (data.toString().equals("Player has folded")) {
								listData.add("Player has folded");
								endButton.setDisable(false);
							} else if (data.toString().equals("Player won") || data.toString().equals("Dealer won")) {
								endButton.setDisable(false);
							}

						});
					});
			clientConnection.start();
			primaryStage.setScene(createGameScene(primaryStage));

		});

		VBox firstScene = new VBox(title, empty, ipAddressBox, portNumberBox, empty2, connectButtonBox);
		firstScene.setAlignment(Pos.CENTER);	
		firstScene.setPadding(new Insets(90, 10, 300, 0));
		try {
			FileInputStream backgroundInput = new FileInputStream(path + "background.png");
	    		Image backgroundInputImg = new Image(backgroundInput);
	    		BackgroundImage background = new BackgroundImage(backgroundInputImg,
	        		BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
	        		BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	   		Background backgroundWithImage = new Background(background);
	   		firstScene.setBackground(backgroundWithImage);
		} catch (Exception e) {System.out.println("Encountered error " + e);}

	     	return new Scene(firstScene, 700,700);
	}
	
	public Scene createGameScene(Stage primaryStage){

		listItems = new ListView();
		listData = FXCollections.observableArrayList();
		listItems.setItems(listData);

		playerCardImageView1 = getImageViewForCard();
	    	playerCardImageView2 = getImageViewForCard();
	    	playerCardImageView2.setTranslateX(-20);
	    	playerCardImageView2.setLayoutY(20);
	    	playerCardImageView3 = getImageViewForCard();
	    	playerCardImageView3.setTranslateX(-40);
		
	    	dealerCardImageView1 = getImageViewForCard(); 
	    	dealerCardImageView2 = getImageViewForCard(); 
	    	dealerCardImageView2.setTranslateX(-20);
	    	dealerCardImageView2.setLayoutY(20);
	    	dealerCardImageView3 = getImageViewForCard(); 
	    	dealerCardImageView3.setTranslateX(-40);
	    
	    	Label anteBetLabel = new Label("Place Ante Bet: $");
		anteBetLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 15px; -fx-font-weight: 200;"	
					+ "-fx-effect: dropshadow(gaussian, #000000		\n"
					+ ", 1, 1, 1, 1);");
	    	TextField anteBetField = new TextField();
	    	anteBetField.setMaxWidth(135);
	    	HBox anteButtonAndFieldBox = new HBox(anteBetLabel, anteBetField);
	    	
		Label pairPlusBetLabel = new Label("Place Pair Plus Bet: $");
		pairPlusBetLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 15px; -fx-font-weight: 200;"	
					+ "-fx-effect: dropshadow(gaussian, #000000		\n"
					+ ", 1, 1, 1, 1);");
	    	TextField pairPlusBetField = new TextField();
	    	pairPlusBetField.setMaxWidth(115);
	    	HBox pairBetandFieldBox = new HBox(pairPlusBetLabel, pairPlusBetField);
	    	
		Button placeBetsButton = new Button("Place bets");
		playWagerButton = new Button("Play");
	    	foldButton = new Button("Fold");
		endButton = new Button("End");
	    	
		HBox wagerAndFoldButton = new HBox(10, placeBetsButton, playWagerButton, foldButton, endButton);	    
	    	VBox wagerFieldAndWagerFoldBox = new VBox(wagerAndFoldButton);	   	   
	    	VBox playerOneBox = new VBox(10, anteButtonAndFieldBox,pairBetandFieldBox,wagerFieldAndWagerFoldBox);	   	    
	    	HBox player1Box = new HBox(playerCardImageView1, playerCardImageView2, playerCardImageView3);	
	    	Label pad = new Label();
		HBox emptyPad = new HBox();
		emptyPad.setPadding(new Insets(20, 0, 0, 0));
		VBox player1Vbox = new VBox(10, emptyPad, player1Box, playerOneBox);
		player1Vbox.setTranslateX(250);
	    	player1Vbox.setTranslateY(-100);
	    	foldButton.setDisable(true);
	    	playWagerButton.setDisable(true);
	    	endButton.setDisable(true);
	    
	    	HBox dealerCards = new HBox(dealerCardImageView1, dealerCardImageView2, dealerCardImageView3);
	    	dealerCards.setTranslateX(-500);
	    	dealerCards.setTranslateY(-15);	 
	    	VBox dealerViewandCards = new VBox(dealerCards);	    
	    	dealerViewandCards.setTranslateY(90);
	    	HBox dealerBox = new HBox(dealerViewandCards);	    
	    	dealerBox.setTranslateX(750);
	    	dealerBox.setTranslateY(0);
	    	VBox dealerVBox = new VBox(dealerBox);
	    
	    	MenuBar menuBar = new MenuBar();
	    	Menu fileMenu = new Menu("Options");
        	MenuItem iFreshStart = new MenuItem("Fresh Start");
        	MenuItem iNewLook = new MenuItem("New Look");
        	MenuItem iExit = new MenuItem("Exit");
        	
		iExit.setOnAction(e->{
        		Platform.exit();
			System.exit(0);
        	});
        	
		iNewLook.setOnAction(e-> {
        		changeLook();
        	});
        	
		fileMenu.getItems().addAll(iFreshStart, iNewLook, iExit);
        	menuBar.getMenus().addAll(fileMenu);
		HBox playerOneAndTwo = new HBox(player1Vbox);
		
		placeBetsButton.setOnAction(e->{
	    		if (anteBetField.getText().isEmpty()) {
				listData.add("Please enter an ante bet amount first!");
			} else if (Double.valueOf(anteBetField.getText()) < 5.0) {
				listData.add("Please enter an ante bet above $5!");
			} else if (Double.valueOf(anteBetField.getText()) > 25.0) {
				listData.add("Please enter an ante bet below $25!");
			} else {
				anteBetField.setDisable(true);
				pairPlusBetField.setDisable(true);
	    			placeBetsButton.setDisable(true);

				PokerInfo anteData = new PokerInfo();
				anteData.setAmount(Double.valueOf(anteBetField.getText()));
				anteData.setMessage("Placed ante bet");
				clientConnection.send(anteData);
				clientConnection.prizeMoney -= anteData.getAmount();
				listData.add(anteData.getMessage() + " for $" + anteData.getAmount());
			
				PokerInfo pairPlusData = new PokerInfo();
				if (!pairPlusBetField.getText().isEmpty()) {
					pairPlusData.setAmount(Double.valueOf(pairPlusBetField.getText()));
					pairPlusData.setMessage("Placed pair plus bet");
					clientConnection.prizeMoney -= pairPlusData.getAmount();
					listData.add(pairPlusData.getMessage() + " for $" + pairPlusData.getAmount());
				} else {
					pairPlusData.setMessage("No pair plus bet placed");
					listData.add(pairPlusData.getMessage());
				}
				clientConnection.send(pairPlusData);
			} 
	    	});
	    
		playWagerButton.setOnAction(e->{
			foldButton.setDisable(true);
	    		playWagerButton.setDisable(true);
			PokerInfo wagerData = new PokerInfo();
			wagerData.setMessage("Player made the play wager");
			listData.add("Player made the play wager");
			clientConnection.send(wagerData);
			showDealerCards(clientConnection.dealerHand);
	    	});
	   	
		foldButton.setOnAction(e->{
			foldButton.setDisable(true);
	    		playWagerButton.setDisable(true);
			PokerInfo foldData = new PokerInfo();
			foldData.setMessage("Player has folded");
			clientConnection.send(foldData);
			endButton.setDisable(false);
	    	});
	
		endButton.setOnAction(e->{
			primaryStage.setScene(createEndScene(primaryStage));	
	    	});


	    	root = new BorderPane();
	   	root.setTop(menuBar);
	    	BorderPane bp = new BorderPane();
		root.setCenter(bp);
		bp.setCenter(listItems);
		listItems.setMaxHeight(200);
		listItems.setMaxWidth(300);
		bp.setBottom(playerOneAndTwo);
		bp.setTop(dealerVBox);
	    	
		try {
			FileInputStream backgroundInput = new FileInputStream(path + "background.png");
	    		Image backgroundInputImg = new Image(backgroundInput);
	    		BackgroundImage background = new BackgroundImage(backgroundInputImg,
	        		BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
	        		BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	   		Background backgroundWithImage = new Background(background);
	   		root.setBackground(backgroundWithImage);
		} catch (Exception e) {System.out.println("Encountered error " + e);}
	    	
		return new Scene(root,700,700);
	}
	
	public Scene createEndScene(Stage primaryStage) {
		totalPrize = clientConnection.prizeMoney;
		Label totalWinnings = new Label("Total Winnings: $" + totalPrize);
		totalWinnings.setTranslateX(100);
		totalWinnings.setTranslateY(100);
		totalWinnings.setStyle("-fx-font-family: Calligraffitti; -fx-font-weight: 500;"
					+ "-fx-font-size: 50px;-fx-text-fill: #FFFFFF;\n"
					+ "-fx-effect: dropshadow(gaussian, #000000		\n"
					+ ", 2, 5, 2, 1);");
		playAgain = new Button("Play Again");
		playAgain.setTranslateY(400);
		playAgain.setTranslateX(40);
		    
		playAgain.setPrefSize(200, 75);
		playAgain.setStyle("-fx-background-color: \n"
					+ "        #ecebe9,\n"
					+ "        rgba(0,0,0,0.05),\n"
					+ "        linear-gradient(#cca30e, #cca30e),\n"
					+ "        linear-gradient(#CD853F 100%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
					+ "        linear-gradient(#e0bb34, #e6c34d);\n"
					+ "    -fx-background-insets: 0,9 9 8 9,9,0,6;\n"
					+ "    -fx-background-radius: 50;\n"
					+ "    -fx-padding: 15 30 15 30;\n"
					+ "    -fx-font-family: \"Helvetica\";\n"
					+ "    -fx-font-size: 18px;\n"
					+ "    -fx-text-fill: #311c09;\n"
					+ "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);");
		Exit = new Button("Exit");
		Exit.setTranslateY(325);
		Exit.setTranslateX(450);
		Exit.setStyle("-fx-background-color: \n"
					+ "        #ecebe9,\n"
					+ "        rgba(0,0,0,0.05),\n"
					+ "        linear-gradient(#cca30e, #cca30e),\n"
					+ "        linear-gradient(#CD853F 100%, #f4e5bc 80%, #e6c75d 20%, #cca30e 40%),\n"
					+ "        linear-gradient(#e0bb34, #e6c34d);\n"
					+ "    -fx-background-insets: 0,9 9 8 9,9,0,6;\n"
					+ "    -fx-background-radius: 50;\n"
					+ "    -fx-padding: 15 30 15 30;\n"
					+ "    -fx-font-family: \"Helvetica\";\n"
					+ "    -fx-font-size: 18px;\n"
					+ "    -fx-text-fill: #311c09;\n"
					+ "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.1) , 2, 0.0 , 0 , 1);");
		Exit.setPrefSize(200,75);
		
		playAgain.setOnAction(e-> {
			totalPrize = 0.0;
			primaryStage.setScene(createGameScene(primaryStage));
		});
	    	
		Exit.setOnAction(e-> {
			Platform.exit();
			System.exit(0);
		});

		HBox totalWinningsBox = new HBox(totalWinnings);
		HBox buttonsBox = new HBox(playAgain);
		HBox exitBox = new HBox(Exit);
		VBox scen3Box = new VBox(totalWinningsBox,buttonsBox,exitBox);
		
		try {
			FileInputStream backgroundInput = new FileInputStream(path + "background.png");
	    		Image backgroundInputImg = new Image(backgroundInput);
	    		BackgroundImage background = new BackgroundImage(backgroundInputImg,
	        		BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
	        		BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	   		Background backgroundWithImage = new Background(background);
	   		scen3Box.setBackground(backgroundWithImage);
		} catch (Exception e) {System.out.println("Encountered error " + e);}
  
		return new Scene(scen3Box,700,700);

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	    	primaryStage.setTitle("3-Card Poker");
		
		primaryStage.setScene(createIntroScene(primaryStage));
		primaryStage.show();

	    	primaryStage.setOnCloseRequest( e -> {
			Platform.exit();
			System.exit(0);
		}); 
	}
}
