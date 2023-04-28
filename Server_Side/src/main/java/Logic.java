import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Logic {
	private ArrayList<Card> deck;
	private ArrayList<Card> dealerHand;
	private ArrayList<Card> clientHand;
	private Double anteBet;
	private Double pairPlusBet;
	private Double prizeMoney;

	public Logic() {
		deck = new ArrayList();
		dealerHand = new ArrayList();
		clientHand = new ArrayList();
		anteBet = 0.0;
		pairPlusBet = 0.0;
		prizeMoney = 0.0;
		initializeDeck();
		dealCards();
	}
	
	public void initializeDeck() {
		String sym = "";
		ArrayList<String> shapes = new ArrayList();
		shapes.add("club");
		shapes.add("spade");
		shapes.add("heart");
		shapes.add("diamond");

		for (String shape : shapes) { 
			for (int i = 1; i < 14; i++) {
				if (i == 1) {sym = "A";}
				else if (i == 11) {sym = "J";}
				else if (i == 12) {sym = "Q";}
				else if (i == 13) {sym = "K";}
				else {sym = Integer.toString(i);}
				Card card = new Card(shape, sym);
				deck.add(card);
			}
		}
	}
	
	public void restoreCards() {
		deck = new ArrayList();
		dealerHand = new ArrayList();
		clientHand = new ArrayList();
		initializeDeck();
		dealCards();

	}
	
	public void dealCards() {
		Random random = new Random();
		int val1 = random.nextInt(52);
		int val2 = random.nextInt(51);
		int val3 = random.nextInt(50);
		int val4 = random.nextInt(49);
		int val5 = random.nextInt(48);
		int val6 = random.nextInt(47);

		dealerHand.add(deck.remove(val1));
		dealerHand.add(deck.remove(val2));
		dealerHand.add(deck.remove(val3));
		clientHand.add(deck.remove(val4));
		clientHand.add(deck.remove(val5));
		clientHand.add(deck.remove(val6));
	}
	
	public boolean dealerHandisQueenHighOrHigher() {
		for (Card c: dealerHand) {
			if (c.getSymbol().equals("Q") || c.getSymbol().equals("K")) {
				return true;
			} 
		}
		return false;
	}
	
	public String evalHand(ArrayList<Card> hand) {
		String shape1 = hand.get(0).getShape();
		String shape2 = hand.get(1).getShape();
		String shape3 = hand.get(2).getShape();
		String symbol1 = hand.get(0).getSymbol();
		String symbol2 = hand.get(1).getSymbol();
		String symbol3 = hand.get(2).getSymbol();
		boolean straight = false;
		boolean flush = false;
		boolean three = false;
		boolean two = false;
		
		ArrayList<String> rankOrder = new ArrayList();
		rankOrder.add("A");
		rankOrder.add("2");
		rankOrder.add("3");
		rankOrder.add("4");
		rankOrder.add("5");
		rankOrder.add("6");
		rankOrder.add("7");
		rankOrder.add("8");
		rankOrder.add("9");
		rankOrder.add("10");
		rankOrder.add("J");
		rankOrder.add("Q");
		rankOrder.add("K");
		
		int index1 = rankOrder.indexOf(symbol1);
		int index2 = rankOrder.indexOf(symbol2);
		int index3 = rankOrder.indexOf(symbol3);
		
		ArrayList<Integer> indices = new ArrayList();
		indices.add(index1);
		indices.add(index2);
		indices.add(index3);

		indices.sort(Comparator.naturalOrder());

		if (shape1.equals(shape2) && shape2.equals(shape3)) {
			flush = true;
		}

		if (symbol1.equals(symbol2) && symbol2.equals(symbol3)) {
			three = true;
		} else if (symbol1.equals(symbol2) || symbol2.equals(symbol3) || symbol3.equals(symbol1)) {
			two = true;
		}

		if ((indices.get(1) - indices.get(0) == 1) && (indices.get(2) - indices.get(1) == 1)) {
			straight = true;
		}

		if (straight && flush) {return "Straight Flush";}
		else if (three) {return "Three of a Kind";}
		else if (straight) {return "Straight";}
		else if (flush) {return "Flush";}
		else if (two) {return "Pair";}
		else {return "Nothing";}

	}	
	
	public String compareHands() {
		String dealer = evalHand(dealerHand);
		String client = evalHand(clientHand);

		ArrayList<String> winningHands = new ArrayList();
		winningHands.add("Straight Flush");
		winningHands.add("Three of a Kind");
		winningHands.add("Straight");
		winningHands.add("Flush");
		winningHands.add("Pair");
		winningHands.add("Nothing");

		int dealerRank = winningHands.indexOf(dealer);
		int clientRank = winningHands.indexOf(client);

		if (dealerRank > clientRank) {
			prizeMoney -= 2 * anteBet; 
			return "Dealer won";
		} else {
			prizeMoney += 4 * anteBet;
			return "Client won";
		}
	}
	
	public String evalPairPlusPrize() {
		String client = evalHand(clientHand);
		if (client.equals("Straight Flush")) {
			prizeMoney += (40 * pairPlusBet) + pairPlusBet;
			return "Client won pair plus bet";
		} else if (client.equals("Three of a Kind")) {
			prizeMoney += (30 * pairPlusBet) + pairPlusBet;
			return "Client won pair plus bet";
		} else if (client.equals("Straight")) {
			prizeMoney += (6 * pairPlusBet) + pairPlusBet;
			return "Client won pair plus bet";
		} else if (client.equals("Flush")) {
			prizeMoney += (3 * pairPlusBet) + pairPlusBet;
			return "Client won pair plus bet";
		} else if (client.equals("Pair")) {
			prizeMoney += 2 * pairPlusBet;
			return "Client won pair plus bet";
		} else {
			prizeMoney -= pairPlusBet;
			return "Client lost pair plus bet";
		}
	}
	
	public void setAnteBet(Double val) {anteBet = val;}
	public Double getAnteBet() {return anteBet;}
	public void setPairPlusBet(Double val) {pairPlusBet = val;}
	public Double getPairPlusBet() {return pairPlusBet;}
	public ArrayList<Card> getClientHand() {return clientHand;}
	public ArrayList<Card> getDealerHand() {return dealerHand;}
	public Double getPrizeMoney() {return prizeMoney;}

} 
