package gamebody;

import java.util.LinkedList;
import java.util.Observable;

import cards.RumourCard;

/**
 * class to store revealed cards
 * @author Jiyang QI
 *
 */
public class RevealedCardArea extends Observable{
	
	private LinkedList<RumourCard> myCardlist;
	static private RevealedCardArea myobject = null;
	
	
	private RevealedCardArea() {myCardlist = new LinkedList<RumourCard>();}
	
	static public RevealedCardArea getObject() {
		if(myobject == null) {
			myobject = new RevealedCardArea();
		}
		return myobject;
	}
	
	/**
	 * add card to the card pile
	 * @param newCard
	 */
	public void addCard(RumourCard newCard) {
		myCardlist.add(newCard);
		this.setChanged();
		this.notifyObservers();
	}
	
	public LinkedList<RumourCard> getCard(){
		return myCardlist;
	}
	
	/**
	 * remove a card from the card pile
	 * @param card
	 */
	public void reduceCard(RumourCard card) {
		myCardlist.remove(card);
		this.setChanged();
		this.notifyObservers(card);
	}
	
	/**
	 * clear the pile
	 */
	public void clear() {
		myCardlist = new LinkedList<RumourCard>();
	}
	
}
