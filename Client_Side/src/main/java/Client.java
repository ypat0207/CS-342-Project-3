import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

public class Client extends Thread {
	Socket client;
	ObjectInputStream in;
    ObjectOutputStream out;
   
	
    int portNum = 5555;
    String serverIP = "192.0.0.0";
    private Consumer<Serializable> callback;
    private Boolean isPortOpen = false;
    	
    Client(PokerInfo transfer) {
    	portNum = transfer.getPortNum();
    	serverIP = transfer.getServerIP();
    	this.callback = callback;
    }
	
    public void run() {
		
		try {
		client= new Socket(serverIP,portNum);
	    out = new ObjectOutputStream(client.getOutputStream());
	    in = new ObjectInputStream(client.getInputStream());
	    client.setTcpNoDelay(true);
	    isPortOpen = true;
		}
		catch(Exception e) {
			isPortOpen = false;
		} 
		finally {
			callback.accept(isPortOpen);
		}
		
		while(true) {
			 
			try {
				PokerInfo pokerInfo = (PokerInfo)in.readObject();
				
				
			}
			catch(Exception e) {
				
			}
		}
		
	
    }
	public void send(PokerInfo data) {
			
			try {
				out.writeObject(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
