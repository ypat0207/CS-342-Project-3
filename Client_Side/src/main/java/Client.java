import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;
import java.util.ArrayList;

public class Client extends Thread {
    Socket client;
    ObjectInputStream in;
    ObjectOutputStream out;
    int portNum;
    String serverIP;
    ArrayList<Card> clientHand;
    ArrayList<Card> dealerHand;
    Double prizeMoney = 0.0;
    private Consumer<Serializable> callback;

    Client(int port, String ip, Consumer<Serializable> call) {
    	portNum = port;
    	serverIP = ip;
	callback = call;
    }
	
    public void run() {
		
	try {
		client= new Socket(serverIP, portNum);
	    	out = new ObjectOutputStream(client.getOutputStream());
	    	in = new ObjectInputStream(client.getInputStream());
	    	client.setTcpNoDelay(true);
		callback.accept("Connected to server!");
	} catch(Exception e) {} 
		
	while(true) {
		try {
			PokerInfo data = (PokerInfo) in.readObject();
			if (data.getMessage().equals("Cards dealt to client")) {
				this.clientHand = data.getHand();
			} else if (data.getMessage().equals("Cards dealt to dealer")) {
				this.dealerHand = data.getHand();
			} else if (data.getMessage().equals("Player won") || 
				   data.getMessage().equals("Dealer won") || 
				   data.getMessage().equals("Player lost pair plus bet") ||
				   data.getMessage().equals("Dealer won pair plus bet")) {
				this.prizeMoney = data.getAmount();
			}
			callback.accept(data.getMessage());
		} catch(Exception e) {}
	}	
	
    }
	public void send(PokerInfo data) {
		try {out.writeObject(data);}
		catch (IOException e) {e.printStackTrace();}
	}
}
