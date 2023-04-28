import java.io.Serializable;

public class Card implements Serializable {
	private String shape;
	private String symbol;

	public Card (String shp, String sym) {
		shape = shp;
		symbol = sym;
	}

	public String getShape() {return shape;}
	public String getSymbol() {return symbol;}
}
