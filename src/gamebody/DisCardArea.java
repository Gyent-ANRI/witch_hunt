package gamebody;

import java.util.LinkedList;
import java.util.Observable;

import cards.RumourCard;

/**
 * Class Singleton to store all discarded cards, observed by the discard window
 * @author Jiyang QI
 *
 */
public class DisCardArea extends Observable{
	
	private LinkedList<RumourCard> myCardlist;
	static private DisCardArea myobject = null;
	
	
	private DisCardArea() {myCardlist = new LinkedList<RumourCard>();}
	
	/**
	 * a method to get the instance of DisCardArea
	 * @return DisCardArea
	 */
	static public DisCardArea getObject() {
		if(myobject == null) {
			myobject = new DisCardArea();
		}
		return myobject;
	}
	
	/**
	 * add a discarded card
	 * @param newCard
	 */
	public void addCard(RumourCard newCard) {
		BroadCast.getObject().broad(newCard.getName() + " is discarded");
		myCardlist.add(newCard);
		this.setChanged();
		this.notifyObservers();
	}
	

	public LinkedList<RumourCard> getCard(){
		return myCardlist;
	}
	
	/**
	 * Remove a card from the discard pile
	 * @param card
	 */
	public void reduceCard(RumourCard card) {
		myCardlist.remove(card);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * clear the discard pile
	 */
	public void clear() {
		myCardlist = new LinkedList<RumourCard>();
	}
	
}