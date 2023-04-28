import java.util.ArrayList;
import java.io.Serializable;

public class PokerInfo implements Serializable {
	private ArrayList<Card> hand;
	private Double amount;
	private String message;

	public PokerInfo() {
		hand = new ArrayList();
		amount = 0.0;
		message = "";
	}
	
	public void setHand(ArrayList<Card> arr) {hand = arr;}
	public ArrayList<Card> getHand() {return hand;}
	public void setAmount(Double val) {amount = val;}
	public Double getAmount() {return amount;}
	public void setMessage(String str) {message = str;}
	public String getMessage() {return message;}
} 
