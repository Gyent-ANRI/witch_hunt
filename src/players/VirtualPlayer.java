package players;

import behavior.Accuse;
import behavior.RevealIdentity;
import behavior.RevealRumourCard_Hunt;
import behavior.RevealRumourCard_Witch;
import cards.TheInquisition;

/**
 * class that describe the virtual player
 * @author User
 *
 */
public class VirtualPlayer extends Charactor{
	static private int numVirtualPlayer = 0;
	
	public VirtualPlayer() {
		super();
		numVirtualPlayer += 1;
		super.setName("V_Player_" + numVirtualPlayer);
	}
	
	public void takeTurn() {
		//70% for accuse and 30% for use rumour card
		double randomNum = Math.random();
		if(randomNum >=0.3) {
			myBehavior = new Accuse(this);
		}
		else {
			myBehavior = new RevealRumourCard_Hunt(this);
		}
		myBehavior.behave();;
	}
	
	public void accused(Charactor reasonOne) {
		if( (myCard.length == 0) || (myCard.length == 1) && myCard[0] instanceof TheInquisition) {
			myBehavior = new RevealIdentity(reasonOne,this);
		}
		else {
			myBehavior = new RevealRumourCard_Witch(reasonOne,this);
		}
		myBehavior.behave();
	}
}
