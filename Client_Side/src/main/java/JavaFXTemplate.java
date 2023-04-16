import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;




public class JavaFXTemplate extends Application {

	
	private String ipInput;
	private int player1NumberCard1;
	private char player1RandomCharCard1;
	private int  player1NumberCard2;
	private char player1RandomCharCard2;
	private int player1NumberCard3;
	private char player1RandomCharCard3;
	private Scene secondScene;
	Client clientConnection;
	private BorderPane borderPane;
	private Button showCards;
	private Button connectButton;
	private TextField ipAddressField = new TextField();
	private TextField portNumberField = new TextField();
	private Label statusLabel;
	private Button showDealerCards;
	private Scene scene2;
	private Scene scene3;

	Player playerOne = new Player();
	Player playerTwo = new Player();
	Player playerThree = new Player();
	Player playerFour = new Player();
	
	Color c = Color.GREEN;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
        borderPane.setBackground(background);
       
       
	}
	


	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("3 Card Poker");
		
		
		
		char[] chars = {'c','d','h','s'};
		Random rand = new Random();
		Random randNum = new Random();
		player1NumberCard1 = randNum.nextInt(52) + 1;
		player1RandomCharCard1 = chars[rand.nextInt(chars.length)];
		player1NumberCard2 = randNum.nextInt(52) + 1;
		player1RandomCharCard2 = chars[rand.nextInt(chars.length)];
		player1NumberCard3 = randNum.nextInt(52) + 1;
		player1RandomCharCard3 = chars[rand.nextInt(chars.length)];
		
		
		FileInputStream cardSceneBGPlayerDealer = new FileInputStream("src/main/resources/images/Card-Back.jpg");
		Image cardSceneImgPlayerDealer = new Image(cardSceneBGPlayerDealer);
	    ImageView cardSceneImgViewPlayerDealer = new ImageView();
		
		
		FileInputStream cardSceneBGPlayer1 = new FileInputStream("src/main/resources/images/"+"" + player1NumberCard1 +".png");
		Image cardSceneImgPlayer1 = new Image(cardSceneBGPlayer1);
	    ImageView cardSceneImgViewPlayer1 = new ImageView();
	    cardSceneImgViewPlayer1.setFitWidth(75);
	    cardSceneImgViewPlayer1.setFitHeight(75);
	    FileInputStream cardSceneBG2Player1 = new FileInputStream("src/main/resources/images/"+"" + player1NumberCard2 +".png");
		Image cardSceneImgPlayer = new Image(cardSceneBG2Player1);
	    ImageView cardSceneImgViewPlayer = new ImageView();
	    cardSceneImgViewPlayer.setTranslateX(-20);
	    cardSceneImgViewPlayer.setLayoutY(20);
	    cardSceneImgViewPlayer.setFitWidth(75);
	    cardSceneImgViewPlayer.setFitHeight(75);	    
	    FileInputStream cardSceneBG2 = new FileInputStream("src/main/resources/images/"+"" + player1NumberCard3 +".png");
		Image cardSceneImg2 = new Image(cardSceneBG2);
	    ImageView cardSceneImgViewPlayer11 = new ImageView();
	    cardSceneImgViewPlayer11.setTranslateX(-40);
	    cardSceneImgViewPlayer11.setFitWidth(75);
	    cardSceneImgViewPlayer11.setFitHeight(75);
	    cardSceneImgViewPlayer1.setImage(cardSceneImgPlayerDealer);
	    cardSceneImgViewPlayer.setImage(cardSceneImgPlayerDealer);
	    cardSceneImgViewPlayer11.setImage(cardSceneImgPlayerDealer);
		
		int player2NumberCard1 = randNum.nextInt(52) + 1;
		char player2RandomCharCard1 = chars[rand.nextInt(chars.length)];
		int player2NumberCard2 = randNum.nextInt(52) + 1;
		char player2RandomCharCard2 = chars[rand.nextInt(chars.length)];
		int player2NumberCard3 = randNum.nextInt(52) + 1;
		char player2RandomCharCard3 = chars[rand.nextInt(chars.length)];
		FileInputStream cardSceneBGPlayer2 = new FileInputStream("src/main/resources/images/"+"" + player2NumberCard1 +".png");
		Image cardSceneImgPlayer2 = new Image(cardSceneBGPlayer2);
	    ImageView cardSceneImgViewPlayer2 = new ImageView();	   
	    cardSceneImgViewPlayer2.setFitWidth(75);
	    cardSceneImgViewPlayer2.setFitHeight(75);
	    FileInputStream cardSceneBG2Player2 = new FileInputStream("src/main/resources/images/"+"" + player2NumberCard2 +".png");
		Image cardSceneImgPlayer22 = new Image(cardSceneBG2Player2);
	    ImageView cardSceneImgViewPlayer22 = new ImageView();
	    cardSceneImgViewPlayer22.setTranslateX(-20);
	    cardSceneImgViewPlayer22.setLayoutY(20);
	    cardSceneImgViewPlayer22.setFitWidth(75);
	    cardSceneImgViewPlayer22.setFitHeight(75);	    
	    FileInputStream cardSceneBG22 = new FileInputStream("src/main/resources/images/"+"" + player2NumberCard3 +".png");
		Image cardSceneImg22 = new Image(cardSceneBG22);
	    ImageView cardSceneImgViewPlayer111 = new ImageView();
	    cardSceneImgViewPlayer111.setTranslateX(-40);
	    cardSceneImgViewPlayer111.setFitWidth(75);
	    cardSceneImgViewPlayer111.setFitHeight(75);
	    cardSceneImgViewPlayer2.setImage(cardSceneImgPlayerDealer);
	    cardSceneImgViewPlayer22.setImage(cardSceneImgPlayerDealer);
	    cardSceneImgViewPlayer111.setImage(cardSceneImgPlayerDealer);
	    
	    
	    int player3NumberCard1 = randNum.nextInt(52) + 1;
		char player3RandomCharCard1 = chars[rand.nextInt(chars.length)];
		int player3NumberCard2 = randNum.nextInt(52) + 1;
		char player3RandomCharCard2 = chars[rand.nextInt(chars.length)];
		int player3NumberCard3 = randNum.nextInt(52) + 1;
		char player3RandomCharCard3 = chars[rand.nextInt(chars.length)];
		FileInputStream cardSceneBGPlayer3 = new FileInputStream("src/main/resources/images/"+"" + player3NumberCard1 +".png");
		Image cardSceneImgPlayer3 = new Image(cardSceneBGPlayer3);
	    ImageView cardSceneImgViewPlayer3 = new ImageView();
	    cardSceneImgViewPlayer3.setFitWidth(75);
	    cardSceneImgViewPlayer3.setFitHeight(75);
	    FileInputStream cardSceneBG2Player3 = new FileInputStream("src/main/resources/images/"+"" + player3NumberCard2 +".png");
		Image cardSceneImgPlayer33 = new Image(cardSceneBG2Player3);
	    ImageView cardSceneImgViewPlayer33 = new ImageView();
	    cardSceneImgViewPlayer33.setTranslateX(-20);
	    cardSceneImgViewPlayer33.setLayoutY(20);
	    cardSceneImgViewPlayer33.setFitWidth(75);
	    cardSceneImgViewPlayer33.setFitHeight(75);
	    
	    FileInputStream cardSceneBG33 = new FileInputStream("src/main/resources/images/"+"" + player3NumberCard3 +".png");
		Image cardSceneImg33 = new Image(cardSceneBG33);
	    ImageView cardSceneImgViewPlayer333 = new ImageView();
	    cardSceneImgViewPlayer333.setTranslateX(-40);
	    cardSceneImgViewPlayer333.setFitWidth(75);
	    cardSceneImgViewPlayer333.setFitHeight(75);
	    cardSceneImgViewPlayer3.setImage(cardSceneImgPlayerDealer);
	    cardSceneImgViewPlayer33.setImage(cardSceneImgPlayerDealer);
	    cardSceneImgViewPlayer333.setImage(cardSceneImgPlayerDealer);
	    
	    
	    int player4NumberCard1 = randNum.nextInt(52) + 1;
		char player4RandomCharCard1 = chars[rand.nextInt(chars.length)];
		int player4NumberCard2 = randNum.nextInt(52) + 1;
		char player4RandomCharCard2 = chars[rand.nextInt(chars.length)];
		int player4NumberCard3 = randNum.nextInt(52) + 1;
		char player4RandomCharCard3 = chars[rand.nextInt(chars.length)];
		FileInputStream cardSceneBGPlayer4 = new FileInputStream("src/main/resources/images/"+"" + player4NumberCard1 +".png");
		Image cardSceneImgPlayer4 = new Image(cardSceneBGPlayer4);
	    ImageView cardSceneImgViewPlayer4 = new ImageView();	   

	    cardSceneImgViewPlayer4.setFitWidth(75);
	    cardSceneImgViewPlayer4.setFitHeight(75);
	    FileInputStream cardSceneBG2Player4 = new FileInputStream("src/main/resources/images/"+"" + player4NumberCard2 +".png");
		Image cardSceneImgPlayer44 = new Image(cardSceneBG2Player4);
	    ImageView cardSceneImgViewPlayer44 = new ImageView();	    
	    cardSceneImgViewPlayer44.setTranslateX(-20);
	    cardSceneImgViewPlayer44.setLayoutY(20);
	    cardSceneImgViewPlayer44.setFitWidth(75);
	    cardSceneImgViewPlayer44.setFitHeight(75);
	    
	    FileInputStream cardSceneBG44 = new FileInputStream("src/main/resources/images/"+"" + player4NumberCard3 +".png");
		Image cardSceneImg44 = new Image(cardSceneBG44);
	    ImageView cardSceneImgViewPlayer444 = new ImageView();	    
	    cardSceneImgViewPlayer444.setTranslateX(-40);
	    cardSceneImgViewPlayer444.setFitWidth(75);
	    cardSceneImgViewPlayer444.setFitHeight(75);
	    cardSceneImgViewPlayer4.setImage(cardSceneImgPlayerDealer);
	    cardSceneImgViewPlayer44.setImage(cardSceneImgPlayerDealer);
	    cardSceneImgViewPlayer444.setImage(cardSceneImgPlayerDealer);
	    
	    
	    int dealerCard1 = randNum.nextInt(52) + 1;
		int dealerCard2 = randNum.nextInt(52) + 1;
		int dealerCard3 = randNum.nextInt(52) + 1;
		FileInputStream dealerCardInput = new FileInputStream("src/main/resources/images/"+"" + dealerCard1 +".png");
		Image dealerCardImg = new Image(dealerCardInput);	   
	    cardSceneImgViewPlayer2.setFitWidth(75);
	    cardSceneImgViewPlayer2.setFitHeight(75);
	    FileInputStream dealerCardInput2 = new FileInputStream("src/main/resources/images/"+"" + dealerCard2 +".png");
		Image dealerCardImg2 = new Image(dealerCardInput2);
	    cardSceneImgViewPlayer22.setTranslateX(-20);
	    cardSceneImgViewPlayer22.setLayoutY(20);
	    cardSceneImgViewPlayer22.setFitWidth(75);
	    cardSceneImgViewPlayer22.setFitHeight(75);	    
	    FileInputStream dealerCardInput3 = new FileInputStream("src/main/resources/images/"+"" + dealerCard3 +".png");
		Image dealerCardImg3 = new Image(dealerCardInput3);
	    
	    
	    
	    FileInputStream dealer = new FileInputStream("src/main/resources/images/dealer1-removebg-preview.png");
		Image dealerImg = new Image(dealer);
	    ImageView dealerView = new ImageView();
	 
	    dealerView.setTranslateX(-290);
	    dealerView.setTranslateY(-50);
	    dealerView.setFitWidth(100);
	    dealerView.setFitHeight(100);
	    dealerView.setImage(dealerImg);
	    
	    
	    cardSceneImgViewPlayerDealer.setImage(cardSceneImgPlayerDealer);
	    cardSceneImgViewPlayerDealer.setFitWidth(75);
	    cardSceneImgViewPlayerDealer.setFitHeight(75);
	    FileInputStream cardSceneBG2PlayerDealer = new FileInputStream("src/main/resources/images/Card-Back.jpg");
		Image cardSceneImgPlayerDealerr = new Image(cardSceneBG2PlayerDealer);
	    ImageView cardSceneImgViewPlayerDealerr = new ImageView();
	  
	    cardSceneImgViewPlayerDealerr.setTranslateX(-20);
	    cardSceneImgViewPlayerDealerr.setLayoutY(20);
	    cardSceneImgViewPlayerDealerr.setFitWidth(75);
	    cardSceneImgViewPlayerDealerr.setFitHeight(75);
	    cardSceneImgViewPlayerDealerr.setImage(cardSceneImgPlayerDealerr);
	    FileInputStream cardSceneBGDealer = new FileInputStream("src/main/resources/images/Card-Back.jpg");
		Image cardSceneImgDealer = new Image(cardSceneBGDealer);
	    ImageView cardSceneImgViewPlayerDealerrr = new ImageView();
	 
	    cardSceneImgViewPlayerDealerrr.setTranslateX(-40);
	    cardSceneImgViewPlayerDealerrr.setFitWidth(75);
	    cardSceneImgViewPlayerDealerrr.setFitHeight(75);
	    cardSceneImgViewPlayerDealer.setImage(cardSceneImgPlayerDealer);
	    cardSceneImgViewPlayerDealerr.setImage(cardSceneImgPlayerDealerr);
	    cardSceneImgViewPlayerDealerrr.setImage(cardSceneImgDealer);
	    
	    
	    
		
		
	
		
	
		Label title = new Label("3 Card Poker");
		title.setStyle("-fx-font-family: Calligraffitti; -fx-font-weight: 500;"
				+ "-fx-font-size: 70px;-fx-text-fill: #CD853F;\n"
				+ "-fx-effect: dropshadow(gaussian, #FFD700		\n"
				+ ", 2, 5, 2, 1);");
		
		Label ipAddressLabel = new Label("IP Address:   ");
		
		HBox ipAddressBox = new HBox(ipAddressLabel,ipAddressField);
		ipAddressBox.setPadding(new Insets(0, 0, 10, 231));
		Label portNumberLabel = new Label("Port Number:   ");
		
		HBox portNumberBox = new HBox(portNumberLabel,portNumberField);
		portNumberBox.setPadding(new Insets(0, 0, 10, 220));
		Label label = new Label();
		HBox empty = new HBox(label);
		empty.setPadding(new Insets(30, 0, 10, 220));
		HBox empty2 = new HBox(label);
		empty2.setPadding(new Insets(0, 0, 10, 220));

