package gamebody;

import java.util.LinkedList;
import Cartes.RumourCard;

public class DisCardArea {
	
	private LinkedList<RumourCard> myCardlist;
	static private DisCardArea myobject = null;
	
	
	private DisCardArea() {myCardlist = new LinkedList<RumourCard>();}
	
	static public DisCardArea getObject() {
		if(myobject == null) {
			myobject = new DisCardArea();
		}
		return myobject;
	}
	
	public void addCard(RumourCard newCard) {
		BroadCast.getObject().broad(newCard.getName() + " is discarded");
		myCardlist.add(newCard);
	}
	
	public LinkedList<RumourCard> getCard(){
		return myCardlist;
	}
	
	public void reduceCard(RumourCard card) {
		myCardlist.remove(card);
	}
	
}