package players;

import behavior.Accuse;
import behavior.Behavior;
import behavior.RevealIdentity;
import behavior.RevealRumourCard_Hunt;
import behavior.RevealRumourCard_Witch;
import cards.RumourCard;
import gamebody.BroadCast;
import gamebody.GameController;
import gamebody.Identity;

/**
 * class corresponding to the game player
 * @author Jiyang QI
 *
 */
public class Charactor{
	
	private String name;
	private int score;
	private Identity identity;
	protected RumourCard[] myCard;
	protected Behavior myBehavior;
	private InteractionWindow myInteractionWindow;
	private boolean identityRevealed;

	public Charactor() {
		score = 0;
		
		if(this instanceof VirtualPlayer) {
			myInteractionWindow = new VirtualInteractionWindow((VirtualPlayer)this);
		}
		else{
			myInteractionWindow = new InteractionWindow(this);
		}
		
		myCard = new RumourCard[0];
		identityRevealed = false;
	}
	
	/**
	 * clear a player's cards,reset his identity and his state
	 */
	public void reset() {
		myCard = new RumourCard[0];
		setIdentity(null);
		identityRevealed = false;
	}
	
	public InteractionWindow getInteractionWindow() {return myInteractionWindow;}
	
	public void setName(String n) {name = n;}
	
	public String getName() {return name;}
	
	public void setIdentity(Identity id) {
		identity = id;
		myInteractionWindow.refreshIdentity();
		}
	
	/**
	 * method to get hand card
	 * @return hand card list 
	 */
	public RumourCard[] cardList(){return myCard;}
	
	/**
	 * add a card to one's hand
	 * @param card
	 */
	public void getCard(RumourCard card) {
		myInteractionWindow.outPut("You get The Card: " + card.getName());
		RumourCard[] oddCard = myCard;
		myCard = new RumourCard[oddCard.length + 1];
		for (int i = 0; i < oddCard.length; i++) {
			myCard[i] = oddCard[i];
		}
		myCard[oddCard.length] = card;
		myInteractionWindow.cardChanged(myCard);
	}
	
	/**
	 * remove a card from one's hand
	 * @param card
	 */
	public void reduceCard(RumourCard card) {
		RumourCard[] oddCard = myCard;
		myCard = new RumourCard[oddCard.length - 1];
		int j = 0;
		for(int i = 0; i < oddCard.length; i++) {
			if(!oddCard[i].equals(card)) {
				myCard[j] = oddCard[i];
				j++;
			}
		}
		this.myInteractionWindow.outPut("You loose Card: " + card.getName());
		myInteractionWindow.cardChanged(myCard);
	}
	
	/**
	 * method to set one's identity revealed
	 * compare with the behavior RevealIdentity: this method just set one's own status
	 * @return
	 */
	public Identity revealIdentity() {
		identityRevealed = true;
		switch(identity) {
		case witch:
			BroadCast.getObject().broad(name + "'s identity is witch!");
			break;
		case villager:
			BroadCast.getObject().broad(name + "'s identity is villager!");
		}
		BroadCast.getObject().scoreModified();
		return identity;
	}
	
	public void setBehavior(Behavior mybehave) {
		myBehavior = mybehave;
	}
	
	/**
	 * player allowed to take his turn
	 */
	public void takeTurn() {
		myInteractionWindow.outPut("Your turn ! What do you want to do? ");
		int answer = myInteractionWindow.makeChoice(new String[] {"Accuse", "Use Rumour Card"});
		switch(answer) {
		case 1:
			setBehavior(new Accuse(this));
			break;
		case 2:
			setBehavior(new RevealRumourCard_Hunt(this));
		}
		myBehavior.behave();
	}
	
	/**
	 * called when player is accused
	 * @param actor
	 */
	public void accused(Charactor actor) {
		myInteractionWindow.outPut("Your are accused ! What do you want to do? ");
		int answer = myInteractionWindow.makeChoice(new String[] {"Use Rumour Card", "Reveal Identity"});
		switch(answer) {
		case 1:
			setBehavior(new RevealRumourCard_Witch(actor, this));
			break;
		case 2:
			setBehavior(new RevealIdentity(actor, this));
		}
		myBehavior.behave();
		
	}
	
	/**
	 * method to modify player's own score
	 * @param s
	 */
	public void modifyScore(int s) {
		
		BroadCast.getObject().broad(name + " has " + s + " score");
		score += s;
		BroadCast.getObject().scoreModified();
		
		if(score >= 5) {
			GameController.getObject().gameOver(this);
		}
		if(score < 0) {
			score = 0;
		}
	}
	
	public Identity getIdentity() {
		return identity;
	}
	
	public boolean identityRevealed() {
		return identityRevealed;
	}
	
	public String getScore() {
		return String.valueOf(score);
	}
	
	
}
