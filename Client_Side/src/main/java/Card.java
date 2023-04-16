import java.io.Serializable;

public class Card implements Serializable {
	private String suite;
    private int value;

    public Card(String theSuite, int theValue){
        suite = theSuite;
        value = theValue;
    }

    public int getValue() {
        return value;
    }

    public String getSuite() {
    	return suite; 
    }
}