//		String getIPAddress = ipAddressField.getText();
//		int getPortNumber = Integer.parseInt(portNumberField.getText());
		
		Button connectButton = new Button("Connect");
		connectButton.setPrefSize(75, 40);
		connectButton.setPadding(new Insets(0, 0, 0, 0));
		HBox connectButtonBox = new HBox(connectButton);
		connectButtonBox.setPadding(new Insets(0, 0, 10, 320));

		VBox firstScene = new VBox(title,empty,ipAddressBox,portNumberBox,empty2,connectButtonBox);
		firstScene.setAlignment(Pos.CENTER);	
		firstScene.setPadding(new Insets(90, 10, 300, 0));
	     Scene scene = new Scene(firstScene, 700,700);
	     
	     Button continueButton = new Button("Continue");
	     continueButton.setTranslateX(450);
	     continueButton.setTranslateY(130);
	     
	     connectButton.setOnAction(e-> {
	    	 primaryStage.setScene(scene2);
	     });
	     
	     continueButton.setOnAction(e-> {
	    	 primaryStage.setScene(scene3);
	     });
	    
	 	     

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	     
	    Button playerOneAnteBet = new Button("Player 1 Ante");
	    
	    TextField playerOneAnteField = new TextField(String.valueOf(playerOne.getAnteBet()));
	    playerOneAnteField.setMaxWidth(135);
	    HBox anteButtonAndFieldBox = new HBox(playerOneAnteBet,playerOneAnteField);
	    Button playerOnePairBet = new Button("Player 1 Pair Plus");
	    
	    TextField playerOnePairField = new TextField(String.valueOf(playerOne.getPairPlus()));
	    playerOnePairField.setMaxWidth(115);
	    HBox pairBetandFieldBox = new HBox(playerOnePairBet,playerOnePairField);
	    TextField wagerField = new TextField(String.valueOf(playerOne.getWager()));
	    wagerField.setPrefWidth(50);
	    wagerField.setMaxWidth(224);
	    Button playWagerPlayerOne = new Button("Player 1 play wager");
	    
	    Button playerOneFold = new Button("Player 1 Fold");
	    playerOneAnteBet.setOnAction(e->{
	    	playerOneAnteField.setDisable(true);
	    	playerOneAnteBet.setDisable(true);
	    });
	    playerOnePairBet.setOnAction(e->{
		   
	    	playerOnePairBet.setDisable(true);
	    	showCards.setDisable(false);
	    	playerOnePairField.setDisable(true);
	    });
	    playWagerPlayerOne.setOnAction(e->{
	    	playWagerPlayerOne.setDisable(true);
	    	showDealerCards.setDisable(false);
	    });
	    playerOneFold.setOnAction(e->{
	    	wagerField.setDisable(true);
	    	playWagerPlayerOne.setDisable(true);
	    	playerOneFold.setDisable(true);
	    	playerOneAnteField.setDisable(true);
	    	playerOnePairField.setDisable(true);
	    	playerOneAnteBet.setDisable(true);
	    	showDealerCards.setDisable(false);
	    	playerOnePairBet.setDisable(true);
	    });
	    HBox wagerAndFoldButton = new HBox(playWagerPlayerOne,playerOneFold);	    
	    VBox wagerFieldAndWagerFoldBox = new VBox(wagerField,wagerAndFoldButton);	   	   
	    VBox playerOneBox = new VBox(anteButtonAndFieldBox,pairBetandFieldBox,wagerFieldAndWagerFoldBox);	   	    
	    HBox player1Box = new HBox(cardSceneImgViewPlayer1,cardSceneImgViewPlayer,cardSceneImgViewPlayer11);	
	    VBox player1Vbox = new VBox(player1Box,playerOneBox);
	    player1Vbox.setTranslateX(275);
	    player1Vbox.setTranslateY(-100);
	    wagerField.setDisable(true);
	    playerOneFold.setDisable(true);
	    playWagerPlayerOne.setDisable(true);
	    
	 
	 	    
	    Button playerTwoAnteBet = new Button("Player 2 Ante");
	    TextField playerTwoAnteField = new TextField(String.valueOf(playerTwo.getAnteBet()));
	    HBox anteButton2AndFieldBox = new HBox(playerTwoAnteBet,playerTwoAnteField);	  
	    Button playerTwoPairBet = new Button("Player 2 Pair Plus");
	    TextField playerTwoPairField = new TextField(String.valueOf(playerTwo.getPairPlus()));
	    HBox pairBet2andFieldBox = new HBox(playerTwoPairBet,playerTwoPairField); 
	    TextField wagerField2 = new TextField(String.valueOf(playerTwo.getWager()));
	    playerTwoAnteField.setMaxWidth(120);
	    playerTwoPairField.setMaxWidth(100);
	    wagerField2.setMaxWidth(210);
	    Button playWagerPlayerTwo = new Button("Player 2 play wager");
	    Button playerTwoFold = new Button("Player 2 Fold");
	    HBox wagerAndFoldButton2 = new HBox(playWagerPlayerTwo,playerTwoFold);	  
	    VBox wagerFieldAndWagerFoldBox2 = new VBox(wagerField2,wagerAndFoldButton2);	   
	    VBox playerTwoBox = new VBox(anteButton2AndFieldBox,pairBet2andFieldBox,wagerFieldAndWagerFoldBox2);	    	  
	    HBox player2Box = new HBox(cardSceneImgViewPlayer2,cardSceneImgViewPlayer22,cardSceneImgViewPlayer111);	    	 
	    VBox player2Vbox = new VBox(player2Box,playerTwoBox);
	    player2Vbox.setTranslateX(400);
	    player2Vbox.setTranslateY(-100);
	    wagerField2.setDisable(true);
	    playerTwoFold.setDisable(true);
	    playWagerPlayerTwo.setDisable(true);
	    playerTwoAnteBet.setOnAction(e->{
	    	playerTwoAnteField.setDisable(true);
	    	playerTwoAnteBet.setDisable(true);
	    });
	    playerTwoPairBet.setOnAction(e->{
	    	
	    	playerTwoPairBet.setDisable(true);
	    	playerTwoPairField.setDisable(true);
	    	showCards.setDisable(false);
	    });
	    playWagerPlayerTwo.setOnAction(e->{
	    	playWagerPlayerTwo.setDisable(true);
	    	showDealerCards.setDisable(false);
	    });
	    playerTwoFold.setOnAction(e->{
	    	wagerField2.setDisable(true);
	    	playWagerPlayerTwo.setDisable(true);
	    	playerTwoFold.setDisable(true);
	    	showDealerCards.setDisable(false);
	    	playerTwoAnteBet.setDisable(true);
	    	playerTwoAnteField.setDisable(true);
	    	playerTwoPairBet.setDisable(true);
	    	playerTwoPairField.setDisable(true);
	    	
	    	
	    });
	    
	    
	    
	    Button playerThreeAnteBet = new Button("Player 3 Ante");
	    TextField playerThreeAnteField = new TextField(String.valueOf(playerThree.getAnteBet()));
	    playerThreeAnteField.setMaxWidth(120);
	    HBox anteButton3AndFieldBox = new HBox(playerThreeAnteBet,playerThreeAnteField);
	    Button playerThreePairBet = new Button("Player 3 Pair Plus");
	    TextField playerThreePairField = new TextField(String.valueOf(playerThree.getPairPlus()));
	    playerThreePairField.setMaxWidth(100);
	    HBox pairBet3andFieldBox = new HBox(playerThreePairBet,playerThreePairField);
	    TextField wagerField3 = new TextField(String.valueOf(playerThree.getWager()));
	    wagerField3.setPrefWidth(50);
	    wagerField3.setMaxWidth(210);
	    Button playWagerPlayerThree = new Button("Player 3 play wager");
	    Button playerThreeFold = new Button("Player 3 Fold");
	    HBox wagerAndFoldButton3 = new HBox(playWagerPlayerThree,playerThreeFold);	    
	    VBox wagerFieldAndWagerFoldBox3 = new VBox(wagerField3,wagerAndFoldButton3);	    
	    VBox playerThreeBox = new VBox(anteButton3AndFieldBox,pairBet3andFieldBox,wagerFieldAndWagerFoldBox3);   
	    HBox player3Box = new HBox(cardSceneImgViewPlayer3,cardSceneImgViewPlayer33,cardSceneImgViewPlayer333);	    
	    VBox player3Vbox = new VBox(player3Box,playerThreeBox);
	    player3Vbox.setTranslateY(125);
	    player3Vbox.setTranslateX(50);
	    wagerField3.setDisable(true);
	    playerThreeFold.setDisable(true);
	    playWagerPlayerThree.setDisable(true);
	    playerThreeAnteBet.setOnAction(e->{
	    	playerThreeAnteField.setDisable(true);
	    	playerThreeAnteBet.setDisable(true);
	    });
	    playerThreePairBet.setOnAction(e->{
	
	    	playerThreePairBet.setDisable(true);
	    	playerThreePairField.setDisable(true);
	    	showCards.setDisable(false);
	    });
	    playWagerPlayerThree.setOnAction(e->{
	    	playWagerPlayerThree.setDisable(true);
	    	showDealerCards.setDisable(false);
	    });
	    playerThreeFold.setOnAction(e->{
	    	wagerField3.setDisable(true);
	    	playWagerPlayerThree.setDisable(true);
	    	playerThreeFold.setDisable(true);
	    	showDealerCards.setDisable(false);
	    	playerThreeAnteBet.setDisable(true);
	    	playerThreeAnteField.setDisable(true);
	    	playerThreePairBet.setDisable(true);
	    	playerThreePairField.setDisable(true);
	    });
	   
	    
	  
	    
	    
	    
	    Button playerFourAnteBet = new Button("Player 4 Ante");
	    TextField playerFourAnteField = new TextField(String.valueOf(playerFour.getAnteBet()));
	    playerFourAnteField.setMaxWidth(120);
	    HBox anteButton4AndFieldBox = new HBox(playerFourAnteBet,playerFourAnteField);
	    Button playerFourPairBet = new Button("Player 4 Pair Plus");
	    TextField playerFourPairField = new TextField(String.valueOf(playerFour.getPairPlus()));
	    playerFourPairField.setMaxWidth(100);
	    HBox pairBet4andFieldBox = new HBox(playerFourPairBet,playerFourPairField);
	    TextField wagerField4 = new TextField(String.valueOf(playerFour.getWager()));
	    wagerField4.setPrefWidth(50);
	    wagerField4.setMaxWidth(210);
	    Button playWagerPlayerFour = new Button("Player 4 play wager");
	    Button playerFourFold = new Button("Player 4 Fold");
	    HBox wagerAndFoldButton4 = new HBox(playWagerPlayerFour,playerFourFold);	    
	    VBox wagerFieldAndWagerFoldBox4 = new VBox(wagerField4,wagerAndFoldButton4);	    
	    VBox playerFourBox = new VBox(anteButton4AndFieldBox,pairBet4andFieldBox,wagerFieldAndWagerFoldBox4);
	    HBox player4Box = new HBox(cardSceneImgViewPlayer4,cardSceneImgViewPlayer44,cardSceneImgViewPlayer444);
	    VBox player4Vbox = new VBox(player4Box,playerFourBox);
	    player4Vbox.setTranslateY(125);
	    player4Vbox.setTranslateX(-50);
	    wagerField4.setDisable(true);
	    playerFourFold.setDisable(true);
	    playWagerPlayerFour.setDisable(true);
	    playerFourAnteBet.setOnAction(e->{
	    	playerFourAnteField.setDisable(true);
	    	playerFourAnteBet.setDisable(true);
	    });
	    playerFourPairBet.setOnAction(e->{
	    	playerFourPairBet.setDisable(true);
		    playerFourPairField.setDisable(true);
		    showCards.setDisable(false);
	    });
	    playWagerPlayerFour.setOnAction(e->{
	    	playWagerPlayerFour.setDisable(true);
	    	showDealerCards.setDisable(false);
	    });
	    playerFourFold.setOnAction(e->{
	    	wagerField4.setDisable(true);
	    	playWagerPlayerFour.setDisable(true);
	    	playerFourFold.setDisable(true);
	    	showDealerCards.setDisable(false);
	    	playerFourAnteBet.setDisable(true);
	    	playerFourAnteField.setDisable(true);
	    	playerFourPairBet.setDisable(true);
	    	playerFourPairField.setDisable(true);
	    	
	    });
	  
	    
	    HBox dealerCards = new HBox(cardSceneImgViewPlayerDealer,cardSceneImgViewPlayerDealerr,cardSceneImgViewPlayerDealerrr);
	    dealerCards.setTranslateX(-330);
	    dealerCards.setTranslateY(-15);	 
	    VBox dealerViewandCards = new VBox(dealerView,dealerCards);	    
	    dealerViewandCards.setTranslateY(90);
	    HBox dealerBox = new HBox(dealerViewandCards);	    
	    dealerBox.setPrefSize(100, 80);
	    dealerBox.setMaxSize(100, 80);
	    dealerBox.setTranslateX(750);
	    dealerBox.setTranslateY(0);
	    VBox dealerVBox = new VBox(dealerBox);
	    
	    showCards = new Button("Show Cards");
	    showCards.setDisable(true);
	    showCards.setTranslateX(150);
	    showCards.setTranslateY(200);
	    
	    showDealerCards = new Button("Show Dealer Cards");
	    showDealerCards.setTranslateX(300);
	    showDealerCards.setTranslateY(173);
	    showDealerCards.setDisable(true);
	    
	    
	    showDealerCards.setOnAction(e-> {
	    	cardSceneImgViewPlayerDealer.setImage(dealerCardImg);
	    	cardSceneImgViewPlayerDealerr.setImage(dealerCardImg2);
	    	cardSceneImgViewPlayerDealerrr.setImage(dealerCardImg3);
	    });
	  
	    MenuBar menuBar = new MenuBar();
	    Menu fileMenu = new Menu("Options");
        MenuItem iFreshStart = new MenuItem("Fresh Start");
        MenuItem iNewLook = new MenuItem("New Look");
        MenuItem iExit = new MenuItem("Exit");
        iExit.setOnAction(e->{
        	Platform.exit();
        });
        iNewLook.setOnAction(e-> {
        	changeLook();
        });
        fileMenu.getItems().addAll(iFreshStart, iNewLook, iExit);
	    
	   
	   
    
        menuBar.getMenus().addAll(fileMenu);
	    borderPane = new BorderPane();
	   borderPane.setTop(menuBar);
	    
	    
		VBox secondScene = new VBox(dealerVBox,showCards,showDealerCards);
		HBox playerOneAndTwo = new HBox(player1Vbox,player2Vbox,continueButton);
	
		borderPane.setCenter(secondScene);
		borderPane.setLeft(player3Vbox);
		borderPane.setBottom(playerOneAndTwo);
		borderPane.setTop(dealerVBox);
		borderPane.setRight(player4Vbox);
	    scene2 = new Scene(borderPane,1100,900);
	    FileInputStream backgroundInput = new FileInputStream("src/main/resources/images/background.jpeg");
	    Image backgroundInputImg = new Image(backgroundInput);
	    BackgroundImage background = new BackgroundImage(backgroundInputImg,
	            BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
	            BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	   Background backgroundWithImage = new Background(background);
	   borderPane.setBackground(backgroundWithImage);

	   showCards.setOnAction(e-> {
		   showCards.setDisable(true);
		   	cardSceneImgViewPlayer1.setImage(cardSceneImgPlayer1);
		    cardSceneImgViewPlayer.setImage(cardSceneImgPlayer);
		    cardSceneImgViewPlayer11.setImage(cardSceneImg2);
		    		    
		    cardSceneImgViewPlayer2.setImage(cardSceneImgPlayer2);
		    cardSceneImgViewPlayer22.setImage(cardSceneImgPlayer22);
		    cardSceneImgViewPlayer111.setImage(cardSceneImg22);
		 
		    cardSceneImgViewPlayer3.setImage(cardSceneImgPlayer3);
		    cardSceneImgViewPlayer33.setImage(cardSceneImgPlayer33);
		    cardSceneImgViewPlayer333.setImage(cardSceneImg33);
		    
		    cardSceneImgViewPlayer4.setImage(cardSceneImgPlayer4);
		    cardSceneImgViewPlayer44.setImage(cardSceneImgPlayer44);
		    cardSceneImgViewPlayer444.setImage(cardSceneImg44);
		    
		    playerOneFold.setDisable(false);
		    playerTwoFold.setDisable(false);
		    playerThreeFold.setDisable(false);
		    playerFourFold.setDisable(false);
		    
		
		    wagerField.setDisable(false);
		    playWagerPlayerOne.setDisable(false);
		    wagerField2.setDisable(false);
		    playWagerPlayerTwo.setDisable(false);
		    wagerField3.setDisable(false);
		    playWagerPlayerThree.setDisable(false);
		    wagerField4.setDisable(false);
		    playWagerPlayerFour.setDisable(false);
		    
		   
	   });
	     
	
		
	   
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		   BorderPane scene3Pane = new BorderPane();
	   		Label totalWinnings = new Label("Total Winnings: ");

		    totalWinnings.setStyle("-fx-font-family: Calligraffitti; -fx-font-weight: 500;"
					+ "-fx-font-size: 70px;-fx-text-fill: #CD853F;\n"
					+ "-fx-effect: dropshadow(gaussian, #FFD700		\n"
					+ ", 2, 5, 2, 1);");
		    totalWinnings.setTranslateX(-150);
		    Button playAgain = new Button("Play Again");
		
		    playAgain.setOnAction(e-> {
		    	wagerField.setDisable(false);
		    	playWagerPlayerOne.setDisable(false);
		    	playerOneFold.setDisable(false);
		    	playerOneAnteField.setDisable(false);
		    	playerOnePairField.setDisable(false);
		    	playerOneAnteBet.setDisable(false);
		    	showDealerCards.setDisable(false);
		    	playerOnePairBet.setDisable(false);
		    	wagerField2.setDisable(false);
		    	playWagerPlayerTwo.setDisable(false);
		    	playerTwoFold.setDisable(false);
		    	showDealerCards.setDisable(false);
		    	playerTwoAnteBet.setDisable(false);
		    	playerTwoAnteField.setDisable(false);
		    	playerTwoPairBet.setDisable(false);
		    	playerTwoPairField.setDisable(false);
		    	wagerField3.setDisable(false);
		    	playWagerPlayerThree.setDisable(false);
		    	playerThreeFold.setDisable(false);
		    	showDealerCards.setDisable(false);
		    	playerThreeAnteBet.setDisable(false);
		    	playerThreeAnteField.setDisable(false);
		    	playerThreePairBet.setDisable(false);
		    	playerThreePairField.setDisable(false);
		    	wagerField4.setDisable(false);
		    	playWagerPlayerFour.setDisable(false);
		    	playerFourFold.setDisable(false);
		    	showDealerCards.setDisable(false);
		    	playerFourAnteBet.setDisable(false);
		    	playerFourAnteField.setDisable(false);
		    	playerFourPairBet.setDisable(false);
		    	playerFourPairField.setDisable(false);
		    	cardSceneImgViewPlayer1.setImage(cardSceneImgPlayerDealer);
			    cardSceneImgViewPlayer.setImage(cardSceneImgPlayerDealer);
			    cardSceneImgViewPlayer11.setImage(cardSceneImgPlayerDealer);
			    cardSceneImgViewPlayer2.setImage(cardSceneImgPlayerDealer);
			    cardSceneImgViewPlayer22.setImage(cardSceneImgPlayerDealer);
			    cardSceneImgViewPlayer111.setImage(cardSceneImgPlayerDealer);
			    cardSceneImgViewPlayer3.setImage(cardSceneImgPlayerDealer);
			    cardSceneImgViewPlayer33.setImage(cardSceneImgPlayerDealer);
			    cardSceneImgViewPlayer333.setImage(cardSceneImgPlayerDealer);
			    cardSceneImgViewPlayer4.setImage(cardSceneImgPlayerDealer);
			    cardSceneImgViewPlayer44.setImage(cardSceneImgPlayerDealer);
			    cardSceneImgViewPlayer444.setImage(cardSceneImgPlayerDealer);
			    cardSceneImgViewPlayerDealer.setImage(cardSceneImgPlayerDealer);
			    cardSceneImgViewPlayerDealerr.setImage(cardSceneImgPlayerDealerr);
			    cardSceneImgViewPlayerDealerrr.setImage(cardSceneImgDealer);
		    	
		    	primaryStage.setScene(scene);
		    });
		    playAgain.setPrefSize(300, 75);
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
		    Button Exit = new Button("Exit");
	
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
		    Exit.setPrefSize(300,75);
		    Exit.setTranslateX(400);
		    Exit.setOnAction(e-> {
		    	Platform.exit();
		    });
		    
//		    HBox totalWinningsBox = new HBox(totalWinnings);
//		    HBox buttonsBox = new HBox(playAgain);
//		    HBox exitBox = new HBox(Exit);
//		    VBox scen3Box = new VBox(totalWinningsBox,buttonsBox,exitBox);
		    HBox playAgainAndExit = new HBox(playAgain,Exit);
		    scene3Pane.setBottom(playAgainAndExit);
		    scene3Pane.setCenter(totalWinnings);
		    scene3 = new Scene(scene3Pane,1000,1000);

	    
	     
			primaryStage.setScene(scene);
			primaryStage.show();
		
	
	}

}
