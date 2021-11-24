package gamebody;

import java.util.LinkedList;
import Cartes.RumourCard;

public class RevealedCardArea {
	
	private LinkedList<RumourCard> myCardlist;
	static private RevealedCardArea myobject = null;
	
	
	private RevealedCardArea() {myCardlist = new LinkedList<RumourCard>();}
	
	static public RevealedCardArea getObject() {
		if(myobject == null) {
			myobject = new RevealedCardArea();
		}
		return myobject;
	}
	
	public void addCard(RumourCard newCard) {
		myCardlist.add(newCard);
	}
	
	public LinkedList<RumourCard> getCard(){
		return myCardlist;
	}
	
	public void reduceCard(RumourCard card) {
		myCardlist.remove(card);
	}
	
}
