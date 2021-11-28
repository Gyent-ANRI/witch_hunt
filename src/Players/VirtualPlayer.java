package Players;

import Behavior.Accuse;
import Behavior.RevealIdentity;
import Behavior.RevealRumourCard_Hunt;
import Behavior.RevealRumourCard_Witch;
import Cartes.TheInquisition;
import gamebody.BroadCast;

public class VirtualPlayer extends Charactor{
	static private int numVirtualPlayer = 0;
	
	public VirtualPlayer() {
		super();
		numVirtualPlayer += 1;
		super.setName("Virtual_Player_" + numVirtualPlayer);
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
		BroadCast.getObject().broad(super.getName() + " is accused");
		if( (myCard.length == 0) || (myCard.length == 1) && myCard[0] instanceof TheInquisition) {
			myBehavior = new RevealIdentity(reasonOne,this);
		}
		else {
			myBehavior = new RevealRumourCard_Witch(reasonOne,this);
		}
		myBehavior.behave();
	}
}
