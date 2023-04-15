import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;



public class PokerInfo implements Serializable {
	private String suite;
    private int value;
    private ArrayList<PokerInfo> playerCards;
    private ArrayList<PokerInfo> dealerCards;
    private String serverIP;
    private int anteBet;
    private int pairPlus;
    private int wagerAmount;
    private int portNum;
    
    
    public PokerInfo(){
        suite = null;
        value = 0;
        anteBet = 0;
        pairPlus = 0;
        wagerAmount = 0;
    }
   
    public int getValue() {
        return value;
    }

    public String getSuite() {
    	return suite;
    }
    public int getAnteBet() {
        return anteBet;
    }
   
    public int getPairPlus() {
        return pairPlus;
    }
    public int getWager() {
        return wagerAmount;
    }
    
    public void setPortNum(int portNum) { 
    	this.portNum = portNum; 
    }
    public void setServerIP(String serverIP) { 
    	this.serverIP = serverIP;
    }
    public int getPortNum() { 
    	return  portNum; 
    }
    public String getServerIP() {
    	return serverIP; 
    }

}
