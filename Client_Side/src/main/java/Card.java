import java.io.Serializable;

public class Card implements Serializable {
	private final String suite;
    private final int value;

    public Card(String theSuite, int theValue){
        suite = theSuite;
        value = theValue;
    }

    public int getValue() {
        return value;
    }

    public String getSuite() { return suite; }
}

