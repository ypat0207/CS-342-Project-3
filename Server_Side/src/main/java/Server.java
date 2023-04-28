import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Server{

	int count = 1;
	Integer port;
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	private Consumer<ArrayList<String>> callback;
	TheServer server;
	
	public Server(Integer prt, Consumer <ArrayList<String>> call) {
		callback = call;
		port = prt;
		server = new TheServer();
		server.start();
	}
	
	public void close() {
		try {
			server.mysocket.close();
			System.out.println("Closing server!");
		}
		catch (Exception e) {System.out.println("Encountered error " + e);}
	}
	
	public ArrayList<String> createData(String message, int id) {
		ArrayList<String> data = new ArrayList();
		data.add(message);
		data.add(Integer.toString(id));
		return data;
	}

	public class TheServer extends Thread {
		ServerSocket mysocket;

		public void run() {
			try {
		    		mysocket = new ServerSocket(port);
			
		    		while (true) {
					ClientThread c = new ClientThread(mysocket.accept(), count);
					callback.accept(createData("Client has connected to the server!", count));
					clients.add(c);
					c.start();
					count++;
				}
			} catch(Exception e) {
				e.printStackTrace();
				callback.accept(createData("Encountered error " + e, 0));
			}
		}
	}
	
	public class ClientThread extends Thread {
		Socket connection;
		int id;
		ObjectInputStream in;
		ObjectOutputStream out;
		Logic game;

		ClientThread(Socket s, int count){
			this.connection = s;
			this.id = count;
			game = new Logic();
		}
			
		public void run(){
					
			try {
				in = new ObjectInputStream(connection.getInputStream());
				out = new ObjectOutputStream(connection.getOutputStream());
				connection.setTcpNoDelay(true);	
			} catch(Exception e) {System.out.println("Streams not open");}
			
			while (true) {
				try {
					PokerInfo data = (PokerInfo) in.readObject();
					String messageStr = data.getMessage();
					if (messageStr.equals("Placed ante bet")) {
						game.setAnteBet(data.getAmount());
						messageStr = data.getMessage() + " for $" + data.getAmount();
					} else if (messageStr.equals("Placed pair plus bet")) {
						game.setPairPlusBet(data.getAmount());
						messageStr = data.getMessage() + " for $" + data.getAmount();

						PokerInfo clientHandData = new PokerInfo();
						clientHandData.setHand(game.getClientHand());
						clientHandData.setMessage("Cards dealt to client");
						this.send(clientHandData);
						callback.accept(createData("Cards dealt to client", id));
						
						PokerInfo dealerHandData = new PokerInfo();
						dealerHandData.setHand(game.getDealerHand());
						dealerHandData.setMessage("Cards dealt to dealer");
						this.send(dealerHandData);
						callback.accept(createData("Cards dealt to dealer", id));

					} else if (messageStr.equals("No pair plus bet placed")) {
						PokerInfo clientHandData = new PokerInfo();
						clientHandData.setHand(game.getClientHand());
						clientHandData.setMessage("Cards dealt to client");
						this.send(clientHandData);
						callback.accept(createData("Cards dealt to client", id));
						
						PokerInfo dealerHandData = new PokerInfo();
						dealerHandData.setHand(game.getDealerHand());
						dealerHandData.setMessage("Cards dealt to dealer");
						this.send(dealerHandData);
						callback.accept(createData("Cards dealt to dealer", id));
					} else if (messageStr.equals("Player made the play wager")) {
						callback.accept(createData(messageStr, id));
						if (game.dealerHandisQueenHighOrHigher()) {
							PokerInfo winData = new PokerInfo();
							if (game.compareHands().equals("Dealer won")) {
								callback.accept(createData("Dealer won", id));
								winData.setMessage("Dealer won");
							} else if (game.compareHands().equals("Client won")){
								callback.accept(createData("Player won", id));
								winData.setMessage("Player won");
							}
							winData.setAmount(game.getPrizeMoney());
							this.send(winData);

							PokerInfo pairPlusWinData = new PokerInfo();
							if (game.evalPairPlusPrize().equals("Client lost pair plus bet")) {
								callback.accept(createData("Player lost pair plus bet", id));
								pairPlusWinData.setMessage("Player lost pair plus bet");
							} else if (game.evalPairPlusPrize().equals("Client won pair plus bet")){
								callback.accept(createData("Player won pair plus bet", id));
								pairPlusWinData.setMessage("Player won pair plus bet");
							}
							pairPlusWinData.setAmount(game.getPrizeMoney());
							this.send(winData);

						} else {
							PokerInfo queenData = new PokerInfo();
							queenData.setMessage("Dealer doesn't have Queen High or higher");
							this.send(queenData);
							callback.accept(createData("Dealer doesn't have Queen High or higher", id));

							game.restoreCards();
							PokerInfo clientHandData = new PokerInfo();
							clientHandData.setHand(game.getClientHand());
							clientHandData.setMessage("Cards dealt to client");
							this.send(clientHandData);
							callback.accept(createData("Cards dealt to client", id));
						
							PokerInfo dealerHandData = new PokerInfo();
							dealerHandData.setHand(game.getDealerHand());
							dealerHandData.setMessage("Cards dealt to dealer");
							this.send(dealerHandData);
							callback.accept(createData("Cards dealt to dealer", id));

						}	
					} else {callback.accept(createData(messageStr, id));}
				    		
				} catch(Exception e) {
					callback.accept(createData("Something went wrong, closing down!", id));
					game.restoreCards();
				    	clients.remove(this);
					count--;
				    	break;
				}
			}
		}

		public void send(PokerInfo data) {
			try {out.writeObject(data);}
			catch (Exception e) {System.out.println("Encountered error while trying to send something: " + e);}
		}
	}
}
